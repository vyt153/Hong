class Solution {
	public String solution(String s, int n) {
		String answer = "";
		char[] arr = s.toCharArray();

		for (char c : arr) {
			int num = (int) c;
			if (Character.isUpperCase(c)) {
				if (num + n > 90)
					answer += (char) (num + n - 26);
				else
					answer += (char) (num + n);
			} else if (Character.isLowerCase(c)) {
				if (num + n > 122)
					answer += (char) (num + n - 26);
				else
					answer += (char) (num + n);
			} else
				answer += (char) num;
		}
		return answer;
	}
}