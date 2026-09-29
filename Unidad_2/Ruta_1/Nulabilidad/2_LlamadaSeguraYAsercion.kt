fun main() {
    longitudSegura("Sandra Oh")   
    longitudSegura(null)          

    longitudConAsercion("Sandra Oh")  

}

fun longitudSegura(favoriteActor: String?) {

    println(favoriteActor?.length)
}

fun longitudConAsercion(favoriteActor: String?) {
    println(favoriteActor!!.length)
}