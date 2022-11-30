%                                   5.
% ======================================================================

% ======================================================================
% a) Write a predicate to compute the union of two sets.
% ======================================================================

% ======================================================================
% myUnion((l1,l2...ln), (y1,y2...yn)) =
% | (y1,y2...yn)                         , if list L - empty list
% | myUnion((l2...ln), (y1,y2...yn))     , if l1 member of list Y
% | l1 + myUnion((l2...ln), (y1,y2...yn) , if l1 NOT member of list Y
% ======================================================================

myUnion( [X|XT] , Y, Z ) :-
    list_member( X , Y ), myUnion( XT , Y , Z ).
myUnion( [X|XT] , Y , [X|Z] ) :-
    \+ list_member( X , Y ), myUnion( XT , Y , Z ).
myUnion( [] , Z , Z ).

% ======================================================================
% b) Write a predicate to determine the set of all the pairs of elements
% in a list.
% ======================================================================

% ======================================================================
% myPair(l1,l2...ln) =
% | []                           , if n == 0
% | l1 + myPair((l2, l3...ln))   , if n > 0
% | myPair(l2,l3...ln)           , if n > 0
% ======================================================================

myPair( [] , [] ).
myPair( [X|T] , X-Y ) :-
    list_member( Y , T ).
myPair( [_|T] , X-Y ) :-
    myPair( T, X-Y ).

myPairList( List , Pairs ) :-
    findall( Pair , myPair( List , Pair ) , Pairs).

myPairList2( List , X-Y ) :-
    append( [_, [X], _, [Y], _] , List ).

% ======================================================================
% myPair3((l1,l2...ln), K) =
% | []                             , if K = 0
% | l1 + myPair3((l2...ln),K-1)    , if K > 0
% | myPair3((l2,l3...ln),K)        , if K > 0
% ======================================================================

myPair3( [] , 0 , [] ).
myPair3( [H|T] , K , [H|R] ):-
    K1 is K - 1,
    myPair3( T , K1 , R ).
myPair3( [_|T] , K , R ):-
     myPair3( T , K , R ).

myPairList3( List, Pairs ) :-
    findall( Pair, myPair3( List , 2 , Pair ), Pairs ).

% =======================================================================
% Utilities
% =======================================================================

list_member( X , [X|_] ).
list_member( X , [_|TAIL] ):-
    list_member( X , TAIL ).
