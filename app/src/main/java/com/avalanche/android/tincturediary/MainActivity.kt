package com.avalanche.android.tincturediary

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avalanche.android.tincturediary.ui.components.Id
import com.avalanche.android.tincturediary.ui.screens.cookBookScreen.CookBook
import com.avalanche.android.tincturediary.ui.screens.listRecipeScreen.ListRecipeScreen
import com.avalanche.android.tincturediary.ui.screens.recipeScreen.AddScreen
import com.avalanche.android.tincturediary.ui.screens.recipeScreen.EditScreen
import com.avalanche.android.tincturediary.ui.theme.TinctureDiaryTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var context = this
        setContent {
            TinctureDiaryTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val bottomItems = listOf("list", "add", "cookbook")

                Surface(modifier = Modifier.fillMaxSize()) {
                    Scaffold(bottomBar = {
                        BottomNavigation {
                            bottomItems.forEach {screen ->
                                BottomNavigationItem(
                                    selected = false ,
                                    onClick = { navController.navigate(screen) },
                                    label = { Text(screen) },
                                    icon = {})
                            }
                        }
                    }) { NavHost(navController = navController,  startDestination = "cookbook") {
                            composable("cookbook") { Text("cookbook")}
                            composable("list") { ListRecipeScreen(context = context, navController)}
                            composable("add") { AddScreen(context = context)}
                            composable("edit") {
                                navController.previousBackStackEntry?.arguments?.getParcelable<Id>("TAKE_ID")?.let{
                                    EditScreen(context = context, id = it.value)
                                    Log.d("LOAD", "NavHost take road: edit")
                                }
                            }
                            Log.d("LOAD", "NavHost loaded")
                        }
                    }

                }

            }
        }
    }
}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TinctureDiaryTheme {
        Greeting("Android")
    }
}