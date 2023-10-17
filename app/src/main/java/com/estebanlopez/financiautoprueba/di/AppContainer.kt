package com.estebanlopez.financiautoprueba.di

import UsersServices
import com.estebanlopez.financiautoprueba.model.datasource.UsersRemoteDataSource
import com.estebanlopez.financiautoprueba.model.repository.UsersRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer {

    private val httpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request =
            chain.request().newBuilder().addHeader("app-id", "63473330c1927d386ca6a3a5")
                .build();
        chain.proceed(request)
    }.build()


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyapi.io/data/v1/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UsersServices::class.java)

    private val remoteDataSource = UsersRemoteDataSource(retrofit)

    val usersRepository = UsersRepository(remoteDataSource)

}