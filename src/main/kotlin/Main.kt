import java.util.Date

fun main() {
    val bibliotheque = Bibliotheque() // Déclaration d'une nouvelle bibliothèque
    val magazine = Magazine("National Geographic", "2024-10-28", "10") //
    val journal = Journal("Le Monde", "28/10/2024")
    val enregistrementAudio = EnregistrementAudio("Beethoven - Symphonie No.9", 960, "Classical", "1967-01-01")
    val dvd = DVD("Inception", 8880, "Action", "2010-01-01") /*2h 28m*/
    val livre = Livre("1984", "George Orwell", "Gallimard", "1972-01-01")
    bibliotheque.emprunter(dvd)
    bibliotheque.emprunter(livre)
    bibliotheque.consulter(magazine)
    bibliotheque.consulter(journal)
    bibliotheque.emprunter(enregistrementAudio)
    bibliotheque.afficherEmprunts()
    bibliotheque.retourner(dvd)
    bibliotheque.retourner(livre)
    bibliotheque.afficherEmprunts()
}

abstract class Media(open var titre: String, open var releaseDate: String){

}

interface Empruntable{
    fun emprunter(media: Media){

    }
    fun retourner(media: Media){

    }
}

class Bibliotheque{
    private var listeMedia = mutableListOf<Media>()
    fun ajouterMedia(media: Media){
        listeMedia.add(media)
    }
    fun emprunter(media: Media) {}
    fun retourner(media: Media) {}
    fun consulter(media: Media){

    }

    interface Consultable{

    }
}

class Magazine(var publieur: String, override var releaseDate: String, var ageMin: String) : Media(publieur, releaseDate){

}
class Journal(override var titre: String, override var releaseDate: String) : Media(titre, releaseDate){

}
class EnregistrementAudio(override var titre: String, var length: Int, var genre: String, override var releaseDate: String) : Media(titre, releaseDate){

}
class DVD(override var titre: String, var length: Int, var genre: String, override var releaseDate: String) : Media(titre, releaseDate){

}
class Livre(override var titre: String, var auteur: String, var publieur: String, override var releaseDate: String) : Media(publieur, releaseDate){

}