package com.pixelbait.app.ui.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pixelbait.app.data.repository.OnboardingRepository
import com.pixelbait.app.ui.history.HistoryScreen
import com.pixelbait.app.ui.history.HistoryViewModel
import com.pixelbait.app.ui.onboarding.ApiKeyScreen
import com.pixelbait.app.ui.onboarding.CameraPermissionScreen
import com.pixelbait.app.ui.onboarding.TermsScreen
import com.pixelbait.app.ui.scanner.ScannerScreen
import com.pixelbait.app.ui.settings.SettingsScreen

/**
 * Grafo de navegación de Pixel Bait.
 * Decide el destino inicial (onboarding vs. escáner) en base al estado persistido
 * en OnboardingRepository (Requerimiento 3.1).
 */
@Composable
fun PixelBaitNavGraph(
    onboardingRepository: OnboardingRepository,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val onboardingComplete by onboardingRepository.onboardingComplete.collectAsState(initial = null)

    // Mientras no sepamos el estado persistido, no dibujamos el grafo (evita parpadeo).
    val startDestination = when (onboardingComplete) {
        true -> Routes.SCANNER
        false -> Routes.TERMS
        null -> return
    }

    NavHost(navController = navController, startDestination = startDestination) {

        composable(Routes.TERMS) {
            TermsScreen(onNext = { navController.navigate(Routes.CAMERA_PERMISSION) })
        }
        composable(Routes.CAMERA_PERMISSION) {
            CameraPermissionScreen(onNext = { navController.navigate(Routes.API_KEY) })
        }
        composable(Routes.API_KEY) {
            ApiKeyScreen(onActivated = {
                navController.navigate(Routes.SCANNER) {
                    popUpTo(Routes.TERMS) { inclusive = true }
                }
            })
        }

        composable(Routes.SCANNER) {
            ScannerScreen(
                onNavigateHistory = { navController.navigate(Routes.HISTORY) },
                onNavigateSettings = { navController.navigate(Routes.SETTINGS) }
            )
        }
        composable(Routes.HISTORY) {
            HistoryScreen(onNavigateSettings = { navController.navigate(Routes.SETTINGS) })
        }
        composable(Routes.SETTINGS) {
            val historyViewModel: HistoryViewModel = hiltViewModel()
            SettingsScreen(
                onNavigateHistory = { navController.navigate(Routes.HISTORY) },
                onOpenTerms = { navController.navigate(Routes.TERMS) },
                onUpdateApiKey = { navController.navigate(Routes.API_KEY) },
                onClearHistory = { historyViewModel.clearHistory() },
                onOpenOfficialSite = {
                    // Placeholder de Política de Privacidad / sitio institucional (Requerimiento 3.6).
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pixelbait.app/privacy"))
                    context.startActivity(intent)
                }
            )
        }
    }
}
