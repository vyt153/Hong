class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int bestCnt = 0;
        for(int i=0;i<lottos.length;i++){
            for(int j=0;j<lottos.length;j++){
                if(lottos[i]==0||lottos[i]==win_nums[j]) {
                    bestCnt++;
                    break;
                }
            }
        }
        int worstCnt = 0;
        for(int i=0;i<lottos.length;i++){
            for(int j=0;j<lottos.length;j++){
                if(lottos[i]==0) break;
                if(lottos[i]==win_nums[j]) worstCnt++;
            }
        }
        if(bestCnt==6) answer[0] = 1;
        else if(bestCnt==5) answer[0] = 2;
        else if(bestCnt==4) answer[0] = 3;
        else if(bestCnt==3) answer[0] = 4;
        else if(bestCnt==2) answer[0] = 5;
        else answer[0] = 6;
        if(worstCnt==6) answer[1] = 1;
        else if(worstCnt==5) answer[1] = 2;
        else if(worstCnt==4) answer[1] = 3;
        else if(worstCnt==3) answer[1] = 4;
        else if(worstCnt==2) answer[1] = 5;
        else if(worstCnt<2) answer[1] = 6;
        return answer;
    }
}