# twitter_guards

# Configuration Management Plan
=============================

### Repozytorium, Wiki, Issue Tracker

Repozytorium naszego projektu zostało umieszczone pod adresem https://github.com/blostic/twitter_guards

Dokumentacja projektu znajduje się na stronie https://github.com/blostic/twitter_guards/wiki oraz (tymczasowo - dla usprawnienia pracy grupowej) na https://docs.google.com/document/d/1upvmFSRcDeXnV039CXxXmGhpl-BepA0uEwBaxPJ9HIY

W projekcie wykorzystujemy issue tracker dostarczony przed github.com. Adres: https://github.com/blostic/twitter_guards/issues

### Branching Model
Zasady tworzenia gałęzi:
* Dla każdego taska powinien być tworzony osobny branch (wyjątkiem są sytuacje, w których 2 taski są ze sobą bardzo silnie powiązane) 
* Przed zmergowaniem do głównego brancha, kod powstały w trakcie wykonywania danego taska powinien zostać poddany ocenie przez właściwego dla autora kodu recenzenta

Nazewnictwo branchy:
* Nazwa brancha powinna być krótka, lecz jednocześnie opisywać task przypisany do danego brancha

Testy:
   - Testy dla danego taska mają się znajdować na tej samej gałęzi co kod

### Standardy commitowania
W projekcie stosujemy się do następujących zasad commitowania:
* commitujemy często
* komentarze przy commitach powinny być po angielsku
* dokładniejszy opis jak powinny wyglądać commity można znaleźć tutaj: [commit messages] (http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html)
* NIE wrzucamy na głównego brancha niekompilującego się kodu!

### Wersjonowanie aplikacji

W zależności od iteracji aplikacja będzie miała nazwę **TG_v0.x**
gdzie x oznacza numer kolejnej iteracji. 

Milestone 1:
  * Integracja zaplanowanych technologii
  * Budowanie projektu przy pomocy mavena
  * Zaprojektowanie interfejsu użytkownika
  
Milestone 2:
Milestone 3:
