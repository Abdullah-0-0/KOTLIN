package Utils

import javafx.animation.FadeTransition
import javafx.animation.ScaleTransition
import javafx.animation.TranslateTransition
import javafx.scene.Node
import javafx.util.Duration

object AnimationUtils {

    fun animationBouton(node: Node) {
        node.setOnMouseEntered {
            val scale = ScaleTransition(Duration.millis(150.0), node)
            scale.toX = 1.08
            scale.toY = 1.08
            scale.play()
        }

        node.setOnMouseExited {
            val scale = ScaleTransition(Duration.millis(150.0), node)
            scale.toX = 1.0
            scale.toY = 1.0
            scale.play()
        }
    }

    fun apparition(node: Node) {
        node.opacity = 0.0

        val fade = FadeTransition(Duration.millis(500.0), node)
        fade.fromValue = 0.0
        fade.toValue = 1.0
        fade.play()
    }

    fun apparitionCarte(node: Node) {
        node.opacity = 0.0
        node.scaleX = 0.4
        node.scaleY = 0.4

        val fade = FadeTransition(Duration.millis(250.0), node)
        fade.fromValue = 0.0
        fade.toValue = 1.0

        val scale = ScaleTransition(Duration.millis(250.0), node)
        scale.fromX = 0.4
        scale.fromY = 0.4
        scale.toX = 1.0
        scale.toY = 1.0

        fade.play()
        scale.play()
    }

    fun pulse(node: Node) {
        val scale = ScaleTransition(Duration.millis(600.0), node)
        scale.fromX = 1.0
        scale.fromY = 1.0
        scale.toX = 1.08
        scale.toY = 1.08
        scale.cycleCount = 2
        scale.isAutoReverse = true
        scale.play()
    }

    fun secousse(node: Node) {
        val translate = TranslateTransition(Duration.millis(70.0), node)
        translate.fromX = -8.0
        translate.toX = 8.0
        translate.cycleCount = 6
        translate.isAutoReverse = true
        translate.play()
    }
}