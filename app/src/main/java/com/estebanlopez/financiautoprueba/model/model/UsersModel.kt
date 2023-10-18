package com.estebanlopez.financiautoprueba.model.model

data class UsersModel(
    val `data`: List<UserData>,
    val limit: Int,
    val page: Int,
    val total: Int
)