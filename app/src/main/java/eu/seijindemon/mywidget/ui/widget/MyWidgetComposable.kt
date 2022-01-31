package eu.seijindemon.mywidget.ui.widget


import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

@Composable
fun MyWidgetComposable() {
    val openUrl = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse("org.telegram.messenger"))
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically,
        modifier = GlanceModifier
            .width(300.dp)
            .height(150.dp)
            .background(color = Color.Cyan)
            .clickable(
                actionStartActivity(openUrl)
            )
    ) {
        Text(
            text = "I missed you!",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
        Text(
            text = "Send me :)",
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        )
        Spacer(modifier = GlanceModifier.size(8.dp))
    }
}