package eu.seijindemon.mywidget.ui.widget

import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidget
import eu.seijindemon.mywidget.ui.theme.MyWidgetTheme

class MyWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        MyWidgetComposable()
    }
}