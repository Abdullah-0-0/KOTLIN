package iut.info1.flip7.prototype.vue

import iut.info1.flip7.prototype.modele.CarteUI
import iut.info1.flip7.prototype.modele.JoueurUI
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment

class PlateauView {

    private val zoneStatut = Label("")
    private val conteneurJoueurs = VBox(32.0)
    private val labelTourDe = Label("")
    private val boutonPiocher = Button("🂠  Piocher une carte")
    private val zoneDerniereCarte = VBox(6.0)   // conteneur vertical : label + ligne de cartes
    private val ligneCartesDernierePioche = HBox(10.0)  // ligne horizontale : carte piochée + cartes 3aLaSuite
    private val boutonStop = Button("✋  Stop")

    private val labelCompteurPioche = Label("94")
    private val labelManche = Label("Manche 1")
    private val labelScoreObjectif = Label("")
    private val conteneurScores = VBox(4.0)
    private var panneauDroit: VBox = VBox()

    fun construireRacine(
        joueurs: List<JoueurUI>,
        pioche: List<CarteUI>,
        defausse: List<CarteUI>,
        surPiocher: () -> Unit,
        surStop: () -> Unit,
        surClicJoueur: (JoueurUI) -> Unit
    ): StackPane {
        val racine = BorderPane()
        racine.style = "-fx-background-color: #2b2b2b;"
        racine.center = construireZoneCentrale(joueurs, surClicJoueur)
        panneauDroit = construirePiocheEtDefausse(pioche, defausse)
        racine.right = panneauDroit
        racine.bottom = construireZoneBasse(surPiocher, surStop)

        zoneDerniereCarte.padding = Insets(0.0, 8.0, 16.0, 0.0)
        StackPane.setAlignment(zoneDerniereCarte, Pos.BOTTOM_RIGHT)
        zoneDerniereCarte.isPickOnBounds = false
        zoneDerniereCarte.isMouseTransparent = true

        return StackPane(racine, zoneDerniereCarte)
    }

    private fun construireZoneCentrale(
        joueurs: List<JoueurUI>,
        surClicJoueur: (JoueurUI) -> Unit
    ): VBox {
        conteneurJoueurs.children.clear()
        conteneurJoueurs.padding = Insets(20.0)
        conteneurJoueurs.alignment = Pos.CENTER

        for (joueur in joueurs) {
            conteneurJoueurs.children.add(construireZoneJoueur(joueur, surClicJoueur))
        }

        val titrePlateau = Label("Plateau de jeu")
        titrePlateau.font = Font.font("System", FontWeight.BOLD, 26.0)
        titrePlateau.textFill = Color.WHITE

        labelTourDe.font = Font.font("System", FontWeight.BOLD, 20.0)
        labelTourDe.textFill = Color.web("#f5d76e")

        zoneDerniereCarte.alignment = Pos.CENTER
        zoneDerniereCarte.isVisible = false
        zoneDerniereCarte.isManaged = false

        val scrollJoueurs = ScrollPane(conteneurJoueurs)
        scrollJoueurs.isFitToWidth = true
        scrollJoueurs.style = "-fx-background: #2b2b2b; -fx-background-color: #2b2b2b;"
        scrollJoueurs.vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
        scrollJoueurs.hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        VBox.setVgrow(scrollJoueurs, javafx.scene.layout.Priority.ALWAYS)

        val conteneur = VBox(10.0, titrePlateau, labelTourDe, scrollJoueurs)
        conteneur.padding = Insets(10.0)
        VBox.setVgrow(conteneur, javafx.scene.layout.Priority.ALWAYS)
        return conteneur
    }

