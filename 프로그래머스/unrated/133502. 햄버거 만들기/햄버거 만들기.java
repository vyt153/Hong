import java.util.Stack;
class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        
        Stack<Integer> hamburger = new Stack<>();
        
        int i = 0;
        int wrapBurger = 0;
        
        while(i<ingredient.length){
            hamburger.push(ingredient[i]);
            
            if(hamburger.peek()-1==wrapBurger){
                wrapBurger++;
            } else if(hamburger.peek()==1&&wrapBurger==3){
                hamburger.pop();hamburger.pop();
                hamburger.pop();hamburger.pop();
                
                answer++;
                if(hamburger.isEmpty()){
                    wrapBurger = 0;
                }else{
                    int bread = hamburger.search(1);
                    int vegetable = hamburger.search(2);

                    if(bread==1) wrapBurger=1;
                    else if(bread+vegetable==3) wrapBurger=2;
                    else wrapBurger=0;
                }
            } else{
                if(hamburger.peek()==1) wrapBurger = 1;
                else wrapBurger = 0;   
            }
            i++;
        }
        
        return answer;
    }
}