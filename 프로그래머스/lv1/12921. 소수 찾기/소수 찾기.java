class Solution {
    public int solution(int n) {
        int answer = 0;
        boolean[] arr = new boolean[n+1];
        arr[0] = arr[1] = true;                 
		for (int i = 2; i * i <= n; i++) {
			if (!arr[i]) {
				for (int j = i * i; j <= n; j += i){
					arr[j] = true;
                }
			}
		}
        for (int i = 0; i < arr.length; i++) {
			if(!arr[i]) answer+=1;
		}
        return answer;
    }
}