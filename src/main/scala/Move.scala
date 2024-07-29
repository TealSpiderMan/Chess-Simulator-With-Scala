class Move(board: ChessBoard, movedPiece: Piece, newColPos: Int, newRowPos: Int) {
  val oldCol: Int = movedPiece.col
  val oldRow: Int = movedPiece.row

  val newCol: Int = newColPos
  val newRow: Int = newRowPos

  val piece: Piece = movedPiece
  val capture: Option[Piece] = board.getPiece(newColPos, newRowPos)

  def execute(): Unit = {
    capture.foreach(c => board.pieces -= c)
    piece.move(newColPos, newRowPos)
  }
}