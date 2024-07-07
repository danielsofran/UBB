(defun suma1(l)
	(cond
		((numberp l) l)
		((atom l) 0)
		(t (+ (suma (car l)) (suma (cdr l))))
	)
)

(defun suma2(l)
	(cond
		((numberp l) l)
		((atom l) 0)
		((+ (suma2 (car l)) (suma2 (cdr l))))
	)
)

(defun adaug(e l)
	(cond
		((null l) (list e))
		(t (cons (car l) (adaug e (cdr l))))
	)
)

(defun invers(l)
	(cond
		((atom l) l)
		(t (append (invers (cdr l)) (list (car l))))
	)
)