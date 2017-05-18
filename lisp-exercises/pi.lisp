
;;
;; Preamble: Lisp prerequisits
;;

;; These two lines sets the number of binary digits used to represent a float
;; in Lisp. This is necessary because you'll be working with tiny numbers
;; TL;DR ignore these two lines
(setf (EXT:LONG-FLOAT-DIGITS) 35000)
(setf *read-default-float-format* 'long-float)

;; This method rounds a number to a certain precision
;; It takes two arguments: the number to round and the number of digits to
;; round in decimals
;;
;; Example: (roundToPrecision 10.0044 3) -> 10.004
(defun roundToPrecision (number precision)
  (let
    ((p (expt 10 precision))) ;; 1000
    (/ (round (* number p)) p) ;; 10.0044 * 1000 = 10004.4 -> 10004.0 / 1000 = 10.004
  )
)

;;
;; Exercise
;;

;; Exercise
;; Your task is to implement the Gauss-Legendre algorithm for calculating pi
;; and extract 10.000 (ten thousand) digits

;; Gauss-Legendre algorithm on Wikipedia
;; https://en.wikipedia.org/wiki/Gauss%E2%80%93Legendre_algorithm
(setf a 1)
(setf b (/ 1 (sqrt 2)))
(setf ts (/ 1 4 ))
(setf p 1)
(setf prevPI 0)

(defun nextA (a b)
    (/ ( + a b ) 2)

)

(defun nextB (a b)
    (sqrt ( * a b ))
    (setf b (sqrt ( * a b )))
)

(defun nextT (ts p a)

    (- ts (* p (expt (- a (nextA a b)) 2)))
    (setf ts (- ts (* p (expt (- a (nextA a b)) 2))))
    ;;(- ts (* p (* (- a (nextA a b ) (- a (nextA a b ))))))
    ;;( (- ts ( * p (* (- a nextA(a b) (- a nextA(a b)) ) ))))
)

(defun nextP (p)
    ( * 2 p )
    (setf p ( * 2 p ))
)

(defun piCal (a b p ts)
  ;;(setf prevPI

  (let ((aNext (nextA a b)) (bNext (nextB a b)) (tNext (nextT ts p a)) (pNext (nextP p)))

  (setf prevPI(/ (expt (+ aNext bNext) 2) (* 4 tNext)))
  ;;(piCal aNext bNext pNext tNext)
  )

     ;;(/ (expt (+ (nextA 1 (/ 1 (sqrt 2))) (nextB 1 (/ 1 (sqrt 2)))) 2) (* 4 (nextT (/ 1 4 ) 1 1)))

)

;;(write(piCal a b p ts prevPI))
(write(piCal 1 (/ 1 (sqrt 2)) 1 (/ 1 4 )))
