package IHM.vue

import IHM.controleur.ControleurCheckEndManche
import IHM.controleur.ControleurStopBtn
import IHM.controleur.Controleur3ALaSuiteBtn
import IHM.controleur.ControleurAfficheMainJoueur
import IHM.controleur.ControleurCheckButton
import IHM.controleur.ControleurFreezeBtn
import IHM.controleur.ControleurPioche
import IHM.modele.ModeleFlip7
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.etats.EtatJoueur
import javafx.event.ActionEvent
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment
import javafx.scene.text.TextFlow

class VueFlip7 : GridPane() {

    val mancheLabel = Label("Manche 1")
    val joueurCourantLabel = Label("Au tour de ")
    val joueurImages = mutableListOf<ImageView>()
    val etatPartieLabel = Label()
    private val historiqueLabel = Label("Historique")
    val contenuHistoriqueLabel = TextFlow()

    val joueursBox: HBox
    val cartesBox : HBox
    val piocheButton: Button
    val stopButton: Button
    val freezeButton: Button
    val troisSuiteButton: Button
    val actionBottonBox: HBox
    val piocheStack: StackPane
    val piocheLabelBox: VBox
    val piocheTextLabel: Label

    val imgCross = Image(javaClass.getResource("/img/cross.png")?.toExternalForm())
    val imgWait  = Image(javaClass.getResource("/img/wait.png")?.toExternalForm())
    val imgStop  = Image(javaClass.getResource("/img/stop.png")?.toExternalForm())
    val imgPlay  = Image(javaClass.getResource("/img/play.png")?.toExternalForm())


    private val idiqlatRegular = Font.loadFont(javaClass.getResource("/fonts/Idiqlat-Regular.ttf")?.toExternalForm(), 14.0)
    private val idiqlatLight = Font.loadFont(javaClass.getResource("/fonts/Idiqlat-Light.ttf")?.toExternalForm(), 14.0)
    private val idiqlatExtraLight = Font.loadFont(javaClass.getResource("/fonts/Idiqlat-ExtraLight.ttf")?.toExternalForm(), 14.0)

