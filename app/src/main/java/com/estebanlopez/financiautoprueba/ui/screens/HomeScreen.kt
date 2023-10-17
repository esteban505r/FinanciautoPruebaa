package com.estebanlopez.financiautoprueba.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.estebanlopez.financiautoprueba.ui.theme.Blue20
import com.estebanlopez.financiautoprueba.ui.theme.Blue50
import com.estebanlopez.financiautoprueba.ui.theme.Gray20
import com.estebanlopez.financiautoprueba.ui.theme.Gray50

@Composable
fun HomeScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
       LazyColumn {
           item {
               Text("Consulta y registro de usuarios",color = Gray50, fontSize = 30.sp, modifier = Modifier.padding(vertical = 20.dp))
           }
           items(count = 5,){
               Box(modifier = Modifier.padding(vertical = 20.dp)){
                   Column(modifier = Modifier.fillMaxWidth().align(Alignment.CenterEnd)) {
                       Column(modifier = Modifier.background(color = Blue20)) {
                           Text("Sara sofia andersen toro", fontSize = 22.sp,color = Blue50)
                           Text("Id: kjdsakjdsajfsalkj")
                       }
                       Column(modifier = Modifier.background(color = Gray20)) {
                           Text("Ver detalle",color = Blue50)
                           IconButton(onClick = { /*TODO*/ }) {
                               Icon(Icons.Default.KeyboardArrowRight,tint = Blue50, contentDescription = "Arrow")
                           }
                       }
                   }
                   Card(modifier = Modifier.width(150.dp).height(150.dp)){

                   }
               }
           }
       }

    }
}