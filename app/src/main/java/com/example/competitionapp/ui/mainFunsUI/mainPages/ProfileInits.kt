package com.example.competitionapp.ui.mainFunsUI.mainPages

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.competitionapp.R
import com.example.competitionapp.ui.extFuns.AddAddressDialog
import com.example.competitionapp.ui.extFuns.HeightSpacer


@Composable
fun MainProfilePageInit(context: Context) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2FCF2)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopProfile(context)
        BottomOfProfile(context)

    }

}


@Composable
fun ProfileEditPageInit(context: Context) {

    val focusManager = LocalFocusManager.current



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2FCF2))
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileEditPageTop()

        ProfileEditPageBottom(context)

    }


}


@Composable
fun ProfileAddressPageInit(context: Context) {


    var newAddressBox = remember { mutableStateOf(false) }
    var defaultAddress = remember { mutableStateOf(true) }
    var data = remember { mutableStateListOf<ProfileAddAddress>() }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2FCF2))
            .verticalScroll(
                state = rememberScrollState()
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        TopOfProfileSubPages(context)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, end = 30.dp, start = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            Icon(
                painter = painterResource(R.drawable.add),
                contentDescription = null,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ){
                    newAddressBox.value = true
                }
            )
            Text(
                text = " آدرس های من",
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {


            if (newAddressBox.value) {
                AddAddressDialog {

                    data.add(it)
                    newAddressBox.value = false

                }
            } else {
                for (index in 0 until data.size) {

                    if (
                        data[index].name.isNotEmpty() &&
                        data[index].address.isNotEmpty() &&
                        data[index].postCode.isNotEmpty() &&
                        data[index].phone.isNotEmpty()
                    ) {
                        ProfileAddressPageBox(data[index], defaultAddress)
                        HeightSpacer(20.dp)

                    }

                }

            }

            if (data.isEmpty()){
                EmptyPage("آدرسی")
            }
            






        }

    }


}


@Composable
fun FavouritePageInit(context: Context) {

    val prefs = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val favState = prefs.getBoolean("fav", false)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .background(Color(0xFFF2FCF2)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopOfProfileSubPages(context)
        HeightSpacer(20.dp)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                text = "علاقه مندی های من",
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            HeightSpacer(30.dp)

            EmptyPage("علاقه مندی")
        }

    }

}


@Composable
fun OrderPageInit(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2FCF2)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopOfProfileSubPages(context)
        HeightSpacer(20.dp)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                text = "سفارشات من",
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            HeightSpacer(30.dp)

            EmptyPage("سفارشی")
        }

    }

}


@Composable
fun FinalBuyPageInit() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 30.dp, top = 30.dp, start = 30.dp)
            .background(Color(0xFFF2FCF2)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "سبد خرید",
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            HeightSpacer(10.dp)
            FinalBuyPageAddressBox(
                ProfileAddAddress(
                    "fdwdwdw",
                    "dwdwdwdw",
                    "dwdwdwd",
                    "wdwdwdw"
                ),
            )
            HeightSpacer(10.dp)

            OutlinedButton(
                onClick = {

                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF038107)

                ),
                border = BorderStroke(1.dp, Color(0xFF038107)),
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = "به آدرس دیگری برود",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            HeightSpacer(5.dp)

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "تقریبا تا 5 روز آینده این محصول بدست شما میرسد",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

        }

        FinalBuyPageBottom(
            "1,000,000",
            "1,000,000",
            "1,000,000",
            "1,000,000",
        )
    }
}

@Composable
fun NotificationPageInit() {





    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2FCF2)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopOfProfileSubPages(LocalContext.current)


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                text = " اعلانات من",
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

                HeightSpacer(8.dp)

            ElanatText()

            HeightSpacer(20.dp)

            val itemCount = 4
            val expendedStates = remember { mutableStateListOf(*Array(itemCount) { false }) }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(itemCount){ index->
                    NotificationItem(
                        expended = expendedStates[index],
                        onToggleExpand = { expendedStates[index] = !expendedStates[index]
                        })
                }
            }
        }

    }





}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview222() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2FCF2)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FinalBuyPageInit()
        HeightSpacer(20.dp)

    }
}










