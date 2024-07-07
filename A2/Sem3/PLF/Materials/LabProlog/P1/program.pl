% insereaza(L: Lista, N: Intreg, E: Element, LO: Lista)
% Lista=Element*
% L: lista initiala
% N: pozitia pe care vom intercala elementul
% E: elementul ce trebuie intercalat
% LO: lista rezultat
% (i, i, i, o), (i , i, i, i)

insereaza([], N, _, []):- N>1, !.
insereaza([], 1, E, [E]):- !.
insereaza(L, 1, E, [E|L]):- !.
insereaza([H|T], N, E, [H|LO]):-
    N1 is N-1,
    insereaza(T, N1, E, LO).

% b

sumanr([], 0):- !.
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
    not(number(H)), not(is_list(H)), !,
    sumanr(T, S).

% c

extend([], L, L):- !.
extend(L, [], L):- !.
extend([H|T], L, [H|LO]):-
    extend(T, L, LO).

has_list([H|_]):- is_list(H), !.
has_list([_|T]):- has_list(T).

subliste([], []):- !.
subliste([H|T], [H|LO]):-
    is_list(H), has_list(H), !,
    subliste(H, LO1),
    subliste(T, LO2),
    extend(LO1, LO2, LO). /* LO = LO1 + LO2 */
subliste([H|T], [H|LO]):-
    is_list(H), not(has_list(H)), !,
    subliste(T, LO).
subliste([H|T], LO):-
    not(is_list(H)), !,
    subliste(T, LO).

% d

apare([H|_], H):- !.
apare([_|T], E):-
    apare(T, E).

mult_egale(L1, L2):-
    findall(X, permutation(L1, X), Perms),
    apare(Perms, L2).

