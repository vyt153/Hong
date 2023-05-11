class Solution {
    public int solution(int n) {
        
        int answer = 0;
        
        String num3 = Integer.toString(n,3);
        
        char[] arr = num3.toCharArray();
        
        String[] reverse = new String[arr.length];
        int idx = arr.length-1;
        
        for (int i = 0; i < reverse.length; i++) {
			reverse[i] = String.valueOf(arr[idx--]);
		}
        
        String res = "";
        
        for (int i = 0; i < reverse.length; i++) {
			res += reverse[i];
		}
        answer = Integer.parseInt(res, 3);
        return answer;
    }
}