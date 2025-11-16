
// Find the count of distinct integers after removing all the zeros 

// Used string as values are long

class Solution {
    public long countDistinct(long n) {

        String s = Long.toString(n);
        int l = s.length();

        long result = 0;

        long p = 9;
        for(int i=1; i<l; i++){
            result += p;
            p *= 9;
        }

        long ways = 1;
        for(int i=0; i<l; i++){
            char c = s.charAt(i);

            int digit = c-'0';

            if(digit==0){
                ways = 0;
                break;
            }

            int smaller = digit-1;
            result += smaller * (long)Math.pow(9, l-i-1);

            ways *=1;
        }

        result += ways;
        return result;
        
        
    }
}
