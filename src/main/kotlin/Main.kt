import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
    @GET("api/users?page=1")
    fun Users(): Call<ApiResponse>
    @GET("api/users/{id}")
    fun SingleUser(@Path("id") id: Int): Call<SingleApiResponse>
    @POST("api/users")
    fun createUser(@Body user: CreatUser): Call<CreateUserResponse>
}
val apiService = apiClient.getClient().create(ApiService::class.java)

fun getUsers(){
    apiService.Users().enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                val apiResponse = response.body()
                val users = apiResponse?.data
                println("get all data")
                users?.forEach {
                    println("Nama : " + it.first_name + " " + it.last_name + ", Email : " + it.email)
                }
            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            println("Error: " + t.message)
        }
    })
}

fun GetSingleUser(){
    val call = apiService.SingleUser(2)

    call.enqueue(object : Callback<SingleApiResponse> {
        override fun onResponse(call: Call<SingleApiResponse>, response: Response<SingleApiResponse>) {
            val apiResponse = response.body()
            val user = apiResponse?.data
            println()
            println("get single data")
            println("Nama : " + user?.first_name + " " + user?.last_name + ", Email : " + user?.email)
        }

        override fun onFailure(call: Call<SingleApiResponse>, t: Throwable) {
            // tangani jika terjadi error
        }
    })
}

fun postData(){
    val newUser = CreatUser("John Doe", "Developer")
    val response = apiService.createUser(newUser).execute()
    val apiResponse = response.body()
    println()
    println("Post Data")
    println("Nama : " + apiResponse?.name + ", job: " + apiResponse?.job + ", CreatedAt : " + apiResponse?.createdAt)
}

fun main() {
    GetSingleUser()
    getUsers()
    postData()
}