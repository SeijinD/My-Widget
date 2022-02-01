package eu.seijindemon.mywidget.ui.widget


import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

@Composable
fun MyWidgetComposable() {
    val openUrl = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=seijind"))
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically,
        modifier = GlanceModifier
            .width(300.dp)
            .height(150.dp)
            .background(color = Color.Cyan)
    ) {
        Text(
            text = "I missed you!",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
        Spacer(modifier = GlanceModifier.size(8.dp))
        Button(
            text = "Send me :)",
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            onClick = actionStartActivity(openUrl)
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun MyWidgetComposablePreview() {
    MyWidgetComposable()
}