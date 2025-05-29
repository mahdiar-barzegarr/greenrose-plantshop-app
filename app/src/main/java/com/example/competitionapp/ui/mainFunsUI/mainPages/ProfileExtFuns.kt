package com.example.competitionapp.ui.mainFunsUI.mainPages

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.rememberAsyncImagePainter
import com.example.competitionapp.R
import com.example.competitionapp.ui.extFuns.CustomTextField
import com.example.competitionapp.ui.extFuns.GreenButton
import com.example.competitionapp.ui.extFuns.HeightSpacer
import com.example.competitionapp.ui.extFuns.LaunchImagePicker
import com.example.competitionapp.ui.extFuns.WidthSpacer
import com.example.competitionapp.ui.extFuns.loadImageFromSharedPreferences
import com.example.competitionapp.ui.mainFunsUI.SecondActivity

@Composable
fun TopProfile(context: Context) {


    val prefs = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val userName = prefs.getString("UserName", "ناشناس")
    val userNumber = prefs.getString("UserNumber", "وارد نشده است")

    var triggerPicker by remember { mutableStateOf(false) }

    val imageUri = remember {
        mutableStateOf(loadImageFromSharedPreferences(context, prefs))
    }

    // فراخوانی لانچر عکس
    LaunchImagePicker(
        trigger = triggerPicker, onLaunched = {
            triggerPicker = false
            imageUri.value = loadImageFromSharedPreferences(context, prefs)
        })

    // اضافه کردن observer برای lifecycle
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // زمانی که صفحه دوباره فعال می‌شود، عکس از SharedPreferences خوانده می‌شود
                imageUri.value = loadImageFromSharedPreferences(context, prefs)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .background(
                brush = Brush.linearGradient(

                    colors = listOf(
                        Color(0xFF2A8C2E),
                        Color(0xFF85FF87),
                    )
                ),
                shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp),

                )

    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            HeightSpacer(10.dp)

            Text(
                text = stringResource(R.string.ProfileScript),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
            HeightSpacer(15.dp)

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                Icon(
                    modifier = Modifier.clickable(
                        onClick = {
                        val intent = Intent(context, SecondActivity::class.java).apply {
                            putExtra("whichPageIs", "EditProfilePage") // ارسال نام کاربر
                        }
                        context.startActivity(intent)
                    },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ),
                    painter = painterResource(R.drawable.edit_ic),
                    contentDescription = null,
                    tint = Color.White
                )


                WidthSpacer(40.dp)

                Box(
                    modifier = Modifier.size(80.dp), contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(80.dp),
                        painter = painterResource(R.drawable.back_user_photo),
                        contentDescription = null,
                        alignment = Alignment.Center
                    )
                    Image(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                        painter = imageUri.value?.let { rememberAsyncImagePainter(it) }
                            ?: painterResource(R.drawable.person_image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop)

                }


                WidthSpacer(40.dp)


                Icon(
                    modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) {

                    triggerPicker = true
                },
                    painter = painterResource(R.drawable.add_photo_ic),
                    contentDescription = null,
                    tint = Color.White
                )

            }
            HeightSpacer(10.dp)

            Text(
                text = userName.toString(), fontSize = 17.sp, color = Color.White
            )
            HeightSpacer(5.dp)
            Text(
                text = userNumber.toString(), fontSize = 15.sp, color = Color.White
            )

        }

    }
}


