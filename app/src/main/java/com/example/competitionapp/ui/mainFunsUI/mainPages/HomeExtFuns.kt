package com.example.competitionapp.ui.mainFunsUI.mainPages

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import coil.compose.AsyncImage
import com.example.competitionapp.R
import com.example.competitionapp.ui.dataModel.Product
import com.example.competitionapp.ui.extFuns.FinalBuyDialog
import com.example.competitionapp.ui.extFuns.GreenButton
import com.example.competitionapp.ui.extFuns.HeightSpacer
import com.example.competitionapp.ui.extFuns.WidthSpacer
import com.example.competitionapp.ui.mainFunsUI.SearchPage
import com.example.competitionapp.ui.mainFunsUI.SecondActivity
import kotlinx.coroutines.launch


@Composable
fun TopItemsScreen(
    images: List<String>,
    isOffer: Boolean = false,
    offerPercent: String = "",
    starRate: String = "0.0"
) {

    val pagerState = rememberPagerState { images.size }
    val coroutineScope = rememberCoroutineScope()





    Box(
        Modifier
            .fillMaxWidth()
            .background(

                shape = RoundedCornerShape(topEnd = 50.dp, topStart = 50.dp),
                color = Color(0xFFEBEBEB)
            )
            .height(400.dp),
    ) {



        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 10.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            coroutineScope.launch {
                                if (pagerState.currentPage > 0) {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }
                        }
                    ),
                tint = if (pagerState.currentPage == 0) Color.Gray else Color.Black,
                painter = painterResource(R.drawable.arrow_left),
                contentDescription = null
            )
            WidthSpacer(10.dp)
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .width(250.dp)
                        .height(250.dp),
                ) { page ->

                    AsyncImage(
                        model = images[page],
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )

                }
                HeightSpacer(10.dp)
                Row(
                    modifier = Modifier.width(250.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(images.size) { index ->
                        val color =
                            if (pagerState.currentPage == index) Color(0xFF454545) else Color.Gray.copy(
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
            WidthSpacer(10.dp)
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 10.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            coroutineScope.launch {
                                if (pagerState.currentPage < images.size - 1) {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            }
                        }
                    ),
                tint = if (images.lastIndex == pagerState.currentPage) Color.Gray else Color.Black,
                painter = painterResource(R.drawable.arrow_right),
                contentDescription = null
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .height(40.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topEnd = 60.dp, topStart = 20.dp)
                )
                .align(Alignment.BottomStart),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Box(
                Modifier.size(30.dp)
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(R.drawable.messages),
                    contentDescription = null,
                    tint = Color.Black
                )
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .background(
                            color = Color(0xFFFF0000),
                            shape = CircleShape
                        )
                        .align(Alignment.BottomEnd)
                ) {
                    Text(
                        "12",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier =
                            Modifier.align(Alignment.Center)
                    )
                }
            }

            WidthSpacer(15.dp)



            Icon(
                painter = painterResource(R.drawable.frame),
                contentDescription = null,
                tint = Color.Black
            )
            WidthSpacer(15.dp)
            Icon(
                painter = painterResource(R.drawable.magic_star),
                contentDescription = null,
                tint = Color(0xFFFFB800)
            )
            WidthSpacer(3.dp)
            Text(
                text = starRate
            )

            WidthSpacer(15.dp)

            Icon(
                painter = painterResource(R.drawable.share),
                contentDescription = null,
                tint = Color.Black
            )
            WidthSpacer(20.dp)

        }



        if (isOffer) {
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .width(50.dp)
                    .height(120.dp)
            ) {

                HeightSpacer(80.dp)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(
                            shape = RoundedCornerShape(bottomStart = 20.dp, topStart = 20.dp),
                            brush =
                                Brush.horizontalGradient(

                                    colors = listOf(
                                        Color(0xFF00A80B),
                                        Color(0xFF00D700),
                                        Color(0xFF00EF00),
                                        Color(0xFF6FFF71),
                                    )
                                )
                        )
                )
                {
                    Text(
                        text = "$offerPercent %",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.align(alignment = Alignment.Center)
                    )
                }
            }

        }
    }


}
@Composable
fun BottomItemsScreen(
    itemName: String,
    itemDescription: String,
    infoCount: Int,
    infoName: List<String>
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(13.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                itemName,
                fontSize = 16.sp,
                fontWeight = FontWeight(600)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Box(
                    Modifier.size(81.dp, 24.dp).clip(RoundedCornerShape(16.dp))
                        .border(2.dp, Color(0xFFF2FCF2), RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                ) {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "محصولات مشابه",
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Color.White
                        )
                    }
                }
                Box(
                    Modifier.size(60.dp, 24.dp).clip(RoundedCornerShape(16.dp))
                        .border(2.dp, Color(0xFFF2FCF2), RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                ) {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "AI",
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Color.White
                        )
                    }
                }
                Box(
                    Modifier.size(60.dp, 24.dp).clip(RoundedCornerShape(16.dp))
                        .border(2.dp, Color(0xFFF2FCF2), RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                ) {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "ویژگی ها",
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Color.White
                        )
                    }
                }
                Box(
                    Modifier.size(60.dp, 24.dp).clip(RoundedCornerShape(16.dp))
                        .border(2.dp, Color(0xFFF2FCF2), RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                ) {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "توضیحات",
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Color.White
                        )
                    }
                }

            }

            Text(
                "توضیحات",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 19.dp)
            )
            HeightSpacer(7.dp)

            Text(
                itemDescription,
                textAlign = TextAlign.Right,
                fontWeight = FontWeight(400),
                fontSize = 13.sp
            )
            Text(
                "ویژگی ها",
                textAlign = TextAlign.Right,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 19.dp)
            )
            HeightSpacer(7.dp)
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                var count = 0
                repeat(infoCount) {

                    HeightSpacer(10.dp)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(41.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (count%2!=1)Color(0xFFE5E5E5) else Color(0xFFFAFAFA))
                            .padding(end = 10.dp, start = 10.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(infoName[count], Modifier.padding(4.dp))
                    }
                    count += 1

                }

            }

            Text(
                "ارتباط با هوش مصنوعی",
                textAlign = TextAlign.Right,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 19.dp)
            )
            HeightSpacer(7.dp)

            Text(
                "در این بخش شما میتوانید با کلیک بر دکمه زیر به صفحه چت با هوش مصنوعی بروید و راجب خرید خود از اون مشورت بگیرید.",
                textAlign = TextAlign.Right,
                fontWeight = FontWeight(400),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

           GreenButton(
               modifier = Modifier.padding(8.dp).fillMaxWidth(),
               text = "AI",

           ) {

           }


        }

    }

}

