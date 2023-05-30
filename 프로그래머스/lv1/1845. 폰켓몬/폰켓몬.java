import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(arr));
        if (set.size()>nums.length/2) answer += nums.length/2;
        else answer += set.size();
        
        return answer;
    }
}