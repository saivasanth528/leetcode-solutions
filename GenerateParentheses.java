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
 * Brute Force:
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
 * for N=3, initially we have 3-Opening and 3-closing parentheses
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
 *
 *
 *
 * 
 *
 *
 *
 *
 * 
 *
 *
 * 
 *
 */



public class GenerateParentheses {
    public void generateParenthesisUtil(List<String> ans, int remainingClosed, int remainingOpen, String currSequence){
        //Termination condition
        if(remainingClosed == 0 && remainingOpen == 0){
            ans.add(currSequence);
            return;
        }
        if(remainingOpen > 0){         
            generateParenthesisUtil(ans, remainingClosed, remainingOpen-1, currSequence+"(");
        }
        if(remainingClosed > remainingOpen){           
            generateParenthesisUtil(ans, remainingClosed-1, remainingOpen, currSequence+")");
        }
        return;
    }
    
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        generateParenthesisUtil(ans, n, n, "");
        return ans;
    }
}
