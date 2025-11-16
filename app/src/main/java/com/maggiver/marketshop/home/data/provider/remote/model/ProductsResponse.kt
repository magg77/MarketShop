package com.maggiver.marketshop.home.data.provider.remote.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName


@Parcelize
data class ProductsResponse(
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("products")
    val products: List<Product> = listOf(),
    @SerializedName("skip")
    val skip: Int? = null,
    @SerializedName("total")
    val total: Int? = null
): Parcelable

@Parcelize
data class Product(
    @SerializedName("availabilityStatus")
    val availabilityStatus: String? = null,
    @SerializedName("brand")
    val brand: String? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("dimensions")
    val dimensions: Dimensions? = null,
    @SerializedName("discountPercentage")
    val discountPercentage: Double? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("images")
    val images: List<String?>? = null,
    @SerializedName("meta")
    val meta: Meta? = null,
    @SerializedName("minimumOrderQuantity")
    val minimumOrderQuantity: Int? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("rating")
    val rating: Double? = null,
    @SerializedName("returnPolicy")
    val returnPolicy: String? = null,
    @SerializedName("reviews")
    val reviews: List<Review?>? = null,
    @SerializedName("shippingInformation")
    val shippingInformation: String? = null,
    @SerializedName("sku")
    val sku: String? = null,
    @SerializedName("stock")
    val stock: Int? = null,
    @SerializedName("tags")
    val tags: List<String?>? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("warrantyInformation")
    val warrantyInformation: String? = null,
    @SerializedName("weight")
    val weight: Int? = null
): Parcelable

@Parcelize
data class Dimensions(
    @SerializedName("depth")
    val depth: Double? = null,
    @SerializedName("height")
    val height: Double? = null,
    @SerializedName("width")
    val width: Double? = null
): Parcelable

@Parcelize
data class Meta(
    @SerializedName("barcode")
    val barcode: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("qrCode")
    val qrCode: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
): Parcelable

@Parcelize
data class Review(
    @SerializedName("comment")
    val comment: String? = null,
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("rating")
    val rating: Int? = null,
    @SerializedName("reviewerEmail")
    val reviewerEmail: String? = null,
    @SerializedName("reviewerName")
    val reviewerName: String? = null
): Parcelable


