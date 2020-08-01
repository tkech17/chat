package ge.edu.freeuni.chat.ui.login

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import ge.edu.freeuni.chat.App
import ge.edu.freeuni.chat.R


class LoginFragment : Fragment(), Login.View {

    private lateinit var profilePicture: ImageView
    private lateinit var nickname: TextInputEditText
    private lateinit var whatIDo: TextInputEditText
    private lateinit var startButton: MaterialButton


    private lateinit var presenter: Login.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.login_fragment, container, false)

        presenter = PresenterImpl(this)

        profilePicture = view.findViewById(R.id.login_fragment_profile_picture)
        profilePicture.setOnClickListener { presenter.selectImage(context) }

        nickname = view.findViewById(R.id.login_fragment_nickname)
        whatIDo = view.findViewById(R.id.login_fragment_what_i_do)

        startButton = view.findViewById(R.id.login_fragment_start_button)

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                0 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage = data.extras!!["data"] as Bitmap?
                    profilePicture.setImageBitmap(selectedImage)
                }
                1 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage: Uri? = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    if (selectedImage != null) {
                        val contentResolver = App.self.contentResolver
                        val cursor: Cursor? = contentResolver.query(selectedImage, filePathColumn, null, null, null)

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

    override fun selectImage(context: Context?) {
        val takePhoto = "Take Photo"
        val chooseFromGallery = "Choose from Gallery"
        val cancel = "Cancel"

        val options = arrayOf<CharSequence>(takePhoto, chooseFromGallery, cancel)

        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            .setTitle("Choose your profile picture")
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

}