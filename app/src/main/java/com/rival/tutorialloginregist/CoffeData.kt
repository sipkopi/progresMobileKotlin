package com.rival.tutorialloginregist



class CoffeData(private val coffeName: String, private val cofeDate: String, private val coffeImage: Int) {
    fun getMovieName(): String {
        return coffeName
    }

    fun getMovieDate(): String {
        return cofeDate
    }

    fun getMovieImage(): Int {
        return coffeImage
    }
}
