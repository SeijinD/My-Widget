package eu.seijindemon.mywidget.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.seijindemon.mywidget.ui.theme.MyWidgetTheme
import eu.seijindemon.mywidget.ui.viewmodel.LanguageViewModel
import eu.seijindemon.mywidget.ui.viewmodel.SaveDataViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    languageViewModel: LanguageViewModel,
    saveDataViewModel: SaveDataViewModel
) {
    SettingsContent(
        navController = navController,
        languageViewModel = languageViewModel,
        saveDataViewModel = saveDataViewModel
    )
}

@Composable
fun SettingsContent(
    navController: NavController,
    languageViewModel: LanguageViewModel,
    saveDataViewModel: SaveDataViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

    }
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
        val languageViewModel: LanguageViewModel = viewModel()
        val saveDataViewModel: SaveDataViewModel = viewModel()
        SettingsContent(
            navController = navController,
            languageViewModel = languageViewModel,
            saveDataViewModel = saveDataViewModel
        )
    }
}