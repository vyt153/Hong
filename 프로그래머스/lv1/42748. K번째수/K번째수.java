import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
		for (int i = 0; i < commands.length; i++) {
			int idx = 0;
			int[] arr = new int[commands[i][1] - commands[i][0] + 1];
			for (int j = commands[i][0] - 1; j < commands[i][1]; j++) {
				arr[idx] = array[j];
				idx++;
			}
			Arrays.sort(arr);
			answer[i] = arr[commands[i][2]-1];
		}
		return answer;
    }
}