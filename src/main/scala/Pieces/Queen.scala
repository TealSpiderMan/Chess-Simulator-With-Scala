class Queen(board: ChessBoard, col: Int, row: Int, isWhite: Boolean) extends Piece(board, col, row, isWhite, "Queen") {
  def possibleMoves(): Seq[(Int, Int)] = {
    (new Rook(board, col, row, isWhite).possibleMoves() ++
      new Bishop(board, col, row, isWhite).possibleMoves()).distinct
  }
}