; stergeAt(L: lista neliniara, E: element)
; returneaza Lista din care se sterge elementl e de la orice nivel
(DEFUN stergeAt (L E)
;(apply #'append
    (COND
        ((NULL L) '(NIL))
        (T (MAPCAN #'(lambda (X)
                        (COND
                            ((EQ X E) NIL)
                            ((ATOM X) (LIST X))
                            (T (LIST (stergeAt X E)))
                        )
                )
                L
        ))
    )
;)
)

; (stergeat '(1 2 3) 0)
; (load "L3.lsp")