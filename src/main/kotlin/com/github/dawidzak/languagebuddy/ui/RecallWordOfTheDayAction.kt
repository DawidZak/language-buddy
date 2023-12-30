package com.github.dawidzak.languagebuddy.ui

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class RecallWordOfTheDayAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        RecallDialogWrapper(e.project).show()
    }


}