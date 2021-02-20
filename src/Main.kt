import java.io.File

fun main() {
    val guess =GuessString(
        1.0,
        " áéíóúabcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,\n")

    //println(guess.bestParent)
    //val grades = Grades(7.5,1.9, 500)
    //println(grades.bestFitness)
    //println(grades.bestParent.map { n -> "%.2f".format(n)})
    val chessBoard = ChessBoard()
    //println(chessBoard.getFitness(chessBoard.bestParent))
    chessBoard.start()
//    val fi = File("chesboard.txt");
//    fi.writeText(chessBoard.result)
    println(chessBoard)

}

enum class ChessPieceColor {
    WHITE, BLACK, EMPTY;

    companion object {

        fun valueToString(color: ChessPieceColor): String {
            return when (color) {
                WHITE -> "B"
                BLACK -> "N"
                else -> "_"
            }
        }
    }
}


