import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox, VBox, Pane, Priority}
import scalafx.scene.paint.Color
import scalafx.scene.text.Text

object ChessGame extends JFXApp {
  val tileSize = 85
  val cols = 8
  val rows = 8
  val chessBoard = new ChessBoard

  // Define the same green color with reduced opacity
  val semiTransparentGreen = Color.web("#769656", 0.9) // adjust the opacity here

  // Add the move history title
  val moveHistoryTitle = new Text {
    text = "Turn History\n"
    style = "-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: black;"
  }

  // Create a HBox for the title and set alignment to the right
  val titleContainer = new HBox {
    children = Seq(moveHistoryTitle)
    style = "-fx-alignment: center; -fx-padding: 10;" // Align to the right with padding
  }

  // VBox for the green pane, including the title
  val greenPane = new VBox {
    prefWidth = 200
    prefHeight = rows * tileSize
    style = s"-fx-background-color: rgba(${(semiTransparentGreen.red * 255).toInt}, ${(semiTransparentGreen.green * 255).toInt}, ${(semiTransparentGreen.blue * 255).toInt}, ${semiTransparentGreen.opacity});"
    children = Seq(
      titleContainer
    )
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
