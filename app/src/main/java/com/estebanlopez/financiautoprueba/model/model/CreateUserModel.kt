package com.estebanlopez.financiautoprueba.model.model

data class CreateUserModel(
    val dateOfBirth: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val phone: String,
    val picture: String,
    val title: String
)