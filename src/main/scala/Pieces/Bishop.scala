class Bishop(board: ChessBoard, col: Int, row: Int, isWhite: Boolean) extends Piece(board, col, row, isWhite, "Bishop") {
  def possibleMoves(): Seq[(Int, Int)] = {
    // Diagonal moves
    for {
      d <- 1 until 8
      (dx, dy) <- Seq((d, d), (-d, d), (d, -d), (-d, -d))
      if board.getPiece(col + dx, row + dy).forall(_.isWhite != isWhite)
    } yield (col + dx, row + dy)
  }
}