    init {
        this.style = "-fx-background-color: #001436"
        padding = Insets(10.0)
        hgap = 10.0
        vgap = 10.0

        // ====== STRUCTURE GRID RESPONSIVE ======
        columnConstraints.addAll(
            ColumnConstraints().apply { hgrow = Priority.ALWAYS; percentWidth = 25.0 },
            ColumnConstraints().apply { hgrow = Priority.ALWAYS; percentWidth = 50.0 },
            ColumnConstraints().apply { hgrow = Priority.ALWAYS; percentWidth = 25.0 }
        )

        rowConstraints.addAll(
            RowConstraints().apply { vgrow = Priority.NEVER;percentHeight = 10.0 },
            RowConstraints().apply { vgrow = Priority.ALWAYS;percentHeight = 10.0  },
            RowConstraints().apply { vgrow = Priority.ALWAYS;percentHeight = 35.0  },
            RowConstraints().apply { vgrow = Priority.ALWAYS;percentHeight = 45.0  }
        )

        joueursBox = HBox(5.0)
        joueursBox.style = "-fx-background-color: #0D2A52;"

        cartesBox = HBox(5.0)
        cartesBox.alignment = Pos.CENTER
        cartesBox.style = "-fx-background-color: #0D2A52;"

        val piocheBox = VBox(30.0)
        piocheStack = StackPane()
        val nbVisibles = 4
        val offset = 7.0
        val off = nbVisibles*offset

        for (i in 0 until nbVisibles) {
            val iv = ImageView(Image("file:src/main/resources/img/Dos.png"))
            iv.fitWidth = 130.0
            iv.fitHeight = 195.0
            iv.isPreserveRatio = true
            iv.translateY = (-i * offset)+off

            piocheStack.children.add(iv)
        }

        piocheButton = Button()
        piocheButton.prefWidth = 130.0
        piocheButton.prefHeight = 195.0
        piocheButton.style = "-fx-background-color: transparent;"

        piocheStack.children.add(piocheButton)

        val piocheArrowLabel = Label("▲")
        piocheArrowLabel.style = "-fx-font-size: 14px;" +
                "-fx-text-fill: #F4C95D;"+
                "-fx-font-family: 'Idiqlat';"
        piocheTextLabel = Label("Tirer une carte (94)")
        piocheTextLabel.style = "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #F4C95D;"+
                "-fx-font-family: 'Idiqlat Light';"

        piocheLabelBox = VBox()
        piocheLabelBox.children.addAll(piocheArrowLabel, piocheTextLabel)
        piocheLabelBox.alignment = Pos.CENTER

        piocheBox.style = "-fx-background-color: #0D2A52;"
        piocheBox.children.addAll(piocheStack, piocheLabelBox)

        stopButton = Button("Stop")
        stopButton.prefWidth = 200.0
        stopButton.prefHeight = 75.0
        stopButton.style = "-fx-font-size: 34px; " +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FFF;" +
                "-fx-background-color: #E63926;" +
                "-fx-border-color: #F4C95D;" +
                "-fx-border-width: 2;"+
                "-fx-font-family: 'Idiqlat';"

        freezeButton = Button("Carte Stop")
        freezeButton.prefWidth = 150.0
        freezeButton.prefHeight = 75.0
        freezeButton.style = "-fx-font-size: 23px; " +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FFF;" +
                "-fx-background-color: #4CC9F0;" +
                "-fx-border-color: #F4C95D;" +
                "-fx-border-width: 2;"+
                "-fx-font-family: 'Idiqlat';"+
                "-fx-padding:0 6 0 6;"
        freezeButton.wrapTextProperty().set(true)

        troisSuiteButton = Button("Carte 3 à la suite")
        troisSuiteButton.prefWidth = 150.0
        troisSuiteButton.prefHeight = 75.0
        troisSuiteButton.style = "-fx-font-size: 23px; " +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FFF;" +
                "-fx-background-color: #287D5A;" +
                "-fx-border-color: #F4C95D;" +
                "-fx-border-width: 2;"+
                "-fx-font-family: 'Idiqlat';"+
                "-fx-padding:0 6 0 6;"
        troisSuiteButton.wrapTextProperty().set(true)
        troisSuiteButton.lineSpacing = -15.0
        troisSuiteButton.textAlignment = TextAlignment.CENTER

        actionBottonBox = HBox(20.0)
        HBox.setHgrow(this, Priority.ALWAYS)
        actionBottonBox.alignment = Pos.CENTER

        // texte de joueurCourantLabel et etatPartieLabel a gerer dans un controlleur
        joueurCourantLabel.style = "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #F4C95D;"+
                "-fx-font-family: 'Idiqlat';"
        etatPartieLabel.style = "-fx-font-size: 25px;" +
                "-fx-text-fill: #F4C95D;"+
                "-fx-font-family: 'Idiqlat ExtraLight';"

        val actionsBox = VBox(15.0)
        actionsBox.children.addAll(actionBottonBox, joueurCourantLabel, etatPartieLabel)
        VBox.setVgrow(this, Priority.ALWAYS)
        actionsBox.alignment = Pos.CENTER

        actionsBox.style = "-fx-background-color: #0D2A52;"

        // ===== HISTORIQUE =====

        historiqueLabel.style = "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #F4C95D;"+
                "-fx-font-family: 'Idiqlat Light';"
        historiqueLabel.translateX = 20.0
        historiqueLabel.translateY = 4.0

        contenuHistoriqueLabel.textAlignment = TextAlignment.LEFT
        contenuHistoriqueLabel.prefHeight = 300.0
        contenuHistoriqueLabel.style = "-fx-background-color: #123A6D;" +
                "-fx-padding: 3;"+
                "-fx-border-color: #F4C95D;" +
                "-fx-border-width: 1;"

        val scroll = ScrollPane(contenuHistoriqueLabel)
        scroll.isFitToWidth = true
        scroll.vbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        scroll.hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER

        val historiqueBox = VBox(15.0)
        contenuHistoriqueLabel.textAlignment = TextAlignment.LEFT
        historiqueBox.style = "-fx-background-color: #123A6D;" +
                "-fx-border-color: #F4C95D;" +
                "-fx-border-width: 2;"+
                "-fx-padding: 8;"
        historiqueBox.children.addAll(historiqueLabel, scroll)
        VBox.setVgrow(this, Priority.ALWAYS)


        mancheLabel.style = "-fx-font-size: 50px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #F4C95D;"
        val mancheBox = VBox()
        mancheBox.alignment = Pos.CENTER
        mancheBox.style = "-fx-background-color: #0D2A52;"+
                "-fx-font-family: 'Idiqlat';"
        mancheBox.children.add(mancheLabel)

        // ===== PLACEMENT =====
        add(mancheBox, 1, 0)

        add(joueursBox, 0, 1, 3, 1)
        add(cartesBox, 0, 2, 3, 1)

        add(piocheBox, 0, 3)
        add(actionsBox, 1, 3)
        add(historiqueBox, 2, 3)
    }

