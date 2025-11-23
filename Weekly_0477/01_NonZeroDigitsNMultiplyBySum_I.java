
// class Solution {


    // Naive - Using String and extracting substring of non-zeros
    // TC-O(d) and SC-O(d)

    // Convert the number n to a string
    // Remove all '0' digits and construct a new number x
    // Compute the sum of digits of x
    // Return x × (sum of digits of x)
/*
    public long sumAndMultiply(int n) {
        String s = Integer.toString(n);
        // System.out.println(s);
        StringBuilder sub = new StringBuilder();

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)!='0')
                sub.append(s.charAt(i));
        }

        if(sub.length()==0)
            return 0;

        long x = Long.parseLong(sub.toString());
        // System.out.println(x);
        long sum = 0;
        long copyx = x;
        
        while(copyx>0){
            sum += copyx%10;
            copyx = copyx/10;
        }
        
        // System.out.println(sum);
        return x*sum;
    }
*/


    // Optimised - Pure Math
    // TC-O(d) (where d = number of digits), SC-O(1) — No extra string or array
    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;

        // Extract digits from right to left
        int place = 1; 
        int temp = n; 
        
        while (temp > 0) {
            int digit = temp % 10; // extract the digit
            if (digit != 0) { // if non-zero
                x += digit * place; // x = x+digit*place
                sum += digit; // sum of digits
                place *= 10; // increase the place value
            }
            temp /= 10; // update the temp
        }

        return x * sum; // return x*sum
    }

}
