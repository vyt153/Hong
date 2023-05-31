class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int maxWidth = sizes[0][0];
        int maxHeight = sizes[0][1];
        
        for (int i=0; i  < sizes.length; i ++) {
			if (maxWidth < sizes[i][0]){
                maxWidth = sizes[i][0];
            }
            if (maxHeight < sizes[i][1]){
                maxHeight = sizes[i][1];
            }
		}
        
        for (int[] idx : sizes) {
            int temp = 0;
			if (maxWidth < maxHeight){
                if(idx[1] != maxHeight){
                    if(idx[0]>idx[1]){
                        temp = idx[0];
                        idx[0] = idx[1];
                        idx[1] = temp;
                    }
                }
            }
            else {
                if(idx[0] != maxWidth){
                    if(idx[1]>idx[0]){
                        temp = idx[0];
                        idx[0] = idx[1];
                        idx[1] = temp;
                    }
                }
            }
		}
        maxWidth = sizes[0][0];
        maxHeight = sizes[0][1];
        for (int i=0; i  < sizes.length; i ++) {
			if (maxWidth < sizes[i][0]){
                maxWidth = sizes[i][0];
            }
            if (maxHeight < sizes[i][1]){
                maxHeight = sizes[i][1];
            }
		}
        
        answer += maxWidth*maxHeight;
        
        return answer;
    }
}