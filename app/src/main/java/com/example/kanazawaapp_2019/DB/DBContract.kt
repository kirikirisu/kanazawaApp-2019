import android.provider.BaseColumns

object DBContract {
    // 保存食一覧's table
    object PreservedFoodEntry: BaseColumns {
        const val TABLE_NAME = "PreservedFoodTable"
        const val FOOD_NAME = "foodName"
        const val DEADLINE = "deadline"
        const val STORAGE_LOCATION = "storageLocation"
        const val QUANTITY = "quantity"
        const val CREATED_AT = "createdAt"
        const val COMMERICAL = "commercial"
        const val FOOD_TYPE = "foodType"
    }

    // 買い物リスト's table
    object ShoppingListEntyty: BaseColumns {
        const val TABLE_NAME = "ShoppingListTable"
        const val FOOD_NAME = "foodName"
        const val CREATED_AT = "createdAt"
        const val USED = "used"
    }
}