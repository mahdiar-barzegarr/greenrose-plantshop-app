package com.example.competitionapp.ui.mainFunsUI

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.competitionapp.ui.dataModel.Product
import com.example.competitionapp.ui.extFuns.AboutUs
import com.example.competitionapp.ui.extFuns.SystemView
import com.example.competitionapp.ui.extFuns.TopAppBarViewSecondScreen
import com.example.competitionapp.ui.mainFunsUI.mainPages.FavouritePageInit
import com.example.competitionapp.ui.mainFunsUI.mainPages.NotificationPageInit
import com.example.competitionapp.ui.mainFunsUI.mainPages.OrderPageInit
import com.example.competitionapp.ui.mainFunsUI.mainPages.ProductBody
import com.example.competitionapp.ui.mainFunsUI.mainPages.ProfileAddressPageInit
import com.example.competitionapp.ui.mainFunsUI.mainPages.ProfileEditPageInit
import com.example.competitionapp.ui.mainFunsUI.mainPages.SearchPage
import com.example.competitionapp.ui.mainFunsUI.mainPages.SubCategoryInit

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondScreenView()
            SystemView()


        }
    }

    @Composable
    fun SecondScreenView() {

        val whichPageIs = intent.getStringExtra("whichPageIs")
        var name by remember { mutableStateOf("") }


        Scaffold(
            modifier = Modifier
                .systemBarsPadding(),
            topBar = { TopAppBarViewSecondScreen(name, this@SecondActivity) },
        ) {
            Column(
                modifier = Modifier.padding(it),
            ) {


                when (whichPageIs) {
                    "EditProfilePage" -> {
                        ProfileEditPageInit(this@SecondActivity)
                        name = "ویرایش اطلاعات "
                    }

                    "AddressProfilePage" -> {
                        ProfileAddressPageInit(this@SecondActivity)
                        name = "آدرس ها "
                    }

                    "FavouriteProfilePage" -> {
                        FavouritePageInit(this@SecondActivity)
                        name = "علاقه مندی ها "
                    }

                    "OrderProfilePage" -> {
                        OrderPageInit(this@SecondActivity)
                        name = "سفارشات "
                    }
                    "NotificationPage"-> {
                        NotificationPageInit()
                        name = "اعلانات "
                    }
                    "ProductBody" -> {
                        ProductBody(
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
                                availability = "mojod",
                                attributes = listOf("abi","ghermez","gray")
                            )
                        )
                        name = "محصول"
                    }

                    "APF" -> {
                        SubCategoryInit("گل های اپارتمانی")
                        name = "گل های اپارتمانی "
                    }

                    "CF" -> {
                        SubCategoryInit("کاکتوس")
                        name = "کاکتوس"
                    }

                    "APP" -> {
                        SubCategoryInit("گیاهان اپارتمانی")
                        name = "گیاهان اپارتمانی"
                    }

                    "YF" -> {
                        SubCategoryInit("گل های باغچه ای")
                        name = "گل های باغچه ای"
                    }

                    "YP" -> {
                        SubCategoryInit("گیاهان باغچه ای")
                        name = "گیاهان باغچه ای "
                    }

                    "BF" -> {
                        SubCategoryInit("گل های تزئینی")
                        name = "گل های تزئینی "
                    }

                    "AboutUs"->{
                        AboutUs()
                        name = "درباره ما"
                    }

                }
            }
        }
    }
}


