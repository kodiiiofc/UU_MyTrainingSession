package com.kodiiiofc.urbanuniversity.mytrainingsession

data class Exercise(
    val name: String,
    val description: String,
    val durationInSeconds: Int,
    val gifImage: Int
) {
    override fun toString(): String {
        return name
    }
}

