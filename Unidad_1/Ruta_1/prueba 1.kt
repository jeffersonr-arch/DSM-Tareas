fun happyBirthday(name: String, age: Int): String {
    val text1 = "Mi nombre es $name"
    val text2 = "Tengo $age de edad"
    return "$text1\n$text2"
}

fun main() {
    println(happyBirthday("Jefferson", 21))
	println(happyBirthday("Maria", 25))
}