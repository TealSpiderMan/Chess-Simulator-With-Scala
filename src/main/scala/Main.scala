import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.HBox

object ChessGame extends JFXApp {
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
  }
}