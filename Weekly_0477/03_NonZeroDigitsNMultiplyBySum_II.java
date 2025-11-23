

class Solution {

    static final long MOD = 1000000007L;
    
    // Burte Force - Giving TLE 
    // TC - O(q * m)  = 10^10   → too slow, TLE in large inputs
    /*
    public int[] sumAndMultiply(String s, int[][] queries) {

        int q = queries.length; // Num of queries
        int result[] = new int[q];

        // Iterate for every query
        for(int qIdx=0; qIdx<q; qIdx++){
            // For every query find left and right index
            int l = queries[qIdx][0];
            int r = queries[qIdx][1];


            // Extract the substring
            String sub = s.substring(l, r+1); 
            // because subtring() takes 1 less on right so r+1

            StringBuilder sb = new StringBuilder();
            // scan the subtring and if non zero append -> sb
            for(int i=0; i<sub.length(); i++){
                if(sub.charAt(i)!='0')
                    sb.append(sub.charAt(i));
            }

            // if sb length is 0 then update result to 0 and continue
            if(sb.length()==0){
                result[qIdx] = 0;
                continue;
            }

        long x_mod = 0;     // value % MOD
        long sum_mod = 0;   // digit sum % MOD

        // Scan the sb and extract each digit
        for(int i = 0; i < sb.length(); i++) {
            int digit = sb.charAt(i) - '0';

            // Build number modulo MOD
            // e.g. -> For 123
            // (0*10)+1->1
            // (1*10)+2->12
            // (12*10)+3->123, So num(x) is 123
            x_mod = (x_mod * 10 + digit) % MOD;

            // Sum digits
            // e.g. -> For 123
            // 0+1->1
            // 1+2->3
            // 3+3->6, So sum is 6
            sum_mod = (sum_mod + digit) % MOD;
        }

        // ans = 123*6, update the result[qIdx] with ans
        long ans = (x_mod * sum_mod) % MOD;
        result[qIdx] = (int) ans;
            

            // long x  = Long.parseLong(sb.toString());
            // long sum = 0;
            // long copyX = x;
            // while(copyX>0){
            //     sum += copyX%10;
            //     copyX = copyX/10;
            // }

            // result[qIdx] = (int) ((x%MOD)*(sum%MOD))%MOD;
            
        }
        return result;   
    }
    */

    // Optimised - Using Prefix Arrays
    // Preprocessing -> O(m) for Prefix Arrays and then Each Query -> O(1) so for q queries -> O(m+q)
    // TC - O(m+q) and SC - O(m)

    // We use r - (l-1) because:
    // Prefix sums store cumulative totals, so subtracting the prefix before l gives the exact count inside [l, r]
    
    public int[] sumAndMultiply(String s, int[][] queries) {

        int n = s.length();
        int q = queries.length;

        // Prefix Arrays
        long[] prefixCnt = new long[n];
        long[] prefixSum = new long[n];
        long[] prefixNum = new long[n];
        long[] pow10 = new long[n+1]; // n+1 because 0->1, 1->10, 2->100....n->10^n

        // Precompute power of 10 
        pow10[0] = 1;
        for(int i=1; i<=n; i++){
            pow10[i] = (pow10[i-1]*10)%MOD; 
        }

        // Build prefix arrays
        for(int i=0; i<n; i++){
            int digit = s.charAt(i)-'0';

            if(i>0){
                prefixCnt[i] = prefixCnt[i-1];
                prefixSum[i] = prefixSum[i-1];
                prefixNum[i] = prefixNum[i-1];
            }

             
            if(digit!=0){
                prefixCnt[i]++;
                prefixSum[i] += digit;
                prefixNum[i] = ((prefixNum[i]*10)+digit)%MOD;
            }
        }

        int[] result = new int[q];

        for(int qi=0; qi<q; qi++){
            int l = queries[qi][0];
            int r = queries[qi][1];

            long totalCnt = prefixCnt[r]-( l>0? prefixCnt[l-1] : 0); // if left is > 0 then prefixCnt[l-1] otherwise 0

            if(totalCnt==0){
                result[qi] = 0;
                continue;
            }

            long totalSum = prefixSum[r]-( l>0? prefixSum[l-1] : 0); // if left is > 0 then prefixCnt[l-1] otherwise 0

            long x = prefixNum[r];

            if(l>0){
                x = (x-prefixNum[l-1]*pow10[(int) totalCnt]%MOD + MOD)%MOD;
            }

            long ans = (x*totalSum)%MOD;
            result[qi] = (int) ans;
        }
        return result;
    }
}