@Composable
fun SearchBox() {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(end = 20.dp, start = 20.dp)
            .fillMaxWidth()
            .shadow(
                shape = RoundedCornerShape(15.dp),
                elevation = 3.dp
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp),
            )

            .height(50.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    val intent = Intent(context, SearchPage::class.java)
                    context.startActivity(intent)

                }
            )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text(
                text = "هرچی میخوای جستجوکن ...",
                fontSize = 18.sp,
                style = TextStyle(textDirection = TextDirection.Rtl),
                color = Color.Gray
            )

            WidthSpacer(15.dp)

            Icon(
                painter = painterResource(R.drawable.search_normal),
                contentDescription = null,
                modifier = Modifier.size(27.dp)

            )
            WidthSpacer(15.dp)
        }
    }
}

@Composable
fun HomeSmallCategory(isHome: Boolean = false) {
    val context2 = LocalContext.current
    val prefs = context2.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val int = prefs.getInt("catState",0)


    var state by remember { mutableIntStateOf(int) }
    prefs.edit { putInt("catState",state) }
    val configuration = LocalConfiguration.current
    val boxWidth = configuration.screenWidthDp



    val fontSize = when {
        boxWidth < 360-> 8.sp
        boxWidth < 400 -> 11.sp
        else -> 13.sp
    }

    val categoryItems = listOf(
        CategoryItem("گل های اپارتمانی", R.drawable.apartement_flower, "APF"),
        CategoryItem("کاکتوس", R.drawable.cactus2, "CF"),
        CategoryItem("گیاهان اپارتمانی", R.drawable.apartment_plant, "APP"),
        CategoryItem("گل های باغچه ای", R.drawable.garden_flowers, "YF"),
        CategoryItem("گیاهان باغچه ای", R.drawable.gardening, "YP"),
        CategoryItem("گل های تزئینی", R.drawable.decorative_flowers, "BF"),

        )
    val context = LocalContext.current

    LazyRow(

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(22.dp),
        reverseLayout = true
    ) {
        items(categoryItems.size) { index ->
            val item = categoryItems[index]

            if (!isHome){
                Card(
                    modifier = Modifier
                        .width(88.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(2.dp),
                    border = if (state == index) BorderStroke(1.dp, color = Color(0xFF007206)) else BorderStroke(0.dp, color = Color.Transparent),
                    colors = CardDefaults.cardColors(Color.White),
                    onClick = {
                        when (index) {
                            0 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state=0
                            }

                            1 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state=1
                            }

                            2 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state=2
                            }
                            3 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state=3
                            }
                            4 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state=4
                            }
                            5 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state=5
                            }
                        }

                    }
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {


                        Image(
                            painter = painterResource(item.icon),
                            contentDescription = "todo",
                            modifier = Modifier.size(47.dp),
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            item.name,
                            fontSize = fontSize,
                            fontWeight = FontWeight.Bold
                        )


                    }


                }

            }
            else{
                Card(
                    modifier = Modifier
                        .width(88.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(2.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    onClick = {
                        when (index) {
                            0 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state=0
                            }

                            1 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state=1
                            }

                            2 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state=2
                            }
                            3 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state=3
                            }
                            4 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state=4
                            }
                            5 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state=5
                            }
                        }

                    }
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {


                        Image(
                            painter = painterResource(item.icon),
                            contentDescription = "todo",
                            modifier = Modifier.size(47.dp),
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            item.name,
                            fontSize = fontSize,
                            fontWeight = FontWeight.Bold
                        )


                    }


                }

            }

        }
    }
}





data class CategoryItem(val name: String, val icon: Int, val route: String)


@Composable
fun MainHomeListItem(listOfPlant: List<Product>? = null) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 10.dp)


    )
    {

        HeightSpacer(10.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp, start = 10.dp)
                .height(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        )
        {
            Row(
                modifier = Modifier
                    .padding(5.dp, top = 8.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { },
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Icon(
                    modifier = Modifier.size(14.dp),
                    painter = painterResource(R.drawable.back_ios),
                    contentDescription = null,
                    tint = Color(0xFF0B5B3F)
                )
                Text(
                    text = "بیشتر",
                    color = Color(0xFF0B5B3F),
                    fontSize = 15.sp
                )
            }
            Text(
                modifier = Modifier.padding(top = 5.dp, end = 13.dp),
                text = "error to receiving name",
                style = TextStyle(fontSize = 16.sp, color = Color.Black),
                fontWeight = FontWeight.Bold
            )

        }

        val list2 = listOf(
            Product(
                id = 1,
                name = "gol",
                description = "ye gol",
                price = "1000000",
                stock = 11,
                discountAfterPrice = "900000",
                discount = "10",
                category = "gols",
                imageUrls = listOf("https://picsum.photos/200"),
                rating = 5.0,
                availability = "mojod"
            ),
            Product(
                id = 1,
                name = "gol",
                description = "ye gol",
                price = "1000000",
                stock = 11,
                discountAfterPrice = "900000",
                discount = "10",
                category = "gols",
                imageUrls = listOf("https://picsum.photos/200"),
                rating = 5.0,
                availability = "mojod"
            ),
            Product(
                id = 1,
                name = "gol",
                description = "ye gol",
                price = "100000",
                discountAfterPrice = "900000",
                discount = "10",
                stock = 11,
                category = "gols",
                imageUrls = listOf("https://picsum.photos/200"),
                rating = 5.0,
                availability = "mojod"
            )


        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp),
            reverseLayout = true,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(end = 22.dp, start = 22.dp)
        ) {


            items(listOfPlant ?: list2) { item: Product ->
                Items_fav(item)
            }


        }

        HeightSpacer(10.dp)


    }


}

@Composable
fun SubCategoryLists(name: String, listOfProduct: List<Product>? = null) {
    Text(
        modifier = Modifier.padding(top = 5.dp, end = 30.dp, bottom = 10.dp).fillMaxWidth(),
        text = name,
        style = TextStyle(fontSize = 19.sp, color = Color.Black, textDirection = TextDirection.Rtl),
        fontWeight = FontWeight.Bold
    )


    val list2 = listOf(
        Product(
            id = 1,
            name = "gol",
            description = "ye gol",
            price = "1000000",
            stock = 11,
            discountAfterPrice = "900000",
            discount = "10",
            category = "gols",
            imageUrls = listOf("https://picsum.photos/200"),
            rating = 5.0,
            availability = "mojod"
        ),
        Product(
            id = 1,
            name = "gol",
            description = "ye gol",
            price = "1000000",
            stock = 11,
            discountAfterPrice = "900000",
            discount = "10",
            category = "gols",
            imageUrls = listOf("https://picsum.photos/200"),
            rating = 5.0,
            availability = "mojod"
        ),
        Product(
            id = 1,
            name = "gol",
            description = "ye gol",
            price = "100000",
            discountAfterPrice = "900000",
            discount = "10",
            stock = 11,
            category = "gols",
            imageUrls = listOf("https://picsum.photos/200"),
            rating = 5.0,
            availability = "mojod"
        )


    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 1000.dp), // یا wrapContentHeight() اگه مطمئنی حجم خیلی زیاد نیست
        contentPadding = PaddingValues(5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        userScrollEnabled = false // ❗ اسکرول شدن رو می‌سپریم به LazyColumn والد
    ) {


        if (listOfProduct != null) {
            items(listOfProduct) { item ->
                Items_fav(item)
            }
        } else {
            items(list2) { item ->
                Items_fav(item)
            }
        }


    }

}


