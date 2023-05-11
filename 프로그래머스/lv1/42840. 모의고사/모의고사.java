import java.util.*;

class Solution {
	public int[] solution(int[] answers) {
		int[] pre_answer = new int[3];
		boolean[] count = new boolean[3];

		int[] supo1 = { 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 };
		int[] supo2 = { 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5 };
		int[] supo3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
        
		int length = answers.length;
        int arrIdx = 0;
        int answersIdx = length;
		while (arrIdx < length) {
            int[] arr = Arrays.copyOfRange(answers, arrIdx, length);
			for (int i = 0; i < supo1.length; i++) {
				if (i == answersIdx) break;
				if (supo1[i] == arr[i])
					pre_answer[0] += 1;
				if (supo2[i] == arr[i])
					pre_answer[1] += 1;
				if (supo3[i] == arr[i])
					pre_answer[2] += 1;
			}
			answersIdx -= supo1.length;
            System.out.println(answersIdx);
            arrIdx += supo1.length;
		}
        System.out.println(Arrays.toString(pre_answer));
        int max = 0;
		for (int i : pre_answer) {
			if(i>max) max = i;
		}
        int cnt = 0;
		 for (int i = 0; i < count.length; i++) {
		 	if(pre_answer[i]==max) {
		 		cnt += 1;
		 		count[i] = true;
		 	}
		 }
		int[] answer = new int[cnt];
		int idx = 0;
		for (int i = 0; i < count.length; i++) {
			if (count[i]) {
				answer[idx] = i + 1;
				idx++;
			}
		}
		return answer;
	}
}