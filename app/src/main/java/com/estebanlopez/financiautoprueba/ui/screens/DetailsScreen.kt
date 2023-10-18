package com.estebanlopez.financiautoprueba.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.estebanlopez.financiautoprueba.FinanciautoApp
import com.estebanlopez.financiautoprueba.R
import com.estebanlopez.financiautoprueba.model.model.CreateUserModel
import com.estebanlopez.financiautoprueba.model.model.Result
import com.estebanlopez.financiautoprueba.model.model.UserDetailModel
import com.estebanlopez.financiautoprueba.ui.navigation.BaseScreens
import com.estebanlopez.financiautoprueba.ui.theme.Gray20
import com.estebanlopez.financiautoprueba.ui.theme.Gray30
import com.estebanlopez.financiautoprueba.ui.theme.Gray40
import com.estebanlopez.financiautoprueba.ui.theme.Gray50
import com.estebanlopez.financiautoprueba.ui.theme.Green30
import com.estebanlopez.financiautoprueba.ui.theme.Green50
import com.estebanlopez.financiautoprueba.ui.theme.Yellow20
import com.estebanlopez.financiautoprueba.ui.utils.Utils
import com.estebanlopez.financiautoprueba.viewmodel.DetailViewModel
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(
    navController: NavHostController,
    context: Context,
    userId: String,
    isEditing: Boolean,
    isCreating: Boolean,
    detailViewModel: DetailViewModel = viewModel(
        factory = viewModelFactory {
            addInitializer(DetailViewModel::class) {
                DetailViewModel((context.applicationContext as FinanciautoApp).appContainer.usersRepository)
            }
        }
    )
) {

    var titleState by remember {
        mutableStateOf("")
    }
    var idState by remember {
        mutableStateOf("")
    }
    var firstNameState by remember {
        mutableStateOf("")
    }
    var lastNameState by remember {
        mutableStateOf("")
    }
    var genreState by remember {
        mutableStateOf("")
    }
    var emailState by remember {
        mutableStateOf("")
    }
    var birthdayState by remember {
        mutableStateOf("")
    }
    var phoneState by remember {
        mutableStateOf("")
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UserInfoTextField(
        label: String,
        icon: ImageVector,
        data: String,
        onValueChange: (String) -> Unit,
        isError: Boolean = false,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        supportingText: @Composable (() -> Unit)? = null
    ) {

        OutlinedTextField(
            value = data,
            onValueChange = {
                if (isCreating || isEditing) {
                    onValueChange(it)
                }
            },
            keyboardOptions = keyboardOptions,
            isError = isError,
            supportingText = supportingText,
            singleLine = true,
            label = {
                Text(label)
            },
            leadingIcon = {
                Icon(icon, "Icon")
            },
            trailingIcon = {
                Icon(Icons.Filled.Check, "Check", tint = Green30)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
        )
    }

    @Composable
    fun UserDetails() {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.padding(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                UserInfoTextField(
                    label = "ID",
                    icon = Icons.Filled.Build,
                    data = idState,
                    onValueChange = {
                        idState = it
                    }
                )
                UserInfoTextField(
                    label = "Titulo",
                    icon = Icons.Filled.Build,
                    data = titleState,
                    isError = titleState != "mr"
                            && titleState != "ms"
                            && titleState != "mrs"
                            && titleState != "miss"
                            && titleState != "dr",
                    onValueChange = {
                        titleState = it
                    },
                    supportingText = {
                        if (isCreating)
                            Text("mr-ms-mrs-miss-dr")
                    }
                )
                UserInfoTextField(
                    label = "Nombres",
                    icon = Icons.Filled.Build,
                    data = firstNameState,
                    onValueChange = {
                        firstNameState = it
                    }
                )
                UserInfoTextField(
                    label = "Apellidos",
                    icon = Icons.Filled.Build,
                    data = lastNameState,
                    onValueChange = {
                        lastNameState = it
                    }
                )
                UserInfoTextField(
                    label = "Genero",
                    icon = Icons.Filled.Build,
                    data = genreState,
                    onValueChange = {
                        genreState = it
                    },
                    isError = genreState != "female" && genreState != "male",
                    supportingText = {
                        if (isCreating)
                            Text("female or male")
                    }
                )
                UserInfoTextField(
                    label = "Correo electronico",
                    icon = Icons.Filled.Build,
                    data = emailState,
                    onValueChange = {
                        emailState = it
                    },
                    isError = if (isCreating) !Utils.isValidEmail(emailState) else false
                )
                UserInfoTextField(
                    label = "Fecha de nacimiento",
                    icon = Icons.Filled.Build,
                    data = birthdayState,
                    onValueChange = {
                        birthdayState = it
                    },
                    supportingText = {
                        if (isCreating)
                            Text("DD/MM/YYYY")
                    },
                    isError = if (isCreating) !Utils.isValidDate(birthdayState) else false
                )
                UserInfoTextField(
                    label = "Telefono",
                    icon = Icons.Filled.Build,
                    data = phoneState,
                    onValueChange = {
                        phoneState = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
    }

    @Composable
    fun EditButtons() {
        val coroutineScope = rememberCoroutineScope()
        Column(modifier = Modifier.padding(bottom = 76.dp)) {
            ElevatedButton(
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                onClick = {
                    coroutineScope.launch {
                        val response = detailViewModel.update(
                            idState,
                            CreateUserModel(
                                title = titleState,
                                email = emailState,
                                dateOfBirth = birthdayState,
                                gender = genreState,
                                firstName = firstNameState,
                                lastName = lastNameState,
                                phone = phoneState,
                                picture = ""
                            )
                        )
                        Utils.handleResponse(response, context, onSuccess = {
                            Toast.makeText(context, "Usuario actualizado!", Toast.LENGTH_SHORT)
                                .show()
                            navController.popBackStack()
                        })
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green50
                ),
            ) {
                Row(modifier = Modifier.padding(vertical = 2.dp, horizontal = 40.dp)) {
                    Icon(Icons.Filled.Check, "", tint = Color.White)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Guardar")
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            ElevatedButton(
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                onClick = {
                    navController.popBackStack()
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Gray20
                )
            ) {
                Row(modifier = Modifier.padding(vertical = 2.dp, horizontal = 40.dp)) {
                    Icon(Icons.Filled.Close, "", tint = Color.Gray)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Cancelar", color = Color.Gray)
                }
            }
        }
    }

    @Composable
    fun CreateButtons() {
        val coroutineScope = rememberCoroutineScope()
        Column(modifier = Modifier.padding(bottom = 76.dp)) {
            ElevatedButton(
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                onClick = {
                    coroutineScope.launch {
                        val response = detailViewModel.create(
                            CreateUserModel(
                                title = titleState,
                                email = emailState,
                                dateOfBirth = birthdayState,
                                gender = genreState,
                                firstName = firstNameState,
                                lastName = lastNameState,
                                phone = phoneState,
                                picture = ""
                            )
                        )
                        Utils.handleResponse(response, context, onSuccess = {
                            Toast.makeText(context, "Usuario creado!", Toast.LENGTH_SHORT)
                                .show()
                            navController.popBackStack()
                        })
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green50
                ),
            ) {
                Row(modifier = Modifier.padding(vertical = 2.dp, horizontal = 40.dp)) {
                    Icon(Icons.Filled.Check, "", tint = Color.White)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Crear")
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            ElevatedButton(
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                onClick = {
                    navController.popBackStack()
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Gray20
                )
            ) {
                Row(modifier = Modifier.padding(vertical = 2.dp, horizontal = 40.dp)) {
                    Icon(Icons.Filled.Close, "", tint = Color.Gray)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Cancelar", color = Color.Gray)
                }
            }
        }
    }

    @Composable
    fun SuccessContent(
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Informacion del usuario",
                    color = Gray50,
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                        .width(250.dp)
                )
                FloatingActionButton(
                    onClick = {
                        if (!isEditing && !isCreating) {
                            navController.navigate("${BaseScreens.DetailScreen.name}/${idState}/true/false")
                        }
                    },
                    containerColor = if (isEditing) Gray20 else Green50,
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier.padding(24.dp),
                ) {
                    Icon(Icons.Filled.Edit, "Edit", tint = if (isEditing) Gray30 else Color.White)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Gray40),

                ) {
                Image(
                    painter = painterResource(id = R.drawable.account),
                    "Account",
                    modifier = Modifier.fillMaxSize()
                )
            }
            UserDetails()
            if (isEditing) {
                EditButtons()
            }
            if (isCreating) {
                CreateButtons()
            }
        }
    }


    val state = detailViewModel.stateFlow.collectAsState()

    //Llamado al servicio de obtener los datos del usuario
    LaunchedEffect(null) {
        if (!isCreating) {
            detailViewModel.getDetail(userId)
            detailViewModel.stateFlow.collect {
                if (it is Result.Success) {
                    val userData = it.data
                    idState = userData.id
                    titleState = userData.title
                    firstNameState = userData.firstName
                    lastNameState = userData.lastName
                    genreState = userData.gender
                    emailState = userData.email
                    birthdayState = userData.dateOfBirth
                    phoneState = userData.phone
                }
            }
        }
    }

    if (isCreating) {
        SuccessContent()
        return
    }

    Surface(color = Yellow20, modifier = Modifier.fillMaxSize()) {
        when (val result = state.value) {
            is Result.Success -> {
                SuccessContent()
            }

            is Result.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is Result.Error -> {
                Log.e("DetailsScreen", "isException: No | message: ${result.message ?: ""}")
                Box(modifier = Modifier.fillMaxSize()) {
                    Text("Ocurrio un error, intentalo de nuevo", color = Color.Red)
                }
            }

            else -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text("Ocurrio un error, intentalo de nuevo", color = Color.Red)
                }
            }
        }
    }
}






