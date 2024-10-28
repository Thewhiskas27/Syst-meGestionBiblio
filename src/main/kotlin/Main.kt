fun main() {
    val bibliotheque = Bibliotheque()
    val magazine = Magazine("National Geographic", "2024-10-28", "10")
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