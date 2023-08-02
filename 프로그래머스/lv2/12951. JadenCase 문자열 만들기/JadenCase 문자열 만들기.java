import java.util.regex.Pattern;
class Solution {
    public String solution(String s) {
        String answer = "";
        String[] words = s.split(" ");
        
        for(int i=0;i<words.length; i++){
            
            String word = words[i];
            if(word.equals("")||word==""){
                answer += " ";
                continue;
            }
            
            if(!isInt(String.valueOf(word.charAt(0)))){  
                
                String upperWord = word.substring(0, 1);
                upperWord = upperWord.toUpperCase();
                
                String remainWord = word.substring(1);
                remainWord = remainWord.toLowerCase();
                
                answer += upperWord + remainWord;
            } else{
                answer += word.toLowerCase();
            }
            
            if(i<words.length-1){
                answer += " ";
            }
        }
        if(s.substring(s.length() - 1).equals(" ")){
            answer += " ";
        }
        return answer;
    }
    
    public boolean isInt(String word){
        try {
          Integer.parseInt(word);
          return true;
        } catch (NumberFormatException ex) {
          return false;
        }
    }
}