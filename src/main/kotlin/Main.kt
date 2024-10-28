/*
* Les lignes avec l'icône ⚠ signifient que l'operation n'a pas pu s'exécuter correctement
* Les lignes avec l'icône ✓ confirment que l'action a été faite avec succès
* */

fun main() {
    val bibliotheque = Bibliotheque() // Déclaration d'une nouvelle bibliothèque
    val magazine = Magazine("National Geographic", "2024-10-28", "10") // Déclaration d'un nouveau magazine
    val journal = Journal("Le Monde", "28/10/2024")   // Déclaration d'un nouveau journal
    val enregistrementAudio = EnregistrementAudio("Beethoven - Symphonie No.9", 960, "Classical", "1967-01-01") // Déclaration d'un nouveau audio
    val dvd = DVD("Inception", 8880, "Action", "2010-01-01") /*2h 28m*/ // Déclaration d'un nouveau dvd
    val livre = Livre("1984", "George Orwell", "Gallimard", "1972-01-01") // Déclaration d'un nouveau livre
    val livre2 = Livre("La ferme des animaux", "George Orwell", "Gallimard", "1945-01-01") // Déclaration d'un nouveau livre
    val mediaList = listOf(magazine, journal, enregistrementAudio, dvd) // Déclaration d'une liste de médias
    bibliotheque.ajouterMedia(mediaList) // Ajout de toute la liste de médias ci-dessus
    bibliotheque.ajouterMedia(livre) // Ajout d'un média (Dans ce cas, le livre)
    bibliotheque.afficherBibliotheque() // Affiche tous les articles présents dans la bibliothèque
    bibliotheque.emprunter(dvd) // Emprunt d'un dvd
    bibliotheque.emprunter(livre) // Emprunt du livre #1
    bibliotheque.consulter(magazine) // Emprunt d'un magazine
    bibliotheque.consulter(journal) // Emprunt d'un journal
    bibliotheque.emprunter(enregistrementAudio) // Emprunt d'un audio
    bibliotheque.afficherEmprunts() // Affichage des emprunts. On a les 3 éléments rajoutés
    bibliotheque.retourner(dvd) // Retour du dvd
    bibliotheque.retourner(livre) // Emprunt du livre
    bibliotheque.afficherEmprunts() // Affichage des emprunts. On ne voit plus le DVD ou le livre
    bibliotheque.emprunter(livre) // Emprunt du livre #1
    bibliotheque.consulter(livre2) // Consultation du livre #2
    // Début des failsafes
    bibliotheque.emprunter(magazine) // Tentative d'emprunt du magazine. Retourne une erreur car les magazines ne peuvent pas être empruntés.
    bibliotheque.consulter(dvd) // Tentative de consultation du DVD. Retourne une erreur car les DVDs ne peuvent pas être consultés.
    bibliotheque.retourner(dvd) // Tentative de retour du DVD. Retourne une erreur car le DVD a déjà été retourné avant.
    bibliotheque.emprunter(enregistrementAudio) // Tentative d'emprunt de l'enregistrement audio. Retourne une erreur car il a déjà été emprunté.
    bibliotheque.consulter(livre) // Tentative de consultation du livre #1. Retourne une erreur car le livre a été emprunté.
    bibliotheque.emprunter(livre2) // Tentative d'emprunt du livre #2. Retourne une erreur car le livre est en train d'être consulté.
    bibliotheque.arretConsulte(journal) // Arrêt de consulte du journal
    bibliotheque.arretConsulte(journal) // Tentative d'arrêt de consultation du journal. Retourne une erreur car le journal n'est pas en train d'être consulté.
}

abstract class Media(open var titre: String, open var releaseDate: String){ // Classe abstraite des medias
    abstract fun afficherDetails() // Fonction abstraite pour afficher les détails
}

interface Empruntable{ // Interface responsable de la gestion des medias empruntés
    var emprunte: Boolean // false: Le media n'est pas emprunté; true: Le media est emprunté
    fun emprunter() : Boolean // Emprunte le média s'il ne l'est pas déjà
    fun retourner() : Boolean // Retourne le média s'il a été emprunté
}
interface Consultable{ // Interface responsable de la gestion des médias consultés
    var enConsulte: Boolean // false: Le media est consultable; true: Le media est en train d'être consulté
    fun consulter() : Boolean // Démarre la consultation d'un media
    fun arretConsulte() : Boolean // Arrête la consultation d'un media
}

