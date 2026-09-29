fun main() {
    semaforo("Red")
    semaforo("Amber")
    semaforo("Black")

    clasificarNumero(3)
    clasificarNumero(4)
    clasificarNumero(20)
    clasificarNumero("Hola")
}

fun semaforo(trafficLightColor: String) {
    when (trafficLightColor) {
        "Red" -> println("Stop")
        "Yellow", "Amber" -> println("Slow")
        "Green" -> println("Go")
        else -> println("Invalid traffic-light color")
    }
}

fun clasificarNumero(x: Any) {
    when (x) {
        2, 3, 5, 7 -> println("$x is a prime number between 1 and 10.")
        in 1..10 -> println("$x is a number between 1 and 10, but not a prime number.")
        is Int -> println("$x is an integer number, but not between 1 and 10.")
        else -> println("$x isn't an integer number.")
    }
}