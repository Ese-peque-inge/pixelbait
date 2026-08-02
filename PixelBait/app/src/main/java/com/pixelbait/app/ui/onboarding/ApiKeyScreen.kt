package com.pixelbait.app.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelbait.app.R
import com.pixelbait.app.core.theme.*

@Composable
fun ApiKeyScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onActivated: () -> Unit
) {
    val apiKey by viewModel.apiKeyInput.collectAsState()
    val showError by viewModel.showApiKeyError.collectAsState()
    var keyVisible by remember { mutableStateOf(false) }
    var showHelp by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PbBlack)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(24.dp))
        PixelBaitHeader()
        OnboardingStepper(current = OnboardingStep.API_KEY)

        Text(stringResource(R.string.apikey_title), color = PbTextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(
            stringResource(R.string.apikey_subtitle),
            color = PbTextSecondary,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(PbSurface, RoundedCornerShape(18.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Key, contentDescription = null, tint = PbVioletLight, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text(stringResource(R.string.apikey_label), color = PbTextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = apiKey,
                onValueChange = viewModel::onApiKeyChanged,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.apikey_placeholder), color = PbTextMuted) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (keyVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { keyVisible = !keyVisible }) {
                        Icon(
                            if (keyVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = null,
                            tint = PbTextMuted
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PbViolet,
                    unfocusedBorderColor = PbBorder,
                    focusedTextColor = PbTextPrimary,
                    unfocusedTextColor = PbTextPrimary
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                stringResource(R.string.apikey_help_link),
                color = PbVioletLight,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { showHelp = true }
            )
        }

        Spacer(Modifier.height(14.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(PbNavy.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            Icon(Icons.Filled.Info, contentDescription = null, tint = PbVioletLight, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(10.dp))
            Text(stringResource(R.string.apikey_info_banner), color = PbTextSecondary, fontSize = 12.sp, lineHeight = 17.sp)
        }

        if (showError) {
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(6.dp).background(PbError, CircleShape))
                Spacer(Modifier.width(6.dp))
                Text(stringResource(R.string.apikey_error_empty), color = PbError, fontSize = 12.sp)
            }
        }

        Spacer(Modifier.weight(1f))

        PixelBaitPrimaryButton(
            text = stringResource(R.string.btn_activate_protection),
            enabled = apiKey.isNotBlank(),
            icon = { Icon(Icons.Filled.Shield, contentDescription = null, tint = androidx.compose.ui.graphics.Color.White, modifier = Modifier.size(18.dp)) }
        ) {
            viewModel.activateProtection(onActivated)
        }
        Spacer(Modifier.height(24.dp))
    }

    if (showHelp) {
        ApiKeyHelpDialog(onDismiss = { showHelp = false })
    }
}

@Composable
private fun ApiKeyHelpDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = PbSurface,
        title = { Text(stringResource(R.string.help_title), color = PbTextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold) },
        text = {
            Column {
                Text(stringResource(R.string.help_step1), color = PbTextSecondary, fontSize = 13.sp, modifier = Modifier.padding(bottom = 8.dp))
                Text(stringResource(R.string.help_step2), color = PbTextSecondary, fontSize = 13.sp, modifier = Modifier.padding(bottom = 8.dp))
                Text(stringResource(R.string.help_step3), color = PbTextSecondary, fontSize = 13.sp, modifier = Modifier.padding(bottom = 8.dp))
                Text(stringResource(R.string.help_step4), color = PbTextSecondary, fontSize = 13.sp)
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.btn_next), color = PbVioletLight)
            }
        }
    )
}

