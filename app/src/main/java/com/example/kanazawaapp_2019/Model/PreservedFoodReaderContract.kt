package com.example.kanazawaapp_2019.Model

import android.provider.BaseColumns

object PreservedFoodReaderContract {
    // Table contents are grouped together in an anonymous object.
    object PreservedFoodEntry : BaseColumns {
        const val TABLE_NAME = "PreservedFood"
        const val NAME ="name"
        const val DEADLINE = "deadline"
        const val LOCATION = "location"
        const val TYPE_ID =  "typeId"
        const val QUANTITY = "quantity"
        const val INITIAL_QUANTITY = "initialQuantity"
        const val IMAGE_PATH = "imagePath"
        const val IS_HANDMAID = "isHandMaid"
        const val CREATED_AT = "createdAt"
    }

    object FoodTypeEntry : BaseColumns {
        const val TABLE_NAME = "FoodType"
        const val NAME = "name"
    }
}