package com.estebanlopez.financiautoprueba.model.repository;

import com.estebanlopez.financiautoprueba.model.datasource.UsersRemoteDataSource
import com.estebanlopez.financiautoprueba.model.model.UsersModel
import retrofit2.Response

class UsersRepository(
    private val remoteDataSource: UsersRemoteDataSource,
) {

    suspend fun getAll(): Response<UsersModel> {
        return remoteDataSource.getAll();
    }
}