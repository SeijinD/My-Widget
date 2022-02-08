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
import androidx.glance.unit.ColorProvider
import androidx.glance.appwidget.cornerRadius
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.seijindemon.mywidget.ui.viewmodel.SaveDataViewModel

@Composable
fun MyWidgetComposable() {
    val viewModel: SaveDataViewModel = viewModel()

    val phoneData = viewModel.phone
    val telegramData = viewModel.telegram

    val phone = remember {
        Intent(Intent.ACTION_CALL, Uri.parse("tel:${phoneData}"))
    }
    val telegram = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=${telegramData}"))
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically,
        modifier = GlanceModifier
            .width(200.dp)
            .height(100.dp)
            .background(color = Color.Gray)
    ) {
        Text(
            text = "I missed you!",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = ColorProvider(color = Color.White)
            )
        )
        Spacer(modifier = GlanceModifier.size(8.dp))
        Row {
            Button(
                text = "Send me",
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = ColorProvider(color = Color.White),
                ),
                modifier = GlanceModifier
                    .background(color = Color.DarkGray)
                    .cornerRadius(8.dp),
                onClick = actionStartActivity(telegram)
            )
            Spacer(modifier = GlanceModifier.size(8.dp))
            Button(
                text = "Call me",
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = ColorProvider(color = Color.White),
                ),
                modifier = GlanceModifier
                    .background(color = Color.DarkGray)
                    .cornerRadius(8.dp),
                onClick = actionStartActivity(phone)
            )
        }
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