class King(board: ChessBoard, col: Int, row: Int, isWhite: Boolean) extends Piece(board, col, row, isWhite, "King") {
  def possibleMoves(): Seq[(Int, Int)] = {
    for {
      (dx, dy) <- Seq((0, 1), (1, 0), (0, -1), (-1, 0), (1, 1), (1, -1), (-1, 1), (-1, -1))
      if board.getPiece(col + dx, row + dy).forall(_.isWhite != isWhite)
    } yield (col + dx, row + dy)
  }
}