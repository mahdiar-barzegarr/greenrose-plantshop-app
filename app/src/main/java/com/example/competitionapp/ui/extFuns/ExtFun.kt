@file:Suppress("NO_REFLECTION_IN_CLASS_PATH")

package com.example.competitionapp.ui.extFuns

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.model.IndicatorStyle
import com.example.competitionapp.R
import com.example.competitionapp.extDataClasses.NavigationItem
import com.example.competitionapp.ui.mainFunsUI.mainPages.ProfileAddAddress
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.core.content.edit
import com.example.competitionapp.ui.mainFunsUI.SecondActivity
import java.io.ByteArrayOutputStream
import java.lang.reflect.Method

@Composable
fun MainBackScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splash),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
    }
}

@Composable
fun SystemView() {

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(Color.White) // استاتوس بار قرمز میشه
        systemUiController.setNavigationBarColor(
            color = Color.White
        )//button's bottom of phone
    }
}

@Composable
fun AboutUs() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAFCEA))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFFD6F5E3))
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Plant",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "اپلیکیشن رز سبز",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color(0xFF1B5E20),
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "طبیعت را در جیب شما قرار دادیم. ما، با هدف آوردن طراوت و زیبایی گیاه‌ها، فروشگاهی آنلاین از گل‌ها راه اندازی کرده‌ایم.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        text = "ماموریت ما",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF1B5E20),
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "ما باور داریم هر برگ گیاهان، یک نفس تازه است. از گیاهان آپارتمانی گرفته تا گل‌های خاص هر سلیقه ای داریم.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "تیم ما",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFF1B5E20),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TeamMember("مهدیار", "برنامه نویس", R.drawable.person_image)
            TeamMember("مهدی", "برنامه نویس", R.drawable.person_image)
        }
        Column(Modifier.padding(top = 34.dp)) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_not_script),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "ارتباط با ما",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color(0xFF1B5E20),
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "برای ارتباط با تیم سبزینو، می‌توانید به ایمیل زیر پیام دهید:",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "sabzino.app@gmail.com",
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            color = Color(0xFF388E3C),
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl)
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun TeamMember(name: String, role: String, imageRes: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = role, fontSize = 14.sp, color = Color.Gray)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun TopAppBarView(onMenuClick: () -> Unit,isExpanded: Boolean) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(57.dp)
            .background(Color.White)
            .padding(end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(R.drawable.logo_not_script),
                contentDescription = null,
                modifier = Modifier.padding(6.dp)
            )
            Text(
                text = "GreenRose",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF034206),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            AnimatedContent(targetState = isExpanded, transitionSpec = {
                fadeIn(animationSpec = tween(400)) with fadeOut(animationSpec = tween(400))
            }) { expanded ->
                Icon(
                    imageVector = if (expanded) Icons.Default.Close else Icons.Default.Menu,
                    contentDescription = "",
                    tint = Color(0xFF034206),
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onMenuClick() }
                )
            }

        }
    }
}
@Composable
fun InfoTab() {

    val context = LocalContext.current
    val perf = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val userName = perf.getString("UserName", "ناشناس")
    val userNumber = perf.getString("UserNumber", "وارد نشده است")
    val imageUri = remember {
        mutableStateOf(loadImageFromSharedPreferences(context,perf))
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color.White)
            .border(2.dp, Color(0xFF2C6B43))
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(18.dp)
                .fillMaxSize()
        ) {
            item {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(Color(0xFF52B788), Color(0xFFB7E4C7))
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    AsyncImage(
                                         model =  imageUri.value,
                                        contentDescription = "profile",
                                        modifier = Modifier
                                            .size(50.dp)
                                            .clip(CircleShape)
                                    )
                                    Text(
                                        userName.toString(),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF1E5A43),
                                        modifier = Modifier.padding(6.dp, top = 16.dp)
                                    )
                                }

                                Text(
                                    userNumber.toString(),
                                    fontSize = 16.sp,
                                    color = Color(0xFF1E5A43),
                                    modifier = Modifier.padding(6.dp, top = 16.dp)
                                )
                            }
                        }
                    }

                    val items = listOf(
                        R.drawable.task_square to "سفارشات من",
                        R.drawable.support to "قوانین و مقررات",
                        R.drawable.people to "درباره ما",
                    )

                    items.forEachIndexed { index,item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, top = 16.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ){
                                    when(index){
                                        0 -> {
                                            val intent = Intent(context, SecondActivity::class.java).apply {
                                                putExtra(
                                                    "whichPageIs", "OrderProfilePage"
                                                ) // ارسال نام کاربر
                                            }
                                            context.startActivity(intent)
                                        }
                                        2->{
                                            val intent = Intent(context, SecondActivity::class.java).apply {
                                                putExtra(
                                                    "whichPageIs", "AboutUs"
                                                ) // ارسال نام کاربر
                                            }
                                            context.startActivity(intent)

                                        }
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(item.first),
                                contentDescription = "",
                                modifier = Modifier.padding(end = 16.dp)
                            )
                            Text(
                                item.second,
                                color = Color(0xFF2A7D55),
                                fontWeight = FontWeight(600)
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun DropDownPanel(isExpanded: Boolean) {
    val density = LocalDensity.current
    val offsetY = with(density) { 70.dp.roundToPx() }

    AnimatedVisibility(
        visible = isExpanded,
        enter = slideInVertically(
            initialOffsetY = { -offsetY },
            animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
        ) + fadeIn(animationSpec = tween(500)),
        exit = slideOutVertically(
            targetOffsetY = { -offsetY },
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
        ) + fadeOut(animationSpec = tween(400)),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            color = Color(0xFFE0F7FA),
            shadowElevation = 4.dp
        ) {
            InfoTab()
        }
    }
}







@Composable
fun TopAppBarViewSecondScreen(topText: String, context: Context) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(57.dp)
            .background(Color.White)
            .padding(end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        WidthSpacer(20.dp)


        Icon(
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    (context as? Activity)?.finish()
                }),
            painter = painterResource(R.drawable.baseline_arrow_back_24),
            contentDescription = null,
            tint = Color(0xFF034206)
        )

        WidthSpacer(20.dp)


        Text(
            text = topText,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF034206),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif
        )


    }
}


