# Système Gestion Bibliothèque

Une application Kotlin pour répondre au projet noté du cours du 28 Octobre 2024.


## Fonctions de la bibliothèque
### ajouterMedia (Media)
Cette fonction permet d'ajouter un media dans la bibliothèque.
### ajouterMedia (Liste de Media)
Cette fonction permet de rajouter un ensemble de medias automatiquement. Très utile pour rajouter beaucoup de medias rapidement
### supprMedia (Media)
Cette fonction supprime un media de la liste. À utiliser avec précaution
### afficherBibliotheque ()
Affiche tous les medias dans la bibliothèque
### afficherEmprunts ()
Affiche tous les medias empruntés dans la bibliothèque
### emprunter (Livre, DVD, Audio)
Marque le media comme emprunté si ce n'est pas encore fait. Renvoie une erreur si on essaye d'emprunter des journaux ou des magazines
### emprunter (Livre, DVD, Audio)
Marque le media emprunté comme disponible si ce n'est pas encore fait. Renvoie une erreur si on essaye de retourner des journaux ou des magazines
### consulter (Livre, Journal, Magazine)
Met le media en consulte si ce n'est pas encore fait. Renvoie une erreur si on essaye de consulter des DVD ou des audios
### arretConsulte (Livre, DVD, Audio)
Marque le media en consulte comme disponible si ce n'est pas encore fait. Renvoie une erreur si on essaye de consulter des DVD ou des audios

## Types de messages
### Messages d'erreur 
Les lignes avec l'icône ⚠ signifient que l'operation n'a pas pu s'exécuter correctement
### Messages de validation
Les lignes avec l'icône ✓ confirment que l'action a été faite avec succès
