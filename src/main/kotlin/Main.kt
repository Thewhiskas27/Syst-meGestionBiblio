fun main() {
    val bibliotheque = Bibliotheque() // Déclaration d'une nouvelle bibliothèque
    val magazine = Magazine("National Geographic", "2024-10-28", "10") // Déclaration d'un nouveau magazine
    val journal = Journal("Le Monde", "28/10/2024")   // Déclaration d'un nouveau journal
    val enregistrementAudio = EnregistrementAudio("Beethoven - Symphonie No.9", 960, "Classical", "1967-01-01") // Déclaration d'un nouveau audio
    val dvd = DVD("Inception", 8880, "Action", "2010-01-01") /*2h 28m*/ // Déclaration d'un nouveau dvd
    val livre = Livre("1984", "George Orwell", "Gallimard", "1972-01-01") // Déclaration d'un nouveau livre
    bibliotheque.emprunter(dvd) // Emprunt d'un dvd
    bibliotheque.emprunter(livre) // Emprunt d'un livre
    bibliotheque.consulter(magazine) // Emprunt d'un magazine
    bibliotheque.consulter(journal) // Emprunt d'un journal
    bibliotheque.emprunter(enregistrementAudio) // Emprunt d'un audio
    bibliotheque.afficherEmprunts() // Affichage des emprunts. On a les 3 éléments rajoutés
    bibliotheque.retourner(dvd) // Retour du dvd
    bibliotheque.retourner(livre) // Emprunt du livre
    bibliotheque.afficherEmprunts() // Affichage des emprunts. On ne voit plus le DVD ou le livre
}

abstract class Media(open var titre: String, open var releaseDate: String){
    abstract fun afficherDetails()
}

interface Empruntable{
    fun emprunter(media: Media)
    fun retourner(media: Media)
}
interface Consultable{
    fun consulter(media: Media)
}

class Bibliotheque: Empruntable, Consultable{
    private var listeMedia = mutableListOf<Media>()
    private var emprunts = mutableListOf<Media>()
    fun ajouterMedia(media: Media){
        listeMedia.add(media)
    }

    fun afficherEmprunts() {
        for (i in 1..emprunts.size)
        {
            print("Média #$i - ")
            emprunts[i-1].afficherDetails()
        } // Boucle affichant chaque véhicule.
    }

    override fun emprunter(media: Media) {
        if (media is Magazine || media is Journal){

        }
    }

    override fun retourner(media: Media) {
        TODO("Not yet implemented")
    }

    override fun consulter(media: Media) {

    }
}

class Magazine(var publieur: String, override var releaseDate: String, var ageMin: String) : Media(publieur, releaseDate){
    override fun afficherDetails(){
        println("Magazine: Publieur = '$publieur', Date de parution = '$releaseDate', Âge minimale = '$ageMin")
    }
}
class Journal(override var titre: String, override var releaseDate: String) : Media(titre, releaseDate){
    var empruntable = false
    override fun afficherDetails(){
        println("Livre: Titre = '$titre', Date de parution = '$releaseDate'")
    }
}
class EnregistrementAudio(override var titre: String, var length: Int, var genre: String, override var releaseDate: String) : Media(titre, releaseDate){
    var empruntable = true
    override fun afficherDetails(){
        println("Enregistrement Audio: Titre = '$titre', Duree = '$length' secondes, Genre = '$genre'")
    }
}
class DVD(override var titre: String, var length: Int, var genre: String, override var releaseDate: String) : Media(titre, releaseDate){
    var empruntable = true
    override fun afficherDetails(){
        println("DVD: Titre = '$titre', Durée = '$length' secondes, Genre = '$genre'")
    }
}
class Livre(override var titre: String, var auteur: String, var editeur: String, override var releaseDate: String) : Media(titre, releaseDate){
    var empruntable = true
    override fun afficherDetails(){
        println("Livre: Titre = '$titre', Auteur = '$auteur', Éditeur = '$editeur', Date de parution = '$releaseDate'")
    }
}