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
import com.avalanche.android.tincturediary.model.Id
import com.avalanche.android.tincturediary.ui.screens.cookBookScreen.CookBookScreen
import com.avalanche.android.tincturediary.ui.screens.listRecipeScreen.ListRecipeScreen
import com.avalanche.android.tincturediary.ui.screens.readyRecipeScreen.ReadyRecipeScreen
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
                val navController = rememberNavController()
                val bottomItems = listOf(R.string.nav_list, R.string.nav_add, R.string.nav_cookbook)

                Surface(modifier = Modifier.fillMaxSize()) {
                    Scaffold(bottomBar = {
                        BottomNavigation {
                            bottomItems.forEach {screen ->
                                BottomNavigationItem(
                                    selected = false ,
                                    onClick = { navController.navigate(screen) },
                                    icon = {})
                            }
                        }
                    }) { NavHost(navController = navController,  startDestination = "cookbook") {
                        Log.d("ROUTE", "NavHost initializing")
                            composable("list") { ListRecipeScreen(context = context, navController)}
                            composable("cookbook") { CookBookScreen(navController) }
                            composable("add") { AddScreen(context = context)}
                            composable("edit") {
                                if (navController.previousBackStackEntry != null && navController.previousBackStackEntry?.arguments != null) {
                                    navController.previousBackStackEntry!!.arguments!!.getParcelable<Id>("TAKE_ID")?.let{
                                        EditScreen(context = context, id = it.value)
                                    }
                                }

                            }
                            composable("readyRecipe") {
                                Log.d("ROUTE", "NavHost ready")
                                if (navController.previousBackStackEntry != null && navController.previousBackStackEntry?.arguments != null) {
                                    navController.previousBackStackEntry!!.arguments!!.getParcelable<Id>("TAKE_ID")?.let{
                                        ReadyRecipeScreen(id = it.value)
                                        Log.d("ROUTE", "NavHost take a link")
                                    }
                                }
                            }
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