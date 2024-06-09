import com.example.model.User

object UserRepository {
    val users: MutableList<User> = mutableListOf(
        User(1, "Kim Hernandez", "kh@me.com"),
        User(2, "Dominique Gregory", "dg@me.com"),
        User(3, "Arden Coffey", "ac@me.com")
    )
}