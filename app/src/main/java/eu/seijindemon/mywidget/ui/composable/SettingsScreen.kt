package eu.seijindemon.mywidget.ui.composable

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.seijindemon.mywidget.ui.theme.MyWidgetTheme
import eu.seijindemon.mywidget.ui.viewmodel.LanguageViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: LanguageViewModel
) {
    SettingsContent(navController = navController, viewModel = viewModel)
}

@Composable
fun SettingsContent(
    navController: NavController,
    viewModel: LanguageViewModel
) {

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun SettingsScreenPreview() {
    MyWidgetTheme {
        val navController = rememberNavController()
        val viewModel: LanguageViewModel = viewModel()
        SettingsContent(
            navController = navController,
            viewModel = viewModel
        )
    }
}