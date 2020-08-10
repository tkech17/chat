package ge.edu.freeuni.chat.ui.login

import android.annotation.SuppressLint
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import ge.edu.freeuni.chat.App
import ge.edu.freeuni.chat.R
import ge.edu.freeuni.chat.server.model.user.LoginRequest
import ge.edu.freeuni.chat.server.model.user.User
import java.io.ByteArrayOutputStream
import java.util.*


class LoginFragment : Fragment(), Login.View {

    private lateinit var progressBar: ProgressBar
    private lateinit var profilePicture: ImageView
    private lateinit var nickname: TextInputEditText
    private lateinit var whatIDo: TextInputEditText
    private lateinit var startButton: MaterialButton


    private lateinit var presenter: Login.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.login_fragment, container, false)

        presenter = PresenterImpl(this)

        progressBar = view.findViewById(R.id.login_fragment_indeterminate_progress_bar)

        profilePicture = view.findViewById(R.id.login_fragment_profile_picture)
        profilePicture.setOnClickListener { presenter.selectImage(context) }

        nickname = view.findViewById(R.id.login_fragment_nickname)
        whatIDo = view.findViewById(R.id.login_fragment_what_i_do)

        startButton = view.findViewById(R.id.login_fragment_start_button)

        startButton.setOnClickListener {

            val imageBase64 = getUserImageInBase64()

            val user = LoginRequest(
                username = nickname.text.toString(),
                whatIDo = whatIDo.text.toString(),
                imageBase64 = imageBase64
            )

            presenter.startChat(user)
        }

        return view
    }

    private fun getUserImageInBase64(): String? {
        if (profilePicture.drawable !is BitmapDrawable) {
            return null
        }

        val bitmap: Bitmap = (profilePicture.drawable as BitmapDrawable?)?.bitmap ?: return null
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageInByte: ByteArray = baos.toByteArray()
        return Base64.getEncoder().encodeToString(imageInByte)
    }

    @SuppressLint("Recycle")
    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                0 -> {
                    if (resultCode == RESULT_OK && data != null) {
                        val selectedImage: Bitmap? = data.extras!!["data"] as Bitmap?
                        profilePicture.setImageBitmap(selectedImage)
                    }
                }
                1 -> {
                    if (resultCode == RESULT_OK && data != null) {
                        val selectedImage: Uri? = data.data
                        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                        if (selectedImage != null) {
                            val cursor: Cursor? = App.self.contentResolver.query(selectedImage, filePathColumn, null, null, null)
                            cursor?.let {
                                it.moveToFirst()
                                val columnIndex: Int = it.getColumnIndex(filePathColumn[0])
                                val picturePath: String = it.getString(columnIndex)
                                profilePicture.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                                it.close()

                            }
                        }
                    }
                }
            }
        }
    }

    override fun selectImage(context: Context?) {

        val takePhoto = resources.getString(R.string.take_photo)
        val chooseFromGallery = resources.getString(R.string.choose_from_gallery)
        val cancel = resources.getString(R.string.cancel)

        val options = arrayOf<CharSequence>(takePhoto, chooseFromGallery, cancel)

        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            .setTitle(resources.getString(R.string.choose_your_profile_picture))
            .setItems(options) { dialog, item ->
                when {
                    options[item] == takePhoto -> {
                        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(takePicture, 0)
                    }
                    options[item] == chooseFromGallery -> {
                        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        startActivityForResult(pickPhoto, 1)
                    }
                    options[item] == cancel -> {
                        dialog.dismiss()
                    }
                }
            }

        alertDialog.show()
    }

    override fun drawFailResponseMode() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun drawWaitingForResponseMode() {
        progressBar.visibility = View.VISIBLE
    }

    override fun navigateToChatFragment(user: User) {
        findNavController().navigate(R.id.action_loginFragment_to_messengerFragment, bundleOf("user" to user))
    }

}