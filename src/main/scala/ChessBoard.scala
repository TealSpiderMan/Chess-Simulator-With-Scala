import scalafx.scene.layout.{HBox, Pane, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.Text
import javafx.scene.input.MouseEvent
import javafx.event.EventHandler

class ChessBoard extends Pane {
  val tileSize = 85
  val cols = 8
  val rows = 8
  var moveCount = 0

  var selectedPiece: Option[Piece] = None
  val pieces = collection.mutable.ListBuffer[Piece]()
  val inputHandler = new InputHandler(this)
  val moveHistory = new VBox {
    spacing = 2 // Adjust the spacing between lines
  }

  // Set preferred size of the Pane
  prefWidth = cols * tileSize
  prefHeight = rows * tileSize

  // Draw the chessboard and pieces
  drawBoard()
  placePieces()

  // Add mouse event handlers
  addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler[MouseEvent] {
    override def handle(event: MouseEvent): Unit = inputHandler.handleMousePressed(event)
  })

  addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler[MouseEvent] {
    override def handle(event: MouseEvent): Unit = inputHandler.handleMouseDragged(event)
  })

  addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler[MouseEvent] {
    override def handle(event: MouseEvent): Unit = inputHandler.handleMouseReleased(event)
  })

  def drawBoard(): Unit = {
    for (r <- 0 until rows; c <- 0 until cols) {
      val color = if ((c + r) % 2 == 0) Color.web("#EEEED2") else Color.web("#769656")
      val tile = new Rectangle {
        width = tileSize
        height = tileSize
        fill = color
        x = c * tileSize
        y = r * tileSize
      }
      children.add(tile)

      if (r == 7) {
        val letter = new Text {
          text = ('a' + c).toChar.toString
          style = "-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: black;"
          x = c * tileSize + tileSize - 15
          y = (r + 1) * tileSize - 10
        }
        children.add(letter)
      }

      if (c == 0) {
        val number = new Text {
          text = (8 - r).toString
          style = "-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: black;"
          x = 5
          y = r * tileSize + 20
        }
        children.add(number)
      }
    }
  }

  def placePieces(): Unit = {
    pieces += new Rook(this, 0, 0, isWhite = false)
    pieces += new Knight(this, 1, 0, isWhite = false)
    pieces += new Bishop(this, 2, 0, isWhite = false)
    pieces += new Queen(this, 3, 0, isWhite = false)
    pieces += new King(this, 4, 0, isWhite = false)
    pieces += new Bishop(this, 5, 0, isWhite = false)
    pieces += new Knight(this, 6, 0, isWhite = false)
    pieces += new Rook(this, 7, 0, isWhite = false)
    for (col <- 0 until cols) pieces += new Pawn(this, col, 1, isWhite = false)

    pieces += new Rook(this, 0, 7, isWhite = true)
    pieces += new Knight(this, 1, 7, isWhite = true)
    pieces += new Bishop(this, 2, 7, isWhite = true)
    pieces += new Queen(this, 3, 7, isWhite = true)
    pieces += new King(this, 4, 7, isWhite = true)
    pieces += new Bishop(this, 5, 7, isWhite = true)
    pieces += new Knight(this, 6, 7, isWhite = true)
    pieces += new Rook(this, 7, 7, isWhite = true)
    for (col <- 0 until cols) pieces += new Pawn(this, col, 6, isWhite = true)
  }

  def getPiece(col: Int, row: Int): Option[Piece] = {
    pieces.find(piece => piece.col == col && piece.row == row)
  }

  def selectPiece(piece: Piece): Unit = {
    selectedPiece = Some(piece)
  }

  def deselectPiece(): Unit = {
    selectedPiece = None
  }

  def movePiece(piece: Piece, newCol: Int, newRow: Int): Unit = {
    piece.move(newCol, newRow)
    println(s"Piece moved to new position: ($newCol, $newRow)") // Debugging line
  }

  // add the log feature in the UI
  def logMove(piece: Piece, newCol: Int, newRow: Int, capturedPiece: Option[Piece] = None): Unit = {
    moveCount += 1
    val moveDescription = capturedPiece match {
      case Some(captured) =>
        s"$moveCount. ${if (piece.isWhite) "White" else "Black"} ${piece.getClass.getSimpleName} captured ${if (captured.isWhite) "White" else "Black"} ${captured.getClass.getSimpleName} at ${('a' + newCol).toChar}${8 - newRow}\n"
      case None =>
        s"$moveCount. Moved ${if (piece.isWhite) "White" else "Black"} ${piece.getClass.getSimpleName} to ${('a' + newCol).toChar}${8 - newRow}\n"
    }
    val moveText = new Text {
      text = moveDescription
      style = "-fx-font-size: 11px; -fx-fill: black;"
    }
    moveHistory.children.add(moveText)
  }


  def resetMoveHistory(): Unit = {
    moveHistory.children.clear()
    moveCount = 0
  }
}
