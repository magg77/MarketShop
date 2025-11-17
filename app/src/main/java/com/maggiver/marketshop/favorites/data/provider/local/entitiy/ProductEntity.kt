package com.maggiver.marketshop.favorites.data.provider.local.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.maggiver.marketshop.home.data.provider.remote.model.ProductDetailResponse

@Entity(tableName = "productEntity")
@TypeConverters(ImagesConverter::class)
data class ProductEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    @ColumnInfo val title: String?,
    @ColumnInfo val description: String?,
    @ColumnInfo val price: Double?,
    @ColumnInfo val rating: Double?,
    @ColumnInfo val thumbnail: String?,
    @ColumnInfo val images: List<String?>?
)

fun ProductEntity.toProductDetail(): ProductDetailResponse {
    return ProductDetailResponse(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        rating = this.rating,
        thumbnail = this.thumbnail,
        images = this.images
    )
}

fun ProductDetailResponse.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        description = this.description,
        price = this.price,
        rating = this.rating,
        title = this.title,
        thumbnail = this.thumbnail,
        images = this.images ?: emptyList()
    )
}
