package Vue

import Controleur.ControleurBoutonPioche
import Controleur.ControleurBoutonStop
import Controleur.ControleurChoixCible
import Controleur.ControleurEtatJoueurs
import Controleur.ControleurFermerScore
import Controleur.ControleurNBPiocheDefausse
import Controleur.ControleurVueScore
import Controleur.ControleurBoutonHome
import Deck
import Joueur
import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.etats.EtatPartie
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Region
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import java.awt.event.ActionEvent
import java.io.FileInputStream

class Flip7Jeu(Joueurs: List<String>,scoreFinPartie: Int) : BorderPane() {
    val listJoueur  = mutableListOf<IJoueur>()
    val boutonPioche = Button("PIOCHER")
    val boutonStop = Button("STOP")
    val boutonHome = Button("HOME")
    val boutonScore = Button("SCORE")
    var LabelJoueurs =  arrayListOf<Label>()
    val espaceGauche = Region()
    val espaceDroite = Region()
    val espaceHautGauche = Region()
    val espaceHautDroite = Region()
    var carteCibleEnAttente: Any? = null
    val joueursPerdusNotifies = mutableSetOf<Int>()

    var taillePioche = Label("")
    var tailleDefausse = Label("")
    var main1 = HBox()
    var main2 = HBox()
    var main3 = HBox()
    var main4 = HBox()
    var flip7 : Flip7

