package com.github.dawidzak.languagebuddy.ui

import com.github.dawidzak.languagebuddy.services.WordService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ui.Messages

class WordOfTheDayAction : AnAction() {


    override fun actionPerformed(event: AnActionEvent) {
        val app = ApplicationManager.getApplication()
        app.executeOnPooledThread {
            val service = app.getService(WordService::class.java)
            val response = service.getWordOfTheDay()
            app.invokeLater {
                Messages.showMessageDialog(
                    event.project,
                    service.toHtmlContent(response),
                    service.toTitle(response),
                    Messages.getInformationIcon()
                )

            }
        }
    }
}