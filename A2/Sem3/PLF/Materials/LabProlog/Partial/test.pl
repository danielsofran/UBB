% candidat(L, E, R)
candidat([H|T], H, T).
candidat([H|T], E, [H|R]):-
    candidat(T, E, R).

% all_sublists(L, R)
allsublists([H|_], [H]).
allsublists([H|T], [H|R]):-
    allsublists(T, R).

findallsublists(L, R):-findall(R1, allsublists(L, R1), R).

% in_list(L: Lista, E: Element)
% verifica daca E e in L
% (i, i) - determinist
in_list([E|_], E):-!.
in_list([_|T], E):-
    in_list(T, E).

% extend(L1: Lista, L2: Lista, L: Lista)
% L = L1+L2
% (i, i, o) - determinist
extend([], L, L):- !.
extend(L, [], L):- !.
extend([H|T], L, [H|LO]):-
    extend(T, L, LO).

% unused
getsubl_aux([], []).
getsubl_aux([H|T], R):-
    findallsublists([H|T], R1),
    getsubl_aux(T, R2),
    extend(R1, R2, R).

% unused
getsubl(L, [[]|R]):-getsubl_aux(L, R).

% getsubl2(L: Lista, R: Lista)
% Returneaza toate sublistele listei L, dar pot aparea duplicate
% (i, o) - nedeterminist
getsubl2([], []).
getsubl2([H|_], [H]).
getsubl2([_|T], R):-
    getsubl2(T, R).
getsubl2([H|T], [H|R]):-
    getsubl2(T, R).

% elimdup(L: Lista, R: Lista)
% elimin duplicatele unei liste
elimdup([], []):-!.
elimdup([H|T], R):-
    in_list(T, H), !,
    elimdup(T, R).
elimdup([H|T], [H|R]):-
    elimdup(T, R).

% getsubl2_p(L: Lista, R: Lista)
% predicat principal
% (i, o) - determinist
getsubl2_p(L, R):-
    findall(R1, getsubl2(L, R1), R2),
    elimdup(R2, R).


