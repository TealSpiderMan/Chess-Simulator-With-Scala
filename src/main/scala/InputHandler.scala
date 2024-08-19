import javafx.scene.input.MouseEvent

class InputHandler(board: ChessBoard) {
  private var draggedPiece: Option[Piece] = None

  // called when the mouse button is pressed
  def handleMousePressed(event: MouseEvent): Unit = {
    // calculate the column and row based on mouse click position
    val col = (event.getX / board.tileSize).toInt
    val row = (event.getY / board.tileSize).toInt

    // check if there is a piece at the position
    board.getPiece(col, row) match {
      case Some(piece) =>
        draggedPiece = Some(piece)
        board.selectPiece(piece)
      case None =>
        board.deselectPiece()
    }
  }

  // called when mouse is dragged
  def handleMouseDragged(event: MouseEvent): Unit = {
    draggedPiece.foreach { piece =>
      piece.sprite.x = event.getX - board.tileSize * 0.375
      piece.sprite.y = event.getY - board.tileSize * 0.375
    }
  }

  // called when the mouse is released
  def handleMouseReleased(event: MouseEvent): Unit = {
    // if piece is being dragged, attempt to move it to new position
    draggedPiece.foreach { piece =>
      val col = (event.getX / board.tileSize).toInt //calculate column and row based on release position
      val row = (event.getY / board.tileSize).toInt
      val move = new Move(board, piece, col, row)
      move.execute()
      board.deselectPiece()
      draggedPiece = None
    }
  }
}