    private fun construireZoneJoueur(joueur: JoueurUI, surClicJoueur: (JoueurUI) -> Unit): VBox {
        val lignePrincipale = HBox(10.0)
        lignePrincipale.alignment = Pos.CENTER_LEFT

        val labelPseudo = Label(joueur.pseudo)
        labelPseudo.font = Font.font("System", FontWeight.BOLD, 22.0)
        labelPseudo.textFill = when {
            joueur.aPerdu         -> Color.web("#888888")
            joueur.aArrete        -> Color.web("#aaaaaa")
            joueur.estJoueurActif -> Color.web("#f5d76e")
            else                  -> Color.WHITE
        }
        labelPseudo.minWidth = 150.0
        lignePrincipale.children.add(labelPseudo)

        if (joueur.main.isEmpty()) {
            val placeholder = Label("(aucune carte)")
            placeholder.textFill = Color.web("#666666")
            placeholder.font = Font.font("System", 14.0)
            lignePrincipale.minHeight = 80.0
            lignePrincipale.children.add(placeholder)
        } else {
            lignePrincipale.minHeight = 170.0
            for (carte in joueur.main) {
                lignePrincipale.children.add(construireVisuelCartePetite(carte))
            }
        }

        if (joueur.main.any { it is CarteUI.SecondeChance }) {
            val badgeChance = Label("🛡")
            badgeChance.style = "-fx-background-color:#a3c9e8;-fx-text-fill:#1a1a1a;" +
                    "-fx-padding:2 5 2 5;-fx-background-radius:6;-fx-font-size:10px;"
            lignePrincipale.children.add(badgeChance)
        }

        val labelStatut = when {
            joueur.aPerdu         -> Label("💀 Perdu — éliminé cette manche")
            joueur.aArrete        -> Label("🛑 Stop — attend la prochaine manche")
            joueur.estJoueurActif -> Label("● son tour")
            else                  -> null
        }

        val contenu = if (labelStatut != null) {
            labelStatut.font = Font.font("System", 12.0)
            labelStatut.textFill = when {
                joueur.aPerdu         -> Color.web("#cc8888")
                joueur.aArrete        -> Color.web("#cccc88")
                else                  -> Color.web("#f5d76e")
            }
            VBox(4.0, lignePrincipale, labelStatut)
        } else {
            VBox(lignePrincipale)
        }

        contenu.padding = Insets(24.0, 28.0, 24.0, 28.0)
        contenu.style = styleZoneJoueur(joueur.estJoueurActif, joueur.aPerdu || joueur.aArrete, survol = false)

        if (joueur.peutJouer || joueur.estJoueurActif) {
            contenu.setOnMouseEntered {
                contenu.style = styleZoneJoueur(joueur.estJoueurActif, false, survol = true)
            }
            contenu.setOnMouseExited {
                contenu.style = styleZoneJoueur(joueur.estJoueurActif, false, survol = false)
            }
        }
        contenu.setOnMouseClicked { surClicJoueur(joueur) }

        return contenu
    }

    private fun construireVisuelCartePetite(carte: CarteUI): StackPane {
        val w = 140.0
        val h = 196.0

        val cheminImage = when (carte) {
            is CarteUI.Num            -> "/Images/num_${carte.valeur}.png"
            is CarteUI.BonusPlus      -> "/Images/Bonus_+${carte.valeur}.png"
            is CarteUI.BonusMultiplie -> "/Images/Bonus_x2.png"
            is CarteUI.Stop           -> "/Images/Spe_Stop.png"
            is CarteUI.SecondeChance  -> "/Images/Spe_SecondeChance.PNG"
            is CarteUI.TroisALaSuite  -> "/Images/Spe_3aLaSuite.PNG"
        }

        val flux = cheminImage?.let { chemin ->
            val nomFichier = chemin.substringAfterLast("/")
            val chemins = listOf(
                "src/main/kotlin/vues/Images/$nomFichier",
                "equipe-32/src/main/kotlin/vues/Images/$nomFichier",
                "vues/Images/$nomFichier",
                nomFichier
            )
            chemins.map { java.io.File(it) }.firstOrNull { it.exists() }
        }
        if (flux != null) {
            val image = javafx.scene.image.Image(flux.toURI().toString())
            val vue = javafx.scene.image.ImageView(image)
            vue.fitWidth = w
            vue.fitHeight = h
            vue.isPreserveRatio = true
            return StackPane(vue)
        }

        val rectangle = Rectangle(w, h)
        rectangle.fill = Color.web(carte.couleurFond)
        rectangle.arcWidth = 14.0
        rectangle.arcHeight = 14.0
        rectangle.stroke = Color.web(bordureCarte(carte))
        rectangle.strokeWidth = 2.0

        val icone = Label(iconeCarte(carte))
        icone.font = Font.font("System", 30.0)

        val valeur = Label(valeurCarte(carte))
        valeur.font = Font.font("System", FontWeight.BOLD, 26.0)
        valeur.textFill = Color.web("#1a1a1a")
        valeur.textAlignment = TextAlignment.CENTER
        valeur.isWrapText = true
        valeur.maxWidth = w - 8.0

        val contenu = VBox(4.0, icone, valeur)
        contenu.alignment = Pos.CENTER

        return StackPane(rectangle, contenu)
    }

