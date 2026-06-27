package IHM.controleur

import IHM.vue.VueDebutPartie
import javafx.event.EventHandler
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent


class ControleurTextFieldNomJoueur(champ: TextField) : EventHandler<KeyEvent> {
    val champ: TextField

    init {
        this.champ = champ
    }
    override fun handle(event: KeyEvent) {
        if (champ.text.length >= 24 && event.code != KeyCode.BACK_SPACE && event.code != KeyCode.DELETE) {
            event.consume()
        }
    }
}