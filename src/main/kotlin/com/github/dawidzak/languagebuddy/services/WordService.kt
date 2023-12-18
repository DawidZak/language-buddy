package com.github.dawidzak.languagebuddy.services

import com.github.dawidzak.languagebuddy.dto.WordOfTheDayResponse
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.util.containers.stream
import java.util.stream.Collectors


@Service
class WordService {
    fun get():WordOfTheDayResponse {
        val service = ApplicationManager.getApplication().service<WordHttpClient>()
        val executeOnPooledThread = ApplicationManager.getApplication().executeOnPooledThread { service.get() }
        executeOnPooledThread.isDone
        return service.get();

    }

    fun toHtmlContent(wordOfTheDayResponse: WordOfTheDayResponse): String {
        return wordOfTheDayResponse.words.stream().map { wordOfTheDay ->
            val examples = wordOfTheDay.examples?.joinToString(separator = "") { "<li>$it</li>" }
            "<h3 style=\"all: initial;\">Definition:</h3><i><p>${wordOfTheDay.definition}</p></i><h3>Examples:</h3><ol>${examples}</ol>"
        }.collect(Collectors.joining())
    }

    fun toTitle(wordOfTheDayResponse: WordOfTheDayResponse): String {
        return "Word Of The Day: ${wordOfTheDayResponse.words.map { it.word }.first()}"
    }
}