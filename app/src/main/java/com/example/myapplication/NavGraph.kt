package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.Screen.BREED_ROUTE_KEY

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BREEDS_SCREEN_ROUTE
    ) {
        composable(
            route = Screen.BREEDS_SCREEN_ROUTE
        ) {
            DogBreedsView(navController = navController)
        }
        composable(
            route = Screen.IMAGE_BY_BREED_ROUTE,
            arguments = listOf(navArgument(BREED_ROUTE_KEY) {
                type = NavType.StringType
            })
        ) {
            ImageByBreedView(
                navController = navController,
                breed = it.arguments?.getString(BREED_ROUTE_KEY).orEmpty()
            )
        }
        composable(
            route = Screen.RANDOM_DOG_IMAGE_ROUTE
        ){
            RandomImageView(navController = navController)
        }
    }


}