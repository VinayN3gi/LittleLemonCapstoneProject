package com.example.littlelemmon2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase


@Entity(tableName = "menu_table")
data class MenuItemRoom (
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String,
)

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM menu_table")
    fun getAll() : LiveData<List<MenuItemRoom>>

    @Insert
    fun insertAll(vararg menuItem: MenuItemRoom)

    @Query("SELECT (SELECT COUNT(*) FROM menu_table) = 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItemRoom::class], version = 1)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
}
