# Oktatás => Nyilvántartó program

## A program célja
Nyílvántartja egy oktatási intézmény dolgozóit és diákjait
Jelenleg 3 különböző dolgozót képes kezelni,
PRINCIPAL = Igazgató
SECRETARY = Titkár
TEACHER = Tanár

A diákoknak pedig osztály nevet lehet meghatározni.
A dolgozók beosztása pedig tárolja a fizetésüket is, jelenleg
nem lehet kézileg meghatározni külön mindenkinek!

Az eltárolt adatokat kimenti egy nyilvantarto.xml fájlba, illetve
a program indulásakor megpróbálja innen beolvasni az eddig eltároltakat!

## A program működése
Első sorban a főmenüt hozza be, ahol 4 opciónk van,

- [x] #21
*** Főmenü ***
1. Személyek kezelése   ** Ez átvisz minket egy kezelő menübe **
2. Mentés               ** Kimenti a jelenleg letárolt személyeket a "nyilvantarto.xml" fájlba **
3. Frissítés            ** Beolvassa a "nyilvantarto.xml" fájlt és ha van új személy, hozzá adja **
4. Kilépés              ** Kilép a programból, elég egyértelmű. :) **

- [ ] #28
*** Személyek kezelése ***
1. Személy hozzáadása   ** Új személy felvétele a listába **
2. Személy törlése      ** Személy törlése a listából (pontos név megadása szükséges) **
3. Személy keresése     ** Pontos név nem kötelező, ha egy részét írjuk be, minden találatot kiír **
4. Személy szerkesztése ** Személy adatainak szerkesztése (pontos név megadása szükséges) **
5. Személyek listázása  ** Minden személy listázása (felbontja dolgozókra és diákokra) **
6. Visssza a főmenübe   ** Vissza a főmenübe, nem igényel túl sok magyarázatot :D **

Jelenleg a program ennyiből áll, további információk az egyes metódusokról, osztályokról,
a kódon belül javadoc segítségével megadásra kerültek, így mindenről lehet tudni, hogy pontosan mit csinál.