package com.example.competitionapp.ui.mainFunsUI.mainPages

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.example.competitionapp.R
import com.example.competitionapp.ui.dataModel.Product
import com.example.competitionapp.ui.extFuns.BasketItem
import com.example.competitionapp.ui.extFuns.HeightSpacer
import com.example.competitionapp.ui.extFuns.ImageSlider
import com.example.competitionapp.ui.mainFunsUI.SecondActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMainPageInit() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAFCEA))
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        ImageSlider(
            listOf(
                "https://picsum.photos/200",
                "https://picsum.photos/200",
                "https://picsum.photos/id/237/300/200", // تصویر نمونه 1
                "https://picsum.photos/id/238/300/200", // تصویر نمونه 2
                "https://picsum.photos/id/239/300/200"
            )
        )

        HeightSpacer(20.dp)


        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 30.dp, start = 30.dp),
            text = "تنها با یک کلیک خرید کن!",
            style = TextStyle(textDirection = TextDirection.Rtl),
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        HeightSpacer(20.dp)


        SearchBox()


        HeightSpacer(10.dp)


        HomeSmallCategory(isHome = true)

        HeightSpacer(10.dp)

        MainHomeListItem()


    }

}

@Composable
fun SubCategoryInit(categoryName: String) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAFCEA)),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        item {
            HomeSmallCategory()
            HeightSpacer(10.dp)
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 30.dp, start = 30.dp),
                text = categoryName,
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            HeightSpacer(20.dp)
        }

        item {
            SearchBox()
            HeightSpacer(10.dp)
        }

        item {
            SubCategoryLists("cactos")
        }
    }


}

@Composable
fun CategoryInit() {

    val context = LocalContext.current
    val prefs = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val int = prefs.getInt("catState", 0)


    var state by remember { mutableIntStateOf(int) }



    prefs.edit { putInt("catState", state) }

    val configuration = LocalConfiguration.current
    val boxWidth = configuration.screenWidthDp



    val fontSize = when {
        boxWidth < 360-> 9.sp
        boxWidth < 400 -> 12.sp
        else -> 13.sp
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAFCEA)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        ImageSlider(
            listOf(
                "https://picsum.photos/200",
                "https://picsum.photos/200",
                "https://picsum.photos/id/237/300/200", // تصویر نمونه 1
                "https://picsum.photos/id/238/300/200", // تصویر نمونه 2
                "https://picsum.photos/id/239/300/200"
            )
        )

        HeightSpacer(20.dp)


        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 30.dp, start = 30.dp),
            text = "دسته بندی ها",
            style = TextStyle(textDirection = TextDirection.Rtl),
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        HeightSpacer(15.dp)

        val categoryItems = listOf(
            CategoryItem("گل های اپارتمانی", R.drawable.apartement_flower, "APF"),
            CategoryItem("کاکتوس", R.drawable.cactus2, "CF"),
            CategoryItem("گیاهان اپارتمانی", R.drawable.apartment_plant, "APP"),
            CategoryItem("گل های باغچه ای", R.drawable.garden_flowers, "YF"),
            CategoryItem("گیاهان باغچه ای", R.drawable.gardening, "YP"),
            CategoryItem("گل های تزئینی", R.drawable.decorative_flowers, "BF"),

            )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            Modifier
                .fillMaxSize()
                .padding(12.dp)
                ,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(8.dp),
            userScrollEnabled = false
        ) {
            items(categoryItems.size) { index ->
                val item = categoryItems[index]

                Card(
                    modifier = Modifier
                        .width(97.dp)
                        .height(110.dp),
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
                                state = 0
                            }

                            1 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state = 1
                            }

                            2 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state = 2
                            }

                            3 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state = 3
                            }

                            4 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state = 4
                            }

                            5 -> {
                                val intent = Intent(context, SecondActivity::class.java).apply {
                                    putExtra(
                                        "whichPageIs", item.route
                                    ) // ارسال نام کاربر
                                }
                                context.startActivity(intent)
                                state = 5
                            }
                        }
                    }//you can definition Intent for going to another page in this app
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {


                        Image(
                            painter = painterResource(item.icon),
                            contentDescription = "todo",
                            modifier = Modifier.size(47.dp),
                        )

                        Spacer(Modifier.height(10.dp))

                        Text(
                            item.name,
                            fontSize = fontSize
                        )


                    }


                }


            }
        }

    }

}

@Composable
fun ProductBody(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFEAFCEA))
            .padding(top = 0.dp, end = 20.dp, start = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HeightSpacer(35.dp)
        TopItemsScreen(
            product.imageUrls,
            product.discount != null,
            product.discount.toString(),
            product.rating.toString()
        )
        BottomItemsScreen(
            product.name,
            product.description,
            product.attributes?.size ?: 1,
            product.attributes ?: listOf("", "")
        )
        HeightSpacer(35.dp)


    }
}


@Composable
fun BasketMainPageInit() {

    val context = LocalContext.current
    val prefs = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)


    var productName by remember { mutableStateOf(prefs.getString("productName", null)) }
    var productCount by remember { mutableStateOf(prefs.getString("productCount", null))}
    var productPrice by remember { mutableStateOf(prefs.getString("productPrice", null))}
    var productPriceDiscount by remember { mutableStateOf(prefs.getString("productPriceDiscount", null))}
    var productImage by remember { mutableStateOf(prefs.getString("productImage", null))}


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2FCF2)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .padding(30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                text = "سبد خرید",
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            HeightSpacer(30.dp)

            if (
                productName == null &&
                productCount == null &&
                productPrice == null &&
                productImage == null
            ) {
                EmptyPage("محصولی در سبد خرید")
            } else {
                BasketItem(
                    productName ?: "",
                    productCount ?: "",
                    productPrice ?: "",
                    productPriceDiscount ?: "",
                    productImage ?: ""
                ) {
                    prefs.edit {
                        putString("productCount",null)
                        putString("productName",null)
                        putString("productImage",null)
                        putString("productPrice",null)
                        putString("productPriceDiscount",null)
                    }
                    productName =prefs.getString("productName", null)
                    productCount =prefs.getString("productCount", null)
                    productPrice = prefs.getString("productPrice", null)
                    productPriceDiscount =prefs.getString("productPriceDiscount", null)
                    productImage =prefs.getString("productImage", null)
                }
            }


        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 30.dp, start = 30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            if (
                productCount != null &&
                productPrice != null
            ) {
                val totalPrice = productPrice.toString().toInt() * productCount.toString().toInt()
                val discount =
                    if (productPriceDiscount != null) (productPriceDiscount.toString().toInt() * productCount.toString().toInt()) else null
                val finalPrice =
                    if (productPriceDiscount != null) totalPrice - (productPriceDiscount.toString().toInt() * productCount.toString().toInt()) else totalPrice

                FinalBuyPageBottom(
                    totalPrice = totalPrice.toString(),
                    discount = discount.toString(),
                    finalPrice = finalPrice.toString()
                )
            }
        }


    }

}