    val controleurEtat = ControleurEtatJoueurs(this)
    init {
        this.style = "-fx-background-color: linear-gradient(to bottom, #0b7f2a, #13d6d0);"
        for (i in 0 until Joueurs.size) {
            listJoueur.add(Joueur(Joueurs[i]))
            val labelJoueur = Label(Joueurs[i])
            labelJoueur.userData = i
            labelJoueur.onMouseClicked = ControleurChoixCible(this)
            LabelJoueurs.add(labelJoueur)
        }
        flip7 = Flip7(listJoueur.size,listJoueur,Deck().donneDeck(), scoreFinPartie = scoreFinPartie)

        // Style Debut//
        for ( i in LabelJoueurs){
            i.padding= Insets(15.0,50.0,15.0, 50.0)
            i.style="-fx-background-color: white;" +
                    "-fx-background-radius: 100;" +
                    "-fx-border-color: black;" +
                    "-fx-text-fill:red; ;" +
                    "-fx-font-size: 20;"+
                    "-fx-border-width: 3;"+
                    "-fx-border-radius: 90;"
        }

        val imgpioche = ImageView(
            Image(FileInputStream("img/Red_Deck.png"))
        )


        val zonepio = VBox(imgpioche)
        zonepio.alignment = Pos.CENTER
        zonepio.padding = Insets(30.0, 0.0, 20.0, 0.0)

        val imgdefausse = ImageView(
            Image(FileInputStream("img/Blue_Deck.png"))
        )


        val zonedef = VBox(imgdefausse)
        zonedef.alignment = Pos.CENTER
        zonedef.padding = Insets(30.0, 0.0, 20.0, 0.0)


        boutonPioche.prefHeight = 200.0
        boutonPioche.prefWidth = 200.0
        //boutonPioche.rotate = 180.0
        val styleBoutonPiocheNormal = "-fx-background-color: blue;" +
                "-fx-background-radius: 100;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 20;"+
                "-fx-cursor: hand;" +
                "-fx-border-width: 6;"+
                "-fx-border-radius: 90;"+
                "-fx-border-color: white;"
        val styleBoutonPiocheSurvol = "-fx-background-color: #0000a8;" +
                "-fx-background-radius: 100;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 20;"+
                "-fx-cursor: hand;" +
                "-fx-border-width: 6;"+
                "-fx-border-radius: 90;"+
                "-fx-border-color: white;"
        boutonPioche.style = styleBoutonPiocheNormal
        boutonPioche.setOnMouseEntered { boutonPioche.style = styleBoutonPiocheSurvol }
        boutonPioche.setOnMouseExited { boutonPioche.style = styleBoutonPiocheNormal }

        boutonStop.prefHeight = 200.0
        boutonStop.prefWidth = 200.0
        val styleBoutonStopNormal = "-fx-background-color: red;" +
                "-fx-background-radius: 100;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 20;"+
                "-fx-cursor: hand;" +
                "-fx-border-width: 6;"+
                "-fx-border-radius: 90;"+
                "-fx-border-color: white;"
        val styleBoutonStopSurvol = "-fx-background-color: #b30000;" +
                "-fx-background-radius: 100;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 20;"+
                "-fx-cursor: hand;" +
                "-fx-border-width: 6;"+
                "-fx-border-radius: 90;"+
                "-fx-border-color: white;"
        boutonStop.style = styleBoutonStopNormal
        boutonStop.setOnMouseEntered { boutonStop.style = styleBoutonStopSurvol }
        boutonStop.setOnMouseExited { boutonStop.style = styleBoutonStopNormal }


        boutonHome.prefHeight = 120.0
        boutonHome.prefWidth = 160.0
        val styleBoutonHomeNormal = """
        -fx-background-color: #cde447;
        -fx-background-radius: 40;
        -fx-text-fill: black;
        -fx-font-size: 20;
        -fx-font-weight: bold;
        -fx-cursor: hand;
        -fx-border-width: 4;
        -fx-border-radius: 35;
        -fx-border-color: white;
        """
        val styleBoutonHomeSurvol = """
        -fx-background-color: #b6c53a;
        -fx-background-radius: 40;
        -fx-text-fill: black;
        -fx-font-size: 20;
        -fx-font-weight: bold;
        -fx-cursor: hand;
        -fx-border-width: 4;
        -fx-border-radius: 35;
        -fx-border-color: white;
        """
        boutonHome.style = styleBoutonHomeNormal
        boutonHome.setOnMouseEntered { boutonHome.style = styleBoutonHomeSurvol }
        boutonHome.setOnMouseExited { boutonHome.style = styleBoutonHomeNormal }

        boutonScore.prefHeight = 120.0
        boutonScore.prefWidth = 160.0
        val styleBoutonScoreNormal = """
        -fx-background-color: #cde447;
        -fx-background-radius: 40;
        -fx-text-fill: black;
        -fx-font-size: 20;
        -fx-font-weight: bold;
        -fx-cursor: hand;
        -fx-border-width: 4;
        -fx-border-radius: 35;
        -fx-border-color: white;
        """
        val styleBoutonScoreSurvol = """
        -fx-background-color: #b6c53a;
        -fx-background-radius: 40;
        -fx-text-fill: black;
        -fx-font-size: 20;
        -fx-font-weight: bold;
        -fx-cursor: hand;
        -fx-border-width: 4;
        -fx-border-radius: 35;
        -fx-border-color: white;
        """
        boutonScore.style = styleBoutonScoreNormal
        boutonScore.setOnMouseEntered { boutonScore.style = styleBoutonScoreSurvol }
        boutonScore.setOnMouseExited { boutonScore.style = styleBoutonScoreNormal }

        taillePioche.font = Font.font("verdana",FontWeight.BOLD,20.0)

        tailleDefausse.font = Font.font("verdana",FontWeight.BOLD,20.0)

        HBox.setHgrow(espaceGauche, Priority.ALWAYS)
        HBox.setHgrow(espaceDroite, Priority.ALWAYS)
        HBox.setHgrow(espaceHautGauche, Priority.ALWAYS)
        HBox.setHgrow(espaceHautDroite, Priority.ALWAYS)

        // Style Fin //

        // Contrôleur debut //
        boutonPioche.onAction = ControleurBoutonPioche(this)
        boutonStop.onAction = ControleurBoutonStop(this)
        boutonScore.onAction = ControleurVueScore(this)
        boutonHome.onAction = ControleurBoutonHome()

        val controleurNbcartes = ControleurNBPiocheDefausse(this)
        ControleurEtatJoueurs(this).majEtatJoueurs()

        // Contrôleur Fin



        main1.alignment = Pos.CENTER
        main2.alignment = Pos.CENTER
        main3.alignment = Pos.CENTER
        main4.alignment = Pos.CENTER

        if (LabelJoueurs.size == 4 ){
            val ZoneBas = HBox()
            val main_Joueur1 = VBox()
            main_Joueur1.children.addAll(LabelJoueurs[flip7.joueurCourant],main1)
            main_Joueur1.alignment = Pos.CENTER
            ZoneBas.alignment = Pos.CENTER
            ZoneBas.children.addAll(boutonPioche, espaceGauche,main_Joueur1, espaceDroite,boutonStop)



            val ZoneGauche = HBox()
            ZoneGauche.alignment = Pos.CENTER
            LabelJoueurs[flip7.joueurCourant+1].rotate = 90.0
            ZoneGauche.children.addAll(main2,LabelJoueurs[flip7.joueurCourant+1])


            val ZoneHaut = HBox()
            ZoneHaut.alignment = Pos.CENTER
            val main_JoueurHaut = VBox()
            main_JoueurHaut.alignment = Pos.CENTER
            main_JoueurHaut.children.addAll(main3, LabelJoueurs[2])

            ZoneHaut.children.addAll(boutonHome, espaceHautGauche, main_JoueurHaut, espaceHautDroite, boutonScore)

            val ZoneDroite = HBox()
            ZoneDroite.alignment = Pos.CENTER
            LabelJoueurs[flip7.joueurCourant+3].rotate = 270.0
            ZoneDroite.children.addAll(LabelJoueurs[flip7.joueurCourant+3],main4)

            if (flip7.etatPartie == EtatPartie.MANCHE_TERMINEE){
                println("manche")
            }
            this.bottom = ZoneBas
            this.left = ZoneGauche
            this.top = ZoneHaut
            this.right = ZoneDroite
        }

        if (LabelJoueurs.size == 2 ){

            val ZoneBas = HBox()
            val main_Joueur1 = VBox()
            main_Joueur1.children.addAll(LabelJoueurs[flip7.joueurCourant],main1)
            main_Joueur1.alignment = Pos.CENTER
            ZoneBas.alignment = Pos.CENTER
            ZoneBas.children.addAll(boutonPioche, espaceGauche,main_Joueur1, espaceDroite,boutonStop)




            val ZoneHaut = HBox()
            ZoneHaut.alignment = Pos.CENTER
            val main_JoueurHaut = VBox()
            main_JoueurHaut.alignment = Pos.CENTER
            main_JoueurHaut.children.addAll(main2, LabelJoueurs[1])

            ZoneHaut.children.addAll(boutonHome, espaceHautGauche, main_JoueurHaut, espaceHautDroite, boutonScore)


            this.bottom = ZoneBas
            this.top = ZoneHaut
        }
        if(LabelJoueurs.size == 3 ){
            val ZoneBas = HBox()
            val main_Joueur1 = VBox()
            main_Joueur1.alignment = Pos.CENTER
            main_Joueur1.children.addAll(LabelJoueurs[flip7.joueurCourant],main1)
            ZoneBas.alignment = Pos.CENTER
            ZoneBas.children.addAll(boutonPioche, espaceGauche,main_Joueur1, espaceDroite,boutonStop)

            val ZoneGauche = HBox()
            ZoneGauche.alignment = Pos.CENTER
            LabelJoueurs[flip7.joueurCourant+1].rotate = 90.0
            ZoneGauche.children.addAll(main2,LabelJoueurs[flip7.joueurCourant+1])


            val ZoneDroite = HBox()
            ZoneDroite.alignment = Pos.CENTER
            LabelJoueurs[flip7.joueurCourant+2].rotate = 270.0
            ZoneDroite.children.addAll(LabelJoueurs[flip7.joueurCourant+2], main3)


            val ZoneHaut = HBox()
            ZoneHaut.alignment = Pos.CENTER

            ZoneHaut.children.addAll(boutonHome, espaceHautDroite, boutonScore)


            this.bottom = ZoneBas
            this.top = ZoneHaut
            this.left = ZoneGauche
            this.right = ZoneDroite
        }
        val ZoneCentre = HBox(50.0)
        taillePioche.text = flip7.taillePioche.toString()
        tailleDefausse.text = flip7.defausse.size.toString()
        val vPioche = VBox()
        vPioche.children.addAll(zonepio,taillePioche)
        val vDefausse = VBox()
        vDefausse.children.addAll(zonedef,tailleDefausse)


        vPioche.alignment = Pos.CENTER
        vDefausse.alignment = Pos.CENTER
        ZoneCentre.alignment = Pos.CENTER
        ZoneCentre.children.addAll(vPioche, vDefausse )

        this.center = ZoneCentre


    }

