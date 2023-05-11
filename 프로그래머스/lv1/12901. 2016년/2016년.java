class Solution {
    public String solution(int a, int b) {
        String answer = "";
        int date = 0;
        for (int i = 0; i < a; i++) {
            if (i == 2) date += 29;
            else if (i == 4||i == 6||i == 9||i == 11) date += 30;
            else if(i==0) continue;
            else date += 31;
		}
        date+=b;
        switch (date%7) {
		case 0: {answer+="THU"; break;}
		case 1: {answer+="FRI";break;}
		case 2: {answer+="SAT";break;}
		case 3: {answer+="SUN";break;}
		case 4: {answer+="MON";break;}
		case 5: {answer+="TUE";break;}
		case 6: {answer+="WED";break;}
		}
        return answer;
    }
}