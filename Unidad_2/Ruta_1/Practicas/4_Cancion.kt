class Song(
    val title: String,
    val artist: String,
    val yearPublished: Int,
    val playCount: Int
) {

    val isPopular: Boolean
        get() = playCount >= 1000

    fun printDescription() {
        println("$title, interpretada por $artist, se lanzó en $yearPublished.")
    }
}

fun main() {
    val song1 = Song("Bohemian Rhapsody", "Queen", 1975, 5000)
    val song2 = Song("Mi canción", "Artista local", 2024, 300)

    song1.printDescription()
    println("¿Es popular? ${song1.isPopular}")

    song2.printDescription()
    println("¿Es popular? ${song2.isPopular}")
}