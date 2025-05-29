package com.example.competitionapp.ui.dataModel

import org.xml.sax.Attributes

data class Product(
    val id: Int, // شناسه محصول
    val name: String, // نام محصول
    val description: String, // توضیحات محصول
    val price: String, // قیمت محصول
    val discount: String? = null, // مقدار تخفیف (اختیاری)
    val discountAfterPrice: String? = null, // مقدار تخفیف (اختیاری)
    val stock: Int, // موجودی محصول
    val category: String, // دسته‌بندی محصول (مثلاً رز، ارکیده)
    val imageUrls: List<String>, // لیست آدرس‌های تصاویر محصول
    val rating: Double, // امتیاز محصول
    val availability: String ,// وضعیت موجودی (موجود، ناموجود و ...)
    val isFav: Double ?=null,
    val attributes: List<String>? = null
)

