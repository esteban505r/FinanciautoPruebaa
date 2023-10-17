package com.estebanlopez.financiautoprueba.model.datasource

import UsersServices
import com.estebanlopez.financiautoprueba.model.model.UsersModel
import retrofit2.Response

class UsersRemoteDataSource(private val services:UsersServices){

    suspend fun getAll(): Response<UsersModel> {
        return services.getAll()
    }
}