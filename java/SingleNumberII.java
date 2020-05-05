/*
 * Brute force:
 * For every number in the array, count it's occurances in the array. Return the one that has only one occurance.
 * Time Complexity: 
 * O(N) traversal to count the occurance of one element. N such iterations. So,
 * TC: O(N^2)
 *
 * We can also store the frequencies in a Hashmap and solve it in linear time complexity. But using extra memory is not allowed in this problem.
 *
 * So will will have to think something out of the box.
 *
 * Observation:
 * Let us consider an array where EVERY number appears thrice. (Ex: [1,2,3,3,2,4,1,4,4,3,1,2])
 * If every number appears thrice, can we say that the number of set bits(1s) and number of unset bits(0s) at any bit position will be divisible by 3?
 *
 * For better understanding let's write these numbers in their binary representaion
 *    
 * 1: 0 0 1
 * 2: 0 1 0
 * 3: 0 1 1
 * 3: 0 1 1
 * 2: 0 1 0
 * 4: 1 0 0
 * 1: 0 0 1
 * 4: 1 0 0
 * 4: 1 0 0
 * 3: 0 1 1
 * 1: 0 0 1
 * 2: 0 1 0
 *
 * count of set bits at the least significant bit position:   6 (divisible by 3)
 * count of unset bits at the least significant bit position: 6 (divisible by 3)
 *
 * count of set bits at the most significant bit position:   3 (divisible by 3)
 * count of unse bits at the most significant bit positino:  9 (divisible by 3)
 *
 * check for the remaining bit position as well.
 *
 * This happens because, every number is occuring thrice in the array. For any number x, if x has a set bit at ith bit position, then that set bit will
 * be counted thrice as x repeats three times in the array.
 *
 * So, if there is one number A that appears once in this array, 
 * and if A has 1 at the ith position, 
 * can we say that the count of set bits in the ith position will now NOT be divisible by 3? (try to add a number other than 1,2,3,4 in the above array
 * and see)
 *
 * Or can we say that,
 * if the count of set bits at ith position is not divisible by 3 then the single number has a set bit at the ith position?
 * and if it is divisible by three, then the single number has an unset bit at the ith position?
 * 
 * So now, we just have to iterate over all possible bit positions and count the number of set bits.
 * If the count is divisible by 3, the single number has a set bit at ith position else an unset bit.
 *
 * Time Complexity:
 * If the data type uses K bits to store the numbers then,
 * O(K) for iterating over all possible bit positions.
 * O(N) for counting the number of set bits for every bit position.
 *
 * TC: O(KN)
 * Can we estimate the value of K?
 *
 * If M is the maximum number that can be accomodated in the data type then, K = log(M)
 *
 * So, TC: O(N logM) (N-> Size of the array, M-> Max value of input data type) 
 * Space: O(1)
 *
 */
class SingleNumberII {
    public int singleNumber(int[] nums) {
        
        int ans = 0, setBitsAtPositionI = 0, p=1;

        //iterating over all possible bit positions (32 for integers)
        for(int i=0; i<32; i++){
            setBitsAtPositionI = 0;

            //iterating over the array to count the number of set bits at the ith bit position
            for(int j=0; j<nums.length; j++){
                if(((p<<i) & nums[j]) != 0){
                    setBitsAtPositionI++;
                }
            }

            // if the count of set bits at ith bit position is not divisible by 3, then the ans must be having a set bit at this position
            if(setBitsAtPositionI%3 > 0){
                ans  = ans | (p<<i);
            }
        }
        return ans;
    }
}
