package com.github.dawidzak.languagebuddy.dto

data class WordOfTheDayResponse(val words: Array<WordOfTheDay>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WordOfTheDayResponse

        if (!words.contentEquals(other.words)) return false

        return true
    }

    override fun hashCode(): Int {
        return words.contentHashCode()
    }

}