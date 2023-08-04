class Solution {
    public int[] solution(String[] wallpaper) {
        
        int minY = 0;
        int maxY = wallpaper.length;
        
        for (int i = 0; i < wallpaper.length; i++) {
			if(!wallpaper[i].contains("#")) minY+=1;
            else break;
		}
        for (int i = wallpaper.length-1; i >= minY; i--) {
			if(!wallpaper[i].contains("#")) maxY-=1;
            else break;
		}
		System.out.println(minY);
        
        int minX = wallpaper[0].length();
        int maxX = 0;
        
        for (int i = minY; i < maxY; i++) {
			if(wallpaper[i].indexOf("#")<minX&&wallpaper[i].indexOf("#")!=-1){
                minX = wallpaper[i].indexOf("#");
            }
            if(wallpaper[i].lastIndexOf("#")>maxX&&wallpaper[i].indexOf("#")!=-1){
                maxX = wallpaper[i].lastIndexOf("#");
            }
		}
        int[] answer = {minY,minX,maxY,maxX+1};
        return answer;
    }
}