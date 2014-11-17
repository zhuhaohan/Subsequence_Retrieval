public class ERP_Hex {
    
    private int minimum(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }
    
    private int df(char p, char q){
        int a = Integer.parseInt(Character.toString(p),16);
        int b = Integer.parseInt(Character.toString(q),16); 
        return Math.abs(a-b);
    }
    
    public int ERP(String p, String q){

        char g = '0';
        
        int[][] distance = new int[p.length() + 1][q.length() + 1];
        distance[0][0] = 0;
        
        for (int i = 1; i <= p.length(); i++)
            distance[i][0] = distance[i-1][0] + df(p.charAt(i-1), g);
        for (int j = 1; j <= q.length(); j++)
            distance[0][j] = distance[0][j-1] + df(g, q.charAt(j-1));
        for (int i = 1; i <= p.length(); i++)
            for (int j = 1; j <= q.length(); j++)
                distance[i][j] = minimum(distance[i-1][j] + df(p.charAt(i-1), g), 
                                         distance[i][j-1] + df(g, q.charAt(j-1)), 
                                         distance[i-1][j-1] + df(p.charAt(i-1), q.charAt(j-1)));
        
        return distance[p.length()][q.length()];
    }
}