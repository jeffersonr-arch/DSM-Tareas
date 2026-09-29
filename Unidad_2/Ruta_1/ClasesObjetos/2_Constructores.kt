class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "online"

  
    constructor(name: String, category: String, statusCode: Int) : this(name, category) {
        deviceStatus = when (statusCode) {
            0 -> "offline"
            1 -> "online"
            else -> "unknown"
        }
    }

    fun turnOn() {
        println("Smart device is turned on.")
    }

    fun turnOff() {
        println("Smart device is turned off.")
    }
}

fun main() {
    
    val smartTv = SmartDevice(name = "Android TV", category = "Entertainment")
    println("${smartTv.name} (${smartTv.category}): ${smartTv.deviceStatus}")

    val smartLight = SmartDevice("Google Light", "Utility", 0)
    println("${smartLight.name} (${smartLight.category}): ${smartLight.deviceStatus}")

    val smartSpeaker = SmartDevice("Speaker", "Audio", 7)
    println("${smartSpeaker.name} (${smartSpeaker.category}): ${smartSpeaker.deviceStatus}")
}