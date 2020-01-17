package com.example.kanazawaapp_2019

import android.graphics.drawable.Drawable

class ShoppingItem {
    var item : String = ""
    var count : String = ""

    constructor() {}

    constructor(item: String, count: String) {
        this.item = item
        this.count = count
    }
}