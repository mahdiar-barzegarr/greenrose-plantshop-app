package com.example.competitionapp.ui.authenticationFunUI

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.example.competitionapp.R
import com.example.competitionapp.ui.extFuns.GreenButton
import com.example.competitionapp.ui.extFuns.MainBackScreen
import com.example.competitionapp.ui.mainFunsUI.MainActivity

class SetName : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SetNameScreen()
        }
    }

    @Composable
    fun SetNameScreen() {

        val sharedPreferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE)


        var text by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }
        val focusManager = LocalFocusManager.current


        Box(
            Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
        ) {
            MainBackScreen()
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 70.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = stringResource(R.string.loginShortScript),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF034206)
                )


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, end = 40.dp, start = 40.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = stringResource(R.string.UserInfo),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 10.dp, top = 30.dp)
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = text,
                        onValueChange = {
                            if (it.length <= 30)
                                text = it
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.UserInfoTextField),
                                fontSize = 15.sp,
                                textAlign = TextAlign.Right,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        singleLine = true,
                        textStyle = TextStyle(
                            textDirection = TextDirection.Rtl,
                            fontSize = 15.sp
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF034206),
                            cursorColor = Color(0xFF034206)
                        ),
                        isError = isError,
                        supportingText = {

                            if (isError) {


                                Text(
                                    text = stringResource(R.string.isErrorText),
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color.Red
                                )

                            }
                        }


                    )
                    GreenButton(
                        onClick = {

                            if (text.isEmpty())
                                isError = true
                            else {
                                sharedPreferences.edit {
                                    putString("UserName", text) // ذخیره مقدار
                                }
                                startActivity(Intent(this@SetName, MainActivity::class.java))
                            }


                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        text =  stringResource(R.string.ButtonConfirm)
                    )
                    Text(
                        text = stringResource(R.string.skip),
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .clickable(
                                onClick = {
                                    startActivity(Intent(this@SetName, MainActivity::class.java))
                                },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            )
                    )
                }

            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        SetNameScreen()
    }
}

