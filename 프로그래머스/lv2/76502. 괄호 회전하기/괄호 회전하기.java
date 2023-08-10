import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        for(int i = 0; i<s.length(); i++){
            String subStr = s.substring(0, i);
            String str = s.substring(i, s.length())+subStr;
            
            String[] checkStr = str.split("");
            Stack<String> stack = new Stack<>();
            for(int j = 0; j<checkStr.length; j++){
                
                stack.push(checkStr[j]);
                if((stack.peek().equals(")")&&stack.search("(")==2)||
                  (stack.peek().equals("}")&&stack.search("{")==2)||
                  (stack.peek().equals("]")&&stack.search("[")==2)){
                    stack.pop();stack.pop();
                }
            }
            if(stack.empty()) answer++;
        }
        return answer;
    }
}