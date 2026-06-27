package ihm


import IHM.controleur.ControleurAjoutJoueur
import IHM.controleur.ControleurLancerFlip7
import IHM.controleur.ControleurRetireJoueur
import IHM.controleur.ControleurTextFieldNomJoueur
import IHM.controleur.ControleurTextFieldScoreFin
import IHM.vue.VueDebutPartie
import IHM.vue.VueFlip7
import javafx.application.Application
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.scene.input.KeyEvent
import javafx.stage.Stage


class Main: Application() {


    override fun start(primaryStage: Stage) {
        primaryStage.title="FLIP7"

        val startVue = VueDebutPartie()
        startVue.champJoueur1.addEventHandler(KeyEvent.KEY_TYPED, ControleurTextFieldNomJoueur(startVue.champJoueur1))
        startVue.champJoueur2.addEventHandler(KeyEvent.KEY_TYPED, ControleurTextFieldNomJoueur(startVue.champJoueur2))
        startVue.champJoueur3.addEventHandler(KeyEvent.KEY_TYPED, ControleurTextFieldNomJoueur(startVue.champJoueur3))
        startVue.champJoueur4.addEventHandler(KeyEvent.KEY_TYPED, ControleurTextFieldNomJoueur(startVue.champJoueur4))
        startVue.champScoreFin.addEventHandler(KeyEvent.KEY_TYPED, ControleurTextFieldScoreFin(startVue.champScoreFin))
        startVue.btnAjouter.addEventHandler(ActionEvent.ACTION, ControleurAjoutJoueur(startVue))
        startVue.btnRetirer.addEventHandler(ActionEvent.ACTION, ControleurRetireJoueur(startVue))

        startVue.btnJouer.addEventHandler(ActionEvent.ACTION, ControleurLancerFlip7(startVue, VueFlip7()))

        val scene = Scene(startVue,  1000.0, 700.0)
        primaryStage.scene=scene
        primaryStage.show()
    }
}
fun main(){
    Application.launch(Main::class.java)
}
