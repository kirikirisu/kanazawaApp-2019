import android.provider.BaseColumns

object DBContract {
    object PreservedFoodEntry: BaseColumns {
        const val TABLE_NAME = "PreservedFoodTable"
        const val FOOD_NAME = "foodName"
        const val DEADLINE = "deadline"
        const val STORAGE_LOCATION = "storageLocation"
        const val QUANTITY = "quantity"
        const val CREATED_AT = "createdAt"
        const val COMMERICAL = "commercial"
    }

//    object ShoppingListEntyty: BaseColumns {
//        const val TABLE_NAME = "ShoppingListTable"
//        const val FOOD_NAME = "foodName"
//        const val CREATED_AT = "createdAt"
//    }
}