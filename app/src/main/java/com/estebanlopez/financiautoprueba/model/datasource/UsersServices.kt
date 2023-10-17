import com.estebanlopez.financiautoprueba.model.model.UsersModel
import retrofit2.Response
import retrofit2.http.GET

interface UsersServices{

    @GET("users")
    suspend fun getAll(): Response<UsersModel>

    @GET("users/{id}")
    suspend fun getById(): Response<UsersModel>
}