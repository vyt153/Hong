import java.util.*;
class Solution {
    public int solution(int[] nums) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < nums.length-2; i++) {
                    for (int j = i+1; j < nums.length-1; j++) {
                        for (int j2 = j+1; j2 < nums.length; j2++) {
                            int num = nums[i]+nums[j]+nums[j2];
                            int pre_answer = 0;
                            for (int k = 1; k < num+1; k++) {
			                    if(num%k==0) pre_answer += k;
		                    }
                            if(pre_answer==num+1) list.add(num);
                        }
                    }
                }
        return list.size();
    }
}