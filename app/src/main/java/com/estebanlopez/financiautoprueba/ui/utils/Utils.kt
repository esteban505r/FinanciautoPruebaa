package com.estebanlopez.financiautoprueba.ui.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.estebanlopez.financiautoprueba.model.model.Result

object Utils{
    fun <T : Any> handleResponse(
        response: Result<T>,
        context: Context,
        onSuccess: () -> Unit,
        onError: (() -> Unit)? = null
    ) {
        when (response) {
            is Result.Success -> {
                onSuccess()
            }
            is Result.Loading -> {}
            is Result.Error -> {
                if(onError!=null){
                    onError()
                    return
                }
                Log.e("DetailsScreen", "isException: No | message: ${response.message ?: ""}")
                Toast.makeText(context, "Error, intentalo de nuevo!", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                Toast.makeText(context, "Error, intentalo de nuevo!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        return email.matches(emailRegex)
    }

    fun isValidDate(date: String): Boolean {
        val dateRegex = "(0[1-9]|1[0-9]|2[0-9]|3[0-1]|[1-9])/(0[1-9]|1[0-2]|[1-9])/([0-9]{4})".toRegex()
        return date.matches(dateRegex)
    }
}