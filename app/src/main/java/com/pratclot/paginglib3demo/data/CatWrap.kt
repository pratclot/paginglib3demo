package com.pratclot.paginglib3demo.data

data class CatWrap(
    val catImage: CatImage,
    val onClick: () -> Unit
) {
    companion object {
        fun from(catImage: CatImage, onClick: () -> Unit): CatWrap {
            return CatWrap(
                catImage,
                onClick
            )
        }
    }
}