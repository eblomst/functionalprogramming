;;
;; Prelude
;;

;; Lambdas are defined by the syntax (lambda (arguments) body)
;;(lambda (a) (+ a 2))

;; Lambdas should be called with the funcall function
;;(funcall (lambda (a) (+ a 2)) 4) ;; 6

;;
;; Exercises
;;

;; Exercise 1
;; Call this lambda in the same line!
(write(funcall (lambda (a b) (+ a b)) 3 4))

;; Exercise 2
;; Write a function that returns the lambda below
(defun test() (lambda (a) (+ a 3)))

(write(funcall (test) 3))
;; Exercise 3
;; Write a function that returns a lamdda that is run twice on some input.
;; If you had a function in Java (f) this would be written: f(f(4))
(defun lambdafunc(f)
    (funcall (lambda (a) (+ a 2)) f)
)

(write(lambdafunc ((lambda (a) (+ a 1)) 4)))
