% intercalare(L: Lista, N: Intreg, E: Element, LO: Lista)
% Lista=Element*
% L - lista initiala
% N - pozitia pe care va fi inserat elementul
% E - elementul care va fi inserat
% LO: lista rezultat
% (i, i, i, 0), (i, i, i, i)

intercalare([], 0, E, [E]):-!.
intercalare([], N, _, []):-not(N is 0), !.
intercalare(L, 0, E, [E|L]):-not(L=[]), !.
intercalare([H|T], N, E, [H|LO]):-
    N>0, !,
    N1 is N-1,
    intercalare(T, N1, E, LO).

% sumanr(L: Lista, S: Intreg)
% Lista=Element*
% L - lista initiala
% S - suma elementelor numerice din lista L, aflate pe orice nivel
% (i, o), (i, i)

sumanr([], 0):-!.
sumanr([H|T], S):-
    is_list(H), !,
    sumanr(H, S1),
    sumanr(T, S2),
    S is S1+S2.
sumanr([H|T], S):-
    number(H), !,
    sumanr(T, S1),
    S is H+S1.
sumanr([H|T], S):-
    not(is_list(H)), not(number(H)), !,
    sumanr(T, S).

% extend(L1: Lista, L2: Lista, LO: Lista)
% Lista=Element*
% L1 - prima lista
% L2 - a doua lista
% LO - lista obtinuta prin concatenarea celor 2 liste
% (i, i, o), (i, i, i)

extend([], L, L):-!.
extend([H|T], L, [H|LO]):-
    extend(T, L, LO).

% has_list(L: Lista)
% Lista=Element*
% L - lista care este verificata
% true, daca unul din elementele listei L este, la randul sau, o lista
% false, daca lista initiala nu contine alte liste

has_list([H|_]):-is_list(H), !.
has_list([_|T]):-has_list(T).

% sublst(L: Lista, LO: Lista)
% Lista=Element*
% L - lista initiala
% LO - lista care contine toate sublistele listei L, aflate la orice
% nivel

sublst([], []):-!.
sublst([H|T], [H|LO]):-
    is_list(H), not(has_list(H)), !,
    sublst(T, LO).
sublst([H|T], [H|LO]):-
    is_list(H), has_list(H), !,
    sublst(H, LO1),
    sublst(T, LO2),
    extend(LO1, LO2, LO).
sublst([H|T], LO):-
    not(is_list(H)), !,
    sublst(T, LO).

% contine(L: Lista, E: Element)
% Lista=Element*
% L - lista data
% E - un element
% true, daca lista contine elementul
% false, altfel

contine([H|_], H):-!.
contine([_|T], E):-
    contine(T, E).

% mult_egale(M1: Lista, M2: Lista)
% Lista=Element*
% M1 - prima lista
% M2 - a doua lista
% true, daca M1 este

mult_egale([], []):-!.
mult_egale(M1, M2):-
    findall(Y, permutation(M1, Y), Perms),
    contine(Perms, M2).
