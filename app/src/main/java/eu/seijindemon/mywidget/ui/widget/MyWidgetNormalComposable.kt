package eu.seijindemon.mywidget.ui.widget


import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyWidgetNormalComposable() {
    val telegram = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=seijind"))
    }
    val phone = remember {
        Intent(Intent.ACTION_CALL, Uri.parse("tel:1122334455"))
    }
    val activity = (LocalContext.current as? Activity)

    Column(
        modifier = Modifier.background(color = Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
                .background(color = Color.Gray)
        ) {
            Text(
                text = "I missed you!",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray
                    ),
                    onClick = {
                        activity?.startActivity(telegram)
                    }
                ) {
                    Text(
                        text = "Send me",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.White,
                        )
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray
                    ),
                    onClick = {
                        activity?.startActivity(phone)
                    }
                ) {
                    Text(
                        text = "Call me",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.White,
                        )
                    )
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun MyWidgetNormalComposablePreview() {
    MyWidgetNormalComposable()
}