@Composable
fun BottomOfProfile(context: Context) {



    val categories = listOf<ProfileCategory>(
        ProfileCategory(
            R.drawable.shopping, "سفارشات من"
        ), ProfileCategory(
            R.drawable.notification, "اعلانات من"
        ), ProfileCategory(
            R.drawable.location, "آدرس های من"
        ), ProfileCategory(
            R.drawable.book_mark, "علاقه مندی های من"
        )

    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(17.dp),
        horizontalArrangement = Arrangement.spacedBy(17.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(categories.size) { index ->
            val item = categories[index]

            Card(
                modifier = Modifier
                    .size(150.dp)
                    .clickable(
                        onClick = {
                            when (index) {
                                0 -> {
                                    val intent = Intent(context, SecondActivity::class.java).apply {
                                        putExtra(
                                            "whichPageIs", "OrderProfilePage"
                                        ) // ارسال نام کاربر
                                    }
                                    context.startActivity(intent)
                                }

                                1 -> {
                                    val intent = Intent(context, SecondActivity::class.java).apply {
                                        putExtra(
                                            "whichPageIs", "NotificationPage"
                                        ) // ارسال نام کاربر
                                    }
                                    context.startActivity(intent)
                                }


                                2 -> {
                                    val intent = Intent(context, SecondActivity::class.java).apply {
                                        putExtra(
                                            "whichPageIs", "AddressProfilePage"
                                        ) // ارسال نام کاربر
                                    }
                                    context.startActivity(intent)
                                }

                                3 -> {
                                    val intent = Intent(context, SecondActivity::class.java).apply {
                                        putExtra(
                                            "whichPageIs", "FavouriteProfilePage"
                                        ) // ارسال نام کاربر
                                    }
                                    context.startActivity(intent)
                                }
                            }
                        },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    listOf(
                                        Color(0xFFD5FFD8), Color(0xFFFFFFFF)
                                    )
                                ), shape = CircleShape
                            ), contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.size(60.dp),
                            painter = painterResource(id = item.icon),
                            contentDescription = null
                        )
                    }
                    Text(
                        text = item.name, fontSize = 16.sp, fontWeight = FontWeight.Bold

                    )
                }

            }

        }


    }


}


@Composable
fun ProfileEditPageTop() {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)


    val imageUri = remember {
        mutableStateOf(loadImageFromSharedPreferences(context, prefs))
    }
    var triggerPicker by remember { mutableStateOf(false) }
    // فراخوانی لانچر عکس
    LaunchImagePicker(
        trigger = triggerPicker, onLaunched = {
            triggerPicker = false
            imageUri.value = loadImageFromSharedPreferences(context, prefs)
        })
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF2A8C2E), Color(0xFF85FF87))
                ), shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp)
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HeightSpacer(10.dp)

            Text(
                text = stringResource(R.string.ProfileEditPage),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                WidthSpacer(75.dp)

                Box(modifier = Modifier.size(80.dp), contentAlignment = Alignment.Center) {
                    Image(
                        modifier = Modifier.size(80.dp),
                        painter = painterResource(R.drawable.back_user_photo),
                        contentDescription = null,
                        alignment = Alignment.Center,
                    )

                    Image(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                        painter = imageUri.value?.let { rememberAsyncImagePainter(it) }
                            ?: painterResource(R.drawable.person_image),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillBounds,
                    )
                }

                WidthSpacer(40.dp)

                Icon(
                    modifier = Modifier.clickable {
                        prefs.edit() { remove("saved_image") }
                        imageUri.value = null
                        imageUri.value = loadImageFromSharedPreferences(context, prefs)
                    },
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun ProfileEditPageBottom(context: Context) {

    val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val userNameSh = sharedPreferences.getString("UserName", "ناشناس")
    val userPhoneNumberSh = sharedPreferences.getString("UserNumber", "وارد نشده است")


    var userName = remember { mutableStateOf(userNameSh.toString()) }
    var userPhoneNumber = remember { mutableStateOf(userPhoneNumberSh.toString()) }
    var userEmail = remember { mutableStateOf("") }
    var userBirthDay = remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 0.dp)

    ) {

        CustomTextField(
            textFieldState = userName,
            placeHolder = "نام کاربری خود را اینجا وارد کنید",
            direction = TextDirection.Rtl,
            scriptUpTextField = "نام کاربری:"
        )
        CustomTextField(
            textFieldState = userPhoneNumber,
            placeHolder = "شماره تلفن همراه خود را اینجا وارد کنید",
            textFieldType = KeyboardType.Number,
            scriptUpTextField = "شماره همراه:"
        )
        CustomTextField(
            textFieldState = userEmail,
            placeHolder = "مثال:example@gmail.com",
            scriptUpTextField = "ایمیل:"
        )
        CustomTextField(
            textFieldState = userBirthDay,
            placeHolder = "1360/4/13",
            scriptUpTextField = "تاریخ تولد:"
        )

        HeightSpacer(10.dp)

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            text = "جنسیت:",
            style = TextStyle(textDirection = TextDirection.Rtl),
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        var rState = remember {
            mutableStateListOf(false, false)
        }

        HeightSpacer(10.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End

        ) {


            Text(
                "مرد", fontWeight = FontWeight.Bold
            )
            RadioButton(
                selected = rState[0], onClick = {
                    rState[0] = true
                    rState[1] = false
                }, colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF034206)
                )

            )
            WidthSpacer(10.dp)

            Text(
                "زن", fontWeight = FontWeight.Bold
            )
            RadioButton(
                selected = rState[1], onClick = {
                    rState[0] = false
                    rState[1] = true
                }, colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF034206)
                )

            )

            WidthSpacer(10.dp)


        }

        GreenButton(
            Modifier
                .padding(10.dp)
                .fillMaxWidth(),

            text = "ثبت تغییرات"
        ) {}


    }
}