@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        NavigationItem(
            "پروفایل",
            selectedIcon = painterResource(id = R.drawable.user_octagon),
            unSelectedIcon = painterResource(id = R.drawable.user_octagon),
            "Profile"
        ),
        NavigationItem(
            "سبد خرید",
            selectedIcon = painterResource(id = R.drawable.shopping_bag),
            unSelectedIcon = painterResource(id = R.drawable.shopping_bag),
            "Shop"
        ),


        NavigationItem(
            "دسته بندی",
            selectedIcon = painterResource(id = R.drawable.category_2),
            unSelectedIcon = painterResource(id = R.drawable.category_2),
            "Category"
        ),
        NavigationItem(
            "خانه",
            selectedIcon = painterResource(id = R.drawable.home_2),
            unSelectedIcon = painterResource(id = R.drawable.home_2),
            "Home"
        ),

        )


    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    var state by remember { mutableIntStateOf(1) }



    AnimatedBottomBar(
        selectedItem = state,
        containerColor = Color(0xFFFFFFFF),
        indicatorStyle = IndicatorStyle.NONE,
        indicatorColor = Color(0xFF056C0B),


        ) {

        Row(
            Modifier
                .fillMaxWidth()
                .height(90.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom

        ) {
            items.forEachIndexed { index, navigationItem ->

                val widthFraction by animateDpAsState(
                    targetValue = if (currentRoute == navigationItem.route) 40.dp else 0.dp,
                    animationSpec = tween(durationMillis = 850)
                )
                Column(
                    modifier = Modifier
                        .width(60.dp)
                        .height(100.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            navController.navigate(navigationItem.route) {
                                popUpTo(0) {
                                    inclusive = false
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true

                            }

                            state = index


                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {


                    Card(
                        modifier = Modifier
                            .width(widthFraction)
                            .height(3.dp),
                        shape = RoundedCornerShape(200.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF0B5B3F))

                    ) { }

                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp),
                        painter = if (navigationItem.route == currentRoute) navigationItem.selectedIcon else navigationItem.unSelectedIcon,
                        contentDescription = navigationItem.title,
                        tint = Color.Unspecified
                    )
                    Text(
                        navigationItem.title,
                        color = if (navigationItem.route == currentRoute) Color(0xFF015F04)
                        else Color(0xFF838383),
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        }
    }

}

@Composable
fun GreenButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 18.sp,
    onClick: () -> Unit,

    ) {

    Button(
        onClick = onClick, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(
            Color(0xFF038107)
        ), modifier = modifier

    ) {
        Text(
            text = text, fontSize = fontSize
        )
    }
}

@Composable
fun CustomTextField(
    textFieldState: MutableState<String>,
    placeHolder: String,
    direction: TextDirection = TextDirection.Ltr,
    textFieldType: KeyboardType = KeyboardType.Text,
    scriptUpTextField: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp, start = 10.dp)
    ) {
        HeightSpacer(10.dp)

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = scriptUpTextField,
            style = TextStyle(textDirection = TextDirection.Rtl),
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        HeightSpacer(10.dp)


        TextField(
            value = textFieldState.value,
            onValueChange = {
                if (textFieldType == KeyboardType.Number) {
                    if (it.length <= 11) textFieldState.value = it
                } else {
                    if (it.length <= 40) textFieldState.value = it
                }
            },
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = placeHolder,
                    style = TextStyle(textDirection = TextDirection.Rtl),
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            },
            shape = RoundedCornerShape(15.dp),
            textStyle = TextStyle(textDirection = direction, fontSize = 14.sp),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = textFieldType),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )

        )
    }


}

