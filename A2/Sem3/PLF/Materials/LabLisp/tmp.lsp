(defun m (L) ; maximul dintr-o lista
    (cond
        ((numberp L) L)
        ((atom L) most-negative-fixnum)
        (t (apply #'max
                    (mapcar #'m L)
            )
        )
    )
)

(defun lista (L)
    (mapcan #'((lambda (v)
                (cond
                    (= 0 (mod v 2) (list v))
                    (t nil)
                )
                ) 
                (m L)
            )
    L)
)


; (lista '((5 a (2 b (8))) (7 a (9)) (c d (10)))) → (8 10)

(defun permutari (L)
    (cond
        ((null (cdr L)) (list L))
        (t (mapcan #'(lambda (e)
                        (mapcar #'(lambda (p)
                                        (cons e p)
                                    )
                                (permutari (remove e L))
                        )
                     )
                    L
            )
        )
    )
)


(defun subm (L) ; submultimi
    (cond
        ((null L) (list nil))
        (t ((lambda (s)
                (append s (mapcar #'(lambda (sb)
                                        (cons (car L) sb)
                                    )
                                    s
                        )
                )
            )
            (subm (cdr L))
            )
        )
    )
)