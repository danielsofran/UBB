(DEFUN creeazaFrunza (X)
    (LIST X 0)
)

(DEFUN appendRadacina (AV1)
    (LIST (CAR AV1) (- (LENGTH AV1) 1))
)

(defun transf (AV1)
    (cond
        ((NULL AV1) '(nil))
        ((= (length AV1) 1) (creeazaFrunza (CAR AV1)))
        ((= (length AV1) 2) '(NIL))
        ;(T (APPEND (appendRadacina AV1) (transf (CADR AV1)) (transf (CADDR AV1))))
        (T (NCONC (appendRadacina AV1) (MAPCAN #'transf (CDR AV1))))
    )
)

; (A (B) (C (D) (E)))
; (A 2 B 0 C 2 D 0 E 0)
; (load "L2.lsp")