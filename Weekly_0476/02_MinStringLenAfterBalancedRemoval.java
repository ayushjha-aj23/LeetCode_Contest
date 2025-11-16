class Solution {

    // Each removal eliminates 1 'a' and 1 'b', so the only characters left after full removals will be:
    // the extra 'a' characters if countA > countB
    // the extra 'b' characters if countB > countA
    // So we have to return absolute count of A and B --> |countA-countB|
    // Or we can say Math.max(A,B)-Math.min(A,B)


    // Approach  - Counting Frequencies
    // TC-O(n) and SC-O(1)

    // Count A and Count B
    public int minLengthAfterRemovals(String s) {

        // int n = s.length();
        int countA = 0;
        int countB = 0;
        for(char c:s.toCharArray()){
            if(c=='a')
                countA++;
            else
                countB++;
        }

        // abs(countA-countB) OR max(cA, cB)-min(cA, cB)
        return Math.max(countA, countB)-Math.min(countA, countB);
    }
}
