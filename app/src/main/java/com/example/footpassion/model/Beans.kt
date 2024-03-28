package com.example.footpassion.model

import java.util.Date

fun main() {

}


data class GameBean(
    var id: Long? = null,
    var equipe1: String = "",
    var equipe2: String = "",
    var date: Date? = null,
    var scoreEquipe1: Int = 0,
    var scoreEquipe2: Int = 0,
    var fini: Boolean = false
)
/*
val GameList = arrayListOf(
    GameBean(1, "Marseille", "PSG", 0),
    GameBean(2, "https://picsum.photos/201", "BCDE", LONG_TEXT),
    GameBean(3, "https://picsum.photos/202", "CDEF", LONG_TEXT),
    GameBean(4, "https://picsum.photos/203", "EFGH", LONG_TEXT)
)

 */