
data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)
data class ApiResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>
)

data class SingleApiResponse(
    val data: User
)

data class CreatUser(
    val name: String,
    val job: String
    )

data class CreateUserResponse(
    val name: String,
    val job: String,
    val id: String,
    val createdAt: String
    )