package com.example.competitionapp.ui.mainFunsUI

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.competitionapp.ui.extFuns.MainBackScreen
import com.example.competitionapp.R
import com.example.competitionapp.ui.authenticationFunUI.Login

@SuppressLint("CustomSplashScreen")
class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            SplashScreenView()
            val sharedPreferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE)
            val isLoggedIn =
                sharedPreferences.getBoolean("isLoggedIn", false) // مقدار پیش‌فرض false


            val handler = Handler(Looper.getMainLooper())


            handler.postDelayed({
                if (isLoggedIn) {
                    val intent = Intent(this@SplashScreen, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this@SplashScreen, Login::class.java)
                    startActivity(intent)
                    finish()
                }

            }, 3000)
        }
    }

    @Composable
    fun SplashScreenView() {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            MainBackScreen()
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(300.dp)

                )
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {

        SplashScreenView()

    }
}


