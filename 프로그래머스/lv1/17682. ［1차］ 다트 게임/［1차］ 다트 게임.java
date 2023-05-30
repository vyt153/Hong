import java.util.*;
class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int[] arr = new int[3];
        int idx = 0;

        for(int i=0; i<3;i++){
            int cnt = 0;
            int num = 0;
            String tenChk = "";
            for(int j=idx; j<dartResult.length();j++){
                char c = dartResult.charAt(j);
                
                if((int)c>47&&(int)c<59){
                    cnt++;
                    if(c=='1'||c=='0'){
                        tenChk += c; 
                    } 
                    if(tenChk.equals("10")){
                        num = 10;
                        idx = j;
                    } else if(cnt>=2){
                        if(cnt>2){
                            idx = j-(cnt-2);
                        } else{
                            idx = j;    
                        }
                        break;  
                    } else{
                        num = Character.getNumericValue(c);    
                    }
                    
                } else if(c=='S'){
                    arr[i] = num;
                } else if(c=='D'){
                    arr[i] = num*num;
                    System.out.print(arr[i]);
                } else if(c=='T'){
                    arr[i] = (num*num)*num;
                } else if(c=='*'){
                    arr[i] *= 2;
                    if(i!=0){
                        arr[i-1] *= 2;
                    }
                } else if(c=='#'){
                    arr[i] *= -1;
                }
            }
        }
        for(int i=0; i<arr.length;i++){
            answer += arr[i];
            System.out.print(arr[i] + " ");
        }
        return answer;
    }
}
