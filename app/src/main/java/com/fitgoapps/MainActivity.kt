package com.fitgoapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.pages.index.IndexViewBody
import com.fitgoapps.ui.pages.lapangan.detail.LapanganDetailViewBody
import com.fitgoapps.ui.pages.login.LoginViewBody
import com.fitgoapps.ui.pages.register.RegisterViewBody
import com.fitgoapps.ui.theme.SuperFitgoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                        FitgoNavHost(navController = navController, modifier = Modifier.padding(innerPadding).weight(1f))
                    }
                }

            }
        }
    }
}

@Composable
fun FitgoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = FitgoScreen.LoginView.name,
        modifier = modifier
    ){

        composable(route = FitgoScreen.IndexView.name) {
            IndexViewBody(navController = navController)
        }
        composable(route = FitgoScreen.LapanganDetailView.name) {
            LapanganDetailViewBody(navController = navController)
        }
        composable(route = FitgoScreen.LoginView.name) {
            LoginViewBody(navController = navController)
        }
        composable(route = FitgoScreen.RegisterView.name) {
            RegisterViewBody(navController = navController)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val allScreens = FitgoScreen.values().toList()
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = FitgoScreen.fromRoute(backstackEntry.value?.destination?.route)

    Scaffold { innerPadding ->
        Column() {
            FitgoNavHost(navController = navController, modifier = Modifier.padding(innerPadding).weight(1f))
        }
    }

}