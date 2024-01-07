package com.github.dawidzak.languagebuddy.services

import com.github.dawidzak.languagebuddy.dto.WordOfTheDayResponse
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.util.containers.stream
import java.util.stream.Collectors


@Service
class WordService {

    fun getWordOfTheDay(): WordOfTheDayResponse {
        val service = ApplicationManager.getApplication().getService(WordHttpClient::class.java)
        val executeOnPooledThread = ApplicationManager.getApplication().executeOnPooledThread { service.getWordOfTheDay() }
        executeOnPooledThread.isDone
        return service.getWordOfTheDay();

    }

    fun toHtmlContent(wordOfTheDayResponseDeprecated: WordOfTheDayResponse): String {
        return wordOfTheDayResponseDeprecated.details.stream().map { wordOfTheDay ->
            val examples = wordOfTheDay.examples.joinToString(separator = "") { "<li>$it</li>" }
            "<h3 style=\"all: initial;\">Definition:</h3><i><p>${wordOfTheDay.meaning}</p></i><h3>Examples:</h3><ol>${examples}</ol>"
          }.collect(Collectors.joining())
    }

    fun toTitle(wordOfTheDayResponseDeprecated: WordOfTheDayResponse): String {
        return "Word Of The Day: ${wordOfTheDayResponseDeprecated.word}"
    }
}