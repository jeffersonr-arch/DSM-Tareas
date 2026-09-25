fun main() {
	println(clima("Ankara", 27, 31, 82))  
    println(clima("Tokyo", 32, 36, 10))  
    println(clima("Cape Town", 59, 64, 2))  
    println(clima("Guatemala City", 50, 55, 7))  
}

fun clima(city: String, lowT: Int, highT: Int, chance: Int): String {
    return "City: $city\nLow temperature: $lowT, High temperature: $highT\nChance of rain: $chance%\n"
}