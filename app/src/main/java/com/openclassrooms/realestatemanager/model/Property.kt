package com.openclassrooms.realestatemanager.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "property_table")
data class Property(
        val type: String = "HOUSE",
        val price: Int,
        val area: Float,
        @ColumnInfo(name = "room_number") val roomNbr: Int,
        val description: String,
        @Embedded val address: Address, //This will store all Address fields in the same table
        @ColumnInfo(name = "creation_time") var creationTime: Long,
        val agent: String,
        @ColumnInfo(name = "is_sold") var isSold: Boolean = false,
        @ColumnInfo(name = "selling_time") var sellingTime: Long = 0,
        @ColumnInfo(name = "thumbnail_uri") var thumbnailUri: String =
                "android.resource://com.openclassrooms.realestatemanager/drawable/default_house") {

    @PrimaryKey(autoGenerate = true) var id: Int = 0  //Let room handle the Id
}