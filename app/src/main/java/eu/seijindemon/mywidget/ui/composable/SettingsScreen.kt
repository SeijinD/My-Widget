package eu.seijindemon.mywidget.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.seijindemon.mywidget.ui.theme.MyWidgetTheme
import eu.seijindemon.mywidget.ui.viewmodel.LanguageViewModel
import eu.seijindemon.mywidget.ui.viewmodel.SaveDataViewModel
import kotlinx.coroutines.launch

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
    val scope = rememberCoroutineScope()

    val phoneData = saveDataViewModel.phone.observeAsState().value ?: ""
    val telegramData = saveDataViewModel.telegram.observeAsState().value ?: ""

    var phone by remember { mutableStateOf(phoneData) }
    var telegram by remember { mutableStateOf(telegramData) }

    Column(
        modifier = Modifier
            .padding(all = 4.dp)
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CustomOutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            leadingIconImageVector = Icons.Filled.Phone,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.DarkGray
            )
        )
        CustomOutlinedTextField(
            value = telegram,
            onValueChange = { telegram = it },
            leadingIconImageVector = Icons.Filled.Message,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.DarkGray
            )
        )
        Divider(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                scope.launch {
                    with(saveDataViewModel) {
                        savePhone(phone = phone)
                        saveTelegram(telegram = telegram)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    leadingIconImageVector: ImageVector,
    leadingIconDescription: String = "",
    isPasswordField: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibilityChange: (Boolean) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    showError: Boolean = false,
    errorMessage: String = "",
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            label = { Text(text = label) },
            leadingIcon = {
                Icon(
                    imageVector = leadingIconImageVector,
                    contentDescription = leadingIconDescription,
                    tint = if (showError) MaterialTheme.colors.error else Color.Black
                )
            },
            isError = showError,
            trailingIcon = {
                if (showError && !isPasswordField) Icon(imageVector = Icons.Filled.Error, contentDescription = "Show error icon")
                if (isPasswordField) {
                    IconButton(onClick = { onVisibilityChange(!isPasswordVisible) }) {
                        Icon(
                            imageVector = if (isPasswordField) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                }
            },
            visualTransformation = when {
                isPasswordField && isPasswordVisible -> VisualTransformation.None
                isPasswordField -> PasswordVisualTransformation()
                else -> VisualTransformation.None
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            colors = colors
        )
        if (showError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .offset(y = (-8).dp)
                    .fillMaxWidth(0.9f)
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