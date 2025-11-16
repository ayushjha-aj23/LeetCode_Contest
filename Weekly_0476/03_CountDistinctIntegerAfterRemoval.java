class Solution {
 
    // count = count of numbers ≤ n where each digit is from {1..9}.
    // classic digit DP + prefix counting problem.

    // Approach 1: Using HashSet will fail and will give TLE as Constraints: 1 <= n <= 10^15

    // Approach 2: Greedy Math Counting (Handles n up to 10^15 easily)
    // TC-O(digits) and SC-O(1)

    // Numbers after zero-removal only contain digits 1–9.
    // Count all zero-free numbers ≤ n.
    // String based approach 
    public long countDistinct(long n) {

        String s = Long.toString(n);
        int l = s.length();

        long result = 0;
        

        // Count all valid numbers with length < l
        long p = 9;
        // 1 digit -> [1,2,3,4,5,6,7,8,9] -> 9 
        // 2 digit -> [11-99] -> 10 will become 1 like that --> Unique count 9*9
        // 3 digit -> [111-999] -> 9*9*9
        // ....
        // length (L−1) -> 9^(l−1)
        // result += 9^i;
        for(int i=1; i<l; i++){
            // result += 9^i;
            result += Math.pow(9,i);
        }


        // Count valid same-length numbers with prefix less than n // Count valid which are less then the number
        long ways = 1;
        // ways is a flag (0 or 1) that decides whether the next positions should be processed or completely skipped.
        for(int i=0; i<l; i++){
            char c = s.charAt(i);

            int digit = c-'0'; // Find the digit at index 0

            // if digit is zero → n is invalid, stop
            if(digit==0){
                ways = 0;
                break;
            }


            int smaller = digit-1; // acceptable smaller digits if digit = 4, acceptable digits are -> 1,2,3 and not 0 so 3
            
            result += smaller * (long)Math.pow(9, l-i-1);
            // where -> 9^(l-i-1 = remaining positions)
            // Because zero is not allowed, we always have 9 options for each remaining digit.
            // i = current index and l = total length
            // Then the remaining digits = l - i - 1.
            // So: Total possibilities = 9^(l − i − 1)

        }

        result += ways; // digit itself contributes exactly 1 way (if not zero) so add 1 extra
        return result;
    }
    // Summary - 
    // Part 1 — Length < l
    // Counts all 1-digit, 2-digit, …, (l−1)-digit zero-free numbers.
    // Part 2 — Same length as n
    // Digit-by-digit:
    // count numbers smaller than n at each position
    // ensure no zero digit occurs
    // multiply by 9^(remaining positions)
    // Part 3 — The number n itself
    // Add 1 only if n has no zero, else add 0.
}
