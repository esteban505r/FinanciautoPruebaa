import com.estebanlopez.financiautoprueba.model.model.CreateUserModel
import com.estebanlopez.financiautoprueba.model.model.UserDetailModel
import com.estebanlopez.financiautoprueba.model.model.UsersModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UsersServices{

    @GET("user")
    suspend fun getAll(): Response<UsersModel>

    @GET("user/{id}")
    suspend fun getById(@Path("id") userId:String): Response<UserDetailModel>

    @POST("user/create")
    suspend fun create(@Body user: CreateUserModel): Response<Unit>

    @PUT("user/{id}")
    suspend fun update(@Path("id") userId:String,@Body user: CreateUserModel): Response<Unit>

    @DELETE("user/{id}")
    suspend fun delete(@Path("id") userId:String): Response<Unit>
}