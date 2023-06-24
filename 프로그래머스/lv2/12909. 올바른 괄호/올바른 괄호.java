class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int leftCnt = 0;
        int rightCnt = 0;

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='(') leftCnt++;
            else if(s.charAt(i)==')') rightCnt++;
            
            if(leftCnt<rightCnt){
                answer = false;
                break;
            }
        }
        if(leftCnt!=rightCnt) answer = false;
        return answer;
    }
}