import androidx.navigation.NavBackStackEntry
import com.estebanlopez.financiautoprueba.ui.navigation.BaseScreens

object NavigationUtils {
    fun getTitleFromBackStackEntry(route: NavBackStackEntry): String {
        try {
            if (route.destination.route == BaseScreens.HomeScreen.name) {
                return "Inicio"
            }
            if ((route.destination.route ?: "").contains("${BaseScreens.DetailScreen.name}/")) {
                if (route.arguments?.getBoolean("isEditing") == true) {
                    return "Editar usuario"
                }
                if (route.arguments?.getBoolean("isCreating") == true) {
                    return "Crear usuario"
                }
                return "Detalle de registro"
            }
            return "_"
        } catch (e: IllegalArgumentException) {
            return "_"
        }
    }


}