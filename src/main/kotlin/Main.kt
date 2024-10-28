fun main() {
    val bibliotheque = Bibliotheque() // Déclaration d'une nouvelle bibliothèque
    val magazine = Magazine("National Geographic", "2024-10-28", "10") // Déclaration d'un nouveau magazine
    val journal = Journal("Le Monde", "28/10/2024")   // Déclaration d'un nouveau journal
    val enregistrementAudio = EnregistrementAudio("Beethoven - Symphonie No.9", 960, "Classical", "1967-01-01") // Déclaration d'un nouveau audio
    val dvd = DVD("Inception", 8880, "Action", "2010-01-01") /*2h 28m*/ // Déclaration d'un nouveau dvd
    val livre = Livre("1984", "George Orwell", "Gallimard", "1972-01-01") // Déclaration d'un nouveau livre
    val mediaList = listOf(magazine, journal, enregistrementAudio, dvd, livre)
    bibliotheque.ajouterMedia(mediaList)
    bibliotheque.afficherBibliotheque()
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
    var emprunte: Boolean
    fun emprunter() : Boolean
    fun retourner() : Boolean
}
interface Consultable{
    var enConsulte: Boolean
    fun consulter() : Boolean
    fun arretConsulte() : Boolean
}

class Bibliotheque{
    private var contenuBibliotheque = mutableListOf<Media>() // En effet, une bibliothèque contient des médias. Ici on l'a initialisée vide
    private var emprunts = mutableListOf<Media>() // Liste des medias empruntés. Ici on l'a initialisée vide
    fun ajouterMedia(media: Media){
        contenuBibliotheque.add(media) // Ajoute 1 media dans la bibliothèque
    }
    fun ajouterMedia(media: List<Media>){
        contenuBibliotheque.addAll(media) // Ajoute toute une liste de medias dans la bibliothèque.
    }
    fun afficherBibliotheque(){
        for (i in 1..contenuBibliotheque.size) {
            print("Média #$i: ")
            contenuBibliotheque[i-1].afficherDetails()
        }
    }
    fun afficherEmprunts() {
        println("Emprunts en cours:")
        for (i in 1..emprunts.size) { emprunts[i-1].afficherDetails() } // Boucle affichant chaque media emprunté
    }

    fun emprunter(media: Media) {
        when (media){
            is Livre -> {
                emprunts.add(media)
                media.emprunter()
            }
            is EnregistrementAudio -> {
                emprunts.add(media)
                media.emprunter()
            }
            is DVD -> {
                emprunts.add(media)
                media.emprunter()
            }
            else -> "Ce media ne peut pas être emprunté !"
        }
    }

    fun retourner(media: Media) {
        when (media){
            is Livre -> {
                emprunts.remove(media)
                media.retourner()
            }
            is EnregistrementAudio -> {
                emprunts.remove(media)
                media.retourner()
            }
            is DVD -> {
                emprunts.remove(media)
                media.retourner()
            }
            else -> "Ce media n'est pas empruntable, donc il ne peut pas être retourné !"
        }
    }

    fun consulter(media: Media) {
        when (media){
            is Livre -> media.consulter()
            is Journal -> media.consulter()
            is Magazine -> media.consulter()
            else -> "Ce media n'est pas consultable !"
        }
    }
    fun arretConsulte(media: Media){
        when (media){
            is Livre -> media.arretConsulte()
            is Journal -> media.arretConsulte()
            is Magazine -> media.arretConsulte()
            else -> "Ce media n'est pas consultable !"
        }
    }
}

class Magazine(var publieur: String, override var releaseDate: String, var numero: String) : Media(publieur, releaseDate), Consultable{
    override fun afficherDetails(){
        println("Magazine: Publieur = '$publieur', Date de parution = '$releaseDate', Numéro = '$numero")
    }
    override var enConsulte: Boolean = false
    override fun consulter(): Boolean {
        if (enConsulte){
            println("Quelqu'un consulte déjà ce magazine, laissez le finir")
        }
        else{
            enConsulte = true
            println("Le magazine '$titre' numéro '$numero' ('$releaseDate') est consulté sur place")
        }
        return enConsulte
    }