@Composable
fun FinalBuyDialog(productName: String, numberOfBuy: String, onClose: () -> Unit) {

    Dialog(onDismissRequest = { onClose() }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(10.dp)
                .background(Color.White, shape = RoundedCornerShape(10.dp))
        ) {
            HeightSpacer(5.dp)

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            onClose()
                        }),
                    painter = painterResource(R.drawable.close_circle),
                    contentDescription = null,
                    tint = Color(0xFF034206)
                )
                WidthSpacer(5.dp)
            }

            HeightSpacer(10.dp)

            Text(
                modifier = Modifier.padding(end = 30.dp, start = 25.dp),
                text = " محصول $productName به تعداد $numberOfBuy عدد به سبد خرید شما اضافه شد جهت نهایی کردن سبد خرید بر روی این دکمه کلیک کنید",
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontSize = 16.sp
            )

            HeightSpacer(35.dp)

            GreenButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp, start = 40.dp),
                text = "نهایی کردن سبد خرید",
                onClick = {

                })


        }
    }
}

@Composable
fun LaunchImagePicker(
    trigger: Boolean, onLaunched: () -> Unit = {}
) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)

    val requiredPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            saveImageToSharedPreferences(context, prefs, it)
        }
        onLaunched()
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            galleryLauncher.launch("image/*")
        } else {
            Toast.makeText(context, "اجازه دسترسی رد شد", Toast.LENGTH_SHORT).show()
        }
        onLaunched()
    }

    LaunchedEffect(trigger) {
        if (trigger) {
            permissionLauncher.launch(requiredPermission)
        }
    }
}