    fun majMain(main : HBox, carte: Carte) {
        // pour le type de main c'est pas que Hbox en fonction des joueurs elle passe en Vbox
        // je te conseil de le mettre en Any et de faire des conditions en fonction des type attendu
        // exemple
        // if (main is HBox){} ou if (main is VBox){}
        var nomcarte = carte.toString()
        val img = ImageView(
            Image(FileInputStream("img/cartes_bonne_taille/$nomcarte.png"))
        )
        var imgcarte = VBox(img)
        imgcarte.alignment = Pos.CENTER
        imgcarte.padding = Insets(30.0, 0.0, 20.0, 0.0)
        imgcarte.prefHeight(50.0)
        imgcarte.prefWidth(50.0)
        main.children.add(imgcarte)
    }

    fun selecMain(joueur: Int) : HBox{
        if (joueur == 0){
            return main1
        }
        if (joueur == 1){
            return main2
        }
        if (joueur == 2){
            return main3
        }
        if (joueur == 3){
            return main4
        }
        return HBox()
    }

    fun supDerniereCarte(main: HBox){
        main.children.removeLast()
    }

    fun resetmains(){
        for (n in 0..9999){
            main1.children.removeLastOrNull()
        }
        for (n in 0..9999){
            main2.children.removeLastOrNull()
        }
        for (n in 0..9999){
            main3.children.removeLastOrNull()
        }
        for (n in 0..9999){
            main4.children.removeLastOrNull()
        }
    }

    fun resetmain(main: HBox) {
        for (n in 0..9999){
            main.children.removeLastOrNull()
        }
    }

}