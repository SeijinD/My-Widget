package eu.seijindemon.mywidget.ui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.mywidget.ui.composable.home.HomeScreen
import eu.seijindemon.mywidget.ui.theme.MyWidgetTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWidgetTheme {
                HomeScreen()
            }
        }
    }
}