class Bibliotheque{ // Classe responsable du regroupement des medias
    private var contenuBibliotheque = mutableListOf<Media>() // En effet, une bibliothèque contient des médias. Ici on l'a initialisée vide
    private var emprunts = mutableListOf<Media>() // Liste des medias empruntés. Ici on l'a initialisée vide
    fun ajouterMedia(media: Media){
        contenuBibliotheque.add(media) // Ajoute 1 media dans la bibliothèque
    }
    fun ajouterMedia(media: List<Media>){
        contenuBibliotheque.addAll(media) // Ajoute toute une liste de medias dans la bibliothèque. Utile si on veut rajouter beaucoup de medias rapidement
    }
    fun supprMedia(media: Media){
        contenuBibliotheque.remove(media) // Supprime 1 media dans la bibliothèque
    }
    fun afficherBibliotheque(){ // Affiche les articles de la bibliothèque
        for (i in 1..contenuBibliotheque.size) { // Pour chaque média présent de la bibliothèque...
            print("Média #$i: ") //... Mettre ceci...
            contenuBibliotheque[i-1].afficherDetails() // ...accompagné des détails du média
        }
    }
    fun afficherEmprunts() { // N'affiche que les medias empruntés
        println("Emprunts en cours:") // Affiche le texte
        for (i in 1..emprunts.size) { // Pour chaque media emprunté...
            print("- ")  // ...Mettre le tiret pour la séparation...
            emprunts[i-1].afficherDetails() // ...puis affiche les détails
        }
    }

    fun emprunter(media: Media) { // Fonction pour emprunter un media
        when (media){ // Vérification du type de media
            is Livre -> { // Si c'est un livre...
                emprunts.add(media) // ... on le met dans la liste des emprunts...
                media.emprunter() // ...Puis on le marque comme emprunté si ce n'est pas fait
            }
            is EnregistrementAudio -> { // Si c'est un audio...
                emprunts.add(media)
                media.emprunter()
            }
            is DVD -> { // Si c'est un DVD...
                emprunts.add(media)
                media.emprunter()
            }
            else -> println("⚠ - Ce media ne peut pas être emprunté !") // Sinon renvoyer l'erreur suivante
        }
    }

    fun retourner(media: Media) { // Fonction pour retourner un media emprunté
        when (media){
            is Livre -> { // Si c'est un livre...
                emprunts.remove(media) // ...enlever de la liste des emprunts...
                media.retourner() // ...puis le marquer comme retourné s'il a été emprunté
            }
            is EnregistrementAudio -> { // Si c'est un audio
                emprunts.remove(media)
                media.retourner()
            }
            is DVD -> { // Si c'est un DVD
                emprunts.remove(media)
                media.retourner()
            }
            else -> println("⚠ - Ce media n'est pas empruntable, donc il ne peut pas être retourné !")
        }
    }

    fun consulter(media: Media) { // Fonction pour consulter un media
        when (media){ // Vérification du type de media
            // Si c'est un livre, journal ou magazine, on le marque comme consulté si ce n'est pas fait
            is Livre -> media.consulter()
            is Journal -> media.consulter()
            is Magazine -> media.consulter()
            else -> println("⚠ - Ce media n'est pas consultable !") // Sinon, renvoyer ce message d'erreur
        }
    }
    fun arretConsulte(media: Media){ // Fonction pour arrêter de consulter un media
        when (media){
            // Si c'est un livre, journal ou magazine, on le remet comme non consulté si ce n'est pas fait
            is Livre -> media.arretConsulte()
            is Journal -> media.arretConsulte()
            is Magazine -> media.arretConsulte()
            else -> println("⚠ - Ce media n'est pas consultable !")
        }
    }
}

class Magazine(var publieur: String, override var releaseDate: String, var numero: String) : Media(publieur, releaseDate), Consultable{ // Classe des magazines
    override fun afficherDetails(){println("Magazine: Publieur = '$publieur', Date de parution = '$releaseDate', Numéro = '$numero")} // Affiche les détails du magazine
    override var enConsulte: Boolean = false // On initialise la valeur des consultes en tant que faux
    override fun consulter(): Boolean { // Démarre une consulte d'un magazine
        if (enConsulte)println("⚠ - Quelqu'un consulte déjà ce magazine, laissez le finir") // Bien sûr, on ne peut pas consulter un magazine s'il est déjà en train d'être consulté
        else{ // Si le magazine n'est pas en consulte
            enConsulte = true // Mettre en consulte
            println("✓ - Le magazine '$titre' numéro '$numero' ('$releaseDate') est consulté sur place") // Confirmation
        }
        return enConsulte // retourne Vrai
    }

    override fun arretConsulte(): Boolean { // Arrête la consulte d'un magazine
        if (!enConsulte) println("⚠ - Commencez par consulter le magazine avant de vouloir arrêter la consulte") // Bien sûr, il faut commencer à consulter un magazine avant d'arrêter de le faire
        else{ // Si le magasin est en train d'être consulté
            enConsulte = false // Mettre consultable
            println("✓ - Vous avez arrêté de consulter le magazine '$titre' numéro '$numero' ('$releaseDate')") // Confirmation
        }
        return enConsulte // Renvoie faux
    }
}
class Journal(override var titre: String, override var releaseDate: String) : Media(titre, releaseDate), Consultable{ // Classe des journaux
    override fun afficherDetails(){ println("Livre: Titre = '$titre', Date de parution = '$releaseDate'") } // Affiche les details du journal
    override var enConsulte: Boolean = false
    override fun consulter(): Boolean {
        if (enConsulte)println("⚠ - Quelqu'un consulte déjà ce journal, laissez le finir")
        else{
            enConsulte = true
            println("✓ - Le journal '$titre' du '$releaseDate' est consulté sur place")
        }
        return enConsulte
    }

