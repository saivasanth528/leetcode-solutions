/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */


public class BadVersion extends VersionControl {
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        int mid=0;
        while(start<end){               
            mid= start + (end-start)/2; //same as (start+end)/2, just to skip the overflow 
            //if mid is a bad version it can be the first bad versino
            // OR the first bad verison can be on the left of it
            if(isBadVersion(mid)){ 
                   end = mid;
            }else{
                //mid is not a bad version, so the first bad version will be on right of the mid
                start = mid+1;
            }          
        }
        return start;
    }
}
