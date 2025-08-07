package vcmsa.projects.jetpackapibasics

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    // retrieve a list of loans from the API
    @GET("/oanloans")
    suspend fun getLoans(): List<LoanResponse>

}

object RetrofitClient {
    private const val BASE_URL = "https://opsc.azurewebsites.net/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)

    }
}