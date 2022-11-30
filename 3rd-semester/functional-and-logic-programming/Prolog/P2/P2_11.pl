%                                    11.
% ======================================================================

% ======================================================================
% a) Replace all occurrences of an element from a list with another
% element e.
% ======================================================================

% ======================================================================
% myReplace((l1,l2...ln), elemOld, elemNew) =
% | []                                                 , if list empty
% | elemNew + myReplace((l2,l3...ln), elemOld, elemNew), if elemOld = l1
% | l1 + myReplace((l2,l3...ln), elemOld, elemNew)     , otherwise
% ======================================================================

myReplace([],_,_,[]).
myReplace([Head|Tail],Old,New,[New|R]):-
    Head=:=Old,
    !,
    myReplace(Tail,Old,New,R).
myReplace([Head|Tail],Old,New,[Head|R]):-
    myReplace(Tail,Old,New,R).

% ======================================================================
% b) For a heterogeneous list, formed from integer numbers and list of
% numbers, define a predicate to determine the maximum number of the
% list, and then to replace this value in sublists with the maximum
% value of sublist.
% ======================================================================

% ======================================================================
% myListMax(l1,l2...ln) =
% | l1                              , if n==1 and l1 is number
% | -9999                           , if n==1 and l1 is NOT number(list)
% | myMax(l1, myListMax(l2,l3...ln)), if n >1 and l1 is number
% | myListMax(l2...ln)              , otherwise
% ======================================================================

myListMax([Head],Head):-
    number(Head).
myListMax([_],-9999).
myListMax([Head|Tail],R):-
    number(Head),
    !,
    myListMax(Tail,TMax),
    myMax(Head,TMax,R).
myListMax([_|Tail],R):-
    myListMax(Tail,R).

% ======================================================================
% Idea:
% myHeterReplace checks if l1 is a number or not. If it's a number, it
% simply adds it to the Result list. If it it's a subList, it uses
% myListMax to find its max, then myReplace to replace the found max,
% then adds it to the Result.
%
% myHeterReplace((l1,l2...ln), max) = | [] , if n = 0 | l1 +
% myHeterReplace((l2,l3...ln), max) , if l1 is NOT list | myReplace(l1,
% max, myListMax(l1)) + } myHeterReplace(l2...ln, max) }, if l1 is list
% ======================================================================

myHeterReplace([],_,[]).
myHeterReplace([Head|Tail],Max,[ReplacedR|R]):-
    is_list(Head),
    !,
    myListMax(Head,RMax),
    myReplace(Head,Max,RMax,ReplacedR),
    myHeterReplace(Tail,Max,R).
myHeterReplace([Head|Tail],Max,[Head|R]):-
    myHeterReplace(Tail,Max,R).

% ======================================================================
% Main:
% myListMax - searches for the maximum integer in the heterogeneous list
% myHeterReplace - uses returned max from myListMax and replaces it in
% every sublist
% Try: [1, [2, 5, 7], 4, 5, [1, 4], 3, [1, 3, 5, 8, 5, 4], 5, [5, 9, 1], 2]
% ======================================================================

main(List,R):-
    myListMax(List,RMax),
    myHeterReplace(List,RMax,R).

% ======================================================================
% UTILITIES
% ======================================================================

myMax(A,B,A):-
    A >= B.
myMax(A,B,B):-
    B > A.

