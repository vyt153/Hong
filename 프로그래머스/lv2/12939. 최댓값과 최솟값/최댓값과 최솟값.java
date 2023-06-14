import java.util.Arrays;
class Solution {
    public String solution(String s) {
        String[] arr = s.split(" ");
        int[] nums = Arrays.asList(arr).stream().mapToInt(Integer::parseInt).toArray();
        Arrays.sort(nums);
        String answer = nums[0] +" "+nums[nums.length-1];
        return answer;
    }
}