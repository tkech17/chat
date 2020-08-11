package ge.edu.freeuni.chatserver.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ge.edu.freeuni.chatserver.database.entities.UserEntity

@Dao
interface UserDAO {

    @Insert(entity = UserEntity::class)
    suspend fun save(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE id == :userId")
    suspend fun getUserById(userId: Long): UserEntity

    @Query("SELECT * FROM UserEntity WHERE username == :usrName")
    suspend fun getUserByUsername(usrName: String): UserEntity?

    @Query("SELECT * FROM UserEntity")
    suspend fun getAll(): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE username LIKE '%' || :usrName  || '%' AND username != :currentUser")
    suspend fun getUsersByUsername(usrName: String, currentUser: String): List<UserEntity>

}
