/*
 * Approach 1:
 * For every height h(i), iterate over the array to find the maximum area for that height h(i).
 * The maximum af all is the answer.
 * Time complexity: O(n^2)
 *
 * Observation:
 * The maximum area for any height h(i) comes with the farthest height greater or equal to h(i)
 * 
 * Do we need to iterate over the array to find the pair? That would again be O(N^2)!
 *
 * NO.
 *
 * Approach 2:
 * Fix a pointer 'start' on 0 and another pointer 'end' on the last height.
 * Now if h(end)>=h(start) => We foud the pair for h(start)
 * else we find the pair for h(end) :)
 * 
 */
class ContainerWithMostWater {

    public int max(int a, int b){
        if(a > b) return a;
        return b;
    }

    public int maxArea(int[] height) {
        //fixing the start and end pointer on the first and last heights respectively
        int start = 0,end = height.length-1;
        int ans = 0;
        int width = 0;
        
        //Calculating arears till we have at least two bars 
        while(start<end){
            width = end-start;

            //if height[end] >= height[start], we have found the farthest height from start greater than or equal to height[start]
            // height[start]*width is the max area for height[start]
            if( height[end] >= height[start]){
                ans = max(ans, height[start]*width);
                start++;
            }else{
                //else we find the farthest height greater than or equal to height[end]
                ans = max(ans, height[end]*width);
                end--;
            }
        }
        return ans;
        
    }
}