    private fun styleZoneJoueur(actif: Boolean, inactif: Boolean, survol: Boolean): String {
        val fond: String
        val bordure: String
        if (inactif) {
            fond = "#252525"
            bordure = "#3a3a3a"
        } else if (actif && survol) {
            fond = "#5a6a3b"
            bordure = "#a8cc5e"
        } else if (actif) {
            fond = "#3d4f28"
            bordure = "#7aaa30"
        } else if (survol) {
            fond = "#4f5a6b"
            bordure = "#6f9fd8"
        } else {
            fond = "#3c3f41"
            bordure = "#555555"
        }

        val epaisseur = if ((actif || survol) && !inactif) "2" else "1"
        val opacite = if (inactif) "0.5" else "1.0"

        return """
            -fx-background-color: $fond;
            -fx-background-radius: 8;
            -fx-border-color: $bordure;
            -fx-border-radius: 8;
            -fx-border-width: $epaisseur;
            -fx-cursor: hand;
            -fx-opacity: $opacite;
        """.trimIndent()
    }

    private fun construireVisuelCarte(carte: CarteUI): StackPane {
        val cheminImage = when (carte) {
            is CarteUI.Num            -> "/Images/num_${carte.valeur}.png"
            is CarteUI.BonusPlus      -> "/Images/Bonus_+${carte.valeur}.png"
            is CarteUI.BonusMultiplie -> "/Images/Bonus_x2.png"
            is CarteUI.Stop           -> "/Images/Spe_Stop.png"
            is CarteUI.SecondeChance  -> "/Images/Spe_SecondeChance.PNG"
            is CarteUI.TroisALaSuite  -> "/Images/Spe_3aLaSuite.PNG"
        }

        val flux = cheminImage?.let { chemin ->
            val nomFichier = chemin.substringAfterLast("/")
            val chemins = listOf(
                "src/main/kotlin/vues/Images/$nomFichier",
                "equipe-32/src/main/kotlin/vues/Images/$nomFichier",
                "vues/Images/$nomFichier",
                nomFichier
            )
            chemins.map { java.io.File(it) }.firstOrNull { it.exists() }
        }
        if (flux != null) {
            val image = javafx.scene.image.Image(flux.toURI().toString())
            val vue = javafx.scene.image.ImageView(image)
            vue.fitWidth = 112.0
            vue.fitHeight = 156.0
            vue.isPreserveRatio = true
            return StackPane(vue)
        }

        val rectangle = Rectangle(112.0, 156.0)
        rectangle.fill = Color.web(carte.couleurFond)
        rectangle.arcWidth = 12.0
        rectangle.arcHeight = 12.0
        rectangle.stroke = Color.web(bordureCarte(carte))
        rectangle.strokeWidth = 2.0

        val icone = Label(iconeCarte(carte))
        icone.font = Font.font("System", 28.0)

        val valeur = Label(valeurCarte(carte))
        valeur.font = Font.font("System", FontWeight.BOLD, 26.0)
        valeur.textFill = Color.web("#1a1a1a")
        valeur.textAlignment = TextAlignment.CENTER
        valeur.isWrapText = true
        valeur.maxWidth = 100.0

        val contenu = VBox(2.0, icone, valeur)
        contenu.alignment = Pos.CENTER

        return StackPane(rectangle, contenu)
    }

