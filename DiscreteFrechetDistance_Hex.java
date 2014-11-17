public class DiscreteFrechetDistance_Hex {

    private int maximum(int a, int b){
        return Math.max(a, b);
    }
  
    private int minimum(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }
    
    public int df(char p, char q){
        int a = Integer.parseInt(Character.toString(p),16);
        int b = Integer.parseInt(Character.toString(q),16); 
        return Math.abs(a-b);
    }
    
    public int DFD (String p, String q){ // Calculates the discrete Frechet distance between sequences P and Q
        
        int[][] distance = new int[p.length() + 1][q.length() + 1];
        distance[0][0] = 0;
        
        char g = '0';
        
        for (int i = 1; i <= p.length(); i++)
            distance[i][0] = maximum(distance[i-1][0], df(p.charAt(i-1), g));
        for (int j = 1; j <= q.length(); j++)
            distance[0][j] = maximum(distance[0][j-1], df(g, q.charAt(j-1)));
        for (int i = 1; i <= p.length(); i++)
            for (int j = 1; j <= q.length(); j++)
                distance[i][j] = maximum( minimum(distance[i-1][j], 
                                                  distance[i][j-1], 
                                                  distance[i-1][j-1]), 
                                          df(p.charAt(i-1), q.charAt(j-1)) );
        
        return distance[p.length()][q.length()];
    
  }

}