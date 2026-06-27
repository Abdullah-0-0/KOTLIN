package flip7.vue

import flip7.modele.PartieFlip7
import flip7.style.Style
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox

class VueJeu(private val partie: PartieFlip7) {

    val boutonPiocher: Button = Style.boutonPrincipal("PIOCHER")
    val boutonStop: Button = Style.boutonPrincipal("STOP")

    val racine: BorderPane = BorderPane()

    init {
        racine.style = "-fx-background-color: ${Style.CYAN};"
        racine.padding = Insets(16.0)
        boutonPiocher.prefWidth = 200.0
        boutonStop.prefWidth = 200.0
        rafraichir()
    }

    fun rafraichir() {
        racine.top = zoneAdversaires()
        racine.center = zoneCentrale()
        racine.bottom = zoneJoueurActif()
    }

    private fun zoneAdversaires(): HBox {
        val rangee = HBox(40.0)
        rangee.alignment = Pos.CENTER
        rangee.padding = Insets(0.0, 0.0, 10.0, 0.0)
        for (i in 0 until partie.nbJoueurs) {
            if (i != partie.joueurCourant) {
                rangee.children.add(panneauAdversaire(i))
            }
        }
        return rangee
    }

    private fun panneauAdversaire(indice: Int): VBox {
        val info = Style.texteContour(
            "${partie.nomJoueur(indice)} | score = ${partie.scoreMancheCourante(indice)}",
            Style.JAUNE, Style.MAGENTA, 15.0
        )
        val etat = Style.texteContour("ETAT : ${partie.etatJoueurTexte(indice)}", Style.JAUNE, Style.MAGENTA, 11.0)

        val mains = HBox(4.0)
        mains.alignment = Pos.CENTER
        val cartes = partie.mainTexte(indice)
        if (cartes.isEmpty()) {
            mains.children.add(Style.texteContour("(aucune carte)", Style.MAGENTA, Style.BLANC, 11.0))
        } else {
            for (libelle in cartes) {
                mains.children.add(CarteVue(libelle, grande = false))
            }
        }

        val boite = VBox(4.0, info, etat, mains)
        boite.alignment = Pos.CENTER
        return boite
    }


    private fun zoneCentrale(): VBox {
        val pioche = StackPane()
        val nbDos = minOf(partie.taillePioche, 4)
        if (nbDos == 0) {
            pioche.children.add(carteVide())
        } else {
            for (i in 0 until nbDos) {
                val dos = CarteVue.dos(grande = true)
                dos.translateX = (i * 3).toDouble()
                dos.translateY = (i * 3).toDouble()
                pioche.children.add(dos)
            }
        }
        val colPioche = VBox(
            6.0,
            pioche,
            Style.texteContour("PIOCHE (${partie.taillePioche})", Style.MAGENTA, Style.BLANC, 14.0)
        )
        colPioche.alignment = Pos.CENTER

        // defausse : derniere carte jouee, ou une case vide si rien encore
        val defausseLibelle = partie.defausseTexte()
        val carteDefausse: StackPane =
            if (defausseLibelle != null) CarteVue(defausseLibelle, grande = true) else carteVide()
        val colDefausse = VBox(6.0, carteDefausse, Style.texteContour("DÉFAUSSE", Style.MAGENTA, Style.BLANC, 14.0))
        colDefausse.alignment = Pos.CENTER

        val ligne = HBox(56.0, colPioche, colDefausse)
        ligne.alignment = Pos.CENTER
        val conteneur = VBox(ligne)
        conteneur.alignment = Pos.CENTER
        return conteneur
    }

    private fun carteVide(): StackPane {
        val carte = StackPane()
        carte.minWidth = 84.0; carte.prefWidth = 84.0; carte.maxWidth = 84.0
        carte.minHeight = 112.0; carte.prefHeight = 112.0; carte.maxHeight = 112.0
        carte.style = """
            -fx-background-color: rgba(255, 255, 255, 0.30);
            -fx-background-radius: 12;
            -fx-border-color: rgba(255, 0, 251, 0.45);
            -fx-border-width: 3;
            -fx-border-radius: 12;
        """
        return carte
    }


    private fun zoneJoueurActif(): VBox {
        val courant = partie.joueurCourant
        val info = Style.texteContour(
            "${partie.nomJoueur(courant)} | score = ${partie.scoreMancheCourante(courant)}",
            Style.JAUNE, Style.MAGENTA, 18.0
        )
        val etat = Style.texteContour("ETAT : ${partie.etatJoueurTexte(courant)}", Style.JAUNE, Style.MAGENTA, 13.0)

        val main = HBox(12.0)
        main.alignment = Pos.CENTER
        val cartes = partie.mainTexte(courant)
        if (cartes.isEmpty()) {
            main.children.add(Style.texteContour("(aucune carte)", Style.MAGENTA, Style.BLANC, 16.0))
        } else {
            for (libelle in cartes) {
                main.children.add(CarteVue(libelle, grande = true))
            }
        }

        val actions = HBox(24.0, boutonPiocher, boutonStop)
        actions.alignment = Pos.CENTER

        val boite = VBox(10.0, info, etat, main, actions)
        boite.alignment = Pos.CENTER
        boite.padding = Insets(12.0, 0.0, 0.0, 0.0)
        return boite
    }
}