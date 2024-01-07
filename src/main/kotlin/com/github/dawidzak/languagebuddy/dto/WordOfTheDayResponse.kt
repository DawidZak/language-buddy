package com.github.dawidzak.languagebuddy.dto

data class WordOfTheDayResponse(val word: String, val details: Array<WordDetails>) {}