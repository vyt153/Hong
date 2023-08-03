import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        
        int leftPointer = 0;
       for (int i = people.length-1; i >= leftPointer; i--) {
          if(people[leftPointer]+people[i]<=limit){
              answer += 1;
              leftPointer++;
          } else{
              answer += 1;
          }
		}
			
        return answer;
    }
}