@Composable
fun TopOfProfileSubPages(
    context: Context
) {
    val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val userName = sharedPreferences.getString("UserName", "ناشناس")
    val userNumber = sharedPreferences.getString("UserNumber", "وارد نشده است")


    val imageUri = remember {
        mutableStateOf(loadImageFromSharedPreferences(context, sharedPreferences))
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(
                brush = Brush.linearGradient(

                    colors = listOf(
                        Color(0xFF2A8C2E),
                        Color(0xFF85FF87),
                    )
                ),
                shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp),

                ), verticalArrangement = Arrangement.Center

    ) {


        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {


            Column {


                Text(
                    text = userName.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = userNumber.toString(), fontSize = 18.sp, color = Color.White
                )


            }

            WidthSpacer(10.dp)
            Box(
                modifier = Modifier.size(80.dp), contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(R.drawable.back_user_photo),
                    contentDescription = null,
                    alignment = Alignment.Center
                )
                Image(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    painter = imageUri.value?.let { rememberAsyncImagePainter(it) }
                        ?: painterResource(R.drawable.person_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop)


            }


        }


    }
}


@Composable
fun ProfileAddressPageBox(
    newAddress: ProfileAddAddress, state: MutableState<Boolean>
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()

            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp),

                )
            .border(
                border = BorderStroke(
                    if (state.value) 1.dp else 0.dp,
                    if (state.value) Color(0xFF034206) else Color.White
                ), shape = RoundedCornerShape(10.dp)
            )
            .padding(top = 20.dp, end = 20.dp, start = 20.dp)
            .height(200.dp),
        horizontalAlignment = Alignment.End
    ) {


        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = newAddress.name,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
            WidthSpacer(10.dp)
            Text(
                text = "گیرنده :",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )

        }
        HeightSpacer(6.dp)
        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = newAddress.address,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
            WidthSpacer(10.dp)
            Text(
                text = "آدرس :",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )

        }
        HeightSpacer(6.dp)

        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = newAddress.postCode,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
            WidthSpacer(10.dp)
            Text(
                text = "کد پستی :",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )

        }
        HeightSpacer(6.dp)

        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = newAddress.phone,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
            WidthSpacer(10.dp)
            Text(
                text = "شماره همراه :",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )

        }
        HeightSpacer(20.dp)


        HorizontalDivider(thickness = 2.dp)
        HeightSpacer(13.dp)


        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {

                Button(
                    modifier = Modifier
                        .width(80.dp)
                        .height(35.dp),
                    onClick = {},
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD3D3D3)
                    )
                ) {
                    Text(
                        "حذف", color = Color(0xFF727272), fontWeight = FontWeight.Bold
                    )
                }
                WidthSpacer(10.dp)
                Button(
                    modifier = Modifier
                        .width(80.dp)
                        .height(35.dp),
                    onClick = {},
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFC6FCC7)
                    )
                ) {
                    Text(
                        text = "ویرایش", color = Color(0xFF005E02), fontWeight = FontWeight.Bold
                    )
                }


            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "آدرس پیش فرض", fontWeight = FontWeight.Bold, fontSize = 15.sp
                )
                WidthSpacer(10.dp)
                RadioButton(
                    selected = state.value,
                    onClick = { state.value = !state.value },
                    modifier = Modifier
                        .size(20.dp) // کاهش اندازه دایره
                        .padding(0.dp) // حذف Padding
                        .defaultMinSize(0.dp, 0.dp), // حذف حداقل اندازه
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF034206)
                    ),
                    interactionSource = remember { MutableInteractionSource() })
                WidthSpacer(5.dp)

            }
        }


    }


}

