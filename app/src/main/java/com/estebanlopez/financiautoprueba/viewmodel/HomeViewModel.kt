package com.estebanlopez.financiautoprueba.viewmodel

import androidx.lifecycle.ViewModel
import com.estebanlopez.financiautoprueba.model.model.UsersModel
import com.estebanlopez.financiautoprueba.model.repository.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.estebanlopez.financiautoprueba.model.model.Result
import retrofit2.Response

class HomeViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    private val mutableStateFlow: MutableStateFlow<Result<UsersModel>> = MutableStateFlow(Result.Loading())
    val stateFlow: StateFlow<Result<UsersModel>> = mutableStateFlow.asStateFlow()

    suspend fun getAll(){
        val response = usersRepository.getAll();
        if(response.isSuccessful && response.body()!=null){
            mutableStateFlow.value = Result.Success(response.body()!!)
            return
        }
        mutableStateFlow.value = Result.Error(response.code(),response.message())
    }

    suspend fun delete(id: String): Result<Response<Unit>> {
        val response = usersRepository.delete(id);
        if(response.isSuccessful){
            return Result.Success(response)
        }
        return Result.Error(response.code(),response.errorBody()?.string())
    }
}