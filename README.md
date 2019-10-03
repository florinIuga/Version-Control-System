# Version-Control-System POO

IUGA FLORIN-EUGEN, 325CA

		Project 2
		 VCS

IDEEA GENERALA
---------------
Am urmarit indicatiile initiale, astfel ca am adaugat parsarea
pentru VCS, am adaugat in OperationType si restul operatiilor
de VCS, iar apoi a urmat efectiv logica VCS-ului prin
intermediul claselor de care m-am folosit. In acest sens,
fiecare operatie de VCS are asociata o clasa specifica ei,
in care am suprascris metoda execute() pentru a respecta
cerinta operatiei respective. In plus, am mai adaugat cateva
clase ajutatoare (Staging, Branch, Commit).

Clasa Branch
-> Branch este efectiv tipul Branch care are un nume, respectiv
o lista de commit-uri executate pe el

Clasa BranchOperation
-> creeaza un nou Branch si il introduce in lista de "branchuri"

Clasa Commit
-> fiecare commit are asociat un mesaj, un ID si un snapshot

Clasa CommitOperation
-> creeaza un nou commit, ii genereaza un  ID commitului,
iar apoi este adaugat commit-ul in lista de commit-uri a
branch-ului curent.

Clasa CheckoutOperation
-> in functie de formatul comenzii, seteaza noul branch curent
sau seteaza snapshot-ul curent, in functie de ID-ul commit-ului
iar apoi sterge restul commit-urilor diferite de acel ID

Clasa RollbackOperation
-> seteaza snapshot-ul curent la snapshot-ul ultimului commit si
goleste staging-ul.

Clasa LogOperation
-> afiseaza mesajele si id-urile commiturilor date pe current
branch.

Clasa StatusOperation
-> obtin currentBranch prin metoda getCurrentBranch(), il
afisez, iar apoi afisez fiecare comanda din staging

Clasa Staging
-> contine o lista de StagingFormat. Ideea e ca in clasa
StaginFormat am 2 constructori care, in functie de tipul
comenzii, seteaza campurile din StagingFormat intr-un mod
in care sa ma avantajeze la afisarea efectiva a Stagingului.

InvalidVcsOperation
-> suprascrie metoda execute, intorcand VCS_BAD_COMMAND in
cazul in care comanda citita nu are formatul corect. De ex.
vcs master in loc de vcs branch master.
