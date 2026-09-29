fun main() {
    sentenciaIfElse("Sandra Oh")
    sentenciaIfElse(null)

    println("Expresión if/else: ${expresionIfElse("Sandra Oh")} y ${expresionIfElse(null)}")
    println("Operador Elvis: ${conElvis("Sandra Oh")} y ${conElvis(null)}")
}

fun sentenciaIfElse(favoriteActor: String?) {
    if (favoriteActor != null) {
        println("The number of characters in your favorite actor's name is ${favoriteActor.length}.")
    } else {
        println("You didn't input a name.")
    }
}

fun expresionIfElse(favoriteActor: String?): Int {
    val lengthOfName = if (favoriteActor != null) {
        favoriteActor.length
    } else {
        0
    }
    return lengthOfName
}

fun conElvis(favoriteActor: String?): Int {
    val lengthOfName = favoriteActor?.length ?: 0
    return lengthOfName
}