    private fun bordureCarte(carte: CarteUI): String = when (carte) {
        is CarteUI.Num           -> "#b8a060"
        is CarteUI.BonusPlus     -> "#4a9e6a"
        is CarteUI.BonusMultiplie -> "#4a9e6a"
        is CarteUI.Stop          -> "#c04040"
        is CarteUI.SecondeChance -> "#4080c0"
        is CarteUI.TroisALaSuite -> "#b07030"
    }

    private fun iconeCarte(carte: CarteUI): String = when (carte) {
        is CarteUI.Num            -> "🃏"
        is CarteUI.BonusPlus      -> "⭐"
        is CarteUI.BonusMultiplie -> "✨"
        is CarteUI.Stop           -> "🛑"
        is CarteUI.SecondeChance  -> "🛡"
        is CarteUI.TroisALaSuite  -> "🎴"
    }

    private fun valeurCarte(carte: CarteUI): String = when (carte) {
        is CarteUI.Num            -> carte.valeur.toString()
        is CarteUI.BonusPlus      -> "+${carte.valeur}"
        is CarteUI.BonusMultiplie -> "×2"
        is CarteUI.Stop           -> "STOP"
        is CarteUI.SecondeChance  -> "2ème Chance"
        is CarteUI.TroisALaSuite  -> "3 à la Suite"
    }


    private fun construirePiocheEtDefausse(pioche: List<CarteUI>, defausse: List<CarteUI>): VBox {
        val conteneur = VBox(20.0)
        conteneur.padding = Insets(20.0)
        conteneur.alignment = Pos.TOP_CENTER
        conteneur.minWidth = 220.0
        conteneur.style = "-fx-background-color: #232323;"

        labelManche.font = Font.font("System", FontWeight.BOLD, 40.0)
        labelManche.textFill = Color.web("#f5d76e")

        labelScoreObjectif.font = Font.font("System", 12.0)
        labelScoreObjectif.textFill = Color.web("#888888")

        val titrePioche = Label("Pioche")
        titrePioche.font = Font.font("System", FontWeight.BOLD, 26.0)
        titrePioche.textFill = Color.WHITE

        val dosPioche = Rectangle(150.0, 185.0)
        dosPioche.fill = Color.web("#4a5a6a")
        dosPioche.arcWidth = 10.0; dosPioche.arcHeight = 10.0
        dosPioche.stroke = Color.web("#222222"); dosPioche.strokeWidth = 1.5
        labelCompteurPioche.text = pioche.size.toString()
        labelCompteurPioche.font = Font.font("System", FontWeight.BOLD, 36.0)
        labelCompteurPioche.textFill = Color.WHITE
        val visuelPioche = StackPane(dosPioche, labelCompteurPioche)

        val titreDefausse = Label("Défausse")
        titreDefausse.font = Font.font("System", FontWeight.BOLD, 26.0)
        titreDefausse.textFill = Color.WHITE
        titreDefausse.maxWidth = Double.MAX_VALUE
        titreDefausse.alignment = Pos.CENTER

        val visuelDefausse = if (defausse.isEmpty()) {
            val rect = Rectangle(150.0, 185.0)
            rect.fill = Color.web("#3c3f41")
            rect.arcWidth = 10.0; rect.arcHeight = 10.0
            rect.stroke = Color.web("#555555"); rect.strokeWidth = 1.0
            val label = Label("(vide)"); label.textFill = Color.web("#888888")
            StackPane(rect, label)
        } else construireVisuelCarte(defausse.last())

        val compteurDefausse = Label("${defausse.size} carte(s)")
        compteurDefausse.font = Font.font("System", 22.0)
        compteurDefausse.textFill = Color.web("#cccccc")
        compteurDefausse.maxWidth = Double.MAX_VALUE
        compteurDefausse.alignment = Pos.CENTER

        val titreScores = Label("Scores")
        titreScores.font = Font.font("System", FontWeight.BOLD, 14.0)
        titreScores.textFill = Color.WHITE

        conteneur.children.addAll(
            labelManche,
            labelScoreObjectif,
            titrePioche, visuelPioche,
            titreDefausse, visuelDefausse, compteurDefausse,
            titreScores, conteneurScores
        )
        return conteneur
    }

