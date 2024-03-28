package com.example.footpassion.model

import java.sql.Date
import java.util.Random

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