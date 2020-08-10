package ge.edu.freeuni.chatserver.model.helper

import ge.edu.freeuni.chatserver.database.entities.UserEntity
import ge.edu.freeuni.chatserver.model.User

object UserHelper {

    fun toDTO(from: UserEntity): User {
        return User(
            id = from.id,
            username = from.username,
            whatIDo = from.whatIDo,
            picture = from.picture
        )
    }

}
