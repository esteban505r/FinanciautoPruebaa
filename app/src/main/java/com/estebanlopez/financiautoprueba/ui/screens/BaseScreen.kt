import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.estebanlopez.financiautoprueba.R
import com.estebanlopez.financiautoprueba.ui.screens.BottomIcons
import com.estebanlopez.financiautoprueba.ui.theme.Blue30
import com.estebanlopez.financiautoprueba.ui.theme.Blue50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(navController: NavController,content: @Composable () -> Unit){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor =  MaterialTheme.colorScheme.background,
        bottomBar = {
            CustomBottomAppBar()
        }
    ) {
        Column(modifier = Modifier.padding(it).padding(horizontal = 20.dp)) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp)){
                Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo")
            }
            TopAppBar(title = {
                Row(){
                    Text("Inicio", color = Color.White)
                    Icon(Icons.Default.ArrowForward,contentDescription = "Arrow", tint = Color.White)
                }
            },colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Blue50))

            content()
        }
    }
}

@Composable
fun CustomBottomAppBar() {
    val selected = remember { mutableStateOf(BottomIcons.HOME) }

    BottomAppBar(modifier = Modifier.fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                IconButton(onClick = { selected.value = BottomIcons.HOME }) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = null,
                        tint = if (selected.value == BottomIcons.HOME) Blue50 else Blue30
                    )
                }
                IconButton(onClick = { selected.value = BottomIcons.HOME }) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = null,
                        tint = if (selected.value == BottomIcons.HOME) Blue50 else Blue30
                    )
                }
            }
        })
}