    fun fixeControlleurJoueurBox(modele: ModeleFlip7){
        for (i in 0..joueursBox.children.size-1){
            joueursBox.children[i].addEventHandler(ActionEvent.ACTION, ControleurAfficheMainJoueur(this, modele, i))
            joueursBox.children[i].addEventHandler(ActionEvent.ACTION, ControleurCheckButton(this, modele))
        }

        troisSuiteButton.addEventHandler(ActionEvent.ACTION, Controleur3ALaSuiteBtn(this, modele))
        troisSuiteButton.addEventHandler(ActionEvent.ACTION, ControleurCheckButton(this,modele))
        troisSuiteButton.addEventHandler(ActionEvent.ACTION, ControleurAfficheMainJoueur(this,modele))
        troisSuiteButton.addEventHandler(ActionEvent.ACTION, ControleurCheckEndManche(this,modele))

        freezeButton.addEventHandler(ActionEvent.ACTION, ControleurFreezeBtn(this, modele))
        freezeButton.addEventHandler(ActionEvent.ACTION, ControleurCheckButton(this,modele))
        freezeButton.addEventHandler(ActionEvent.ACTION, ControleurAfficheMainJoueur(this,modele))
        freezeButton.addEventHandler(ActionEvent.ACTION, ControleurCheckEndManche(this,modele))


        stopButton.addEventHandler(ActionEvent.ACTION, ControleurStopBtn(this,modele))
        stopButton.addEventHandler(ActionEvent.ACTION, ControleurAfficheMainJoueur(this, modele,joueurSuivant=true))
        stopButton.addEventHandler(ActionEvent.ACTION, ControleurCheckButton(this, modele))
        stopButton.addEventHandler(ActionEvent.ACTION, ControleurCheckEndManche(this,modele))

        piocheButton.addEventHandler(ActionEvent.ACTION, ControleurPioche(this,modele))
        piocheButton.addEventHandler(ActionEvent.ACTION, ControleurAfficheMainJoueur(this,modele, joueurCourant = true))
        piocheButton.addEventHandler(ActionEvent.ACTION, ControleurCheckButton(this, modele))
        piocheButton.addEventHandler(ActionEvent.ACTION, ControleurCheckEndManche(this,modele))
    }

    fun historiquePioche(joueur: String, carte: Carte){
        //Text
        val t1 = baseText("- ")
        val t2 = baseText(joueur)
        val t3 = baseText(" a piochée ")
        val t4 = baseText(carte.toString())

        // Style
        t2.fill = Color.WHITE
        t2.font = idiqlatRegular
        if (carte is Carte3aLaSuite){
            t4.fill = Color.valueOf("#D7FF80")
        }else if(carte is CarteStop){
            t4.fill = Color.valueOf("#4CC9F0")
        }else if(carte is Carte2ndeChance){
            t4.fill = Color.RED
        }else if(carte is CarteBonusMultiplie){
            t4.fill = Color.ORANGE
        }else if(carte is CarteBonusPlus){
            t4.fill = Color.YELLOW
        }else{
            t4.fill = Color.YELLOWGREEN
        }
        t4.font = idiqlatRegular

        // ajoute la ligne
        addLine(t1, t2, t3, t4)
    }

    fun historiqueCible(joueur: String,joueurCible:String, carte: Carte, self: Boolean = false){
        //Text
        var t1 = baseText("- ")
        val t2 = baseText(joueur)
        var t3 = baseText(" a ciblé avec sa ")
        val t4 = baseText(carte.toString() + " ")
        val t5 = baseText(joueurCible)

        // Style
        t2.fill = Color.WHITE
        t2.font = idiqlatRegular
        if (carte is Carte3aLaSuite){
            t4.fill = Color.valueOf("#D7FF80")
        }else if(carte is CarteStop){
            t4.fill = Color.valueOf("#4CC9F0")
        }
        t4.font = idiqlatRegular
        t5.fill = Color.WHITE
        t5.font = idiqlatRegular

        // ajoute la ligne par rapport a self
        if (!self){
            addLine(t1, t2, t3, t4,t5)
        }else{
            t1 = baseText("- Aucun joueur n'est actif donc ")
            t3 = baseText(" est la cible de sa ")
            addLine(t1, t2, t3, t4)
        }


    }

    fun historiqueJoueurNonActif(joueur: String, stop: Boolean = false){
        //Text
        val t1 = baseText("- ")
        val t2 = baseText(joueur)
        var t3 = baseText(" a perdu")

        if (stop){
            t3 = baseText(" a dit stop")
        }

        //Style
        t2.fill = Color.WHITE
        t2.font = idiqlatRegular

        // ajoute la ligne
        addLine(t1, t2, t3)
    }

    fun historiqueNouvelleManche(n:Int){
        //Text
        val t1 = baseText("\n===========\n   Début de la manche "+n.toString()+"\n===========\n")

        // ajoute la ligne
        addLine(t1)
    }

    // Permet d'avoir une police d'ecriture par defaut
    private fun baseText(value: String): Text {
        val text = Text(value)
        text.fill = Color.valueOf("#F4C95D")
        text.font = idiqlatExtraLight
        return text
    }

    // Permet d'ajouter les ligne a contenuHistoriqueLabel
    private fun addLine(vararg texts: Text /* Tous les Text sont regroupés dans un tableau*/) {
        val line = TextFlow(*texts) // Il transforme le tableau texts en arguments séparés
        line.prefWidthProperty().bind(contenuHistoriqueLabel.widthProperty()) // Necessaire  pour le warp
        contenuHistoriqueLabel.children.add(0, line)
    }

    fun setEtatJoueur(i: Int, etat: EtatJoueur, joueurCourant: Int) {
        joueurImages[i].image = when (etat) {
            EtatJoueur.JOUE_ENCORE -> {
                if (i == joueurCourant) imgPlay else imgWait
            }
            EtatJoueur.STOP -> imgStop
            EtatJoueur.PERDU -> imgCross
        }
    }
}