; 2. Return the list of nodes on the k-th level of a tree of type (1).
; ============================================================================

; ============================================================================
; leftWalk((t1,t2...tn), nodes, edges) = 
; | nil                                                    , if n == 0
; | nil                                                    , if nodes==edges+1
; | {t1} U {t2} U leftWalk((t3,t4...tn), nodes+1, edges+t2), otherwise
; ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
(defun leftWalk (tree nodes edges)
  (cond
    ((null tree) nil)
    ((= nodes ( + 1 edges)) nil)
    (t (cons (car tree) (cons (cadr tree) (leftWalk (cddr tree) (+ 1 nodes) (+ (cadr tree) edges)))))
  )
)
; Basically, walks along the left side of the binary tree
; - if tree is empty, return nil
; - if nodes == edges+1, return nil (if reached end of left depth)
; - otherwise, recursively go again through with next key-value pair
; ============================================================================

;      A            - not taken
;     / \
;    B   C              - takes B if target=1, ignores C
;       / \
;      D   E                - takes NIL if target>1

;(print(leftWalk `(B 0 C 2 D 0 E 0) 0 0))

; ============================================================================
; rightWalk((t1,t2...tn), nodes, edges) =
; | nil                                         , if n == 0
; | (t1,t2...tn)                                , if nodes==edges+1
; | rightWalk((t3,t4...tn), nodes+1, edges+t2)  , otherwise
; ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
(defun rightWalk (tree nodes edges)
  (cond
    ((null tree) nil)
    ((= nodes (+ 1 edges)) tree)
    (t (rightWalk (cddr tree) (+ 1 nodes) (+ (cadr tree) edges)))
  )
)
; Same as with leftWalk, walks along the right side of the tree
; - if tree is empty, return nil
; - if nodes == edges+1, reached end of left, return rest(right nodes)
; - otherwise, recursively go through with next key-value pair
; ============================================================================

;      A        - not taken
;     / \
;    B   C          - B is ignored, goes C, takes C is target=1
;       / \
;      D   E            - returns D E if target>1

;(print(rightWalk `(B 0 C 2 D 0 E 0) 0 0))
;(print(rightWalk `(C 2 D 0 E 0) 2 0))

; ============================================================================
; myAppend((a1,a2...an), (b1,b2...bn)) = 
; | (b1,b2...bn)                               , if n = 0
; | {a1} U myAppend((a2,a3...an), (b1,b2...bn)), otherwise
; ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
(defun myAppend(array1 array2)
  (cond
    ((null array1) array2)
    (t (cons (car array1) (myAppend (cdr array1) array2)))
  )
)
; This simply appends the array2 at the end of the array1
; - if we reached the end of the array1, return the array2
; - otherwise we still have elements in array1, keep linking elements via cons
; ============================================================================



; ============================================================================
; k-level((t1,t2...tn), level, target) = 
; | nil                                                       , if n = 0 
; | t1                                                        , if level=target
; | myAppend(k-level(leftWalk(t1,t2...tn), (level+1), target), 
;           k-level(rightWalk(t1,t2...tn), (level+1), target)), otherwise
; ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
(defun k-level(tree level target)
  (cond
    ((null tree) nil)
    ((equal level target) (list (car tree)))
    (t (myAppend (k-level (leftwalk (cddr tree) 0 0) (+ 1 level) target) (k-level (rightWalk (cddr tree) 0 0) (+ 1 level) target)))
  )
)
; Basically, uses a kind-of divide and conquer algorithm
; - if tree is empty, return nil
; - if we reached the target level, return the node at that level
; - otherwise, keep searching left and right, appending to the left result the nodes found in the right result
; ============================================================================

;      A
;     / \
;    B   C
;       / \
;      D   E
; k = 0 ; A
; k = 1 ; appends into k-level(leftWalk(B C D E,0,0),1,1) -> B
;                      k-level(rightWalk(B C D E,0,0),1,1) -> C
; k = 2 ; appends into k-level(leftWalk(B C D E, 0,0),1,2) ->
;           appends into -> k-level(leftWalk(B,0,0),2,2) -> NIL
;                      k-level(rightWalk(B C D E,0,0),1,2) ->
;                           k-level(rightWalk(C D E),2,2) -> D E

;(print (leftWalk `(B 0 C 2 D 0 E 0) 0 0))
;(print (k-level (leftWalk (cddr `(A 2 B 0 C 2 D 0 E 0)) 0 0) 1 2))
;(print (k-level (rightWalk (cddr `(A 2 B 0 C 2 D 0 E 0)) 0 0) 1 2))

(write (k-level `(A 2 B 0 C 2 D 0 E 0) 0 2))


Next homework L2 - 8
