POLITIC ANDREI BOGDAN, GRUPA 325CD
TEMA 2 POO -

				DETALII LEGATE DE IMPLEMENTAREA TEMEI

	Tema este legata de simularea unui utilitar ce serveste la desenarea automata a uneia sau
mai multor figuri geometrice. In cadrul algoritmilor, am aranjat clasele de obiecte in 2 path-uri:
pachetul default, ale carui clase contin functionalitati de baza in program, pe langa definirea 
metodelor de lucru pe figuri.
	Astfel, clasa getFromFile primeste fisierul de input si, pe baza a diferite metode, parcurge
si insereaza in buffer date de diverse tipuri: numere intregi, string-uri, ... . Apoi, are
implementata metoda outputShapes care duce spre efectuarea operatiilor pe figuri, avand deja 
stocate intr-un Array (de tip Bounded Wild Card - interfata Visitable, pe care o mostenesc 
toate clasele ce desemneaza figuri geometrice) datele despre figurile geometrice cu care se lucreaza.
	Clasa ShapeFactory contine metode ce atribuie date unui tip particular de figura geometrica, 
primind acest tip ca parametru, apoi incarca un nou obiect de tipul respectiv cu acele date, obiect 
care mai apoi este returnat. Aceasta clasa este implementata dupa design pattern-ul Factory.
	In clasa getFromFile, pentru a se vizita fiecare element din vectorul Figures de tip 
Wildcard, se foloseste un loop for-each, cu metoda accept pentru fiecare forma geometrica retinuta 
in vector. Clasa visitor are ca constructor buffer-ul cu imaginea construita pana in acel moment.
Metodele din clasa Visitor (7 tipuri de forme => 7 metode), semnate cu numele "Draw" si tipul 
figurii primite, "plaseaza" figura in buffer (buffer-ul este de tip ImageBuffer). Dupa desenarea 
conturului, se apeleaza tot in cadrul clasei Visitor functia pentru floodfill, care se afla in clasa 
tipului figurii.
	Metoda floodfill se bazeaza pe vizitarea tuturor pixelilor vecini care inca nu au culoarea 
specificata. Se formeaza o lista (o coada) cu toti pixelii, se incarca in ea pixelii vecini care nu 
respecta culoarea data (se porneste de la primul pixel din centrul de greutate care se plaseaza prima 
oara in coada), iar la final, pixelului de referinta i se atribuie acea culoare, dupa care se sterge 
din coada. Evident, schimbarea de culoare se face in buffer care este primit ca parametru de functia 
floodfill. Algoritmul se termina cand coada se goleste. Pentru tipuri particulare de figuri, cum sunt 
patratul si dreptunghiul, pixelii se umplu linie cu linie, nu se mai merge pe principiul cozii. Pentru 
cerc, se realizeaza conturul de doua ori, deoarece trebuie sa se umple toata figura, indiferent de 
alte contururi de aceeasi culoare ce trec prin interior. Astfel, conturul "limita" specificat in 
functia floodfill are transparenta cu valoarea 101. Dupa ce se coloreaza toti pixelii din interiorul 
acelui contur, se stabileste conturul normal.
	Clasele tipurilor de figuri se afla in pachetul "shapes", unde mai este si clasa Canvas-ului, 
si interfetele draw (pentru metodele din Visitor) si Visitable (pentru metoda accept din clasele 
formelor), si inclusiv clasa pentru tipul pixelului. Fiecare clasa pentru un tip de figura are 
metodele draw (cu algoritmul Bresenham pentru desenarea cercurilor sau dupa caz a liniilor ce 
constituie figura), calculateCentroid (care calculeaza coordonatele centrului de greutate) si floodfill.
	Pe langa Visiable-Visitor si Factory, am implementat si design pattern-ul Singleton, ce 
realizeaza obiecte unice pentru clasele getFromFile si Canvas.
	La final, buffer-ul se copiaza in imaginea pentru output "Drawing".