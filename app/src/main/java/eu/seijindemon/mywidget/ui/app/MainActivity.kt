package eu.seijindemon.mywidget.ui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.mywidget.ui.composable.HomeScreen
import eu.seijindemon.mywidget.ui.composable.SettingsScreen
import eu.seijindemon.mywidget.ui.composable.general.SetLanguage
import eu.seijindemon.mywidget.ui.theme.MyWidgetTheme
import eu.seijindemon.mywidget.ui.viewmodel.AppViewModel
import eu.seijindemon.mywidget.ui.viewmodel.LanguageViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWidgetTheme {
                NavigationComponent()
            }
        }
    }
}

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    val viewModel: AppViewModel = viewModel()
    val languageViewModel: LanguageViewModel = viewModel()

    LoadLanguage(languageViewModel = languageViewModel)

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("settings") { SettingsScreen(navController, languageViewModel) }
    }
}

@Composable
fun LoadLanguage(
    languageViewModel: LanguageViewModel
) {
    val currentLanguage = languageViewModel.language.observeAsState().value
    SetLanguage(language = currentLanguage!!)
}