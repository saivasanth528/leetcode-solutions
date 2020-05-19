/*
 * Generate Parentheses
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [
 *  "((()))",
 *  "(()())",
 *  "(())()",
 *  "()(())",
 *  "()()()"
 * ]
 *
 * Basic Observations:
 * 1. If we have n pairs, the lenght of every combination will be 2*n (one openeing and one closing parentheses in every pair).
 * 2. For every index i, we have only two choices, 
 *      a. Either put '(' at index i
 *      b. OR put ')' in at index i
 * 
 * Solution
 * Generate all sequences of n opening and n closing parentheses.
 * 
 * How to do that?
 *
 * Initially, we have n openeing parentheses ["("] and n closing parentheses [")"] to be put. 
 * For the first index we have 2 choices, either put "(" or ")". (Since we are generating all the possibilities, we will be exploring both the choices)
 *
 * if we select "(", we have a similar problem to solve for n-1 openeing parentheses and n closing parentheses.
 * if we select ")", we have a similar problem to solve n openeing parentheses and n-1 closing parentheses.
 *
 * For every selection, we will have 2 choinces for the next index also. And this goes on till the last index (2*n).
 * The following tree summarises this
 *
 * for N=2, initially we have 2-Opening and 2-closing parentheses
 *                                      
 *                                                                  2-Opening, 2-Closing
 *                                                               /                        \
 *                                                              /                           \
 *                                                             put '('                        put ')'
 *                                                            /                                \
 *                                                           /                                  \
 *                                                       1-O,2-C                               2-O, 1-C
 *                                                        ['(']                                 [')']
 *                                                       /     \                               /     \
 *                                                      /        \                            /        \
 *                                                  put '('     put ')'                     put '('     put ')'
 *                                                    /             \                       /              \
 *                                                   /                \                    /                 \  
 *                                                0-O,2-C          1-O,1-C                1-O,1-C            2-O,0-C 
 *                                                 ['((']           ['()']                [")("]             ["))"]
 *                                                    |            /     \               /    \                | 
 *                                                    |           /       \             /      \               |  
 *                                                put ')'      put '('   put ')'       put '('  put')'        put '('
 *                                                    |         /          \           /         \             |
 *                                                    |        /            \         /           \            |
 *                                                0-O,1-C    0-O,1-C       1-O,0-O   0-O,1-C      1-O,0-C    1-O,0-C 
 *                                                ["(()"]    ["()("]       ["())"]  [")(("]       [")()"]    ["))("]
 *                                                   |          |             |        |             |          |
 *                                                   |          |             |        |             |          |
 *                                                 put ')'    put ')'      put '('  put ')'       put '('     put '('
 *                                                   |          |             |        |             |          |
 *                                                   |          |             |        |             |          |
 *                                                0-O,0-C    0-O,0-C     0-O,0-C    0-O,0-C       0-O,0-C    0-O,0-C
 *                                                ["(())"]   ["()()"]    ["()))"]   [")(()"]      [")()("]   ["))(("]
 *                                                Valid        Valid      Invalid   Invalid       Invalid     Invalid
 *
 * 
 * In this case we are generating all possible sequences and then cheching which one of them are valid sequences.
 *
 * If we observe, any sequence is invalid, 
 * if at any index the number of closing parantheses till that index is greater than the opening parentheses till that index.
 * 
 * Which means that while creating the sequence,
 * if putting the closing parentheses makes the count of closing parentheses in the current sequence greater than the count of opening parentheses
 * then all the results of that sequence will result in invalid sequences.(Verify in the above tree).
 * 
 * Can we use this observation to generate only the valid sequence and prune branches resulting in an invalid sequence?
 *
 * Yes! The only change that we have to make is,
 * While making the choice of putting ')' check if the count of '(' in the current sequence is greater than count of ')'.
 * Or in other words,
 * Only put ')' if the count of '(' remaining is less than the count of ')' remaining.
 *
 * The above tree in that case changes to,
 *                                                                   2-Opening, 2-Closing
 *                                                               /                        \
 *                                                              /                          \
 *                                                             put '('                      put ')'
 *                                                            /                               \
 *                                                           /                                 \
 *                                                       1-O,2-C                             NOT Possible
 *                                                        ['(']                            No need to explore
 *                                                       /     \                               
 *                                                      /        \                           
 *                                                  put '('     put ')'                     
 *                                                    /             \                    
 *                                                   /                \                    
 *                                                0-O,2-C          1-O,1-C                
 *                                                 ['((']           ['()']                
 *                                                    |            /     \             
 *                                                    |           /       \             
 *                                                put ')'      put '('   put ')'       
 *                                                    |         /          \           
 *                                                    |        /            \         
 *                                                0-O,1-C    0-O,1-C      NOT POSSIBLE    
 *                                                ["(()"]    ["()("]     No need to explore
 *                                                   |          |                     
 *                                                   |          |                     
 *                                                 put ')'    put ')'      
 *                                                   |          |                    
 *                                                   |          |                          
 *                                                0-O,0-C    0-O,0-C         
 *                                                ["(())"]   ["()()"]       
 *                                                Valid        Valid         
 *
 */

public class GenerateParentheses {
    public void generateParenthesesUtil(List<String> ans, int remainingClosed, int remainingOpen, String currSequence){
        //Termination condition 
        //If all opening and closing parentheses have been used
        if(remainingClosed == 0 && remainingOpen == 0){
            ans.add(currSequence);
            return;
        }
        // If the count of remaining opening parentheses is > 0 then we can make a choice to put a '('
        if(remainingOpen > 0){         
            generateParenthesesUtil(ans, remainingClosed, remainingOpen-1, currSequence+"(");
        }
        // If count of remaining closing parentheses is greater that the count of remaining closing parentheses, this means that
        // putting a closing parentheses willalways generate a valid sequence
        if(remainingClosed > remainingOpen){           
            generateParenthesesUtil(ans, remainingClosed-1, remainingOpen, currSequence+")");
        }
        return;
    }
    
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        generateParenthesesUtil(ans, n, n, "");
        return ans;
    }
}
