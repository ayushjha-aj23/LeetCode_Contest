
// Find a, b, c such that we have to return max(a+b−c)
// choose max a and b, choose min c as it has to be subtracted

class Solution {

    // Approach 1: Sort 
    // TC-O(n log n) and SC-O(1)

    // Sort the array:
    // largest = nums[n-1]
    // secondLargest = nums[n-2]
    // smallest = nums[0]
    // then return nums[n - 1] + nums[n - 2] - nums[0];


    // Approach 2: Linear Scan
    // TC-O(n) and SC-O(1)

    // Find the largest, secondLargest and smallest
    // return largest+secondLargest-smallest
    public int maximizeExpressionOfThree(int[] nums) {
        int n = nums.length;
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;

        
        for(int i=0; i<n; i++){
            if(nums[i]>largest){
                secondLargest = largest;
                largest = nums[i];
            }
            else if(nums[i]>secondLargest)
                secondLargest = nums[i];
            if(nums[i]<smallest)
                smallest = nums[i];
        }

        return largest+secondLargest-smallest;
    }
}
