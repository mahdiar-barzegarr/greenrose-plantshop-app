package com.example.competitionapp.ui.mainFunsUI

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.competitionapp.ui.dataModel.Product
import com.example.competitionapp.ui.extFuns.AddToBasket
import com.example.competitionapp.ui.extFuns.BottomNavigation
import com.example.competitionapp.ui.extFuns.DropDownPanel
import com.example.competitionapp.ui.extFuns.HeightSpacer
import com.example.competitionapp.ui.extFuns.SystemView
import com.example.competitionapp.ui.extFuns.TopAppBarView
import com.example.competitionapp.ui.extFuns.WidthSpacer
import com.example.competitionapp.ui.mainFunsUI.mainPages.BasketMainPageInit
import com.example.competitionapp.ui.mainFunsUI.mainPages.CategoryInit
import com.example.competitionapp.ui.mainFunsUI.mainPages.SubCategoryInit
import com.example.competitionapp.ui.mainFunsUI.mainPages.HomeMainPageInit
import com.example.competitionapp.ui.mainFunsUI.mainPages.MainProfilePageInit
import com.example.competitionapp.ui.mainFunsUI.mainPages.ProductBody
import com.example.competitionapp.ui.mainFunsUI.mainPages.TopOfProfileSubPages

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeScreenView()
            SystemView()

        }
    }

    @SuppressLint("FrequentlyChangedStateReadInComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeScreenView() {


        val navController = rememberNavController()
        var menu by remember { mutableStateOf(false) }





        Scaffold(
            containerColor =  Color.Unspecified.copy(alpha = 0f),
            topBar = { TopAppBarView(
                {
                    menu = !menu
                },
                menu
            )

            },
            bottomBar = { BottomNavigation(navController) },
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()



        ) {


            Box(
                modifier = Modifier
                    .padding(it)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ){
                        menu = false
                    }
                ,
            ) {


                NavHost(

                    navController = navController,
                    startDestination = "Home",

                    ) {

                    composable("Profile") { MainProfilePageInit(this@MainActivity) }
                    composable("Shop") { BasketMainPageInit() }
                    composable("Home") { HomeMainPageInit() }
                    composable("Category") { CategoryInit() }


                }
                DropDownPanel(isExpanded = menu)

            }



        }


    }


    @Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_7_PRO)
    @Composable
    fun GreetingPreview() {
        TopOfProfileSubPages(this)
    }
}


