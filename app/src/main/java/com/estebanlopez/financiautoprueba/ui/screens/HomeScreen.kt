package com.estebanlopez.financiautoprueba.ui.screens


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.estebanlopez.financiautoprueba.FinanciautoApp
import com.estebanlopez.financiautoprueba.R
import com.estebanlopez.financiautoprueba.model.model.CreateUserModel
import com.estebanlopez.financiautoprueba.ui.theme.Blue20
import com.estebanlopez.financiautoprueba.ui.theme.Blue50
import com.estebanlopez.financiautoprueba.ui.theme.Gray20
import com.estebanlopez.financiautoprueba.ui.theme.Gray50
import com.estebanlopez.financiautoprueba.viewmodel.HomeViewModel
import com.estebanlopez.financiautoprueba.model.model.Result
import com.estebanlopez.financiautoprueba.model.model.UserData
import com.estebanlopez.financiautoprueba.ui.navigation.BaseScreens
import com.estebanlopez.financiautoprueba.ui.theme.Green50
import com.estebanlopez.financiautoprueba.ui.utils.Utils
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    context: Context,
    homeViewModel: HomeViewModel = viewModel(factory = viewModelFactory {
        addInitializer(HomeViewModel::class) {
            HomeViewModel((context.applicationContext as FinanciautoApp).appContainer.usersRepository)
        }
    })
) {

    val users = homeViewModel.stateFlow.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val pullRefreshState = rememberPullRefreshState(
        users.value is Result.Loading,
        {
            coroutineScope.launch {
                homeViewModel.getAll()
            }
        })

    LaunchedEffect(null) {
        homeViewModel.getAll()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 20.dp),
            ) {
                item {
                    Text(
                        "Consulta y registro de usuarios",
                        color = Gray50,
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                when (val state = users.value) {
                    is Result.Success -> {
                        items(count = state.data.data.size) {
                            val user = state.data.data[it]
                            UsersListItem(
                                context, homeViewModel, user,
                                onClick = {
                                    navController.navigate("${BaseScreens.DetailScreen.name}/${user.id}/false/false")
                                },
                            )
                        }
                    }

                    is Result.Error -> {
                        Log.e("HomeScreen", "isException: No | message: ${state.message ?: ""}")
                        item {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text("Ocurrio un error, intentalo de nuevo", color = Color.Red)
                            }
                        }
                    }

                    is Result.Loading -> {}

                    else -> {
                        item {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text("Ocurrio un error, intentalo de nuevo", color = Color.Red)
                            }
                        }
                    }
                }
            }
            PullRefreshIndicator(users.value is Result.Loading, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }

    }
}

@Composable
fun DismissBackground() {
    val color = Color(0xFFFFd0d0)
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp)
            .background(color, shape = RoundedCornerShape(15.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            Icons.Default.Delete,
            contentDescription = "delete",
            tint = Color.Red
        )
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UsersListItem(
    context: Context,
    homeViewModel: HomeViewModel,
    userData: UserData,
    onClick: () -> Unit,
) {
    var show by remember { mutableStateOf(true) }
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart) {
                showDeleteDialog = true
                return@rememberDismissState true
            }
            return@rememberDismissState false
        },
    )

    if (showDeleteDialog) {
        Dialog(onDismissRequest = {
            showDeleteDialog = false
        }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(440.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .background(color = Color(0xFFF9FBFB))
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Novedad",
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 30.sp,
                            color = Green50,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Eliminar",
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 43.sp,
                            color = Blue50,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "¿Está seguro que desea eliminar el registro?",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 24.sp,
                        color = Gray50,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    ElevatedButton(
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        onClick = {
                            showDeleteDialog = false
                            show = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Green50
                        ),
                    ) {
                        Row(modifier = Modifier.padding(vertical = 2.dp, horizontal = 40.dp)) {
                            Icon(Icons.Filled.Check, "", tint = Color.White)
                            Spacer(modifier = Modifier.width(5.dp))
                            Text("Aceptar")
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    ElevatedButton(
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        onClick = {
                            showDeleteDialog = false
                            coroutineScope.launch {
                                dismissState.reset()
                            }
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
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
        }
    }

    LaunchedEffect(show) {
        if (!show) {
            delay(800)
            val response = homeViewModel.delete(userData.id)
            Utils.handleResponse(response, context, onSuccess = {
                Toast.makeText(context, "Usuario eliminado!", Toast.LENGTH_SHORT).show()
                coroutineScope.launch {
                    homeViewModel.getAll()
                }
            }, onError = {
                show = true
                Toast.makeText(context, "Error, intentalo de nuevo!", Toast.LENGTH_SHORT).show()
            })
        }
    }



    AnimatedVisibility(
        show, exit = fadeOut(spring())
    ) {
        SwipeToDismiss(state = dismissState,
            directions = setOf(DismissDirection.EndToStart),
            modifier = Modifier,
            background = {
                DismissBackground()
            },
            dismissContent = {
                Box(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .background(
                                    color = Blue20,
                                    shape = RoundedCornerShape(
                                        topStart = 15.dp,
                                        topEnd = 15.dp
                                    )
                                )
                                .padding(vertical = 16.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.End,
                        ) {
                            Text(
                                "${userData.firstName} ${userData.lastName}",
                                fontSize = 22.sp,
                                color = Blue50,
                                modifier = Modifier.width(200.dp)
                            )
                            Text(
                                "ID: ${userData.id}", fontSize = 12.sp, modifier = Modifier
                                    .width(200.dp)
                                    .padding(vertical = 15.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .background(
                                    color = Gray20, shape = RoundedCornerShape(
                                        bottomStart = 15.dp,
                                        bottomEnd = 15.dp
                                    )
                                )
                                .clickable {
                                    onClick()
                                }
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .width(200.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Ver detalle", color = Blue50)
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        Icons.Default.KeyboardArrowRight,
                                        tint = Blue50,
                                        contentDescription = "Arrow"
                                    )
                                }
                            }
                        }
                    }
                    Card(
                        modifier = Modifier
                            .width(140.dp)
                            .height(160.dp)
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Image(painter = painterResource(id = R.drawable.account), "Account")
                    }
                }
            })
    }
}
