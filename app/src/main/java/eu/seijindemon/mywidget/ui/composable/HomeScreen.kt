package eu.seijindemon.mywidget.ui.composable


import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import eu.seijindemon.mywidget.ui.theme.MyWidgetTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen() {

    val context = LocalContext.current

    val doNotShowRationale = rememberSaveable { mutableStateOf(false) }

    val callPhonePermission = rememberPermissionState(permission = android.Manifest.permission.CALL_PHONE)
    PermissionRequired(
        permissionState = callPhonePermission,
        permissionNotGrantedContent = {
            if (doNotShowRationale.value) {
                NotShowRationale()
            } else {
                Rationale(
                    onDoNotShowRationale = { doNotShowRationale.value = true },
                    onRequestPermission = { callPhonePermission.launchPermissionRequest() }
                )
            }
        },
        permissionNotAvailableContent = {
            PermissionDenied(
                navigateToSettingsScreen = {
                    context.startActivity(
                        Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", context.packageName, null)
                        )
                    )
                }
            )
        }
    ) {
        HomeContent()
    }
}

@Composable
fun HomeContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Call Phone permission Granted!",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NotShowRationale() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Feature not available!",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Rationale(
    onDoNotShowRationale: () -> Unit,
    onRequestPermission: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "The call phone is important for this app. Please grant the permission.",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onRequestPermission,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            Text(text = "Request permission")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onDoNotShowRationale,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            Text(text = "Don't show rationale again")
        }
    }
}

@Composable
private fun PermissionDenied(
    navigateToSettingsScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Call Phone permission denied. See this FAQ with information about why we " +
                    "need this permission. Please, grant us access on the Settings screen.",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = navigateToSettingsScreen,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            Text(text = "Open Settings")
        }
    }
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