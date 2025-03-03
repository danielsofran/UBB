maxim([A], A):-!.
maxim([H|T], R):-
    maxim(T, R),
    R >= H, !.
maxim([H|T], H):-
    maxim(T, R),
    R < H.

eliminare([], _, []):-!.
eliminare([H|T], H, R):-
    eliminare(T, H, R), !.
eliminare([H|T], E, [H|R]):-
    eliminare(T, E, R).

elimmx(L, Rez):-
    maxim(L, M),
    eliminare(L, M, Rez).

