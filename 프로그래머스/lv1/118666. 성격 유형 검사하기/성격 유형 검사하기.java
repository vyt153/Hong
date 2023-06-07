class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        int[] type1 = new int[2];
        int[] type2 = new int[2];
        int[] type3 = new int[2];
        int[] type4 = new int[2];
        
        int standard = 4;
        for(int i=0; i<choices.length;i++){
            if(survey[i].equals("RT")||survey[i].equals("TR")){
                if(survey[i].equals("RT")){
                    if(choices[i]>standard) type1[1] += choices[i]-standard;
                    else type1[0] += standard-choices[i];
                } else{
                    if(choices[i]>standard) type1[0] += choices[i]-standard;
                    else type1[1] += standard-choices[i];
                }
            }else if(survey[i].equals("CF")||survey[i].equals("FC")){
                if(survey[i].equals("CF")){
                    if(choices[i]>standard) type2[1] += choices[i]-standard;
                    else type2[0] += standard-choices[i];
                } else{
                    if(choices[i]>standard) type2[0] += choices[i]-standard;
                    else type2[1] += standard-choices[i];
                }
            }else if(survey[i].equals("JM")||survey[i].equals("MJ")){
                if(survey[i].equals("JM")){
                    if(choices[i]>standard) type3[1] += choices[i]-standard;
                    else type3[0] += standard-choices[i];
                } else{
                    if(choices[i]>standard) type3[0] += choices[i]-standard;
                    else type3[1] += standard-choices[i];
                }
            }else if(survey[i].equals("AN")||survey[i].equals("NA")){
                if(survey[i].equals("AN")){
                    if(choices[i]>standard) type4[1] += choices[i]-standard;
                    else type4[0] += standard-choices[i];
                } else{
                    if(choices[i]>standard) type4[0] += choices[i]-standard;
                    else type4[1] += standard-choices[i];
                }
            }
        }
        int[][] result = {type1,type2,type3,type4};
        for(int i=0; i<4;i++){
            if(result[i][0]>result[i][1]||result[i][0]==result[i][1]) {
                if(i==0) answer+="R";
                if(i==1) answer+="C";
                if(i==2) answer+="J";
                if(i==3) answer+="A";
            } else{
                if(i==0) answer+="T";
                if(i==1) answer+="F";
                if(i==2) answer+="M";
                if(i==3) answer+="N";
            }
        }
        return answer;
    }
}