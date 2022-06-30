package com.fitgoapps

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.pages.ShareViewModel
import com.fitgoapps.ui.pages.ViewModelFactory
import com.fitgoapps.ui.pages.account.AccountViewBody
import com.fitgoapps.ui.pages.booking.calendar.CalendarViewBody
import com.fitgoapps.ui.pages.booking.detail.BookingDetailViewBody
import com.fitgoapps.ui.pages.booking.jam.JamViewBody
import com.fitgoapps.ui.pages.booking.payment.PaymentViewBody
import com.fitgoapps.ui.pages.favorites.FavortiesViewBody
import com.fitgoapps.ui.pages.index.IndexViewBody
import com.fitgoapps.ui.pages.lapangan.detail.LapanganDetailViewBody
import com.fitgoapps.ui.pages.lapangan.search.SearchViewBody
import com.fitgoapps.ui.pages.login.LoginViewBody
import com.fitgoapps.ui.pages.notification.NotificationViewBody
import com.fitgoapps.ui.pages.register.RegisterViewBody
import com.fitgoapps.ui.pages.sport.SportViewBody
import com.fitgoapps.ui.theme.SuperFitgoTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SuperFitgoTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }

                val allScreens = FitgoScreen.values().toList()
                val navController = rememberNavController()
                val backstackEntry = navController.currentBackStackEntryAsState()
                val currentScreen = FitgoScreen.fromRoute(backstackEntry.value?.destination?.route)

                Scaffold { innerPadding ->
                    Column() {
                        FitgoNavHost(navController = navController, modifier = Modifier
                            .padding(innerPadding)
                            .weight(1f))
                    }
                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FitgoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    shareViewModel: ShareViewModel = viewModel(
        factory = ViewModelFactory(
            LocalContext.current.applicationContext as Application
        )
    )
){
    NavHost(
        navController = navController,
        startDestination = FitgoScreen.IndexView.name,
        modifier = modifier
    ){

        composable(route = FitgoScreen.IndexView.name) {
            IndexViewBody(navController = navController, shareViewModel = shareViewModel)
        }
        composable(route = FitgoScreen.LapanganDetailView.name) {
            LapanganDetailViewBody(navController = navController)
        }
        composable(route = FitgoScreen.LoginView.name) {
            LoginViewBody(navController = navController, shareViewModel = shareViewModel)
        }
        composable(route = FitgoScreen.RegisterView.name) {
            RegisterViewBody(navController = navController)
        }
        composable(route = FitgoScreen.BookingDetailView.name) {
            BookingDetailViewBody(navController = navController)
        }
        composable(route = FitgoScreen.JamDetailView.name) {
            JamViewBody(navController = navController)
        }
        composable(route = FitgoScreen.CalendarDetailView.name) {
            CalendarViewBody(navController = navController)
        }
        composable(route = FitgoScreen.SearchView.name) {
            SearchViewBody(navController = navController)
        }
        composable(route = FitgoScreen.NotificationView.name) {
            NotificationViewBody(navController = navController)
        }
        composable(route = FitgoScreen.AccountView.name) {
            AccountViewBody(navController = navController, shareViewModel = shareViewModel)
        }
        composable(route = FitgoScreen.FavoritesView.name) {
            FavortiesViewBody(navController = navController)
        }
        composable(route = FitgoScreen.SportView.name) {
            SportViewBody(navController = navController)
        }
        composable(route = FitgoScreen.PaymentView.name) {
            PaymentViewBody(navController = navController)
        }

    }

}