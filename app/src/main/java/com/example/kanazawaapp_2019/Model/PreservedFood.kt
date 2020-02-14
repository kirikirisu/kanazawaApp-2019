package com.example.kanazawaapp_2019.Model

class PreservedFood (name: String = "", deadline: String = "" , location: String = "", typeId: Int = 1, quantity: Int = 1,
                     initialQuantity: Int = 1, imagePath: String = "", isHandMade: Boolean = false, createdAt: String = ""){
    var name: String = ""
    var deadline: String = ""
    var location: String = ""
    var typeId: Int = 1
    var quantity: Int = 1
    var initialQuantity: Int = 1
    var imagePath: String = ""
    var isHandMade: Boolean = false
    var createdAt: String = ""
    init {
        this.name = name
        this.deadline = deadline
        this.location = location
        this.typeId = typeId
        this.quantity = quantity
        this.initialQuantity = initialQuantity
        this.imagePath = imagePath
        this.isHandMade = isHandMade
        this.createdAt = createdAt
    }
}