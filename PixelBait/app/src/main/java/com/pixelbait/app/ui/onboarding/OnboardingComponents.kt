package com.pixelbait.app.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pixelbait.app.R
import com.pixelbait.app.core.theme.*

enum class OnboardingStep { TERMS, CAMERA, API_KEY }

@Composable
fun PixelBaitHeader() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(PbViolet, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Filled.Security, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(10.dp))
        Text(
            stringResource(R.string.app_name),
            color = PbTextPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun OnboardingStepper(current: OnboardingStep) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StepBadge("1", stringResource(R.string.onboarding_step_terms),
            state = stepState(OnboardingStep.TERMS, current))
        StepDivider()
        StepBadge("2", stringResource(R.string.onboarding_step_camera),
            state = stepState(OnboardingStep.CAMERA, current))
        StepDivider()
        StepBadge("3", stringResource(R.string.onboarding_step_apikey),
            state = stepState(OnboardingStep.API_KEY, current))
    }
}

private enum class StepState { DONE, ACTIVE, PENDING }

private fun stepState(step: OnboardingStep, current: OnboardingStep): StepState = when {
    step.ordinal < current.ordinal -> StepState.DONE
    step == current -> StepState.ACTIVE
    else -> StepState.PENDING
}

@Composable
private fun RowScope.StepBadge(number: String, label: String, state: StepState) {
    val bg = when (state) {
        StepState.DONE -> PbSuccess.copy(alpha = 0.18f)
        StepState.ACTIVE -> PbViolet
        StepState.PENDING -> PbSurfaceElevated
    }
    val textColor = when (state) {
        StepState.DONE -> PbSuccess
        StepState.ACTIVE -> Color.White
        StepState.PENDING -> PbTextMuted
    }
    Row(
        modifier = Modifier
            .background(bg, RoundedCornerShape(50))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (state == StepState.DONE) {
            Icon(Icons.Filled.Check, contentDescription = null, tint = PbSuccess, modifier = Modifier.size(14.dp))
        } else {
            Text(number, color = textColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.width(4.dp))
        Text(label, color = textColor, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun RowScope.StepDivider() {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(1.dp)
            .background(PbBorder)
    )
}

@Composable
fun PixelBaitPrimaryButton(
    text: String,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth().height(54.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PbViolet,
            disabledContainerColor = PbSurfaceElevated,
            disabledContentColor = PbTextMuted
        )
    ) {
        icon?.invoke()
        if (icon != null) Spacer(Modifier.width(8.dp))
        Text(text, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}
