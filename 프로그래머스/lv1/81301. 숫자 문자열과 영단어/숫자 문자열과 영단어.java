class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] numList = {"zero","one","two","three","four","five","six","seven","eight","nine"};
		for (int i = 0; i < numList.length; i++) {
			s = s.replace(numList[i],Integer.toString(i));
		}
        answer = Integer.parseInt(s);
        return answer;
    }
}