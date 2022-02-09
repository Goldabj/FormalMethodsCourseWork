------------------------------ MODULE sorting ------------------------------
EXTENDS Integers, Sequences

CONSTANTS N

(***
--fair algorithm Sorting {
    variables array \in [1..N -> Int] , i, j, minIndex;
    {
        j := 1;
        while (j < Len(array)) {
            minIndex := j;
            i := j + 1;
            while (i <= Len(array)) {
                if (array[i] < array[minIndex]) {
                    minIndex := i
                };
                i := i + 1
            };
            if (minIndex # j) {
                with (temp = array[j]) {
                    array[j] := array[minIndex] ||
                    array[minIndex] := temp
                }
            };
            j := j + 1
        }
    
    
    
    }




}

***)
\* BEGIN TRANSLATION
CONSTANT defaultInitValue
VARIABLES array, i, j, minIndex, pc

vars == << array, i, j, minIndex, pc >>

Init == (* Global variables *)
        /\ array \in [1..N -> Int]
        /\ i = defaultInitValue
        /\ j = defaultInitValue
        /\ minIndex = defaultInitValue
        /\ pc = "Lbl_1"

Lbl_1 == /\ pc = "Lbl_1"
         /\ j' = 1
         /\ pc' = "Lbl_2"
         /\ UNCHANGED << array, i, minIndex >>

Lbl_2 == /\ pc = "Lbl_2"
         /\ IF j < Len(array)
               THEN /\ minIndex' = j
                    /\ i' = j + 1
                    /\ pc' = "Lbl_3"
               ELSE /\ pc' = "Done"
                    /\ UNCHANGED << i, minIndex >>
         /\ UNCHANGED << array, j >>

Lbl_3 == /\ pc = "Lbl_3"
         /\ IF i <= Len(array)
               THEN /\ IF array[i] < array[minIndex]
                          THEN /\ minIndex' = i
                          ELSE /\ TRUE
                               /\ UNCHANGED minIndex
                    /\ i' = i + 1
                    /\ pc' = "Lbl_3"
                    /\ UNCHANGED << array, j >>
               ELSE /\ IF minIndex # j
                          THEN /\ LET temp == array[j] IN
                                    array' = [array EXCEPT ![j] = array[minIndex],
                                                           ![minIndex] = temp]
                          ELSE /\ TRUE
                               /\ array' = array
                    /\ j' = j + 1
                    /\ pc' = "Lbl_2"
                    /\ UNCHANGED << i, minIndex >>

Next == Lbl_1 \/ Lbl_2 \/ Lbl_3
           \/ (* Disjunct to prevent deadlock on termination *)
              (pc = "Done" /\ UNCHANGED vars)

Spec == /\ Init /\ [][Next]_vars
        /\ WF_vars(Next)

Termination == <>(pc = "Done")

\* END TRANSLATION


PartialCorrectness == (pc = "Done") => (\A k \in 1..Len(array)-1 : array[k] <= array[k + 1])

=============================================================================
\* Modification History
\* Last modified Tue May 16 20:09:54 EDT 2017 by Goldacbj
\* Created Tue May 16 19:56:47 EDT 2017 by Goldacbj