    private fun construireZoneBasse(surPiocher: () -> Unit, surStop: () -> Unit): VBox {
        val conteneur = VBox(12.0)
        conteneur.padding = Insets(16.0, 20.0, 18.0, 20.0)
        conteneur.style = "-fx-background-color: #1e1e1e;"

        boutonPiocher.style = styleBoutonPiocher("#4a8e5f", "#5a9e6f")
        boutonPiocher.setOnMouseEntered { boutonPiocher.style = styleBoutonPiocher("#5a9e6f", "#5a9e6f") }
        boutonPiocher.setOnMouseExited  { boutonPiocher.style = styleBoutonPiocher("#4a8e5f", "#5a9e6f") }
        boutonPiocher.setOnAction { surPiocher() }

        boutonStop.style = styleBoutonPiocher("#a84040", "#c05050")
        boutonStop.setOnMouseEntered { boutonStop.style = styleBoutonPiocher("#c05050", "#c05050") }
        boutonStop.setOnMouseExited  { boutonStop.style = styleBoutonPiocher("#a84040", "#c05050") }
        boutonStop.setOnAction { surStop() }

        val ligneActions = HBox(16.0, boutonPiocher, boutonStop)
        ligneActions.alignment = Pos.CENTER_LEFT

        zoneStatut.textFill = Color.web("#dddddd")
        zoneStatut.font = Font.font("System", 12.0)

        conteneur.children.addAll(ligneActions)
        return conteneur
    }

    private fun styleBouton(fond: String, survol: String) = """
        -fx-background-color: $fond;
        -fx-text-fill: white;
        -fx-font-weight: bold;
        -fx-font-size: 14px;
        -fx-padding: 12 40 12 40;
        -fx-background-radius: 8;
        -fx-cursor: hand;
    """.trimIndent()

    private fun styleBoutonPiocher(fond: String, survol: String) = """
        -fx-background-color: $fond;
        -fx-text-fill: white;
        -fx-font-weight: bold;
        -fx-font-size: 22px;
        -fx-padding: 22 70 22 70;
        -fx-background-radius: 10;
        -fx-cursor: hand;
    """.trimIndent()

    fun indiquerStatut(message: String) { zoneStatut.text = message }

    fun definirBoutonsActifs(piocherActif: Boolean, stopActif: Boolean) {
        boutonPiocher.isDisable = !piocherActif
        boutonStop.isDisable = !stopActif
        boutonPiocher.opacity = if (piocherActif) 1.0 else 0.4
        boutonStop.opacity = if (stopActif) 1.0 else 0.4
    }

    fun rafraichirJoueurs(joueurs: List<JoueurUI>, surClicJoueur: (JoueurUI) -> Unit) {
        conteneurJoueurs.children.clear()
        for (joueur in joueurs) {
            conteneurJoueurs.children.add(construireZoneJoueur(joueur, surClicJoueur))
        }
    }

    fun rafraichirTourDe(pseudo: String?) {
        labelTourDe.text = if (pseudo != null) "Tour de $pseudo" else ""
    }

    fun rafraichirCompteurPioche(taille: Int) {
        labelCompteurPioche.text = taille.toString()
    }

    fun rafraichirDefausse(defausse: List<CarteUI>) {
        // affichage non requis
    }

    fun rafraichirManche(numero: Int) {
        labelManche.text = "Manche $numero"
    }

    fun rafraichirScoreObjectif(score: Int) {
        labelScoreObjectif.text = "Objectif : $score pts"
    }

    fun rafraichirScores(joueurs: List<JoueurUI>) {
        conteneurScores.children.clear()
        for (joueur in joueurs) {
            val label = Label("${joueur.pseudo} : ${joueur.score} pts")
            label.textFill = Color.web("#cccccc")
            label.font = Font.font("System", 22.0)
            conteneurScores.children.add(label)
        }
    }

