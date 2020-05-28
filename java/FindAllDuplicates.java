/*
 * # Find All Duplicates in an Array
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 * Could you do it without extra space and in O(n) runtime?
 * -------------------------------------------------------------------------------------------------------------------------------------------
 *
 *  Brute Force:
 *  For every element nums[i], iterate over elements from 0 to i-1 and check if it has already been seen in the array.
 *  We will have 2 loops
 *  First loop runs for every index i [0,N)
 *  Second loop runs for every index j [0,i) and checks if the element A[i] is already present.
 *  Time complexity: O(N^2)
 *  Space Complexity: O(1)
 *
 *  Observations:
 *  - For Every element we just need to check if it is already been seen or not.
 *  - Is there any data-structure that can provide this information in almost constant time?
 *    Yes! Hash map.
 *
 *  So, if we maintain a map to store the elements, for every element we can check in O(1) time,
 *  whether that element is already present in the array or not.
 *  So we can elininate the second loop from the brute force approach.
 *    
 *  Time complexity: O(N)
 *  But using a hashMap adds to the space complexity and makes it O(N)
 *
 *  Can we do it without using the hashmap?
 *  
 *  More observations:
 *  - It is given in the question that 1 ≤ a[i] ≤ N
 *  - Can we say that if there are no duplicates then there will be a 1-to-1 mapping between the array values and the indices? 
 *    (Assuming 1 based indexing.)
 *  - And, if there are duplicates then there can be 2-to-1 mapping where both the copies of an element will map to a single index.
 *  - Which means that, if for every element nums[i] we mark index nums[i] visited, then for duplicate pairs the same index will be visited twice.
 *
 *  How can we mark an index visited without using extra space?
 *  Can we modify the array values at an index to indicate that the index has been visited?
 *
 *  Yes. Two things that we need to keep in mind while modifing the values 
 *  1) The modified value should be such that it can not be confused as the original array values.
 *  2) The original value should be easily retrievable from it.
 *
 *  One easy option is to negate the number (ie. turn to negative)
 *  Does it satisfies the above conditions?
 *  1) The original values are >=1 so any negative number means that the index has been already visited.
 *  2) To retrieve the original value take the absolute value.
 *
 *  So now we have an easy way to keep track of all the numbers that we visit.
 *  Start iterating over the array.
 *  For every element nums[i], check the sign of the value at index nums[i] (i.e. sign of nums[nums[i]] )
 *  If the sign is -ve that means that the index nums[i] has already been visited so add nums[i] to the ans.
 *  If the sign is +ve that means that the index nums[i] has not been visited. Turn the value at index nums[i] -ve and proceed.*  
 *
 */

public class FindAllDuplicates {

    public int abs(int a){
        if(a<0) a*=-1;
        return a;
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        int index;
        for(int i=0; i<nums.length; i++){
            index = abs(nums[i])-1;
            //if sign of nums[index] is -ve that means that this index has already been visited.
            if(nums[index] < 0){
                ans.add(abs(nums[i]));
            }else{
                //mark visited by turning the sign to -ve 
                nums[index] *= -1;
            }
        }
        return ans;
    }
}
