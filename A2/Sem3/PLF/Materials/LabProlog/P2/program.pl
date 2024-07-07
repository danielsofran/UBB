% adaugaSf(L: Lista, E: Element, LO: Lista)
% Lista=Element*
% (i, i, o), (o, i, i), (i, o, i), (i, i, i), (o, o, i),  - determinist
% adauga elem E la finalul listei L
adaugaSf([], E, [E]).
adaugaSf([H|T], E, [H|LO]):-
    adaugaSf(T, E, LO).

% rast(L:Lista, LO: Lista)
% Lista=Element*
% (i, o), (i, i) - determinist
% determina rasturnatul unei liste
rast([], []).
rast([H|T], LO):-
    rast(T, LR),
    adaugaSf(LR, H, LO).

% getcarry(C: Integer, F: Integer, O: Integer)
% C - o cifra
% F - flag (transportul)
% O - rezultat
% return: true daca C+F depaseste domeniul cifrei, false altfel
% (i, i, o), (i, i, i) - determinist
getcarry(C, F, O):-
    O is (C+F) div 10.

% nextcif(C: Integer, F: Integer, O: Integer)
% C - o cifra
% F - flag (transportul)
% O - rezultat
% (i, i, o), (i, i, i) - determinist
nextcif(C, F, O):-
    O is (C+F) mod 10.

% nextnr_aux(NR: List, F: Integer, REZ: Integer)
% List=Integer*
% NR - numatul dat, inversat, ca lista de cifre
% F - flagul, initial 1, deoarece incrementam
% REZ - numarul incrementat, ca lista de cifre
% (i, i, o), (i, i, i) - determinist
nextnr_aux([], 0, []):-!.
nextnr_aux([], 1, [1]):-!.
nextnr_aux([C|T], F, [C1|NR1]):-
    nextcif(C, F, C1),
    getcarry(C, F, F1),
    nextnr_aux(T, F1, NR1).

% nextnr(NR: List, NRO: List)
% NR - numarul dat
% NRO - numarul incrementat
% (i, i), (i, o) - determinist
nextnr(NR, Rez):-
    rast(NR, NRI),
    nextnr_aux(NRI, 1, R),
    rast(R, Rez).

% succesori(L: Lista, LO: Lista)
% Lista=(Integer/Lista)*
% L - lista de numere si liste de cifre reprezentand numere
% LO - lista care contine intregii initiali si succesorii numerelor
% reprezentate sub forma de lista
% (i, o), (i, i) - determinist
succesori([], []):-!.
succesori([H|T], [H|LO]):-
    not(is_list(H)), !,
    succesori(T, LO).
succesori([H|T], [RH|LO]):-
    is_list(H),
    nextnr(H, RH),
    succesori(T, LO).


