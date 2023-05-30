import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
		HashMap<Integer, Double> map = new HashMap<>();
		double[] failRate = new double[N];
		for (int i = 1; i < N+1; i++) {
			map.put(i, 0.);
		}
		for (int i = 0; i < stages.length; i++) {
			if(map.containsKey(stages[i])) {
				map.put(stages[i], map.get(stages[i]) +1);
			}
		}
		List<Double> valueList = new ArrayList<>(map.values());
	
		int stage = stages.length;
		for (int i = 0; i < valueList.size(); i++) {
            if (valueList.get(i) == 0)
				failRate[i] = 0;
			else {
				failRate[i] = valueList.get(i) / stage;
			}
			stage -= valueList.get(i);
			map.put(i+1, failRate[i]);
		}

		valueList = new ArrayList<>(map.values());
		Collections.sort(valueList, Collections.reverseOrder());
		for(Integer key : map.keySet()) {
			for (int i = 0; i < valueList.size(); i++) {
				if(valueList.get(i)==map.get(key)) {
					answer[i] = key;
				}
			}
		}
        return answer;
    }
}