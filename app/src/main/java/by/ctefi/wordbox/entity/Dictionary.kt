package by.ctefi.wordbox.entity

data class Dictionary(
    val description: String = "New dictionary",
    val name: String,
    val wordsAmount: Int = 0,
    val wordsList: ArrayList<Word> = ArrayList()
)