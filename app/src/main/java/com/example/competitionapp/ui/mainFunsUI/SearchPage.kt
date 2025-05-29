package com.example.competitionapp.ui.mainFunsUI

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.competitionapp.ui.mainFunsUI.mainPages.SearchPage
import com.example.competitionapp.ui.mainFunsUI.mainPages.SearchTopAppBar

class SearchPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                modifier = Modifier
                    .systemBarsPadding()
                    .fillMaxSize()
                    ,
                topBar = {
                    SearchTopAppBar(this)
                }
            ) {

                Column(
                    modifier = Modifier.padding(it)
                ) {
                    SearchPage()
                }
            }

        }
    }
}

