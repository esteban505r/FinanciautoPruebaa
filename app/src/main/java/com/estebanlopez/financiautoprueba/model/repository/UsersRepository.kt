package com.estebanlopez.financiautoprueba.model.repository;

import com.estebanlopez.financiautoprueba.model.datasource.UsersRemoteDataSource
import com.estebanlopez.financiautoprueba.model.model.CreateUserModel
import com.estebanlopez.financiautoprueba.model.model.UserDetailModel
import com.estebanlopez.financiautoprueba.model.model.UsersModel
import retrofit2.Response

class UsersRepository(
    private val remoteDataSource: UsersRemoteDataSource,
) {

    suspend fun getAll(): Response<UsersModel> {
        return remoteDataSource.getAll();
    }

    suspend fun getDetail(userId: String): Response<UserDetailModel> {
        return remoteDataSource.getDetail(userId)
    }

    suspend fun create(createUserModel: CreateUserModel): Response<Unit> {
        return remoteDataSource.create(createUserModel)
    }

    suspend fun update(userId:String,createUserModel: CreateUserModel): Response<Unit> {
        return remoteDataSource.update(userId,createUserModel)
    }

    suspend fun delete(userId: String): Response<Unit> {
        return remoteDataSource.delete(userId)
    }
}