@Composable
fun Items_fav(
    product: Product
) {

    val context = LocalContext.current
    val prefs = context.getSharedPreferences("MyPrefs",MODE_PRIVATE)

    Box(
        Modifier
            .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }){
                val intent = Intent(context, SecondActivity::class.java).apply {
                    putExtra(
                        "whichPageIs", "ProductBody"
                    ) // ارسال نام کاربر
                }
                context.startActivity(intent)
            },
        contentAlignment = Alignment.Center,
    ) {
        Card(
            Modifier
                .width(220.dp)
                .height(250.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color(0xFFF6F6F6)),
            border = BorderStroke(width = 3.dp, color = Color(0xFFFFFFFF))
        ) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = product.imageUrls[0],
                    contentDescription = "todo",
                    Modifier
                        .height(135.dp)
                        .fillMaxWidth(0.6f),

                )
                Spacer(Modifier.height(6.dp))
                Card(
                    Modifier
                        .fillMaxWidth(0.9f)
                        .height(85.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(3.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                product.name,
                                modifier = Modifier.padding(6.dp),
                                fontSize = 13.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF333333),
                                textAlign = TextAlign.Right
                            )
                            Spacer(Modifier.height(2.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,

                                modifier = Modifier.padding(end = 8.dp, top = 18.dp)
                            ) {
                                Text("تومان")
                                Spacer(Modifier.width(2.dp))
                                Text(
                                    "%,d".format((product.price).toInt()),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(500)
                                )
                                Spacer(Modifier.width(8.dp))

                                if (product.discount != null)
                                    Text(
                                        "%,d".format((product.discountAfterPrice)?.toInt()),
                                        fontSize = 10.sp,
                                        style = TextStyle(textDecoration = TextDecoration.LineThrough),
                                        color = Color.Gray,
                                    )
                            }
                        }

                    }


                }
            }


        }
        var isVisible by remember {
            mutableStateOf(false)
        }

        val scale by animateFloatAsState(
            targetValue = if (isVisible) 1.2f else 1f, // تغییر مقیاس بین 1 و 1.2
            animationSpec = tween(durationMillis = 300) // زمان انیمیشن
        )

        IconButton(
            {
                isVisible = !isVisible
            },
            modifier = Modifier
                .padding(bottom = 45.dp, start = 8.dp)
                .align(Alignment.BottomStart)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF1EEF21),
                            Color(0xFF038107)
                        )
                    ), shape = RoundedCornerShape(100.dp)
                )
                .size(32.dp)
                .clip(
                    RoundedCornerShape(100.dp)
                ),

            ) {
            Icon(
                imageVector = if (isVisible)  Icons.Default.Clear else Icons.Default.Add,
                contentDescription = "Favorite",
                tint = Color.White,
                modifier = Modifier.scale(scale)
            )
        }

        AnimatedVisibility(

            visible = isVisible,
            enter = scaleIn(
                tween(500),
                transformOrigin = TransformOrigin(0.5f, 0.5f)
            ),
            exit = scaleOut(
                tween(500),
                transformOrigin = TransformOrigin(0.5f, 0.5f)
            )
        ) {

            var count by remember {
                mutableIntStateOf(1)
            }
            Column {
                Card(
                    modifier = Modifier.size(150.dp, 80.dp),
                    border = BorderStroke(width = 3.dp, color = Color(0xFFFFFFFF))
                ) {
                    Column(
                        Modifier.fillMaxSize().background(color = Color(0xFFF6F6F6))
                        ,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            IconButton(
                                {
                                    if (count > 1)
                                        count--
                                },
                                modifier = Modifier
                                    .background(
                                        Color.White,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .size(29.dp)
                                    .clip(
                                        RoundedCornerShape(16.dp)
                                    )
                            ) {
                                Icon(
                                    Icons.Default.KeyboardArrowDown,
                                    contentDescription = "todo",
                                )
                            }
                            Spacer(Modifier.width(8.dp))

                            Text(count.toString())

                            Spacer(Modifier.width(8.dp))
                            IconButton(
                                {
                                    count++
                                },
                                modifier = Modifier
                                    .background(
                                        Color.White,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .size(29.dp)
                                    .clip(
                                        RoundedCornerShape(16.dp)
                                    )
                            ) {
                                Icon(
                                    Icons.Filled.KeyboardArrowUp,
                                    contentDescription = "todo",
                                )
                            }
                        }
                        Spacer(Modifier.height(8.dp))

                        var showDialog by remember {
                            mutableStateOf(false)
                        }

                        GreenButton(
                            text = "نهایی کردن خرید",
                            modifier = Modifier.size(140.dp, 35.dp),
                            fontSize = 14.sp

                        ) {
                            showDialog = true
                            prefs.edit {
                                putString("productCount",count.toString())
                                putString("productName",product.name)
                                putString("productImage",product.imageUrls[0])
                                putString("productPrice",product.price)
                                putString("productPriceDiscount",product.discountAfterPrice)
                            }

                        }

                        if (showDialog) {
                            FinalBuyDialog(productName = product.name, numberOfBuy = count.toString(), onClose = { showDialog=false })
                        }
                    }
                }
            }

        }

        if (product.discount != null)
            Text(
                product.discount,
                Modifier
                    .align(alignment = Alignment.TopEnd)
                    .padding(top = 20.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFF05C909),
                                Color(0xFF038107)
                            )
                        ), shape = RoundedCornerShape(topStart = 22.dp, bottomStart = 22.dp)
                    )
                    .size(32.dp, 18.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
            )

        if (product.isFav != null)
            Column(
                Modifier.padding(bottom = 220.dp, end = 150.dp)
            ) {

                Icon(

                    Icons.Filled.Favorite,
                    contentDescription = "todo",
                    tint = Color.Gray

                )

            }


    }


}


