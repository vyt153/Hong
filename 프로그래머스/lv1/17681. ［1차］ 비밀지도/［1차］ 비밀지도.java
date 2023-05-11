class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

		for (int i = 0; i < n; i++) {
			Long idx1 = Long.parseLong(Long.toBinaryString(arr1[i]));
			Long idx2 = Long.parseLong(Long.toBinaryString(arr2[i]));

			String[] map1 = String.format("%0" + n + "d", idx1).split("");
			String[] map2 = String.format("%0" + n + "d", idx2).split("");
			String pre_answer = "";

			for (int j = 0; j < n; j++) {
				if (map1[j].equals("1") || map2[j].equals("1"))
					pre_answer += "#";
				else
					pre_answer += " ";
			}
			answer[i] = pre_answer;
		}
		return answer;
    }
}