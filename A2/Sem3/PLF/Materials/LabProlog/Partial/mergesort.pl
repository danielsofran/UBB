% interclas(L1, L2, Rez)
interclas(L1, [], L1):-!.
interclas([], L2, L2):-!.
interclas([H1|T1], [H2|T2], [H1|R]):-
    H1 =< H2, !,
    interclas(T1, [H2|T2], R).
interclas([H1|T1], [H2|T2], [H2|R]):-
    H2 < H1, !,
    interclas([H1|T1], T2, R).

% len(L, N)
len([], 0):-!.
len([_|T], R):-
    len(T, R1),
    R is R1+1.

% split(L, L1, L2, N)
split([], [], [], _):-!.
split([H|T], [H|L1], L2, N):-
    len(T, L),
    L*2 < N, !,
    split(T, L1, L2, N).
split([H|T], L1, [H|L2], N):-
    len(T, L),
    L*2 >= N,
    split(T, L1, L2, N).

% mergesort(L, Rez)
mergesort([], []):-!.
mergesort([A], [A]):-!.
mergesort(L, Rez):-
    len(L, N),
    split(L, L1, L2, N),
    mergesort(L1, R1),
    mergesort(L2, R2),
    interclas(R1, R2, Rez).
