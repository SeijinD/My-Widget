package eu.seijindemon.mywidget.ui.composable

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import eu.seijindemon.mywidget.ui.theme.MyWidgetTheme

@Composable
fun HomeScreen() {

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun HomeScreenPreview() {
    MyWidgetTheme {
        HomeScreen()
    }
}