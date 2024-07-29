class Rook(board: ChessBoard, col: Int, row: Int, isWhite: Boolean) extends Piece(board, col, row, isWhite, "Rook") {
  def possibleMoves(): Seq[(Int, Int)] = {
    // Horizontal and vertical moves
    (for {
      d <- 1 until 8
      (dx, dy) <- Seq((d, 0), (-d, 0), (0, d), (0, -d))
      if board.getPiece(col + dx, row + dy).forall(_.isWhite != isWhite)
    } yield (col + dx, row + dy)).distinct
  }
}