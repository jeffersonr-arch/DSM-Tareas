fun main() {

    val trickReference = ::trickDeclarada
    trickReference()

    val trickFunction = trick
    trick()
    trickFunction()

    treat()
}

fun trickDeclarada() {
    println("No treats! (función con fun)")
}

val trick = {
    println("No treats!")
}

val treat: () -> Unit = {
    println("Have a treat!")
}