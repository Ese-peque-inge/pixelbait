package com.pixelbait.app.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelbait.app.R
import com.pixelbait.app.core.theme.*

@Composable
fun TermsScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PbBlack)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(24.dp))
        PixelBaitHeader()
        OnboardingStepper(current = OnboardingStep.TERMS)

        Text(
            stringResource(R.string.terms_title),
            color = PbTextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            stringResource(R.string.terms_version),
            color = PbTextMuted,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(PbSurface, RoundedCornerShape(20.dp))
                .padding(18.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TermsSection(stringResource(R.string.terms_section1_title), stringResource(R.string.terms_section1_body))
            TermsSection(stringResource(R.string.terms_section2_title), stringResource(R.string.terms_section2_body))
            TermsSection(stringResource(R.string.terms_section3_title), stringResource(R.string.terms_section3_body))
            TermsSection(stringResource(R.string.terms_section4_title), stringResource(R.string.terms_section4_body), isLast = true)
        }

        Spacer(Modifier.height(16.dp))
        PixelBaitPrimaryButton(text = stringResource(R.string.btn_next)) {
            viewModel.acceptTerms(onNext)
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun TermsSection(title: String, body: String, isLast: Boolean = false) {
    Text(title, color = PbVioletLight, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    Text(
        body,
        color = PbTextSecondary,
        fontSize = 13.sp,
        lineHeight = 19.sp,
        modifier = Modifier.padding(top = 4.dp, bottom = if (isLast) 0.dp else 16.dp)
    )
}
