fun main() {
    println(mensajeIf("Black"))
    println(mensajeIf("Green"))
    println(mensajeWhen("Amber"))
    println(mensajeWhen("Blue"))
}

fun mensajeIf(trafficLightColor: String): String {
    val message =
        if (trafficLightColor == "Red") "Stop"
        else if (trafficLightColor == "Yellow") "Slow"
        else if (trafficLightColor == "Green") "Go"
        else "Invalid traffic-light color"
    return message
}

fun mensajeWhen(trafficLightColor: String): String {
    val message = when (trafficLightColor) {
        "Red" -> "Stop"
        "Yellow", "Amber" -> "Slow"
        "Green" -> "Go"
        else -> "Invalid traffic-light color"
    }
    return message
}