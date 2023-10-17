import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.estebanlopez.financiautoprueba.ui.navigation.BaseScreens
import com.estebanlopez.financiautoprueba.ui.screens.HomeScreen


@Composable
fun BaseNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BaseScreens.HomeScreen.name){
        composable(BaseScreens.HomeScreen.name){
            BaseScreen(navController){
                HomeScreen(navController = navController)
            }
        }

    }
}