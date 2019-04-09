(define (factorial n)
    (if (equal? n 0) 1 
      (* n (factorial (- n 1)))
    )
  )
  (print (factorial 3))