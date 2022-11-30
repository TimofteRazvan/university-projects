; a) Write a function to return the dot product of two vectors.
; =============================================================================

; =============================================================================
; dot((l1,l2...ln), (k1,k2...kn)) =
; | 0                                           , if n == 0
; | (l1*k1) + dot((l2,l3...ln), (k2,k3...kn))   , otherwise
(defun dot(array1 array2)
  (cond
    ((null array1) 0)
    (T (+ 
        (* (car array1) (car array2)) 
        (dot (cdr array1) (cdr array2))))
  )
)
; Cond: if (a1 not empty) => return (a1[i] * a2[i]) + dot(a1+1, a2+1)
;       else              => return 0
; =============================================================================
;(write (dot (list 2 3 5 6) (list 3 2 7 8)))

; =============================================================================
; b) Write a function to return the depth of a list.
; =============================================================================

; =============================================================================
; myMax(x, y) =
; | x, if x >= y
; | y, otherwise
(defun myMax (x y)
  (cond
    ((>= x y) x)
    (t y)
  )
)
; Straight-forward like in Prolog
; - return x if bigger than y, y otherwise
; =============================================================================

; =============================================================================
; depth((l1,l2...ln), count) =
; | count                                                   , n == 0
; | myMax(depth(l1, count+1), depth((l2,l3...ln), count))   , l1 - list
; | depth((l2,l3...ln), count)                              , otherwise
(defun depth (array count)
  (cond
    ((null array) count)
    ((listp (car array)) (myMax (depth (car array) (+ count 1)) (depth (cdr array) count)))
    (t (depth (cdr array) count))
  )
)
; Basically, check if the current array element is a list:
; yes -> find its depth and keep adding to count for current iteration
;     -> go on with the next iteration where another count is being found
;     -> make a max between the two, aka current list depth and the REST
; no -> find the depth of the next element which MIGHT be a list
; null -> end of the array, return the maximum count reached from 'yes'
; =============================================================================
;(write (cond (t (depth (list 1 (list 2) 3 (list (list 2))) 1))))

; =============================================================================
; c) Write a function to sort a linear list without keeping the double values.
; =============================================================================

; =============================================================================
; myInsert((l1,l2...ln), elem) =
; | list(elem)                          , n == 0
; | {l1} U {l2,l3...ln)                 , elem == l1
; | {elem, l1} U {l2,l3...ln)           , elem < l1
; | {l1} U myInsert((l2,l3...ln), elem) , otherwise
(defun myInsert (array elem)
  (cond
    ((null array) (list elem))
    ((= (car array) elem) array)
    ((< elem (car array)) (cons elem array))
    (t (cons (car array) (myInsert (cdr array) elem)))
  )
)
; Basically, check the relation between ELEM and the array's HEAD
; equal -> insert the HEAD only, we don't want double values
; lesser -> insert ELEM and the HEAD in this order via cons
; bigger -> insert the HEAD and the return value of the rest of the recursivity
; null -> reached the end of the array, return list elem
; =============================================================================

; =============================================================================
; mySort(l1,l2...ln) =
; | nil                             , n == 0
; | myInsert(mySort(l2...ln), l1)   , otherwise
(defun mySort (array)
  (cond
    ((null array) nil)
    (t (myInsert (mySort (cdr array)) (car array)))
  )
)
; Check if we reached the end of the array
; yes -> return nil
; no -> do myInsert with the HEAD and the rest of the TAIL, recursively
; =============================================================================
;(write (mySort (list 1 5 3 7 1 9)))

; =============================================================================
; d) Write a function to return the intersection of two sets.
; =============================================================================

; =============================================================================
; myContains((l1,l2...ln), elem) =
; | true                            , if l1 == elem
; | false                           , if n == 0
; | myContains((l2,l3...ln), elem)  , otherwise
(defun myContains(array elem)
  (cond
    ((null array) nil)
    ((equal (car array) elem) t)
    (t (myContains (cdr array) elem))
  )
)
; We check if:
; HEAD == ELEM -> array contains element
; null -> array empty
; HEAD != ELEM -> check next head
; =============================================================================

; =============================================================================
; myIntersect((l1,l2...ln), (k1,k2...km)) =
; | nil                                             , if n == 0
; | {l1} U myIntersect((l2,l3...ln), (k1,k2...km))  , if l1 in (k1,k2...km)
; | myIntersect((l2,l3...ln), (k1,k2...kn))         , otherwise
(defun myIntersect(array1 array2)
  (cond
    ((null array1) NIL)
    ((myContains array2 (car array1)) (cons (car array1) (myIntersect (cdr array1) array2)))
    (t (myIntersect (cdr array1) array2))
  )
)
; Check if:
; - array1 is empty, we reached the end
; - HEAD of array1 is in array2, put it in resulting intersect array with cons
; - neither, in which case check the rest of the elements in array1
; =============================================================================

;(write (myContains (list 1 2 3 4) 3))
;(write " ")
;(write (myContains (list 1 4 6) 2))
;(write " ")
;(write (myIntersect (list 1 2 3 4) (list 3 4 4 5 1)))
