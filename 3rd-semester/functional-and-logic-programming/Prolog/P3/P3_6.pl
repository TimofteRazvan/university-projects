%                                     6
% =============================================================================
% Inserts element in list in different positions via backtracking.
% insertBacktrack((l1,l2...ln), Elem) =
% | Elem + (l1,l2...ln)
% | l1 + insertBacktrack(l2,l3...ln, Elem)
% ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
% insertBacktrack(L:start_list, E:nr_to_insert,
% R:result_list) insertBacktrack(i,i,o)
% =============================================================================

insertBacktrack(L, E, [E|L]).
insertBacktrack([H|T], E, [H|R]):-
    insertBacktrack(T, E, R).

% =============================================================================
% Creates sets with backtracking, insertBacktrack for arrangements.
% arrangements((l1,l2...ln), K) =
% | l1                                             , K = 1
% | insertBacktrack(arrangements(l2...ln, K-1), l1), K > 1
% | arrangements((l1,l2...ln), K)                  , K >= 1
% ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
% arrangements(L:start_list, K:nr_pair, R:result_pair)
% arrangements(i,i,o)
% =============================================================================

arrangements([H|_], 1, [H]).
arrangements([H|T], K, RF):-
    K > 1,
    KN is K - 1,
    arrangements(T, KN, R),
    insertBacktrack(R, H, RF).
arrangements([_|T], K, R):-
    arrangements(T, K, R).

% =============================================================================
% Uses findall to get a list of all the arrangements at once
% allArrangements(L:start_list, K:nr_pair, R:result_list)
% allArrangements(i,i,o)

allArrangements(L, K, R):-
    findall(Template, arrangements(L, K, Template), R).
