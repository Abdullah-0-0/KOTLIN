package iut.info1.flip7.prototype.vue

import java.text.Normalizer
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

object PseudoBan {

    private val motsInterdits: Set<String> = setOf(
        "con", "conne", "connard", "connasse", "abruti", "idiot",
        "imbecile", "imbécile", "cretin", "crétin", "debile",
        "débile", "demeure", "demeuré", "tare", "taré",
        "cingle", "cinglé", "bouffon", "guignol", "tocard",
        "clown", "minable", "nul", "loser", "rate", "raté",
        "lache", "lâche", "ordure", "raclure", "vermine",
        "pourriture", "parasite", "cafard", "dechet",
        "déchet", "pitoyable", "pathetique", "pathétique",
        "incapable", "ridicule", "grotesque", "stupide",
        "attarde", "attardé", "cassos",
        "merde", "putain", "bordel", "fdp", "ntm",
        "tg", "tagueule", "ta gueule", "nique",
        "niquer", "nique ta mere", "nique ta mère",
        "enfoire", "enfoiré", "va te faire foutre",
        "salope", "pute",
        "ferme ta gueule", "salecon", "groscon",
        "fuck", "fucking", "shit", "bullshit",
        "asshole", "bitch", "bastard", "motherfucker",
        "dumbass", "jackass", "moron", "jerk",
        "scumbag", "wanker", "twat", "prick",
        "cunt", "whore", "slut",
        "porno", "porn", "pornographie", "xxx",
        "sexe", "sex", "sexual", "penis",
        "pénis", "bite", "queue", "couilles",
        "vagin", "chatte", "nichons", "boobs",
        "tits", "dick", "cock", "pussy",
        "branlette", "masturbation", "fellation",
        "pipe", "gangbang",
        "suicide", "tuetoi", "tue-toi", "tue toi",
        "meurt", "killyourself", "creve",
        "crève", "vamourir", "va mourir",
        "nazi", "hitler", "hitlair", "gestapo",
        "kkk", "terroriste", "terrorism",
        "isis", "daesh", "extremiste", "extrémiste",
        "cocaine", "heroine", "héroïne",
        "meth", "methamphetamine", "crack",
        "lsd", "mdma", "ecstasy", "weed", "cannabis", "marijuana",
        "arme", "fusil", "pistolet",
        "revolver", "ak47", "kalachnikov",
        "grenade", "bombe", "explosif",
        "admin", "administrator", "administrateur",
        "moderator", "moderateur", "modérateur",
        "mod", "staff", "support", "owner",
        "founder", "official", "officiel",
        "system", "console", "root",
        "sysadmin", "serviceclient",
        "arnaque", "arnaqueur", "escroc",
        "voleur", "criminel", "mafieux",
        "dealer", "trafiquant",
        "trump", "tromp", "poutine", "sarkozy", "sarcosy",
        "sarcozy", "putine", "kim jong", "kim jong un", "king jong",
        "king jong un", "kimp jong", "kimp jong un", "kin jong", "kin jong un",
    )

    private fun normaliser(texte: String): String {
        val sansAccents = Normalizer.normalize(texte, Normalizer.Form.NFD)
            .replace(Regex("\\p{M}"), "")
        return sansAccents
            .lowercase()
            .replace("0", "o")
            .replace("1", "i")
            .replace("3", "e")
            .replace("4", "a")
            .replace("5", "s")
            .replace("7", "t")
            .replace(Regex("[^a-z0-9]"), "")
    }

    fun estAutorise(pseudo: String): Boolean = trouverMotInterdit(pseudo) == null

    fun trouverMotInterdit(pseudo: String): String? {
        val pseudoNormalise = normaliser(pseudo)
        return motsInterdits.firstOrNull { mot ->
            pseudoNormalise.contains(normaliser(mot))
        }
    }
}

class VueAccueil : BorderPane() {

    private val champsNoms = mutableListOf<TextField>()
    private val conteneurChamps = VBox(10.0)
    private val labelErreur = Label("")
    private val boutonDemarrer = Button("Démarrer la partie")
    private var nbJoueurs: Int = 2
    private val labelNbJoueurs = Label("2")
    private val boutonMoins = Button("−")
    private val boutonPlus = Button("+")

    private val scoresPossibles = listOf(50, 100, 150, 200)
    private var indexScore: Int = 3  // 200 par défaut
    private val labelScore = Label("200")
    private val boutonScoreMoins = Button("−")
    private val boutonScorePlus = Button("+")

