package com.example.kidsphotogallery

import android.graphics.Picture
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main_screen"){
        composable("main_screen"){ MainScreen(navController = navController)}
        composable("settings_screen"){ SettingsScreen()}
        composable("detail_screen/{pictureId}",
            arguments = listOf(navArgument("pictureId"){
                type = NavType.IntType
            })
        ){
            navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("pictureId")?.let { DetailScreen(pictureId = it, navController = navController) }
        }
    }
}

@Composable
fun MainScreen(navController: NavController){
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.fillMaxSize()) {

            MyGrid() {
                navController.navigate("detail_screen/$it")
            }
        }
    }
}

@Composable
fun DetailScreen(pictureId: Int, navController: NavController){
    Surface(color = Color.Black) {
        Image(
            painter = painterResource(id = pictureId),
            contentDescription = "Picture",
            modifier = Modifier
                .fillMaxSize()
                .clickable { navController.navigateUp() }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyGrid(onClick: (Int) -> Unit){
    val pictureItems = listOf(
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_foreground
    )
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
        modifier = Modifier.fillMaxSize()
    ) {
        items(pictureItems) {pictureId ->
            Image(
                painter = painterResource(id = pictureId) ,
                contentDescription = "Picture",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick(pictureId) }
            )
        }
    }
}



@Composable
fun SettingsScreen(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "Hello Settings"
        )
    }
}





