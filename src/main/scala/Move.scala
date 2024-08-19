class Move(board: ChessBoard, movedPiece: Piece, newColPos: Int, newRowPos: Int) {
  // store old column and eow pos before the move
  val oldCol: Int = movedPiece.col
  val oldRow: Int = movedPiece.row

  // store the new column and row pos where the piece is being moved to
  val newCol: Int = newColPos
  val newRow: Int = newRowPos

  // the piece that is being moved
  val piece: Piece = movedPiece

  // check if there is a piece at the new pos that can be captured
  val capture: Option[Piece] = board.getPiece(newColPos, newRowPos)

  // handle capture and invalid moves
  def execute(): Unit = {
    capture match {
      // can eat a piece the pieces color (boolean) does not match
      case Some(capturedPiece) if capturedPiece.isWhite != piece.isWhite =>
        board.pieces -= capturedPiece
        board.children.remove(capturedPiece.sprite) // Remove the visual representation
        board.movePiece(piece, newColPos, newRowPos)
        board.logMove(piece, newColPos, newRowPos, Some(capturedPiece))

      // if there is a piece at the target pos but its of the same colour
      case Some(_) =>
        // Do nothing and move the piece back to its original position (invalid move)
        piece.move(oldCol, oldRow)

      // no piece is at target position
      case None =>
        board.movePiece(piece, newColPos, newRowPos)
        board.logMove(piece, newColPos, newRowPos)
    }
    println(s"Executed move to ($newCol, $newRow)")
  }
}

