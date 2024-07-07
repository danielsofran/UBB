; adaug_aux(L: lista, N: intreg, E: element, index: Intreg)
; L - lista data
; N - numarul intreg reprezentand CMMDC al pozitiilor pe care inseram elem E
; E - elementul pe care il inseram
; returneaza lista rezultat
(defun  adaug_aux (L N E index)
    (cond
        ((null L) NIL)
        ((and (not (null L)) 
            (not (= (mod index N) 0)))
                    (CONS (CAR L) (adaug_aux (CDR L) N E (+ 1 index))))
        (t (APPEND (list (CAR L) E) (adaug_aux (CDR L) N E (+ 1 index))))
        ;(t (CONS (CAR L) (CONS E (adaug_aux (CDR L) N E (+ 1 index)))))
    )
)

; predicat principal care initializeaza index-ul pred ant cu 1
(defun adaug (L N E)
    (adaug_aux L N E '1)
)

; (load "test.lsp")
; (adaug_aux '(1 2 3 4 5 6) '2 '7 '1)
; (adaug '(1 2 3 4 5 6) '2 '7)