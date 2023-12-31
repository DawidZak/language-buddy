package com.github.dawidzak.languagebuddy.ui

import com.github.dawidzak.languagebuddy.services.RecallModel
import com.github.dawidzak.languagebuddy.services.WordHttpClient
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager

class RecallWordOfTheDayAction : AnAction() {
//TODO persistent state
    override fun actionPerformed(e: AnActionEvent) {
        val app = ApplicationManager.getApplication()
        app.executeOnPooledThread {
            val response = app.getService(WordHttpClient::class.java).get()
            app.invokeLater {
                val wordOfTheDay = response.words.asList().random()
                val recallModel = RecallModel(wordOfTheDay.word, wordOfTheDay.definition)
                RecallDialogWrapper(e.project, recallModel).show()
            }
        }
    }


}