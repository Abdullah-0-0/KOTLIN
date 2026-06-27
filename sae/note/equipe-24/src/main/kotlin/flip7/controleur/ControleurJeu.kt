package flip7.controleur

import flip7.Routeur
import flip7.modele.PartieFlip7
import flip7.vue.VueFinPartie
import flip7.vue.VueJeu
import flip7.vue.VueMancheTerminee
import flip7.vue.modales.ModaleChoixCible
import flip7.vue.modales.ModaleConfirmation

class ControleurJeu(
    private val routeur: Routeur,
    private val vue: VueJeu,
    private val partie: PartieFlip7
) {

    init {
        vue.boutonPiocher.setOnAction { onPiocher() }
        vue.boutonStop.setOnAction { onStop() }
    }

    private fun onPiocher() {
        partie.piocher()
        traiterEtat()
    }

    private fun onStop() {
        val modale = ModaleConfirmation(
            partie.nomJoueur(partie.joueurCourant),
            "Êtes-vous sûr de vouloir vous arrêter ?"
        )
        modale.boutonNon.setOnAction { routeur.fermerModale(modale.racine) }
        modale.boutonOui.setOnAction {
            routeur.fermerModale(modale.racine)
            partie.ditStop()
            traiterEtat()
        }
        routeur.ouvrirModale(modale.racine)
    }

    private fun traiterEtat() {
        when {
            partie.attendCibleStop() -> demanderCible(estStop = true)
            partie.attendCible3aLaSuite() -> demanderCible(estStop = false)
            partie.mancheTerminee() -> finDeManche()
            else -> vue.rafraichir()
        }
    }

    private fun demanderCible(estStop: Boolean) {
        val cibles = partie.ciblesPossibles()
        val typeCarte = if (estStop) "STOP" else "3 à la\nsuite"
        val nomCourant = partie.nomJoueur(partie.joueurCourant)
        val titre = "$nomCourant a pioché une carte spéciale\nSur quel joueur l'utiliser ?"

        val noms = mutableListOf<String>()
        for (cible in cibles) {
            noms.add(cible.second)
        }
        val modale = ModaleChoixCible(titre, noms, carteJouee = typeCarte)

        for (cible in cibles) {
            val bouton = modale.boutonsCibles[cible.second]
            bouton?.setOnAction {
                routeur.fermerModale(modale.racine)
                if (estStop) {
                    partie.ciblerStop(cible.first)
                } else {
                    partie.cibler3aLaSuite(cible.first)
                }
                traiterEtat()
            }
        }

        // pas de fermeture au clic sur le fond : choisir une cible est obligatoire
        routeur.ouvrirModale(modale.racine)
    }

    private fun finDeManche() {
        val lignes = partie.cloturerManche()
        if (partie.partieTerminee()) {
            val vueFin = VueFinPartie(partie.gagnant(), lignes)
            ControleurFinPartie(routeur, vueFin, partie)
            routeur.ouvrirModale(vueFin.racine)
        } else {
            val vueManche = VueMancheTerminee(partie.numeroManche, lignes)
            ControleurMancheTerminee(routeur, vueManche, partie, vue)
            routeur.ouvrirModale(vueManche.racine)
        }
    }
}