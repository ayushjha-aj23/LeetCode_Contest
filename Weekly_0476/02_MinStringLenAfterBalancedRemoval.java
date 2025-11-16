

// Return the Length of the string after removing the balanced substring

class Solution {

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

        // abs (countA-countB)
        // OR
        // max(cA, cB)-min(cA, cB)
        return Math.max(countA, countB)-Math.min(countA, countB);
    }
}
