% candidat(A: Integer, B: Integer, E: Integer)
% A, B - determina un interval inchis
% E - un element din interval
% (i, i, o)- nedeterminist
candidat(A, B, A):- A =< B.
candidat(A, B, R):-
    A1 is A+1,
    A1 =< B,
    candidat(A1, B, R).

% candidat2(L: Lista, E: Element, R: Lista)
% aleg un element din lista si obtin si celelalte elemente din lista pe
% langa asta
% Lista=Element*
% L: lista de valori posibile
% E: o valoare posibila din lista
% R: restul listei, excluzand valoarea E
% (i, o, o), (i, i, i) - nedeterminist
candidat2([H|T], H, T).
candidat2([H|T], E, [H|R]):-
    candidat2(T, E, R).

% absdif(A: Integer, B: Integer, R: Integer)
% calculez diferenta in modul
% R = |A-B|
% (i, i, o), (i, i, i) - determinist
absdif(A, B, R):- A >= B, R is A-B.
absdif(A, B, R):- B > A, R is B-A.

% back(L: Lista, M: Intreg, Col: Lista, Rez: Lista)
% determin toate secventele care se pot forma cu elemente din lista L
% care au diferenta in modul dintre elem consec >= M
% Col: variabila de tip colectoare, memoreaza rezultate partiale ale
% secv
% Rez: secventa finala
% (i, i, i, o), (i, i, i, i) - nedeterminist
back([], _, Col, Col).
back(L, M, [Col1|T], Rez):-
    not(L = []),
    candidat2(L, E, R),
    absdif(E, Col1, D),
    D >= M,
    back(R, M, [E,Col1|T], Rez).

% gen1N(N: Intreg, Rez: Lista)
% Lista=Intreg*
% Rez = secv 1..N
% (i, o), (i, i) - determinist
gen1N(1, [1]):-!.
gen1N(N, [N|L]):-
    N > 1,
    N1 is N-1,
    gen1N(N1, L).

% genSecv(N: Integer, M: Integer, Rez: Lista)
% Lista=Intreg*
% predicat principal
% (i, i, o) - nedeterminist
genSecv(N, M, Rez):-
    N > 1, M > 0,
    gen1N(N, L),
    candidat2(L, E, R),
    back(R, M, [E], Rez).











