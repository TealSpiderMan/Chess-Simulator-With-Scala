import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox, Pane}
import scalafx.scene.paint.Color

object ChessGame extends JFXApp {
  val tileSize = 85
  val cols = 8
  val rows = 8
  val chessBoard = new ChessBoard

  // Create a new Pane for the green background
  val greenPane = new Pane {
    prefWidth = 200
    prefHeight = rows * tileSize
    style = "-fx-background-color: green;"
  }

  stage = new JFXApp.PrimaryStage {
    title = "ScalaFX Chess Game"
    scene = new Scene {
      content = new HBox {
        children = Seq(
          chessBoard,
          greenPane
        )
      }
    }
    width = cols * tileSize + 200 + 14
    height = rows * tileSize + 36
  }
}