    init {
        this.padding = Insets(40.0)
        this.style = "-fx-background-color: #2b2b2b;"

        val conteneurTop = VBox(30.0)
        val conteneurCentral = VBox(20.0)
        val conteneurBottom = VBox(20.0)

        conteneurTop.alignment = Pos.TOP_CENTER
        conteneurCentral.alignment = Pos.CENTER
        conteneurBottom.alignment = Pos.BOTTOM_CENTER

        val titre = Label("FLIP7 — Configuration")
        titre.textFill = Color.web("#ffffff")
        titre.font = Font.font("System", FontWeight.BOLD, 80.0)

        val labelSpinner = Label("Nombre de joueurs :")
        labelSpinner.textFill = Color.WHITE
        labelSpinner.font = Font.font("System", FontWeight.BOLD, 35.0)

        labelNbJoueurs.font = Font.font("System", FontWeight.BOLD, 30.0)
        labelNbJoueurs.textFill = Color.WHITE
        labelNbJoueurs.minWidth = 36.0
        labelNbJoueurs.alignment = Pos.CENTER

        val styleBoutonCompteur = """
            -fx-background-color: #4a4a4a;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            -fx-font-size: 40px;
            -fx-min-width: 60px;
            -fx-min-height: 60px;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """.trimIndent()

        boutonMoins.style = styleBoutonCompteur
        boutonPlus.style = styleBoutonCompteur

        boutonMoins.setOnAction {
            if (nbJoueurs > 2) {
                nbJoueurs--
                labelNbJoueurs.text = nbJoueurs.toString()
                reconstruireChamps(nbJoueurs)
            }
        }
        boutonPlus.setOnAction {
            if (nbJoueurs < 4) {
                nbJoueurs++
                labelNbJoueurs.text = nbJoueurs.toString()
                reconstruireChamps(nbJoueurs)
            }
        }

        val ligneSpinner = HBox(16.0, labelSpinner, boutonMoins, labelNbJoueurs, boutonPlus)
        ligneSpinner.alignment = Pos.CENTER

        val labelScoreTexte = Label("Score objectif :")
        labelScoreTexte.textFill = Color.WHITE
        labelScoreTexte.font = Font.font("System", FontWeight.BOLD, 25.0)

        labelScore.font = Font.font("System", FontWeight.BOLD, 30.0)
        labelScore.textFill = Color.WHITE
        labelScore.minWidth = 52.0
        labelScore.alignment = Pos.CENTER

        boutonScoreMoins.style = styleBoutonCompteur
        boutonScorePlus.style = styleBoutonCompteur

        boutonScoreMoins.setOnAction {
            if (indexScore > 0) {
                indexScore--
                labelScore.text = scoresPossibles[indexScore].toString()
            }
        }
        boutonScorePlus.setOnAction {
            if (indexScore < scoresPossibles.size - 1) {
                indexScore++
                labelScore.text = scoresPossibles[indexScore].toString()
            }
        }

        val ligneScore = HBox(16.0, labelScoreTexte, boutonScoreMoins, labelScore, boutonScorePlus)
        ligneScore.alignment = Pos.CENTER

        boutonDemarrer.style = """
            -fx-background-color: #ffffff;
            -fx-text-fill: #1e1e1e;
            -fx-font-weight: bold;
            -fx-font-size: 30px;
            -fx-padding: 10 20 10 20;
            -fx-background-radius: 5;
        """.trimIndent()

        labelErreur.textFill = Color.RED
        labelErreur.font = Font.font("System", 13.0)

        val esapceTop1 = Label("")
        val esapceTop2 = Label("")
        val espaceTop3 = Label("")
        val espaceBottom1 = Label("")
        val espaceBottom2 = Label("")

        conteneurTop.children.addAll(esapceTop1, esapceTop2, espaceTop3, titre, ligneSpinner, ligneScore)
        conteneurCentral.children.addAll(conteneurChamps, labelErreur)
        conteneurBottom.children.addAll(boutonDemarrer, espaceBottom1, espaceBottom2)
        this.top = conteneurTop
        this.center = conteneurCentral
        this.bottom = conteneurBottom

        reconstruireChamps(nbJoueurs)


    }

    private fun reconstruireChamps(nbJoueurs: Int) {
        val nomsExistants = champsNoms.map { it.text }
        champsNoms.clear()
        conteneurChamps.children.clear()

        for (i in 0 until nbJoueurs) {
            val champ = TextField(nomsExistants.getOrElse(i) { "" })
            champ.promptText = "Joueur${i + 1}"

            champ.textProperty().addListener { _, ancienTexte, nouveauTexte ->
                if (nouveauTexte.length > 12) {
                    champ.text = ancienTexte
                }
            }

            champ.style = """
                -fx-background-color: #3c3f41;
                -fx-text-fill: white;
                -fx-prompt-text-fill: #777777;
                -fx-border-color: #555555;
                -fx-border-radius: 6;
                -fx-background-radius: 6;
                -fx-padding: 8 12 8 12;
                -fx-font-size: 30px;
            """.trimIndent()
            champ.prefWidth = 300.0

            val ligne = HBox(champ)
            ligne.alignment = Pos.CENTER
            conteneurChamps.children.add(ligne)
            champsNoms.add(champ)
        }
    }

    fun donneScoreObjectif(): Int = scoresPossibles[indexScore]

    fun sAbonnerBoutonDemarrer(callback: () -> Unit) {
        this.boutonDemarrer.setOnAction { callback() }
    }

    fun verifierEtDonnerNoms(): List<String>? {
        val listeNoms = champsNoms.map { it.text.trim() }

        if (listeNoms.any { it.isEmpty() }) {
            labelErreur.text = "Veuillez renseigner un nom pour chaque joueur."
            return null
        }

        for ((index, nom) in listeNoms.withIndex()) {
            if (nom.length > 12) {
                labelErreur.text = "Le pseudo du joueur ${index + 1} ne doit pas dépasser 12 caractères."
                return null
            }
        }

        for ((index, nom) in listeNoms.withIndex()) {
            val motInterdit = PseudoBan.trouverMotInterdit(nom)
            if (motInterdit != null) {
                labelErreur.text = "Le pseudo du joueur ${index + 1} (\"$nom\") n'est pas autorisé."
                return null
            }
        }

        labelErreur.text = ""
        return listeNoms
    }
}