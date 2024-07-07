% bubblesort(L, Col, R)
bubblesort([], Col, Col):-!.
bubblesort([A], [A]):-!.
bubblesort([A, B], [A, B]):-A=<B.
bubblesort([A, B], [B, A]):-B<A.

