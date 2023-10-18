import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.estebanlopez.financiautoprueba.R
import com.estebanlopez.financiautoprueba.ui.navigation.BaseScreens
import com.estebanlopez.financiautoprueba.ui.screens.BottomIcons
import com.estebanlopez.financiautoprueba.ui.theme.Blue30
import com.estebanlopez.financiautoprueba.ui.theme.Blue50
import com.estebanlopez.financiautoprueba.ui.theme.Green50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(navController: NavController, content: @Composable () -> Unit) {
    val currentBackStack = navController.currentBackStack.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            CustomBottomAppBar()
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            val isCreating = currentBackStack.value.firstOrNull { it.arguments?.getBoolean("isCreating") == true } != null
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    if (!isCreating) {
                        navController.navigate("${BaseScreens.DetailScreen.name}/nouser/false/true")
                    }
                },
                containerColor = if(isCreating) Color.White else Green50
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add user",
                    tint = if(isCreating) Color.Gray else Color.White
                )
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 25.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo")
            }
            TopAppBar(title = {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 12.dp)
                ) {
                    items(currentBackStack.value.size) { index ->
                        if (index != 0) {
                            if (index != 1) {
                                Icon(
                                    Icons.Default.KeyboardArrowRight,
                                    contentDescription = "Arrow",
                                    tint = Color.White,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                NavigationUtils.getTitleFromBackStackEntry(
                                    currentBackStack.value[index]
                                ),
                                color = if (index == currentBackStack.value.size - 1) Green50 else Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }, colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Blue50))

            content()
        }
    }
}

@Composable
fun CustomBottomAppBar() {
    BottomNavigation(modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        content = {
            BottomNavigationItem(
                selected = true,
                onClick = { /*TODO*/ },
                unselectedContentColor = Blue50,
                icon = { Icon(Icons.Default.Home, "home", tint = Blue50) })
            BottomNavigationItem(
                selected = false,
                onClick = { /*TODO*/ },
                unselectedContentColor = Blue30,
                icon = { Icon(Icons.Default.Search, "home", tint = Blue30) })
            BottomNavigationItem(
                selected = false,
                onClick = { /*TODO*/ },
                unselectedContentColor = Blue30,
                icon = { Icon(Icons.Default.Notifications, "home", tint = Blue30) })
            BottomNavigationItem(
                selected = false,
                onClick = { /*TODO*/ },
                unselectedContentColor = Blue30,
                icon = { Icon(Icons.Default.Settings, "home", tint = Blue30) })
        })
}
