package com.pixelbait.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pixelbait.app.core.theme.PbBlack
import com.pixelbait.app.core.theme.PixelBaitTheme
import com.pixelbait.app.data.repository.OnboardingRepository
import com.pixelbait.app.ui.navigation.PixelBaitNavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var onboardingRepository: OnboardingRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PixelBaitRoot(onboardingRepository)
        }
    }
}

@Composable
private fun PixelBaitRoot(onboardingRepository: OnboardingRepository) {
    PixelBaitTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = PbBlack) {
            PixelBaitNavGraph(onboardingRepository = onboardingRepository)
        }
    }
}