@Composable
fun FinalBuyPageAddressBox(newAddress: ProfileAddAddress) {

    Column(
        modifier = Modifier
            .fillMaxWidth()

            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp),

                )

            .padding(top = 20.dp, end = 20.dp, start = 20.dp)
            .height(140.dp),
        horizontalAlignment = Alignment.End
    ) {


        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = newAddress.name,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
            WidthSpacer(10.dp)
            Text(
                text = "گیرنده :",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )

        }
        HeightSpacer(6.dp)
        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = newAddress.address,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
            WidthSpacer(10.dp)
            Text(
                text = "آدرس :",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )

        }
        HeightSpacer(6.dp)

        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = newAddress.postCode,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
            WidthSpacer(10.dp)
            Text(
                text = "کد پستی :",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )

        }
        HeightSpacer(6.dp)

        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = newAddress.phone,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )
            WidthSpacer(10.dp)
            Text(
                text = "شماره همراه :",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = TextStyle(textDirection = TextDirection.Rtl)
            )

        }


    }


}

@Composable
fun FinalBuyPageBottom(
    totalPrice: String,
    discount: String? = null,
    sentPrice: String? = null,
    finalPrice: String,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(245.dp),
        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$totalPrice تومان",
                        style = TextStyle(textDirection = TextDirection.Rtl),
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "جمع قیمت :",
                        style = TextStyle(textDirection = TextDirection.Rtl),
                        fontSize = 20.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )


                }
                HeightSpacer(5.dp)
                if (discount != null) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "$discount تومان",
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontSize = 20.sp,
                            color = Color(0xFF04650A),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = " تخفیف :",
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontSize = 20.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )


                    }
                    HeightSpacer(5.dp)
                }

                if (sentPrice != null) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "$sentPrice تومان",
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = " هزینه ارسال :",
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontSize = 20.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )


                    }
                }
            }


            Column {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$finalPrice تومان",
                        style = TextStyle(textDirection = TextDirection.Rtl),
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " قیمت نهایی:",
                        style = TextStyle(textDirection = TextDirection.Rtl),
                        fontSize = 20.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )


                }

                HeightSpacer(10.dp)

                GreenButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "پرداخت نهایی",
                ) {

                }
            }

        }


    }


}

@Composable
fun OrderItems(
    purchaseCode: String,
    purchaseDate: String,
    productName: String,
    productPrice: String,
    purchaseState: Boolean
) {

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,


            ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .height(120.dp),

                ) {
                HeightSpacer(3.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = purchaseDate,
                        style = TextStyle(textDirection = TextDirection.Rtl),
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = " کد سفارش :$purchaseCode",
                        style = TextStyle(textDirection = TextDirection.Rtl),
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Text(
                    text = productName,
                    style = TextStyle(textDirection = TextDirection.Rtl),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(90.dp)
                            .clip(CircleShape)
                            .background(if (purchaseState) Color(0xFFC6FCC7) else Color(0xFFFDC1C1)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (purchaseState) "پرداخت موفق" else "پرداخت ناموفق",
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontSize = 14.sp,
                            color = if (purchaseState) Color(0xFF448646) else Color(0xFF904949),
                            fontWeight = FontWeight.Bold,
                        )

                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically

                    ) {


                        Text(
                            "تومان",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 4.dp)
                        )

                        Text(
                            text = productPrice,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500),
                            modifier = Modifier.padding(start = 4.dp)
                        )


                    }


                }

            }



            WidthSpacer(8.dp)

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray)
                    .padding(), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.home_2), contentDescription = "image"
                )

            }

            WidthSpacer(8.dp)


        }
    }


}


