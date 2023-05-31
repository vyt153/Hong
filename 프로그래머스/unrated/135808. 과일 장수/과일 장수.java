import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        Integer[] arr = Arrays.stream(score).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Collections.reverseOrder());
        int numOfBox = score.length/m;
        int profit = 0; 
        int idx = 0;
        int count = 0;
        int[] box = new int[m]; 
        for (int i = 0; i<score.length;i++) {
            box[idx] = arr[i]; 
            idx++; 
            if(i==m*(count+1)-1){
            	idx = 0;
            	profit += box[m-1] * m;
                count++;
            }
            if(count==numOfBox) break;
        }
        return profit;
        }
}