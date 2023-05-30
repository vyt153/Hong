class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        for (int i = 0; i < t.length(); i++) {
			if (i+p.length()>t.length()) break;
            
			String str = t.substring(i, i+p.length());

			if (Long.parseLong(str)<= Long.parseLong(p)) {
				answer += 1;
			}
		}
        return answer;
    }
}