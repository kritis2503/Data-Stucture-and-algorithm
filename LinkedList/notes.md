don't use recursion in linkedlist if not absolutely necessary.
Mathematical proof is important in linkedlist

Q1-MIDDLE OF A LINKEDLIST
APPROACH:we move the slow pointer by one pace and fast by two.
MATHEMATICAL FORMULA:speed=distance/time. we want to cover half distance in same time hence one
            speed is twice of other. we check fast.next!=null and fast.next.next!=null. This gives
            the first node of an even lengthed linkedlist.
TIME COMPLEXITY:O(n)
SPACE COMPLEXITY:O(1)

Q2:REVERSE A LINKEDLIST
APPROACH:we make three pointers prev, curr and forw. we store the present next of current in FORW,
    change curr's next to PREV and move prev to curr and curr to forw.
MATHEMATICAL FORMULA:NONE, just simple logic.
TIME COMPLEXITY:O(N)
SPACE COMPLEXITY:O(1)

Q3:PALINDROME LINKEDLIST
APPROACH:We find the mid of linkedlist. reverse the latter half of the linkedlist and check if the
    first half is equal to the second half.
MATHEMATICAL APPROACH:NONE, JUST LOGIC
TIME COMPLEXITY:O(N)
SPACE COMPLEXITY:O(1)

Q4:FOLD LINKEDLIST
APPROACH:We find the mid and reverse from the next node making mid.next=null. Then,we will attach linked
    list alternatively to each other.
MATHEMATICAL APPROACH:NONE, JUST LOGIC
TIME COMPLEXITY:O(N)
SPACE COMPLEXITY:O(1)

Q5:UNFOLD LINKEDLIST
APPROACH:we connect every alternate node in different linkedlist. and then reverse second linkedlist and 
    then attach them
TIME COMPLEXITY:O(N)
SPACE COMPLEXITY:O(1)

