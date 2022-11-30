% =======================================================
% myMax(A, B) =
% | A, if A >= B
% | B, if B > A
% ~~~~~~~~~~~~~~~
% myMax(A-leftValue, B-rightValue, R-maximumValue)
% myMax(i, i, o)
% =======================================================

myMax(A,B,A):-
    A>=B.
myMax(A,B,B):-
    A<B.

% =======================================================
% myListMax(l1,l2...ln) =
% | l1                              , if n = 1
% | myMax(l1, myListMax(l2,l3...ln)), otherwise
% ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
% myListMax(L-startList, R-maxOfList)
% myListMax(i,o)
% =======================================================

myListMax([E], E).
myListMax([H|T], RM):-
    myListMax(T, R),
    myMax(H, R, RM).

% =======================================================
% myReplace((l1,l2...ln), Max, El) =
% | []                 , if n = 0
% | {El} U (l2,l3...ln), if l1 = Max
% | {l1} U (l2,l3...ln), otherwise
% ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
% myReplace(L-startList, Max-maxOfList, E-replaceElem, R-replacedList)
% myReplace(i,i,i,o)
% =======================================================

myReplace([], _, _, []).
myReplace([H|T], Max, E, [E|R]):-
    H =:= Max,
    myReplace(T, Max, E, R).
myReplace([H|T], Max, E, [H|R]):-
    H =\= Max,
    myReplace(T, Max, E, R).

% =======================================================
% main((l1,l2...ln), Elem) =
% | myReplace((l1,l2...ln), myListMax(l1,l2...ln), Elem)
% ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
% main(L-startList, E-replaceElem, R-finalList)
% main(i,i,o)
% =======================================================

main(L, E, R):-
    myListMax(L, Max),
    myReplace(L, Max, E, R).

% l1a & l1b (5 problems in total) - problem 2 from both for me
