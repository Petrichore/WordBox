package by.ctefi.wordbox.entity

data class Word(
    val original: String,
    val translation: String,
    val meaning: String = ""
)