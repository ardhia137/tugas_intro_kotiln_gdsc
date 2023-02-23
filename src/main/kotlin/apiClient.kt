import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class apiClient {
    companion object {
        fun getClient(): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
    }


}