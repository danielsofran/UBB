fact(N, 1):-N=:=1, !.
fact(N, 1):-N=:=0, !.
fact(N, F):-
    N1 = N-1,
    fact(N1, F1),
    F = F1*N.

g([H|_], E, [E,H]).
g([_|T], E, P):-
    g(T, E, P).

f1([H|T],P):-
    g(T, H, P).
f1([_|T], P):-
    f1(T, P).

candidat([H|T], H, T).
candidat([H|L], C, [H|T]):-candidat(L, C, T).

% aranj(L: lista, K:INtreg, Rez: Lista)
comb([H|_], 1, [H]).
comb([_|T], K, R):-
    comb(T, K, R).
comb([H|T], K, [H|R]):-
    K>1, K1 is K-1,
    comb(T, K1, R).

perm([], []).
perm(L, [C|R]):-
    candidat(L, C, T),
    perm(T, R).

aranj(L, K, R):-
    comb(L, K, C),
    perm(C, R).


sP([],[]).
sP([_|T],S):-sP(T,S).
sP([H|T],[H|S]):- H mod 2 =:=0,
    !,
    sP(T,S).
sP([H|T],[H|S]):-sI(T,S).


sI([H],[H]):-H mod 2 =\=0, !.
sI([_|T],S):-sI(T,S).
sI([H|T],[H|S]):-H mod 2 =:=0,
    !,
    sI(T,S).
sI([H|T],[H|S]):-sP(T,S).

subm([H], [H], H).
subm([_|T], R, U):-subm(T, R, U).
subm([H|T], [H|R], H):-
    subm(T, R, U),
    H<U.

verifMod([H1,H2], M):-!, abs(H1-H2)=<M.
verifMod([H1,H2|T], M):-
    D is abs(H1-H2),
    D =< M,
    verifMod([H2|T], M).

genL(N, [], N):-!.
genL(N, [E|L], I):-
    I<N,
    E is N+I,
    I1 is I+1,
    genL(N, L, I1).

pb3_aux(N, P):-
    genL(N, L, 0),
    perm(L, P),
    verifMod(P, 2).
pb3(N,R):-findall(P, pb3_aux(N, P), R).

prim(N, D):-N>1, D>N/2, !.
prim(N, D):-N>1,
    N mod D =\= 0,
    prim(N, D+1).

% dublare(L, N, I, R)
dublare([], _, _, []):-!.
dublare([H|T], N, I, [H|R]):-
    I < N, !, I1 is I+1,
    dublare(T, N, I1, R).
dublare([H|T], N, N, [H,H|R]):-
    dublare(T, N, 1, R).

dubP(L, N, R):-
    dublare(L, N, 1, R).

f([], 0).
f([H|T], P):-!,H mod 2 =:= 0, f(T, P1), P is P1*H.
f([_|T], P):-f(T, P1), P is P1.
