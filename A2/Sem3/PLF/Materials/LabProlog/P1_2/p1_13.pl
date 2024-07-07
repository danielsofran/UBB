% apare(L: Lista, E: Element)
% Lista=Element*
% L: lista data
% true, daca E apare in lista
% (i, i)
apare([H|_], H):-!.
apare([_|T], E):-
    apare(T, E).

% starge(L: Lista, E: Element, LO: Lista)
% Lista=Element*
% L: lista data
% E: Elementul care va fi sters
% LO: lista care va avea primul element E sters
% (i, i, o), (i, i, i)
sterge([], _, []):-!.
sterge([H|T], H, T):-!.
sterge([H|T], E, [H|LO]):-
    sterge(T, E, LO).

% getmult_aux(L: Lista, LC: Lista, LO: Lista)
% Lista=Element*
% L: Lista data
% LC: Copia listei initiale
% LO: multimea formata din el listei L
% (i, i, o), (i, i, i)
getmult_aux([], LC, LC).
getmult_aux([H|T], LC, LO):-
    apare(T, H), !,
    sterge(LC, H, LC1),
    getmult_aux(T, LC1, LO).
getmult_aux([H|T], LC, LO):-
    not(apare(T, H)),
    getmult_aux(T, LC, LO).


% getmult(L: Lista, M: Lista)
% Lista=Element*
% L: Lista data
% LO: multimea formata din el listei L
% (i, o), (i, i)
getmult(L, M):-
    getmult_aux(L, L, M).

% cmmdc(A: Intreg, B: Intreg, REZ: Intreg)
% A, B: Cei doi intregi
% REZ: CMMDC(A, B)
% (i, i, o), (i, i, i)
cmmdc(0, B, B):-!.
cmmdc(A, 0, A):-!.
cmmdc(A, B, REZ):-
    R is A mod B,
    cmmdc(B, R, REZ).

% cmmdcn(L: Lista, REZ: Intreg)
% % Lista=Element*
% L: Lista data
% REZ: cmmdc-ul numerelor din lista
% (i, o), (i, i)
cmmdcn([A], A):-!.
cmmdcn([A,B], REZ):-
    cmmdc(A, B, REZ), !.
cmmdcn([H1,H2|T], REZ):-
    cmmdc(H1, H2, R1),
    cmmdcn(T, R2),
    cmmdc(R1, R2, REZ).
