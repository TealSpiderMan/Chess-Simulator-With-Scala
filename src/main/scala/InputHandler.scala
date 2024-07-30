import javafx.scene.input.MouseEvent

class InputHandler(board: ChessBoard) {
  private var draggedPiece: Option[Piece] = None

  def handleMousePressed(event: MouseEvent): Unit = {
    val col = (event.getX / board.tileSize).toInt
    val row = (event.getY / board.tileSize).toInt

    board.getPiece(col, row) match {
      case Some(piece) =>
        draggedPiece = Some(piece)
        board.selectPiece(piece)
      case None =>
        board.deselectPiece()
    }
  }

  def handleMouseDragged(event: MouseEvent): Unit = {
    draggedPiece.foreach { piece =>
      piece.sprite.x = event.getX - board.tileSize * 0.375
      piece.sprite.y = event.getY - board.tileSize * 0.375
    }
  }

  def handleMouseReleased(event: MouseEvent): Unit = {
    draggedPiece.foreach { piece =>
      val col = (event.getX / board.tileSize).toInt
      val row = (event.getY / board.tileSize).toInt
      val move = new Move(board, piece, col, row)
      move.execute()
      board.deselectPiece()
      draggedPiece = None
    }
  }
}