    override fun arretConsulte(): Boolean {
        if (!enConsulte) println("⚠ - Commencez par consulter le journal avant de vouloir arrêter la consulte")
        else{
            enConsulte = false
            println("✓ - Vous avez arrêté de consulter le journal '$titre' du '$releaseDate'")
        }
        return enConsulte
    }
}
class EnregistrementAudio(override var titre: String, var length: Int, var genre: String, override var releaseDate: String) : Media(titre, releaseDate), Empruntable{ // Classe des enregistrements audio
    override fun afficherDetails(){ println("Enregistrement Audio: Titre = '$titre', Durée = '$length' secondes, Genre = '$genre'") } // Affiche les détails de l'enregistrement audio
    override var emprunte: Boolean = false // On initialise la valeur de l'emprunt en tant que faux
    override fun emprunter(): Boolean { // Fonction pour emprunter l'audio
        if (emprunte) println("⚠ - Cet enregistrement audio a déjà été emprunté") // Bien sur, on ne peut pas emprunter un audio s'il a déjà été emprunté
        else{ // S'il est disponible,
            emprunte = true // On le met comme emprunté
            println("✓ - L'enregistrement audio '$titre' a été emprunté") // Confirmation
        }
        return emprunte // Renvoie vrai
    }

    override fun retourner(): Boolean { // Fonction pour retourner l'audio
        if (!emprunte) println("⚠ - Vous ne pouvez pas retourner un enregistrement audio qui n'a pas été emprunté") // Bien sûr, on ne peut pas retourner un audio qui n'a pas été emprunté
        else{ // S'il a été emprunté
            emprunte = false // On le met comme disponible
            println("✓ - L'enregistrement audio '$titre' a été retourné") // Confirmation
        }
        return emprunte // Retourne faux
    }
}
class DVD(override var titre: String, var length: Int, var genre: String, override var releaseDate: String) : Media(titre, releaseDate), Empruntable{ // Classe des DVDs
    override fun afficherDetails(){ println("DVD: Titre = '$titre', Durée = '$length' secondes, Genre = '$genre'") } // Affiche les détails du DVD
    override var emprunte: Boolean = false
    override fun emprunter(): Boolean {
        if (emprunte)println("⚠ - Ce DVD a déjà été emprunté")
        else{
            emprunte = true
            println("✓ - Le DVD '$titre' a été emprunté")
        }
        return emprunte
    }

    override fun retourner(): Boolean {
        if (!emprunte)println("⚠ - Vous ne pouvez pas retourner un DVD qui n'a pas été emprunté")
        else{
            emprunte = false
            println("✓ - Le DVD '$titre' a été retourné")
        }
        return emprunte
    }
}
class Livre(override var titre: String, var auteur: String, var editeur: String, override var releaseDate: String) : Media(titre, releaseDate), Empruntable, Consultable{ // Classe des Livres

    override fun afficherDetails(){ println("Livre: Titre = '$titre', Auteur = '$auteur', Éditeur = '$editeur', Date de parution = '$releaseDate'") } // Affiche les détails du livre

    override var emprunte: Boolean = false

    override fun emprunter(): Boolean {
        if (emprunte) println("⚠ - Ce livre a déjà été emprunté")
        if (enConsulte) println("⚠ - Quelqu'un est en train de consulter ce livre!") // Bien sûr, on ne va pas arracher le livre des mains de quelqu'un qui le consulte à présent. C'est irréspectueux
        else{
            emprunte = true
            println("✓ - Le livre '$titre' de '$auteur' a été emprunté")
        }
        return emprunte
    }

    override fun retourner(): Boolean {
        if (!emprunte) println("⚠ - Vous ne pouvez pas retourner un livre qui n'a pas été emprunté")
        else{
            emprunte = false
            println("✓ - Le livre '$titre' de '$auteur' a été retourné")
        }
        return emprunte
    }

    override var enConsulte: Boolean = false
    override fun consulter(): Boolean {
        if (enConsulte) println("⚠ - Quelqu'un consulte déjà ce livre, laissez le finir")
        if (emprunte) println("⚠ - Quelqu'un a emprunté ce livre. Il ne peut donc pas être consulté en ce moment") // Bien sûr, on ne peut pas consulter un livre qui a été emprunté
        else{
            enConsulte = true
            println("✓ - Le livre '$titre' de '$auteur' est consulté sur place")
        }
        return enConsulte
    }

    override fun arretConsulte(): Boolean {
        if (!enConsulte) println("⚠ - Commencez par consulter le livre avant de vouloir arrêter la consulte")
        else{
            enConsulte = false
            println("✓ - Vous avez arrêté de consulter le livre '$titre' de '$auteur'")
        }
        return enConsulte
    }
}