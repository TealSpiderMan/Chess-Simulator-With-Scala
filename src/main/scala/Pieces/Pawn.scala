class Pawn(board: ChessBoard, col: Int, row: Int, isWhite: Boolean) extends Piece(board, col, row, isWhite, "Pawn") {
  def possibleMoves(): Seq[(Int, Int)] = {
    val direction = if (isWhite) -1 else 1
    val startingRow = if (isWhite) 6 else 1

    val moves = collection.mutable.ListBuffer[(Int, Int)]()
    if (board.getPiece(col, row + direction).isEmpty)
      moves += ((col, row + direction))
    if (row == startingRow && board.getPiece(col, row + 2 * direction).isEmpty)
      moves += ((col, row + 2 * direction))
    moves.toList
  }
}