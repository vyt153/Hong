import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        for(int i=0; i<=discount.length-10;i++){
            Map<String, Integer> map = new HashMap<>();
            
            for(int j=i; j<i+10; j++)map.put(discount[j], map.getOrDefault(discount[j], 0)+1);
            
            answer += checkDay(map, number, want)? 1 : 0;
        }
        return answer;
    }
    
    public boolean checkDay(Map map, int[] number, String[] want){
        int check = 0;
        for(int i=0; i<number.length; i++){
            if(map.get(want[i])!=null&&map.get(want[i]).equals(number[i])) check++;
        }
        
        if(check==number.length) return true;
        else return false;
    }
}