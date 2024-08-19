import scalafx.scene.image.{Image, ImageView}

abstract class Piece(val board: ChessBoard, var col: Int, var row: Int, val isWhite: Boolean, pieceType: String) {
  val colorPrefix = if (isWhite) "White" else "Black"
  val imagePath = s"/${colorPrefix}${pieceType}.png"
  val pieceImage: Image = new Image(getClass.getResourceAsStream(imagePath))

  // edit this to adjust sprite sizes (chess piece.png)
  val sprite = new ImageView(pieceImage) {
    fitWidth = board.tileSize * 0.75
    fitHeight = board.tileSize * 0.75
    x = col * board.tileSize + (board.tileSize * 0.125) // Centering the piece
    y = row * board.tileSize + (board.tileSize * 0.125) // Centering the piece
  }

  board.children.add(sprite)

  // piece movement logic
  def move(newCol: Int, newRow: Int): Unit = {
    println(s"Moving piece from ($col, $row) to ($newCol, $newRow)") // Debugging line
    col = newCol
    row = newRow
    sprite.x = newCol * board.tileSize + (board.tileSize * 0.125)
    sprite.y = newRow * board.tileSize + (board.tileSize * 0.125)
  }
}
