import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        for(int i = 0; i<s.length(); i++){
            String str = s.substring(i, s.length())+s.substring(0, i);
            Stack<Character> stack = new Stack<>();
            
            for(int j = 0; j<str.length(); j++){
                if(stack.empty()) stack.push(str.charAt(j));
                else{
                    if(str.charAt(j)==')'&&stack.peek()=='(') stack.pop();
                    else if(str.charAt(j)=='}'&&stack.peek()=='{') stack.pop();
                    else if(str.charAt(j)==']'&&stack.peek()=='[') stack.pop();
                    else stack.push(str.charAt(j));
                }
            }
            if(stack.empty()) answer++;
        }
        return answer;
    }
}