import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.estebanlopez.financiautoprueba.ui.navigation.BaseScreens
import com.estebanlopez.financiautoprueba.ui.screens.DetailsScreen
import com.estebanlopez.financiautoprueba.ui.screens.HomeScreen


@Composable
fun BaseNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = BaseScreens.HomeScreen.name) {
        composable(BaseScreens.HomeScreen.name) {
            BaseScreen(navController) {
                HomeScreen(navController, context)
            }
        }
        composable(
            "${BaseScreens.DetailScreen.name}/{userId}/{isEditing}/{isCreating}",
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("isEditing") { type = NavType.BoolType },
                navArgument("isCreating") { type = NavType.BoolType })
        ) { backStackEntry ->
            BaseScreen(navController) {
                DetailsScreen(
                    navController,
                    context,
                    backStackEntry.arguments?.getString("userId") ?: "",
                    backStackEntry.arguments?.getBoolean("isEditing") ?: false,
                    backStackEntry.arguments?.getBoolean("isCreating") ?: false,
                )
            }
        }

    }
}