@Composable
fun SearchHistoryBox(text: String) {
    Box(
        Modifier
            .height(30.dp)
            .width(IntrinsicSize.Max)
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, Color(0xFFF2FCF2), RoundedCornerShape(16.dp))
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}


@Composable
fun SearchPage(){
    val searchHistory = listOf("گل","گل رز","افتابگردان","کاکتوس","گیاه","درخت","درختچه","خاک")

    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ){
            Text(
                text = "تاریخچه جستجو های اخیر",
                modifier = Modifier.padding(end = 8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                Icons.Default.List,
                contentDescription = "ArrowBack"
            )
        }
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items(searchHistory.size){
                    SearchHistoryBox(searchHistory[it])
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )

    }

}

@Composable
fun SearchTopAppBar(context: Context){
    var value by remember { mutableStateOf("") }
    Row(
        modifier = Modifier.padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        WidthSpacer(10.dp)
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back_24),
            contentDescription = "ArrowBack",
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ){
                (context as? Activity)?.finish()
            }
        )
        WidthSpacer(10.dp)
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            TextField(
                value = value,
                onValueChange = {
                    value = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.98f),
                placeholder = {
                    Text("جستجو در بین همه کالاها....")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "آیکون جست‌وجو"
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFE8F5E9),
                    unfocusedContainerColor = Color(0xFFE8F5E9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color(0xFF43A047),
                    focusedTextColor = Color(0xFF2E7D32),
                    unfocusedTextColor = Color(0xFF2E7D32),
                    focusedPlaceholderColor = Color(0xFF81C784),
                    unfocusedPlaceholderColor = Color(0xFF81C784),
                    focusedLeadingIconColor = Color(0xFF66BB6A),
                    unfocusedLeadingIconColor = Color(0xFF66BB6A)
                )
            )

        }
        WidthSpacer(10.dp)



    }
}


@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_7_PRO)
@Composable
fun HomePreview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
            color = Color.Red
        )

    ) {
        HeightSpacer(300.dp)
        Items_fav(Product(
            id = 1,
            name = "gol",
            description = "ye gol",
            price = "1000000",
            stock = 11,
            discountAfterPrice = "900000",
            discount = "10",
            category = "gols",
            imageUrls = listOf("https://picsum.photos/200"),
            rating = 5.0,
            availability = "mojod"
        ))
        HeightSpacer(300.dp)
    }

}