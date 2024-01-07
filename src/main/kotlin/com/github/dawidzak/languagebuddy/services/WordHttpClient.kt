package com.github.dawidzak.languagebuddy.services

import com.github.dawidzak.languagebuddy.dto.WordOfTheDayResponse
import com.google.gson.GsonBuilder
import com.intellij.openapi.components.Service
import com.intellij.util.io.HttpRequests


@Service
class WordHttpClient {
    private val SERVER_ADDRESS = "https://langauge-buddy.com/words/daily"

    fun getWordOfTheDay(): WordOfTheDayResponse {
        return HttpRequests.request(SERVER_ADDRESS).tuner {
        }.useProxy(true).connect {
            val gson = GsonBuilder().create()
            val message = try {
                val fromJson: WordOfTheDayResponse = gson.fromJson(it.readString(), WordOfTheDayResponse::class.java)
                fromJson
            } catch (e: Exception) {
                WordOfTheDayResponse("",arrayOf())
            }
            message
        }
    }
}