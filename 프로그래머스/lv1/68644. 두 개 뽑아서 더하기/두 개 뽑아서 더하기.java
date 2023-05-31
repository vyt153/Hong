import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        Integer[] num = new Integer[numbers.length*(numbers.length-1)/2];
        int idx = 0;
		for (int i = 0; i < numbers.length-1; i++) {
			for (int j = i+1; j < numbers.length; j++) {
				num[idx] = numbers[i]+numbers[j];
                idx++;
			}
		}
        
		Set<Integer> set = new HashSet<Integer>(Arrays.asList(num));
		Integer[] pre_answer = set.toArray(new Integer[0]);
		int[] answer = Arrays.stream(pre_answer).mapToInt(i -> i).toArray();
        Arrays.sort(answer);
        return answer;
    }
}