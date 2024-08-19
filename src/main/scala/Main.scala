import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox, VBox, Pane}
import scalafx.scene.control.{Button, ScrollPane}
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

  // Create a VBox for the title
  val titleContainer = new VBox {
    children = Seq(moveHistoryTitle)
    style = "-fx-alignment: center; -fx-padding: 15 11 -10 25;" // Center alignment with padding (top, left, bottom, right)
  }

  // Create a ScrollPane for the move history
  val moveHistoryScrollPane = new ScrollPane {
    content = chessBoard.moveHistory
    style = s"-fx-background: rgba(${(semiTransparentGreen.red * 255).toInt}, ${(semiTransparentGreen.green * 255).toInt}, ${(semiTransparentGreen.blue * 255).toInt}, ${semiTransparentGreen.opacity});"
    fitToWidth = true
    fitToHeight = true
    // Set the background of the viewport
    vbarPolicy = ScrollPane.ScrollBarPolicy.AsNeeded
    hbarPolicy = ScrollPane.ScrollBarPolicy.Never
  }

  chessBoard.moveHistory.setStyle(s"-fx-background-color: rgba(${(semiTransparentGreen.red * 255).toInt}, ${(semiTransparentGreen.green * 255).toInt}, ${(semiTransparentGreen.blue * 255).toInt}, ${semiTransparentGreen.opacity});")

  // Create the reset button
  val resetButton = new Button("Reset Game") {
    style = "-fx-font-size: 14px; -fx-padding: 10 20 10 ; -fx-alignment: center"
    onAction = _ => {
      // Reset the game logic
      chessBoard.children.clear()
      chessBoard.pieces.clear()
      chessBoard.drawBoard()
      chessBoard.placePieces()
    }
  }

  val resetHistoryButton = new Button("Reset History") {
    style = "-fx-font-size: 14px; -fx-padding: 10 20 10 ; -fx-alignment: center"
    onAction = _ => {
      chessBoard.resetMoveHistory()
    }
  }

  // VBox for the green pane, including the title, move history, and reset button
  val greenPane = new VBox {
    prefWidth = 200
    prefHeight = rows * tileSize
    style = s"-fx-background-color: rgba(${(semiTransparentGreen.red * 255).toInt}, ${(semiTransparentGreen.green * 255).toInt}, ${(semiTransparentGreen.blue * 255).toInt}, ${semiTransparentGreen.opacity});"
    children = Seq(
      titleContainer,
      moveHistoryScrollPane,
      resetButton,
      resetHistoryButton
    )
    spacing = 10
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
    width = cols * tileSize + 200 + 36
    height = rows * tileSize + 36
  }


}