fun saveImageToSharedPreferences(context: Context, prefs: SharedPreferences, uri: Uri) {
    try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        val encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT)

        prefs.edit {
            putString("saved_image", encodedImage)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun loadImageFromSharedPreferences(context: Context, prefs: SharedPreferences): Uri? {
    val encodedImage = prefs.getString("saved_image", null) ?: return null
    try {
        val byteArray = Base64.decode(encodedImage, Base64.DEFAULT)
        // برای سادگی، تصویر رو به صورت موقت در فایل ذخیره می‌کنیم تا Uri بسازیم
        val tempFile = java.io.File.createTempFile("image", ".png", context.cacheDir)
        tempFile.writeBytes(byteArray)
        return Uri.fromFile(tempFile)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}


@Composable
fun AddAddressDialog(onDismiss: (ProfileAddAddress) -> Unit) {

    val focusManager = LocalFocusManager.current


    val name = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    val postCode = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }

    var data by remember { mutableStateOf(ProfileAddAddress("", "", "", "")) }

    Dialog(onDismissRequest = { onDismiss(data) }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(520.dp)
                .padding(10.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
                .verticalScroll(rememberScrollState())
                .background(Color.White, shape = RoundedCornerShape(10.dp))) {
            HeightSpacer(5.dp)

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {


                Text(
                    text = "ثبت آدرس جدید",
                    style = TextStyle(textDirection = TextDirection.Rtl),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                WidthSpacer(60.dp)


                Icon(
                    modifier = Modifier.clickable(
                        onClick = {
                            onDismiss(data)
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }),
                    painter = painterResource(R.drawable.close_circle),
                    contentDescription = null,
                    tint = Color(0xFF034206)
                )
                WidthSpacer(5.dp)
            }

            HeightSpacer(10.dp)


            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, bottom = 5.dp, top = 10.dp),
                text = "اسم گیرنده :",
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, start = 10.dp),
                value = name.value,
                onValueChange = {
                    name.value = it
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF038107), cursorColor = Color(0xFF038107)
                ),
                textStyle = TextStyle(textDirection = TextDirection.Rtl, fontSize = 12.sp),
                placeholder = {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "اسم گیرنده را اینجا وارد کنید.",
                        style = TextStyle(textDirection = TextDirection.Rtl),
                    )

                },

                )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, bottom = 5.dp, top = 10.dp),
                text = "آدرس :",
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, start = 10.dp),
                value = address.value,
                onValueChange = {
                    address.value = it
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF038107), cursorColor = Color(0xFF038107)
                ),
                textStyle = TextStyle(textDirection = TextDirection.Rtl, fontSize = 12.sp),
                placeholder = {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "آدرس خود را اینجا وارد کنید.",
                        style = TextStyle(textDirection = TextDirection.Rtl),
                    )

                },

                )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, bottom = 5.dp, top = 10.dp),
                text = "کد پستی :",
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, start = 10.dp),
                value = postCode.value,
                onValueChange = {
                    postCode.value = it
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF038107), cursorColor = Color(0xFF038107)
                ),
                textStyle = TextStyle(textDirection = TextDirection.Rtl, fontSize = 12.sp),
                placeholder = {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "کد پستی را اینجا وارد کنید.",
                        style = TextStyle(textDirection = TextDirection.Rtl),
                    )

                },

                )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, bottom = 5.dp, top = 10.dp),
                text = "شماره همراه گیرنده :",
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, start = 10.dp),
                value = phone.value,
                onValueChange = {
                    phone.value = it
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF038107), cursorColor = Color(0xFF038107)
                ),
                textStyle = TextStyle(textDirection = TextDirection.Rtl, fontSize = 12.sp),
                placeholder = {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "شماره همراه گیرنده را اینجا وارد کنید.",
                        style = TextStyle(textDirection = TextDirection.Rtl),
                    )

                },

                )




            HeightSpacer(20.dp)

            GreenButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp, start = 40.dp),
                text = "ثبت آدرس",
                onClick = {
                    data = ProfileAddAddress(name.value, address.value, postCode.value, phone.value)

                    onDismiss(data)

                })


        }

    }


}


