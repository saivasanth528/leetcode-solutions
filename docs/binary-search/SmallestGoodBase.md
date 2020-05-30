---
layout: default
title: Smallest Good Base
parent: Binary Search
nav_order: 1
---

## Smallest Good Base

 For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.
 Now given a string representing n, you should return the smallest good base of n in string format.
 {: .fs-4}
 
---
 
### Basic Observations:

  Any number N when represented in the base (N-1), will always be 11.
 ```
3 in base 2 -> 11
  
4 in base 3 -> 11
  
5 in base 4 -> 11
  .
  .
  .
  and so on
  
  ```
  
##### Why? 
 
  `11 => 1*base^0 + 1*base^1`
  
  this should be equal to N
 
 ```
  => N = 1*base^0 + 1*base^1
  => base = N-1
 ```
 
  So, the maximum value of the Good Base will be N-1.
  
  What could be the minimum value? 
  
  min val = 2 (We have to find the actual minimum value but the lowest possible value that the ans can take is 2)
 
  So we have the ans space ranging from [2, N-1]
 
  How to find the smallest good base now?
 
### Brute force:

  Iterate over the ans space and check if the ith element is a good base or not.
  TC: 
  
  `O(N) -> Iterating over the array`
  
  `O(Log N) -> Checking if the ith no is a good base.`
  
  `Total TC O(NlogN)`
  
  
### How to optimize?
  
  Let us analyse how the brute force is working:
  
  For any number N  we are iterating from 2 to N-1 till we find the good base.
  
  check if 2 is a good base 
  check if 3 is a good base
  .
  .
  .
  check if N-1 is a good base
  
 
  If you observe, we are doing a linear search over the answer.
  We also have the ans space with us. Let us see if we can discard a part of the ans space.
 
  For a number N,
  
 
  if m is a good base,
  
Do we need to check for any value greater than m?
No! We have to find the smallest good base.
We can discard the right half of the ans space in this case.
{: .ml-md-4 .ml-xs-2}
      
  else if m is NOT a good base
  
Can we discard a part of the ans space in this case?
Can we say that if m is not a good base then any number greater than m will also not be good base?
OR Can we say that if m is not a good base then any number less than m will also not be a good base?
{: .ml-md-4 .ml-xs-2}
  
 
   **NO!**
   See an exmaple:
   
      For N=13,
      m=6 is NOT a good base,
      but 12 and 3 are good base.
      
So the ans can be found on any side of m.
We can not make the decision to discard a part of the search space in this case!
  
  Why?  What is it that is not allowing us to make any desicion here?
  It is "the number of digits"
  Yes. Different bases can be good bases for a number for different number of digits.
 
  for 13
  3 is a good base, 13 in base 3 is "1 1 1"
  12 is also a good base, 13 in base 12 is "1 1"
  a larger number can be a good base with lesser number of digits
  and a smaller can be a good base with more number of digits
 
  What if we fix the number of digits!
  will that help us in making a decision to discard a part of the search space?
 
 Lets say that we are only finding a good base for d digits (i.e. the number when represented in the good base should have exactly d digits)
  Which means that the number N when represented in the good base will be, 1 1 1 1 1 .....d times
 
  Can we now make a decision for d digits?
 
  if m is a base, and number of digits fixed to d
  then the number would be
 
  `num = 1*m^0 + 1*m^1 + 1*m^2 + ... + 1*m^(d-1)`
  
  if num == n
     m is a good base
     we don't need to check for any value >m
     right half of the ans space can be discarded
 
  if num < n
     we need to increase the value of m
     left half of the ans space can be discarded
 
  if num > n
     we need to reduce the value of m
     again,  right part of the ans space can be discarded
     
  So now, we have a monotous function
 
  isPossible(base B, digits d, number N) -> Returns true if the number N in base B can be represented as 11111...d times
 
  Can we apply binary search to find the ans for a given number of digits now?
 
  Yes :)
 
  If we do it for all possible number of digits, the min goodbase becomes the ans.
  
  What would be the maximum number of digits??
  Will that be same as the number of digits in the smallest possible base?
  What is the smallest possible base?
  Think ;)
 
  #### Time Complexity: 
  O(log N) -> finding a good base for a fixed number of digits
  O(Number of digits) -> for doing the above for all possible digit sizes.
  
  Total TC: O(D log N) (Where D is the maximum number of digits you can have)
 
 
```java
class Solution {   

    //The monotonous function
    //Checks if we can represent N as 11111...d times in the given base
    //i.e. is 1*base^0 + 1*base^1 + 1*base^3 + ... + 1*base^(digits-1) == N ?
    public long isEqual(long base, long digits, long N){
        long num = 0L;
        long term = 1L;
        long maxPower = (long)(18.00/Math.log10(base));
        for(int i=0; i<digits; i++){
            if(i>maxPower) return 1;
            N -= term;
            if(N < 0L) return 1;
            term = term * base;
        }
        if(N == 0L) return 0;
        return -1;
    }
    
    //Function to find the good base for number N for fixed number of digits
    public long findBase(long N, long digits){
        long low = 2L, high = N-1L, mid, num;      
        //standard binary search template
        while(low <= high){   
            mid = low + (high-low)/2;
            num = isEqual(mid, digits, N);
            if(num == 0L){
                return mid;
            }
            else if(num > 0L){
                high = mid-1;
            }
            else low = mid+1;
        }
        return 0L;
    }
    
    public String smallestGoodBase(String n) {
        long N = Long.parseLong(n);
        long m;
        //Looping over all possible 'number of digits'
        //Observe that we are starting from the biggest value. Can you think why?
        for(long i=64; i>0; i--){          
            m = findBase(N, i);
            if(m>=2) return Long.toString(m); 
        }
        return "";
    }
}
```
