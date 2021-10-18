package com.yberezhnyi.dinosaurcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yberezhnyi.dinosaurcompose.ui.BottomNavItem
import com.yberezhnyi.dinosaurcompose.ui.air.AirScreen
import com.yberezhnyi.dinosaurcompose.ui.aqua.AquaScreen
import com.yberezhnyi.dinosaurcompose.ui.home.HomeScreen
import com.yberezhnyi.dinosaurcompose.ui.land.LandScreen
import com.yberezhnyi.dinosaurcompose.ui.theme.DinosaurComposeTheme
import com.yberezhnyi.dinosaurcompose.ui.theme.GreenDark

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DinosaurComposeTheme {
                StatusBar(GreenDark)
                val navController = rememberNavController()
                Scaffold(
                    topBar = { Toolbar(GreenDark, "Hello") },
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = stringResource(id = R.string.home),
                                    route = "Home",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = stringResource(id = R.string.land),
                                    route = "Land",
                                    icon = Icons.Default.Call
                                ),
                                BottomNavItem(
                                    name = stringResource(id = R.string.water),
                                    route = "Aqua",
                                    icon = Icons.Default.Share
                                ),
                                BottomNavItem(
                                    name = stringResource(id = R.string.air),
                                    route = "Air",
                                    icon = Icons.Default.Clear
                                ),
                            ),
                            navController = navController,
                            backgroundColor = GreenDark,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeScreen()
        }
        composable("Land") {
            LandScreen()
        }
        composable("Aqua") {
            AquaScreen()
        }
        composable("Air") {
            AirScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = backgroundColor,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name
                        )
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp
                            )
                        } else {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }

                })
        }
    }

}

@Composable
fun StatusBar(color: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = color
    )
}

@Composable
fun Toolbar(color: Color, title: String) {
    val context = LocalContext.current
    TopAppBar(
        backgroundColor = color,
        contentColor = Color.White,
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = {
                Toast.makeText(context, "Share!", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Filled.Share, contentDescription = "Share")
            }
        }
    )
}