@Composable
fun AddToBasket(firstPrice: String, finalPrice: String, quantityCount: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color(0xFFCBFACB).copy(alpha = 0.7f)),

        ) {

        Row(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(end = 25.dp, start = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            Text(
                text = "$firstPrice تومان",
                fontSize = 20.sp,
                style = TextStyle(textDirection = TextDirection.Rtl),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = finalPrice,
                fontSize = 21.sp,
                textDecoration = TextDecoration.LineThrough,
                color = Color.Black,
            )


        }

        var count = remember { mutableIntStateOf(quantityCount.toInt()) }

        Row(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(end = 25.dp, start = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            GreenButton(
                text = "  افزودن به سبد خرید  "
            ) { }

            Box(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .size(44.dp)
                    .clickable(
                        onClick = {
                            if (count.intValue > 1) count.intValue--
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }),

                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painterResource(R.drawable.minimize),
                    contentDescription = null,
                )
            }

            Text(
                text = count.intValue.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .size(44.dp)
                    .clickable(
                        onClick = {
                            if (count.intValue < quantityCount.toInt()) count.intValue++
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }),

                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painterResource(R.drawable.add),
                    contentDescription = null,
                    tint = Color(0xFF008806)
                )
            }


        }

    }
}

@Composable
fun BasketItem(
    productName: String,
    productCount: String,
    productPrice: String,
    productPriceDiscount: String,
    productImage: String,
    delete: ()-> Unit
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(6.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,


        ) {

        Icon(
            modifier = Modifier
                .align(alignment = Alignment.Top)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ){
                    delete()
                }
            ,
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.Black
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .height(65.dp),
        ) {

            Text(
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .padding(start = 4.dp),
                text = productName,
                textAlign = TextAlign.Right,
                fontSize = 12.sp,
                fontWeight = FontWeight(500),

                )

            Row(
                modifier = Modifier.align(alignment = Alignment.BottomStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "تومان",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.padding(start = 4.dp)
                )

                Text(
                    text = productPrice,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.padding(start = 4.dp)
                )

                Text(
                    text = productPriceDiscount,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.padding(start = 4.dp),
                    style = TextStyle(textDecoration = TextDecoration.LineThrough),
                    color = Color.Gray
                )
            }


        }
        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .height(88.dp)
                .fillMaxWidth(0.7f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
                .padding(), contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = productImage,
                contentDescription = "image"
            )

        }

        Spacer(modifier = Modifier.width(8.dp))


        Column(
            modifier = Modifier
                .size(23.dp, 88.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFEAFCEA)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var count by remember {
                mutableIntStateOf(productCount.toInt())
            }
            IconButton({ count++ }, modifier = Modifier.weight(1f)) {
                Icon(
                    Icons.Default.Add, contentDescription = "todo", tint = Color.Green
                )

            }

            Text(
                count.toString(), modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp)
            )

            IconButton({ count-- }, modifier = Modifier.weight(1f)) {
                Icon(
                    painterResource(R.drawable.minimize),
                    contentDescription = "todo",
                    tint = Color.Red
                )

            }

        }


    }


}


@Composable
fun ImageSlider(images: List<String>) {


    val pagerState = rememberPagerState { images.size }
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(pagerState.currentPage) {
        while (true) {
            delay(3000)

            if (pagerState.isScrollInProgress) continue

            val nextPage = (pagerState.currentPage + 1) % (images.size)
            coroutineScope.launch {
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 30.dp, end = 30.dp, start = 30.dp, bottom = 15.dp)
        ) { page ->
            Card(
                Modifier.fillMaxSize(),

                colors = CardDefaults.cardColors(Color.White)
            ) {
                AsyncImage(
                    model = images[page],
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
        }


        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(images.size) { index ->
                val color =
                    if (pagerState.currentPage == index) Color(0xFF0B5B3F) else Color.Gray.copy(
                        alpha = 0.5f
                    )
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(color)
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
        }
    }
}


@Composable
fun HeightSpacer(spacer: Dp) {
    Spacer(modifier = Modifier.height(spacer))
}

@Composable
fun WidthSpacer(spacer: Dp) {
    Spacer(modifier = Modifier.width(spacer))
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_7_PRO)
@Composable
fun GreetingPreview() {
    Column(
        modifier = Modifier
            .background(color = Color.Red)
            .fillMaxSize()
    ) {
        BasketItem("s", "2", "s", "ss", "s"){}

    }

}
