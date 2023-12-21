package com.github.dawidzak.languagebuddy.ui

import com.github.dawidzak.languagebuddy.services.WordHttpClient
import com.github.dawidzak.languagebuddy.services.WordService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.service
import com.intellij.openapi.ui.Messages

class WordOfTheDayAction : AnAction() {


    override fun actionPerformed(event: AnActionEvent) {
        val app = ApplicationManager.getApplication()
        app.executeOnPooledThread {
            val response = app.getService(WordHttpClient::class.java).get();
            app.invokeLater {
                val service = app.getService(WordService::class.java)
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