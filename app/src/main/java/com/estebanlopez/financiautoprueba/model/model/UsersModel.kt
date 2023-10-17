package com.estebanlopez.financiautoprueba.model.model

data class UsersModel(
    val `data`: List<Data>,
    val limit: Int,
    val page: Int,
    val total: Int
)