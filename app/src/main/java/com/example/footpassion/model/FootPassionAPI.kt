
package com.example.footpassion.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun main() {

}



object FootPassionAPI {

     private val gson = Gson()
     private val client = OkHttpClient()

    fun addGame(equipe1: String, equipe2: String, date: String) {
        //val formattedDate = convertStringToDate(date)

        val url = "http://10.0.2.2:8080/addGame?equipe1=$equipe1&equipe2=$equipe2&date=$date"

        val response = client.newCall(Request.Builder().url(url).get().build()).execute()
        if (response.isSuccessful) {
            println("Le match $equipe1 - $equipe2 du $date a bien été enregistré en base de données")
        } else {
            println("Erreur lors de l'enregistrement du match")
        }
    }

    private fun convertStringToDate(dateString: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE) // Format de la date à parser
        return formatter.parse(dateString)!!
    }



    fun updateGame(id: Long?, action: String){
        val url = "http://10.0.2.2:8080/updateGame?id=$id&action=$action"

        val response = client.newCall(Request.Builder().url(url).get().build()).execute()
        if (response.isSuccessful) {
            println("Mise à jour effectuée")
        } else {
            println("Erreur lors de l'enregistrement du match")
        }
    }



    fun getAll(): List<GameBean> {
        var json = sendGet("http://10.0.2.2:8080/getAll")
        return gson.fromJson(json, Array<GameBean>::class.java).toList()
    }



    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    private fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()

        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}
