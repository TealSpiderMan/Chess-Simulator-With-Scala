class Knight(board: ChessBoard, col: Int, row: Int, isWhite: Boolean) extends Piece(board, col, row, isWhite, "Knight") {
  def possibleMoves(): Seq[(Int, Int)] = {
    for {
      (dx, dy) <- Seq((2, 1), (1, 2), (-1, 2), (-2, 1), (-2, -1), (-1, -2), (1, -2), (2, -1))
      if board.getPiece(col + dx, row + dy).forall(_.isWhite != isWhite)
    } yield (col + dx, row + dy)
  }
}