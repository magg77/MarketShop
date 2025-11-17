package com.maggiver.marketshop.favorites.data.provider.local.serviceLocal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maggiver.marketshop.favorites.data.provider.local.entitiy.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalServiceProductDAO {

    //@Query("SELECT COUNT(*) FROM productEntity WHERE id = :id")
    @Query("SELECT EXISTS(SELECT 1 FROM productEntity WHERE id = :id)")
    fun searchFavoriteProductId(id: Int): Flow<Int>

    @Query("SELECT * FROM productEntity WHERE id = :id LIMIT 1")
    suspend fun getProductFavorite(id: Int): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Delete
    suspend fun deleteFavorite(productEntity: ProductEntity)


}