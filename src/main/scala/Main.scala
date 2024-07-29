import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.HBox

object ChessGame extends JFXApp {
  val tileSize = 85
  val cols = 8
  val rows = 8
  val chessBoard = new ChessBoard

  stage = new JFXApp.PrimaryStage {
    title = "ScalaFX Chess Game"
    scene = new Scene {
      content = new HBox {
        children = Seq(
          chessBoard
        )
      }
    }
    width = cols * tileSize + 14
    height = rows * tileSize + 36
    resizable = false
  }
}