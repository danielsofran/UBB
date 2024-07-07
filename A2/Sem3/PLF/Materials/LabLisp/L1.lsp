(DEFUN interclas (L1 L2)
    (COND
        ((AND (NULL L1) (NOT (NULL L2))) L2)
        ((NULL L2) L1)
        ((< (CAR L1) (CAR L2)) (CONS (CAR L1) (interclas (CDR L1) L2)))
        (T (CONS (CAR L2) (interclas L1 (CDR L2))))
    )
)

(DEFUN schimba (L E L1)
    (COND
        ((NULL L) NIL)
        ((EQ (CAR L) E) (APPEND L1 (schimba (CDR L) E L1)))
        ((NOT (LISTP (CAR L))) (CONS (CAR L) (schimba (CDR L) E L1)))
        (T (CONS (schimba (CAR L) E L1) (schimba (CDR L) E L1)))
    )
)

; L1 - primul nr sub forma de lista inversata
; L2 - al doilea nr sub forma de lista inversata
; Tr - transportul
(DEFUN suma_liste (L1 L2 Tr)
    (COND
        ((AND (NULL L1) (NULL L2) (= Tr 0)) NIL)
        ((AND (NULL L1) (NULL L2)) Tr) ; Tr != 0
        ((NULL L1) (CONS (MOD (+ (CAR L2) Tr) 10) (suma_liste L1 (CDR L2) (FLOOR (+ (CAR L2) Tr) 10))))
        ((NULL L2) (CONS (MOD (+ (CAR L1) Tr) 10) (suma_liste (CDR L1) L2 (FLOOR (+ (CAR L1) Tr) 10))))
        (T (CONS (MOD (+ (CAR L1) (CAR L2) Tr) 10) (suma_liste (CDR L1) (CDR L2) (FLOOR (+ (CAR L1) (CAR L2) Tr) 10))))
    )
)

(DEFUN toNr (L)
    (COND
        ((NULL L) 0)
        (T (+ (* (CAR L) (EXPT 10 (- (LENGTH L) 1))) (toNr (CDR L))))
    )
)

(DEFUN rev (L Col)
    (COND
        ((NULL L) Col)
        (T (rev (CDR L) (CONS (CAR L) Col)))
    )
)

(DEFUN suma (L1 L2)
    ((LAMBDA (S) (VALUES S (toNr S))) (rev (suma_liste (rev L1 ()) (rev L2 ()) 0) ()))
)

(DEFUN cmmdc2 (A B)
    (COND
        ((= A 0) B)
        ((= B 0) A)
        (T (cmmdc2 B (MOD A B)))
    )
)

(DEFUN cmmdcn (L)
    (COND
        ((< (LENGTH L) 2) "Eroare!")
        ((= (LENGTH L) 2) (cmmdc2 (CAR L) (CADR L)))
        (T (cmmdc2 (CAR L) (cmmdcn (CDR L))))
    )
)

; (load "L1.lsp")