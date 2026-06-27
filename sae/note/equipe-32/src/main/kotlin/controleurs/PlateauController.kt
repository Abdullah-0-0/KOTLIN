package iut.info1.flip7.prototype.controleur

import iut.info1.flip7.etats.EtatPartie
import iut.info1.flip7.prototype.modele.CarteUI
import iut.info1.flip7.prototype.modele.JoueurUI
import iut.info1.flip7.prototype.modele.PartieFlip7UI
import iut.info1.flip7.prototype.vue.PlateauView
import iut.info1.flip7.prototype.vue.VueFinDePartie
import iut.info1.flip7.prototype.vue.VueInterManche
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.layout.StackPane

class PlateauController(
    private val nomsJoueurs: List<String>,
    private val scoreObjectif: Int = 200
) {

    private val vuePlateau = PlateauView()
    private val modele = PartieFlip7UI()

    init {
        modele.initialiserPartie(nomsJoueurs, scoreObjectif)
    }

    fun construireScene(): Scene = Scene(construireRacine(), 1280.0, 820.0)

    fun construireRacine(): StackPane {
        val racine = vuePlateau.construireRacine(
            joueurs       = modele.donneTousLesJoueurs(),
            pioche        = modele.donnePioche(),
            defausse      = modele.donneDefausse(),
            surPiocher    = ::onPiocher,
            surStop       = ::onStop,
            surClicJoueur = ::onClicJoueur
        )
        rafraichir()
        return racine
    }

    private fun onPiocher() {
        if (modele.donneEtatPartie() != EtatPartie.ATTENTE_CHOIX_JOUEUR) {
            vuePlateau.indiquerStatut("Impossible de piocher dans cet état.")
            return
        }

        // on sauvegarde le nom avant la pioche car après l'état change
        val nomJoueur = modele.donneJoueurActif()?.pseudo ?: "?"

        val carteUI = modele.piocherEtMemoriser() ?: run {
            vuePlateau.indiquerStatut("Impossible de piocher.")
            return
        }

        rafraichir()
        vuePlateau.afficherDerniereCarte(carteUI)

        if (modele.enAttenteDonSecondeChance) {
            val nomDonneur = modele.donneTousLesJoueurs()
                .getOrNull(modele.indexJoueurAvecDoublon)?.pseudo ?: "?"
            vuePlateau.indiquerStatut("$nomDonneur a déjà une 2nde Chance — clique sur un autre joueur pour la lui donner.")
            // désactiver les boutons pendant le don (rafraichir() a été appelé avant que l'état change)
            vuePlateau.definirBoutonsActifs(piocherActif = false, stopActif = false)
            return
        }

        val etatApres = modele.donneEtatPartie()
        val nom = nomJoueur

        if (etatApres == EtatPartie.MANCHE_TERMINEE) {
            vuePlateau.indiquerStatut("Manche terminée !")
            onMancheTerminee()
        } else if (etatApres == EtatPartie.ATTENTE_CIBLE_STOP) {
            vuePlateau.indiquerStatut("$nom a pioché un STOP — clique sur le joueur à stopper.")
        } else if (etatApres == EtatPartie.ATTENTE_CIBLE_3SUITE) {
            vuePlateau.indiquerStatut("$nom a pioché 3 à la suite — clique sur le joueur ciblé.")
        } else if (carteUI is CarteUI.Num) {
            val joueur = modele.donneJoueurActif()
            if (joueur != null && !joueur.peutJouer) {
                vuePlateau.indiquerStatut("${joueur.pseudo} a un doublon — il perd cette manche !")
            } else {
                rafraichirStatut()
            }
        } else {
            rafraichirStatut()
        }
    }

    private fun onStop() {
        if (modele.donneEtatPartie() != EtatPartie.ATTENTE_CHOIX_JOUEUR) {
            vuePlateau.indiquerStatut("Impossible de dire Stop dans cet état.")
            return
        }

        val nomActuel = modele.donneJoueurActif()?.pseudo ?: "?"
        modele.stop()
        rafraichir()

        when (modele.donneEtatPartie()) {
            EtatPartie.MANCHE_TERMINEE -> {
                vuePlateau.indiquerStatut("$nomActuel a dit Stop — manche terminée !")
                onMancheTerminee()
            }
            else -> {
                val nomSuivant = modele.donneJoueurActif()?.pseudo ?: "—"
                vuePlateau.indiquerStatut("$nomActuel a dit Stop. Tour de $nomSuivant.")
            }
        }
    }

    private fun onClicJoueur(joueur: JoueurUI) {
        if (modele.enAttenteDonSecondeChance) {
            val indexReceveur = modele.donneTousLesJoueurs().indexOf(joueur)
            if (indexReceveur == modele.indexJoueurAvecDoublon) {
                val nomDonneur = modele.donneTousLesJoueurs().getOrNull(modele.indexJoueurAvecDoublon)?.pseudo ?: "?"
                vuePlateau.indiquerStatut("$nomDonneur ne peut pas se donner sa propre carte — clique sur un autre joueur.")
                return
            }
            if (joueur.main.any { it is CarteUI.SecondeChance }) {
                vuePlateau.indiquerStatut("${joueur.pseudo} a déjà une 2nde Chance — choisis un autre joueur.")
                return
            }
            modele.donnerSecondeChance(indexReceveur)
            rafraichir()
            vuePlateau.indiquerStatut("2nde Chance donnée à ${joueur.pseudo}.")
            rafraichirStatut()
            return
        }

        val etat = modele.donneEtatPartie()
        val carteBiblio = modele.dernierCartePiochee

        when {
            etat == EtatPartie.ATTENTE_CIBLE_STOP && carteBiblio != null -> {
                val indexCible = modele.donneTousLesJoueurs().indexOf(joueur)
                if (indexCible < 0) return
                modele.ciblerStop(carteBiblio, indexCible)
                rafraichir()
                when (modele.donneEtatPartie()) {
                    EtatPartie.MANCHE_TERMINEE -> {
                        vuePlateau.indiquerStatut("${joueur.pseudo} est stoppé — manche terminée !")
                        onMancheTerminee()
                    }
                    else -> vuePlateau.indiquerStatut(
                        "${joueur.pseudo} est stoppé. Tour de ${modele.donneJoueurActif()?.pseudo ?: "—"}."
                    )
                }
            }
            etat == EtatPartie.ATTENTE_CIBLE_3SUITE && carteBiblio != null -> {
                val indexCible = modele.donneTousLesJoueurs().indexOf(joueur)
                if (indexCible < 0) return
                val cartesObtenues = modele.ciblerTroisALaSuite(carteBiblio, indexCible)
                rafraichir()
                if (cartesObtenues.isNotEmpty()) {
                    vuePlateau.afficherCartesTroisALaSuite(joueur.pseudo, cartesObtenues)
                }
                if (modele.enAttenteDonSecondeChance) {
                    val nomDonneur = modele.donneTousLesJoueurs()
                        .getOrNull(modele.indexJoueurAvecDoublon)?.pseudo ?: "?"
                    vuePlateau.indiquerStatut("$nomDonneur a reçu une 2nde Chance en doublon — clique sur un autre joueur pour la lui donner.")
                    vuePlateau.definirBoutonsActifs(piocherActif = false, stopActif = false)
                    return
                }
                when (modele.donneEtatPartie()) {
                    EtatPartie.MANCHE_TERMINEE -> {
                        vuePlateau.indiquerStatut("3 à la suite pour ${joueur.pseudo} — manche terminée !")
                        onMancheTerminee()
                    }
                    else -> rafraichirStatut()
                }
            }
        }
    }

    private fun onMancheTerminee() {
        val scoresManche = modele.calculerScoreManche()
        rafraichir()

        when (modele.donneEtatPartie()) {
            EtatPartie.PARTIE_TERMINEE -> afficherEcranFinDePartie()
            EtatPartie.NOUVELLE_MANCHE -> {
                val listeJoueurs = modele.donneTousLesJoueurs()
                val scoresMancheParPseudo = scoresManche
                    .mapKeys { (index, _) -> listeJoueurs.getOrNull(index)?.pseudo ?: "?" }

                val vueRecap = VueInterManche()
                val sceneRecap = Scene(vueRecap, 1280.0, 820.0)
                vueRecap.construire(
                    numeroManche = modele.donneMancheNumero(),
                    scoresManche = scoresMancheParPseudo,
                    scoresTotaux = listeJoueurs,
                    surContinuer = {
                        val stage = vueRecap.scene?.window as? javafx.stage.Stage ?: return@construire
                        modele.lancerNouvelleManche()
                        val nouvelleRacine = vuePlateau.construireRacine(
                            joueurs       = modele.donneTousLesJoueurs(),
                            pioche        = modele.donnePioche(),
                            defausse      = modele.donneDefausse(),
                            surPiocher    = ::onPiocher,
                            surStop       = ::onStop,
                            surClicJoueur = ::onClicJoueur
                        )
                        val scenePlateau = Scene(nouvelleRacine, 1280.0, 820.0)
                        stage.scene = scenePlateau
                        stage.isFullScreen = true
                        rafraichir()
                        rafraichirStatut()
                    }
                )
                vuePlateau.changerScene(sceneRecap)
            }
            else -> rafraichir()
        }
    }

    private fun afficherEcranFinDePartie() {
        val joueurs = modele.donneTousLesJoueurs()
        val scoreMax = joueurs.maxOfOrNull { it.score } ?: 0

        val gagnants = joueurs.filter { it.score == scoreMax }
        if (gagnants.size > 1) {
            modele.lancerNouvelleManche()
            vuePlateau.masquerDerniereCarte()
            val nouvelleRacine = vuePlateau.construireRacine(
                joueurs       = modele.donneTousLesJoueurs(),
                pioche        = modele.donnePioche(),
                defausse      = modele.donneDefausse(),
                surPiocher    = ::onPiocher,
                surStop       = ::onStop,
                surClicJoueur = ::onClicJoueur
            )
            val scenePlateau = Scene(nouvelleRacine, 1280.0, 820.0)
            vuePlateau.changerScene(scenePlateau)
            vuePlateau.indiquerStatut("Égalité à $scoreMax pts — manche supplémentaire !")
            rafraichir()
            return
        }

        val classement = joueurs
            .sortedByDescending { it.score }
            .map { Pair(it.pseudo, it.score) }

        val vueFinDePartie = VueFinDePartie()
        vueFinDePartie.construire(
            classement = classement,
            surRejouer = {
                // relancer directement avec les mêmes joueurs et le même score
                modele.initialiserPartie(nomsJoueurs, scoreObjectif)
                val nouvelleRacine = vuePlateau.construireRacine(
                    joueurs       = modele.donneTousLesJoueurs(),
                    pioche        = modele.donnePioche(),
                    defausse      = modele.donneDefausse(),
                    surPiocher    = ::onPiocher,
                    surStop       = ::onStop,
                    surClicJoueur = ::onClicJoueur
                )
                val stage = vueFinDePartie.scene?.window as? javafx.stage.Stage ?: return@construire
                stage.scene = Scene(nouvelleRacine, 1280.0, 820.0)
                stage.isFullScreen = true
                rafraichir()
                rafraichirStatut()
            },
            surQuitter = { Platform.exit() }
        )

        val sceneFinDePartie = Scene(vueFinDePartie, 1280.0, 820.0)
        vuePlateau.changerScene(sceneFinDePartie)
    }


    private fun rafraichir() {
        vuePlateau.rafraichirJoueurs(modele.donneTousLesJoueurs(), ::onClicJoueur)
        vuePlateau.rafraichirCompteurPioche(modele.donneTaillePioche())
        vuePlateau.rafraichirManche(modele.donneMancheNumero())
        vuePlateau.rafraichirScores(modele.donneTousLesJoueurs())
        vuePlateau.rafraichirTourDe(modele.donneJoueurActif()?.pseudo)
        vuePlateau.rafraichirScoreObjectif(scoreObjectif)

        // TODO désactiver les boutons
        val etat = modele.donneEtatPartie()
        val boutonsNormaux = etat == EtatPartie.ATTENTE_CHOIX_JOUEUR && !modele.enAttenteDonSecondeChance
        vuePlateau.definirBoutonsActifs(
            piocherActif = boutonsNormaux,
            stopActif    = boutonsNormaux
        )
    }

    private fun rafraichirStatut() {
        val actif = modele.donneJoueurActif()
        if (actif != null) {
            vuePlateau.indiquerStatut("${actif.pseudo} — pioche une carte ou dis Stop.")
            vuePlateau.rafraichirTourDe(actif.pseudo)
        } else {
            vuePlateau.indiquerStatut("En attente…")
            vuePlateau.rafraichirTourDe(null)
        }
    }
}