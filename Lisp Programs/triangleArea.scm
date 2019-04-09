; Function to calculate the area of a triangle
(define (checkTriangleArea ans)
  (print ans)
  (if (> ans 10)
    (print "That's a big triangle")
    (print "That's a small triangle")
  )
)
(define (triangleArea b h)
  (checkTriangleArea (/ (* b h) 2))
)

(triangleArea 3.5 4)