    fun afficherCartesTroisALaSuite(cibleNom: String, cartes: List<CarteUI>) {
        val sep = Label("→  $cibleNom :")
        sep.textFill = Color.web("#e8d3a3")
        sep.font = Font.font("System", FontWeight.BOLD, 13.0)
        sep.padding = Insets(0.0, 4.0, 0.0, 12.0)

        ligneCartesDernierePioche.children.add(sep)
        for (carte in cartes) {
            ligneCartesDernierePioche.children.add(construireVisuelCarte(carte))
        }
        zoneDerniereCarte.isVisible = true
        zoneDerniereCarte.isManaged = true
    }


    fun afficherDerniereCarte(carte: CarteUI) {
        zoneDerniereCarte.children.clear()
        ligneCartesDernierePioche.children.clear()
        ligneCartesDernierePioche.alignment = Pos.CENTER

        val label = Label("Carte piochée")
        label.textFill = Color.web("#aaaaaa")
        label.font = Font.font("System", 22.0)

        val rectangle = javafx.scene.shape.Rectangle(140.0, 196.0)
        rectangle.fill = Color.web(carte.couleurFond)
        rectangle.arcWidth = 16.0
        rectangle.arcHeight = 16.0
        rectangle.stroke = Color.web("#f5d76e")
        rectangle.strokeWidth = 3.0

        val cheminImageGrande = when (carte) {
            is CarteUI.Num            -> "/Images/num_${carte.valeur}.png"
            is CarteUI.BonusPlus      -> "/Images/Bonus_+${carte.valeur}.png"
            is CarteUI.BonusMultiplie -> "/Images/Bonus_x2.png"
            is CarteUI.Stop           -> "/Images/Spe_Stop.png"
            is CarteUI.SecondeChance  -> "/Images/Spe_SecondeChance.PNG"
            is CarteUI.TroisALaSuite  -> "/Images/Spe_3aLaSuite.PNG"
        }

        val fichierGrande = cheminImageGrande?.let { chemin ->
            val nomFichier = chemin.substringAfterLast("/")
            listOf(
                "src/main/kotlin/vues/Images/$nomFichier",
                "equipe-32/src/main/kotlin/vues/Images/$nomFichier",
                "vues/Images/$nomFichier",
                nomFichier
            ).map { java.io.File(it) }.firstOrNull { it.exists() }
        }
        val visuCarte = if (fichierGrande != null) {
            val image = javafx.scene.image.Image(fichierGrande.toURI().toString())
            val vue = javafx.scene.image.ImageView(image)
            vue.fitWidth = 140.0
            vue.fitHeight = 196.0
            vue.isPreserveRatio = true
            StackPane(rectangle, vue)
        } else {
            val icone = Label(iconeCarte(carte))
            icone.font = Font.font("System", 22.0)
            val valeur = Label(valeurCarte(carte))
            valeur.font = Font.font("System", FontWeight.BOLD, 16.0)
            valeur.textFill = Color.web("#1a1a1a")
            valeur.textAlignment = javafx.scene.text.TextAlignment.CENTER
            valeur.isWrapText = true
            valeur.maxWidth = 72.0
            val contenuCarte = VBox(4.0, icone, valeur)
            contenuCarte.alignment = Pos.CENTER
            StackPane(rectangle, contenuCarte)
        }
        ligneCartesDernierePioche.children.add(visuCarte)

        zoneDerniereCarte.children.addAll(label, ligneCartesDernierePioche)
        zoneDerniereCarte.isVisible = true
        zoneDerniereCarte.isManaged = true
    }

    fun masquerDerniereCarte() {
        zoneDerniereCarte.isVisible = false
        zoneDerniereCarte.isManaged = false
    }

    fun changerScene(nouvelleScene: javafx.scene.Scene) {
        val stage = boutonPiocher.scene?.window as? javafx.stage.Stage
        stage?.scene = nouvelleScene
        stage?.isFullScreen = true
    }
}