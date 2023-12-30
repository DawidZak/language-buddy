package com.github.dawidzak.languagebuddy.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.*
import java.awt.Dimension
import javax.swing.JComponent

class RecallDialogWrapper(project: Project?) :
    DialogWrapper(project, null, true, IdeModalityType.MODELESS, true) {

    init {
        init()
    }

    override fun createCenterPanel(): JComponent {
        val recallPanel = recallPanel()
        recallPanel.preferredSize = Dimension(400, 200)
        recallPanel.minimumSize = Dimension(200, 150)
        return recallPanel
    }

    private fun recallPanel(): DialogPanel {
        val model = Model();

        val panel: DialogPanel = panel {
            row {
                label("Definition")
            }
            row {
                label("<html>to bend your head or body forward, especially as a way of showing someone respect or expressing thanks to people who have watched you perform</html>")
            }.bottomGap(BottomGap.MEDIUM)
            row("Answer") {
                textField()
                    .validationOnInput {
                        if (it.text != "test") {
                            error("Incorrect")
                        } else {
                            null
                        }

                    }
                    .bindText(model::textField)
            }
        }
        return panel;
    }

    override fun doOKAction() {
        super.doOKAction()
        //TODO correct
    }

    override fun doCancelAction() {
        super.doCancelAction()
        //TODO incorrect
    }
}

internal data class Model(
    var checkbox: Boolean = false,
    var textField: String = "",
)