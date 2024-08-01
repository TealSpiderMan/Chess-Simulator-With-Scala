class Move(board: ChessBoard, movedPiece: Piece, newColPos: Int, newRowPos: Int) {
  val oldCol: Int = movedPiece.col
  val oldRow: Int = movedPiece.row

  val newCol: Int = newColPos
  val newRow: Int = newRowPos

  val piece: Piece = movedPiece
  val capture: Option[Piece] = board.getPiece(newColPos, newRowPos)

  def execute(): Unit = {
    capture match {
      case Some(capturedPiece) if capturedPiece.isWhite != piece.isWhite =>
        board.pieces -= capturedPiece
        board.children.remove(capturedPiece.sprite) // Remove the visual representation
        board.movePiece(piece, newColPos, newRowPos)
      case Some(capturedPiece) if capturedPiece.isWhite == piece.isWhite =>
        // Do nothing and move the piece back to its original position
        piece.move(oldCol, oldRow)
      case None =>
        board.movePiece(piece, newColPos, newRowPos)
    }
    println(s"Executed move to ($newCol, $newRow)")
  }
}

