(defun inloc (L k)
    (cond
        ((and (= 0 k) (atom L))
            '(0)
        )
        ((atom L) (LIST L))
        (t (LIST (apply #'append
                    (mapcar #'(LAMBDA (ll) (inloc ll (- k 1)))
                        L
                    )
                )
            )
        )
    )
)

(DEFUN inlocP (L k)
    (car (inloc L k))
)

; ultimul atom numeric (de pe un nivel par/impar)
(defun AtNum (l)
    (cond
        ((numberp l) (list l))
        ((atom l) nil)
        (T (apply #'append (MAPCAR #'AtNum l)))
    )
)

(DEFUN ultAtNumImp (L)
    ((lambda (L)
        (cond
            ((null L) NIL)
            ((= 0 (mod (car L) 2)) NIL)
            (T T)
        )
    ) (reverse (AtNum L))
    )
)

(defun lin (l)
    (cond
        ((atom l) (list l))
        (t (apply #'append (mapcar #'lin l)))    )
)

(defun cont (l e)
    (cond
        ((null l) nil)
        ((EQ (car l) e) T)
        (t (cont (cdr l) e))
    )
)

(defun cale (l e)
    (cond
        ((atom l) (list l))
        ((and (listp l) (cont (lin l) e))
            (mapcan #'(lambda (l) (cale l e)) l)
        )
        (t nil)
    )
)
;'(a (b (c) (d (e))) (f (g)))

; (load "train.lsp")
; (inloc '(a (1 (2 b)) (c (d))) '2)