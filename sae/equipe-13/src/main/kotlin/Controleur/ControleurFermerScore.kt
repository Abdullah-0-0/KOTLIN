package Controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage

class ControleurFermerScore(fenetreScore: Stage) : EventHandler<ActionEvent> {
    private val fenetreScore: Stage
    init {
        this.fenetreScore = fenetreScore
    }
    override fun handle(event: ActionEvent) {
        fenetreScore.close()
    }
}