package ge.edu.freeuni.chatserver.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ge.edu.freeuni.chatserver.database.entities.UserEntity

@Dao
interface UserDAO {

    @Insert(entity = UserEntity::class)
    suspend fun save(user: UserEntity): UserEntity

    @Update
    suspend fun update(user: UserEntity)

    @Query("select * from UserEntity where username == :usrName")
    suspend fun getUserByUsername(usrName: String): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE username LIKE '%' || :usrName  || '%'")
    suspend fun getUsersByUsername(usrName: String): List<UserEntity>

}
