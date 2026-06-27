package flip7.controleur

import flip7.Routeur
import flip7.modele.PartieFlip7
import flip7.style.Style
import flip7.vue.VueConfiguration
import flip7.vue.VueJeu

class ControleurConfiguration(
    private val routeur: Routeur,
    private val vue: VueConfiguration
) {

    private var nombreJoueurs: Int = 3

    init {
        vue.bouton2.setOnAction { onNombreJoueurs(2) }
        vue.bouton3.setOnAction { onNombreJoueurs(3) }
        vue.bouton4.setOnAction { onNombreJoueurs(4) }

        vue.boutonLancer.setOnAction { onLancerClick() }

        // applique l'etat initial (3 joueurs : bouton surligne + champs actifs)
        majSelection()
    }

    private fun onNombreJoueurs(nombre: Int) {
        nombreJoueurs = nombre
        majSelection()
    }

    private fun majSelection() {
        vue.bouton2.style = styleBoutonCarre(nombreJoueurs == 2)
        vue.bouton3.style = styleBoutonCarre(nombreJoueurs == 3)
        vue.bouton4.style = styleBoutonCarre(nombreJoueurs == 4)

        // baisse l'opacité par rapport au nombre de joueurs sélectionnés et empêche qu'on écrit
        val champs = listOf(vue.champJoueur1, vue.champJoueur2, vue.champJoueur3, vue.champJoueur4)
        for (i in champs.indices) {
            val utilise = i < nombreJoueurs
            champs[i].isDisable = !utilise
            champs[i].opacity = if (utilise) 1.0 else 0.4
        }
    }
    // Récupération du nombre de points mis avec le curseur et les noms mis dans les champs des joueurs
    private fun onLancerClick() {
        val noms = lireNoms()
        val scoreCible = vue.curseurScore.value.toInt()

        val partie = PartieFlip7(noms, scoreCible)
        val vueJeu = VueJeu(partie)
        ControleurJeu(routeur, vueJeu, partie)
        routeur.afficherEcran(vueJeu.racine)
    }
    // si on remarque qu il y a aucune saisi donc aucun carctère dans les champs des joueurs sélectionnés on garde joueur 1, joueur2 etc.
    private fun lireNoms(): List<String> {
        val champs = listOf(vue.champJoueur1, vue.champJoueur2, vue.champJoueur3, vue.champJoueur4)
        val noms = mutableListOf<String>()
        for (i in 0 until nombreJoueurs) {
            val saisi = champs[i].text.orEmpty()
            noms.add(if (saisi.isEmpty()) "Joueur ${i + 1}" else saisi)
        }
        return noms
    }

    private fun fermer() {
        routeur.fermerModale(vue.racine)
    }

    private fun styleBoutonCarre(selectionne: Boolean): String {
        val bordure = if (selectionne) {
            "-fx-border-color: ${Style.MAGENTA}; -fx-border-width: 4; -fx-border-radius: 18;"
        } else {
            ""
        }
        return """
            -fx-background-color: ${Style.CYAN};
            -fx-text-fill: ${Style.MAGENTA};
            -fx-font-size: 24px;
            -fx-font-weight: bold;
            -fx-min-width: 64; -fx-min-height: 64;
            -fx-max-width: 64; -fx-max-height: 64;
            -fx-background-radius: 18;
            -fx-cursor: hand;
            $bordure
        """
    }
}