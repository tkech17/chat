package ge.edu.freeuni.chatserver.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ge.edu.freeuni.chatserver.database.converter.DateConverter
import ge.edu.freeuni.chatserver.database.daos.UserDAO
import ge.edu.freeuni.chatserver.database.entities.ChatEntity
import ge.edu.freeuni.chatserver.database.entities.MessageEntity
import ge.edu.freeuni.chatserver.database.entities.UserEntity

@Database(entities = [ChatEntity::class, UserEntity::class, MessageEntity::class], version = 1, exportSchema = false)
@TypeConverters(value = [DateConverter::class])
abstract class ChatServerDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDAO

    companion object {

        @Volatile
        private var instance: ChatServerDatabase? = null

        fun getInstance(context: Context): ChatServerDatabase {
            if (instance == null) {
                synchronized(ChatServerDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(context, ChatServerDatabase::class.java, "NoteDatabase")
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return instance!!
        }

    }

}
