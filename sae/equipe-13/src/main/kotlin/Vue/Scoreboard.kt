package Vue

import iut.info1.flip7.IJoueur
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Region
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.Text
import java.io.FileInputStream
import javax.imageio.stream.FileImageInputStream

class Scorebord(name : MutableList<IJoueur>, score : Map<Int, Int>) : VBox(){

    //titre
    val titre = Label()



    //partie score
    val bloqueDesScore = VBox()
    //p0
    val sc0= HBox()
    val p0 = Text()
    val n0 = Text()
    //p1
    val sc1= HBox()
    val p1 = Text()
    val n1 = Text()
    //p2
    val sc2= HBox()
    val p2 = Text()
    val n2 = Text()
    //p3
    val sc3= HBox()
    val p3 = Text()
    val n3 = Text()

    //bouton
    val boutonFermer = Button("Fermer")

    val zoneBouton  = HBox()

    init {

        //titre
        titre.text="score de la partie"
        titre.font = Font.font("Arial", FontWeight.BOLD, 32.0)
        titre.textFill = Color.BLUE

        titre.style = """
        -fx-background-color: yellow;
        -fx-background-radius: 10;
        -fx-padding: 15;
        """
        titre.maxWidth = 700.0
        titre.alignment = Pos.CENTER


        //inclusion des joueur et de leur score

        //on crée scoring pour avoir un tableau des scores
        val scoring = mutableListOf<Int>()
        for (i in 0 ..< score.size){
            scoring.add(score[i]!!)
        }
        //on utilise zip pour les liées (fonction découverte grace a l'ia
        //puis on a utiliser sortedbydescending pour trié deux tableau dans l'ordre decroissant dans l'ordre décroissant
        val sorted=scoring.zip(name).sortedByDescending { it.first }



        //--------------------------------------original
        //en fonction de la taille
        //si il y a deux joueur
        /*if (name.size==2){
            p0.text=sorted[0].second.donneNom()
            n0.text=sorted[0].first.toString()
            p1.text=sorted[1].second.donneNom()
            n1.text=sorted[1].first.toString()
        }else{
            //si il y a 3 joueur
            if(name.size==3){
                p0.text=sorted[0].second.donneNom()
                n0.text=sorted[0].first.toString()
                p1.text=sorted[1].second.donneNom()
                n1.text=sorted[1].first.toString()
                p2.text=sorted[2].second.donneNom()
                n2.text=sorted[2].first.toString()
            }
            //si il y a 4 joueur
            else{
                p0.text=sorted[0].second.donneNom()
                n0.text=sorted[0].first.toString()
                p1.text=sorted[1].second.donneNom()
                n1.text=sorted[1].first.toString()
                p2.text=sorted[2].second.donneNom()
                n2.text=sorted[2].first.toString()
                p3.text=sorted[3].second.donneNom()
                n3.text=sorted[3].first.toString()
            }
        }*/

        //amelioration avec ia
        //crée les ligne de manière automatique pour ne pas avoir a répéter les modifications
        for ((index, element) in sorted.withIndex()) {

            val points = element.first
            val joueur = element.second

            //création de la ligne
            val ligne = HBox()

            //on récupere le nom de l'utilisateur
            val nom = Label(joueur.donneNom())
            nom.prefWidth = 180.0
            nom.prefHeight=50.0
            nom.alignment = Pos.CENTER_LEFT

            nom.style = """
        -fx-background-color:#29c2e6;
        -fx-background-radius:15 0 0 15;
        -fx-font-size:24;
        -fx-font-weight:bold;
        -fx-padding:20;
    """
            //on récupere le score du meme utilisateur
            val score = Label("$points points")
            score.prefWidth = 500.0
            score.prefHeight=75.0
            score.alignment = Pos.CENTER

            score.style = """
        -fx-background-color:#f4d400;
        -fx-background-radius:0 15 15 0;
        -fx-font-size:24;
        -fx-font-weight:bold;
    """

            //on verifie si il est premier pour lui mettre une couronne pour lui donner ou faire un espace pour que toutes les lignes soit aligné
            if (index == 0) {

                val couronne = ImageView(
                    Image(FileInputStream("src/main/ressources/Assert/imagecouronedechatgpt.png"))
                )

                couronne.fitWidth = 45.0
                couronne.fitHeight = 45.0

                ligne.children.add(couronne)

            } else {

                // Pour que toutes les lignes restent alignées
                val espace = Region()
                espace.prefWidth = 45.0
                ligne.children.add(espace)
            }

            ligne.children.addAll(nom, score)
            ligne.alignment = Pos.CENTER

            bloqueDesScore.children.add(ligne)
        }

        //bouton
        zoneBouton.alignment = Pos.CENTER
        zoneBouton.padding = Insets(20.0)
        zoneBouton.children.add(boutonFermer)
        boutonFermer.style = """
        -fx-background-color: yellow;
        -fx-text-fill: black;
        -fx-font-size:18;
        -fx-font-weight:bold;
        -fx-background-radius:10;
    """
        boutonFermer.setOnMouseEntered{
            boutonFermer.style="""
                -fx-background-color: red;
                -fx-text-fill: white;
                -fx-font-size:18;
                -fx-font-weight:bold;
                -fx-background-radius:10;
            """
        }
        boutonFermer.setOnMouseExited{
            boutonFermer.style="""
                -fx-background-color: yellow;
                -fx-text-fill: black;
                -fx-font-size:18;
                -fx-font-weight:bold;
                -fx-background-radius:10;
            """
        }

        boutonFermer.prefWidth = 180.0
        boutonFermer.prefHeight = 60.0


        /*sc0.children.addAll(p0,n0)
        sc1.children.addAll(p1,n1)
        sc2.children.addAll(p2,n2)
        sc3.children.addAll(p3,n3)
        bloqueDesScore.children.addAll(sc0,sc1,sc2,sc3)*/
        bloqueDesScore.spacing=50.0

        this.children.addAll(titre,bloqueDesScore,zoneBouton)
        this.style="-fx-background-color: linear-gradient(to bottom right, #d62828 0%, #e76f51 50%, #e07a3f 100%)"
        this.padding = Insets(30.0)
        this.spacing = 30.0
        this.alignment = Pos.TOP_CENTER

    }



}