@Composable
fun NotificationItem(
    expended: Boolean, onToggleExpand: () -> Unit
) {
    val rotationState by animateFloatAsState(
        targetValue = if (expended) 180f else 0f, label = ""
    )

    val parentHeight by animateDpAsState(
        targetValue = if (expended) 194.dp else 90.dp, animationSpec = tween(
            durationMillis = 300, easing = LinearOutSlowInEasing
        )
    )

    val showMoreHeight by animateDpAsState(
        targetValue = if (expended) 128.dp else 27.dp, animationSpec = tween(
            durationMillis = 300, easing = LinearOutSlowInEasing
        )
    )
    Box(
        Modifier.fillMaxSize()
    ) {

        Card(
            Modifier
                .fillMaxWidth()
                .height(parentHeight)
                .align(Alignment.TopEnd),
            colors = CardDefaults.cardColors(containerColor = Color.White)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 8.dp, 8.dp, 0.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier

                        .height(41.dp)
                        .weight(1f), contentAlignment = Alignment.BottomStart

                ) {
                    Text(
                        "۲۶ مهر ۱۴۰۲", fontSize = 14.sp, color = Color.Gray
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        "بازگشت وجه",
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Text(
                        "غرفه دار", fontWeight = FontWeight(400), fontSize = 14.sp
                    )
                }
                Column(
                    Modifier.padding(top = 6.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_not_script),
                        contentDescription = "message",
                        Modifier.size(41.dp, 25.dp)
                    )
                }
            }

            Column(
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    Modifier
                        .width(290.dp)
                        .height(showMoreHeight)
                        .clickable { onToggleExpand() }
                        .animateContentSize(
                            animationSpec = tween(
                                durationMillis = 300, easing = LinearOutSlowInEasing
                            )
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF2FCF2)
                    ),
                ) {
                    Column(Modifier.fillMaxSize()) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 3.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowDown,
                                contentDescription = "todo",
                                Modifier
                                    .size(16.dp)
                                    .rotate(rotationState)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                "مشاهده اعلان", fontSize = 14.sp, fontWeight = FontWeight(400)
                            )
                        }
                        if (expended) {
                            Spacer(Modifier.height(15.dp))
                            Divider(
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.Gray,
                                thickness = 1.dp
                            )
                            Spacer(Modifier.height(15.dp))
                            Text(
                                modifier = Modifier.padding(end = 10.dp, start = 5.dp),
                                text = "متن اعلان در اینجا نوشته می شود. ممکن است این اعلان لینک داشته باشد." + "داشته باشد متن اعلان در اینجا نوشته می شود. ممکن است این اعلان لینک",
                                style = TextStyle(
                                    textDirection = TextDirection.Rtl, lineHeight = 24.sp
                                ),
                                fontSize = 14.sp,
                                maxLines = 6,
                                fontWeight = FontWeight.Bold,
                            )
                        }

                    }
                }
            }
        }
    }
}


@Composable
fun ElanatText() {

    Row(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text("خوانده نشده ها")
        Spacer(Modifier.width(6.dp))
        Box(
            modifier = Modifier
                .size(22.dp)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(3.dp)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(Color.Green, shape = RoundedCornerShape(3.dp))
            )
        }

        Spacer(Modifier.width(48.dp))

        Text("خوانده شده ها")
        Spacer(Modifier.width(6.dp))
        Box(
            modifier = Modifier
                .size(22.dp)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(3.dp)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(Color.Green, shape = RoundedCornerShape(3.dp))
            )
        }
    }
}


@Composable
fun EmptyPage(name: String) {

    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = " شما هنوز هیج  $name ندارید! ",
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold

            )
        }
    }
}


data class ProfileCategory(
    val icon: Int, val name: String
)

data class ProfileAddAddress(
    var name: String,
    var address: String,
    var postCode: String,
    var phone: String,
)


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {


    Column(
        Modifier.fillMaxSize()
    ) {

        HeightSpacer(30.dp)

        EmptyPage("سفارشی")
    }


}