package com.estebanlopez.financiautoprueba.viewmodel

import androidx.lifecycle.ViewModel
import com.estebanlopez.financiautoprueba.model.model.CreateUserModel
import com.estebanlopez.financiautoprueba.model.model.UsersModel
import com.estebanlopez.financiautoprueba.model.repository.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.estebanlopez.financiautoprueba.model.model.Result
import com.estebanlopez.financiautoprueba.model.model.UserDetailModel
import retrofit2.Response

class DetailViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    private val mutableStateFlow: MutableStateFlow<Result<UserDetailModel>> =
        MutableStateFlow(Result.Loading())
    val stateFlow: StateFlow<Result<UserDetailModel>> = mutableStateFlow.asStateFlow()

    suspend fun getDetail(userId: String) {
        val response = usersRepository.getDetail(userId)
        if (response.isSuccessful && response.body() != null) {
            mutableStateFlow.value = Result.Success(response.body()!!)
            return
        }
        mutableStateFlow.value = Result.Error(response.code(), response.message())
    }

    suspend fun create(createUserModel: CreateUserModel): Result<Response<Unit>> {
        val response = usersRepository.create(createUserModel)
        if (response.isSuccessful) {
            return Result.Success(response)
        }
        return Result.Error(code = response.code(), message = response.errorBody()?.string())
    }

    suspend fun update(userId: String,createUserModel: CreateUserModel): Result<Response<Unit>> {
        val response = usersRepository.update(userId,createUserModel)
        if (response.isSuccessful) {
            return Result.Success(response)
        }
        return Result.Error(code = response.code(), message = response.errorBody()?.string())
    }
}