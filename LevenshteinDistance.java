/*This class is original from "http://en.wikibooks.org/wiki/Algorithm_implementation/Strings/Levenshtein_distance#Java*/
/*But some parts are revised*/

public class LevenshteinDistance {
    
    private int minimum(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }
    
    private int df(char p, char q){
     return (p == q ? 0 : 1);
    }
    
    public int LD(String p, String q){
        
        int[][] distance = new int[p.length() + 1][q.length() + 1];
        
        for (int i = 0; i <= p.length(); i++)
            distance[i][0] = i;
        for (int j = 0; j <= q.length(); j++)
            distance[0][j] = j;
        for (int i = 1; i <= p.length(); i++)
            for (int j = 1; j <= q.length(); j++)
                distance[i][j] = minimum(distance[i-1][j] + 1, 
                                         distance[i][j-1] + 1, 
                                         distance[i-1][j-1] + df(p.charAt(i-1), q.charAt(j-1)));
                                         
        return distance[p.length()][q.length()];
    }
}