    override fun arretConsulte(): Boolean {
        if (!enConsulte){
            println("Commencez par consulter le magazine avant de vouloir arrêter la consulte")
        }
        else{
            enConsulte = false
            println("Vous avez arrêté de consulter le magazine '$titre' numéro '$numero' ('$releaseDate')")
        }
        return enConsulte
    }
}
class Journal(override var titre: String, override var releaseDate: String) : Media(titre, releaseDate), Consultable{
    override fun afficherDetails(){
        println("Livre: Titre = '$titre', Date de parution = '$releaseDate'")
    }
    override var enConsulte: Boolean = false
    override fun consulter(): Boolean {
        if (enConsulte){
            println("Quelqu'un consulte déjà ce journal, laissez le finir")
        }
        else{
            enConsulte = true
            println("Le journal '$titre' du '$releaseDate' est consulté sur place")
        }
        return enConsulte
    }

    override fun arretConsulte(): Boolean {
        if (!enConsulte){
            println("Commencez par consulter le journal avant de vouloir arrêter la consulte")
        }
        else{
            enConsulte = false
            println("Vous avez arrêté de consulter le journal '$titre' du '$releaseDate'")
        }
        return enConsulte
    }
}
class EnregistrementAudio(override var titre: String, var length: Int, var genre: String, override var releaseDate: String) : Media(titre, releaseDate), Empruntable{
    override fun afficherDetails(){
        println("Enregistrement Audio: Titre = '$titre', Duree = '$length' secondes, Genre = '$genre'")
    }
    override var emprunte: Boolean = false
    override fun emprunter(): Boolean {
        if (emprunte){
            println("Cet enregistrement audio a déjà été emprunté")
        }
        else{
            emprunte = true
            println("L'enregistrement audio '$titre' a été emprunté")
        }
        return emprunte
    }

    override fun retourner(): Boolean {
        if (!emprunte){
            println("Vous ne pouvez pas retourner un enregistrement audio qui n'a pas été emprunté")
        }
        else{
            emprunte = false
            println("L'enregistrement audio '$titre' a été retourné")
        }
        return emprunte
    }
}
class DVD(override var titre: String, var length: Int, var genre: String, override var releaseDate: String) : Media(titre, releaseDate), Empruntable{
    override fun afficherDetails(){
        println("DVD: Titre = '$titre', Durée = '$length' secondes, Genre = '$genre'")
    }
    override var emprunte: Boolean = false
    override fun emprunter(): Boolean {
        if (emprunte){
            println("Ce DVD a déjà été emprunté")
        }
        else{
            emprunte = true
            println("Le DVD '$titre' a été emprunté")
        }
        return emprunte
    }

    override fun retourner(): Boolean {
        if (!emprunte){
            println("Vous ne pouvez pas retourner un DVD qui n'a pas été emprunté")
        }
        else{
            emprunte = false
            println("Le DVD '$titre' a été retourné")
        }
        return emprunte
    }
}
class Livre(override var titre: String, var auteur: String, var editeur: String, override var releaseDate: String) : Media(titre, releaseDate), Empruntable, Consultable{

    override fun afficherDetails(){
        println("Livre: Titre = '$titre', Auteur = '$auteur', Éditeur = '$editeur', Date de parution = '$releaseDate'")
    }

    override var emprunte: Boolean = false

    override fun emprunter(): Boolean {
        if (emprunte){
            println("Ce livre a déjà été emprunté")
        }
        else{
            emprunte = true
            println("Le livre '$titre' de '$auteur' a été emprunté")
        }
        return emprunte
    }

    override fun retourner(): Boolean {
        if (!emprunte){
            println("Vous ne pouvez pas retourner un livre qui n'a pas été emprunté")
        }
        else{
            emprunte = false
            println("Le livre '$titre' de '$auteur' a été retourné")
        }
        return emprunte
    }

    override var enConsulte: Boolean = false
    override fun consulter(): Boolean {
        if (enConsulte){
            println("Quelqu'un consulte déjà ce livre, laissez le finir")
        }
        else{
            enConsulte = true
            println("Le livre '$titre' de '$auteur' est consulté sur place")
        }
        return enConsulte
    }

    override fun arretConsulte(): Boolean {
        if (!enConsulte){
            println("Commencez par consulter le livre avant de vouloir arrêter la consulte")
        }
        else{
            enConsulte = false
            println("Vous avez arrêté de consulter le livre '$titre' de '$auteur'")
        }
        return enConsulte
    }
}