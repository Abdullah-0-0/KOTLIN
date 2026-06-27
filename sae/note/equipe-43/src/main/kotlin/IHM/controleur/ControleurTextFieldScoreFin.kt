package IHM.controleur

import javafx.event.EventHandler
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent

class ControleurTextFieldScoreFin(champ: TextField) : EventHandler<KeyEvent> {
    val champ: TextField

    init {
        this.champ = champ
    }
    override fun handle(event: KeyEvent) {

        val key = event.character

        if (event.code == KeyCode.BACK_SPACE || event.code == KeyCode.DELETE) return

        if (key.isEmpty() || !key[0].isDigit()) {
            event.consume()
            return
        }

        if (champ.text.length >= 6) {
            event.consume()
        }
    }
}