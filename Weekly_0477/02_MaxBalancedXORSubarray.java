
// LC - 3755
class Solution {


    // We need to find the length of the longest subarray such that:
    // XOR of the subarray = 0
    // Count of even numbers == count of odd numbers
    // range property problem -> So Prefix Technique

    // If prefix XOR is same at two positions → the XOR of the elements in between cancels out and becomes zero.

    public int maxBalancedSubarray(int[] nums) {
        
        int[] arr = nums;
        int n = arr.length;

        // prefixXor stores cumulative XOR
        // diff stores (evenCount - oddCount)
        // We need to find same (prefixXor, diff) pair to form valid subarray
        Map<String, Integer> map = new HashMap<>();

        int prefixXor = 0;
        int even = 0;
        int odd = 0;
        int longest = 0;

        // Base case before array starts
        // PrefixXor should be 0 and difference between evenCount and OddCount should also be 0
        // key is a string used as a map key combining prefix XOR and even-odd difference.
        // # is just a separator to avoid ambiguity in the stored key.
        // key -> PrefixXor#Difference
        map.put("0#0", -1);

        for (int i = 0; i < n; i++) {

            // Update XOR
            prefixXor ^= arr[i];

            // Update even/odd count
            if (arr[i] % 2 == 0) even++;
            else odd++;

            int diff = even - odd;
            String key = prefixXor + "#" + diff;

            // If seen this (xor,diff) before then subarray XOR=0 and even=odd
            if (map.containsKey(key)) {
                longest = Math.max(longest, i - map.get(key)); // map.get(key) -> Gives value
            } else {
                map.put(key, i);
            }
        }

        return longest;
    }
}
