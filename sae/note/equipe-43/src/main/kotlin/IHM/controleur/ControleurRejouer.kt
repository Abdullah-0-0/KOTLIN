package IHM.controleur

import IHM.modele.ModeleFlip7
import IHM.vue.VueDebutPartie
import IHM.vue.VueFinPartie
import IHM.vue.VueFlip7
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.input.KeyEvent

class ControleurRejouer(vue: VueFinPartie): EventHandler<ActionEvent>  {

    val vue: VueFinPartie


    init {
        this.vue = vue
    }

    override fun handle(event: ActionEvent?) {
        val startVue = VueDebutPartie()
        startVue.champJoueur1.addEventHandler(KeyEvent.KEY_TYPED, ControleurTextFieldNomJoueur(startVue.champJoueur1))
        startVue.champJoueur2.addEventHandler(KeyEvent.KEY_TYPED, ControleurTextFieldNomJoueur(startVue.champJoueur2))
        startVue.champJoueur3.addEventHandler(KeyEvent.KEY_TYPED, ControleurTextFieldNomJoueur(startVue.champJoueur3))
        startVue.champJoueur4.addEventHandler(KeyEvent.KEY_TYPED, ControleurTextFieldNomJoueur(startVue.champJoueur4))
        startVue.champScoreFin.addEventHandler(KeyEvent.KEY_TYPED, ControleurTextFieldScoreFin(startVue.champScoreFin))
        startVue.btnAjouter.addEventHandler(ActionEvent.ACTION, ControleurAjoutJoueur(startVue))
        startVue.btnRetirer.addEventHandler(ActionEvent.ACTION, ControleurRetireJoueur(startVue))

        startVue.btnJouer.addEventHandler(ActionEvent.ACTION, ControleurLancerFlip7(startVue, VueFlip7()))

        vue.scene.root = startVue
    }
}