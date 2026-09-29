fun main() {
    
    println(1 == 1)  
    println(1 < 1)   
    println(1 != 1)  

    semaforo("Red")
    semaforo("Yellow")
    semaforo("Green")
    semaforo("Black")
}

fun semaforo(trafficLightColor: String) {
    if (trafficLightColor == "Red") {
        println("Stop")
    } else if (trafficLightColor == "Yellow") {
        println("Slow")
    } else if (trafficLightColor == "Green") {
        println("Go")
    } else {
        println("Invalid traffic-light color")
    }
}