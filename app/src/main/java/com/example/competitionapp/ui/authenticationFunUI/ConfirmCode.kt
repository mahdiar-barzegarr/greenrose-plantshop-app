package com.example.competitionapp.ui.authenticationFunUI

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.example.competitionapp.R
import com.example.competitionapp.ui.extFuns.GreenButton
import com.example.competitionapp.ui.extFuns.MainBackScreen
import kotlinx.coroutines.delay

class ConfirmCode : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConfirmCodeScreen()
        }
    }
    @SuppressLint("DefaultLocale")
    @Composable
    fun ConfirmCodeScreen() {


        val sharedPreferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE)



        var remainingTime by remember { mutableStateOf(60) } // 60 ثانیه تایمر
        val minutes = remainingTime / 60
        val seconds = remainingTime % 60

        // تایمر اجرا شود
        LaunchedEffect(remainingTime) {
            if (remainingTime > 0) {
                delay(1000L) // هر ثانیه یک بار اجرا شود
                remainingTime--
            }
        }


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
                        text = stringResource(R.string.confirmCode),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 10.dp, top = 30.dp)
                    )
                    Text(

                        text = stringResource(R.string.confirmLongScript),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        textAlign = TextAlign.Right,
                        fontSize = 15.sp,
                        color = Color(0xFF323832)
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = text,
                        onValueChange = {
                            if (it.length<= 4)
                                text = it
                            if (!text.isEmpty())
                                isError = false
                        },
                        keyboardOptions =KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.confirmTextFieldScript),
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
                        leadingIcon = {
                            Box(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .background(Color(0xFFB2F1BB), shape = RoundedCornerShape(8.dp))
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = String.format("%02d:%02d", minutes, seconds), // نمایش دقیقه و ثانیه
                                    color = Color(0xFF193410),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        },
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
                            else{
                                if (text=="1313") {
                                    startActivity(Intent(this@ConfirmCode, SetName::class.java))
                                    sharedPreferences.edit {
                                        putBoolean("isLoggedIn", true) // ذخیره مقدار
                                    }                                }
                                else
                                    Toast.makeText(this@ConfirmCode, "کد وارد شده اشتباه است", Toast.LENGTH_SHORT).show()
                            }

                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        text = stringResource(R.string.ButtonLogin)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.editNum),
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .clickable(
                                    onClick = {
                                        startActivity(Intent(this@ConfirmCode, Login::class.java))
                                    },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ),
                            color = Color(0xFF034206),

                        )
                        Text(
                            text = stringResource(R.string.resendScript),
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .clickable(
                                    onClick = {
                                        if (remainingTime == 0) {
                                            remainingTime = 60
                                            Toast.makeText(
                                                this@ConfirmCode,
                                                "کد ارسال شد",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else
                                            Toast.makeText(
                                                this@ConfirmCode,
                                                "ثانیه دیگر تا ارسال مجدد کد صبر کنید{$remainingTime}",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                    },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ),
                            color = Color(0xFF034206)

                        )
                    }
                }

            }
        }


    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        ConfirmCodeScreen()
    }
}

