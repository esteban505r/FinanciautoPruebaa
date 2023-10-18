package com.estebanlopez.financiautoprueba.model.datasource

import UsersServices
import com.estebanlopez.financiautoprueba.model.model.CreateUserModel
import com.estebanlopez.financiautoprueba.model.model.UserDetailModel
import com.estebanlopez.financiautoprueba.model.model.UsersModel
import retrofit2.Response

class UsersRemoteDataSource(private val services:UsersServices){

    suspend fun getAll(): Response<UsersModel> {
        return services.getAll()
    }

    suspend fun getDetail(userId:String): Response<UserDetailModel> {
        return  services.getById(userId)
    }

    suspend fun create(createUserModel: CreateUserModel): Response<Unit> {
        return services.create(createUserModel)
    }

    suspend fun update(userId: String, createUserModel: CreateUserModel): Response<Unit> {
        return services.update(userId,createUserModel)
    }

    suspend fun delete(userId: String): Response<Unit> {
        return services.delete(userId)
    }
}