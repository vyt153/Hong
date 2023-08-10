import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        for(int i = 0; i<s.length(); i++){
            String str = s.substring(i, s.length())+s.substring(0, i);
            String[] checkStr = str.split("");
            Stack<String> stack = new Stack<>();
            for(int j = 0; j<checkStr.length; j++){
                if(stack.empty()) stack.push(checkStr[j]);
                else{
                    if(checkStr[j].equals(")")&&stack.peek().equals("(")) stack.pop();
                    else if(checkStr[j].equals("}")&&stack.peek().equals("{")) stack.pop();
                    else if(checkStr[j].equals("]")&&stack.peek().equals("[")) stack.pop();
                    else stack.push(checkStr[j]);
                }
            }
            if(stack.empty()) answer++;
        }
        return answer;
    }
}