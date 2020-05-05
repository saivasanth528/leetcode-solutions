/*
 * Brute force:
 * Start from left to right. For every character in s check if it occurs only once.
 * Index of the first character that occurs only once in the string is the ans.
 * O(N) iteration to check the frequency of every character.
 *
 * Total time compexity: O(N^2)
 *
 * Do we need to do O(N) iterations for every character to check the frequency?
 *
 * Can we store the frequency of every character in the string in a single iteration?
 *
 * Yes. Take an array/hashMap to store the frequenies of every character in the string. All values initialed by 0.
 * Loop over the string once and for every character that we visit, increase the frquency by one.
 *
 * Now we have the frequencies of all the characters in the given string. We just have to find the first character in the string with single occurence.
 *
 * Loop over the string again (from left to right), and for every character that we visit, check the frequency in the frequency array. 
 * Return the index of the first character for which frequency is one.
 *
 * Time complexity: 
 * One O(N) iteration to store the frequencies of all the character.
 * One O(N) iteration to find the FIRST character with single occurance in the string.
 *
 * Total TC: O(N)
 * Space Used: O(26)
 *
 */
class FirstUniqCharacter {
    public int firstUniqChar(String s) {
        //array to store the frequencies of the characters in the given string
        int characterFreq[] = new int[26];
        
        //count the frequencies of every character in the given string s
        for(int i=0; i<s.length(); i++){
            characterFreq[s.charAt(i) - 'a']++;
        }
        
        //the index of the first character that occurs only once in the string is the ans
        for(int i=0; i<s.length(); i++){
            if(characterFreq[s.charAt(i) - 'a'] == 1){
                return i;
            }
        }
        
        //if there isn't any character that occurs only once then return -1
        return -1;
        
    }
}
