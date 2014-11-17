public class ERP_2D {
    
    private double minimum(double a, double b, double c){
        return Math.min(Math.min(a, b), c);
    }
    
    private double df(String x, String y){
        
        int x1 = Integer.parseInt(x.substring(1, x.indexOf(',')));
        int x2 = Integer.parseInt(x.substring(x.indexOf(',')+1, x.length()-1));
        
        int y1 = Integer.parseInt(y.substring(1, y.indexOf(',')));
        int y2 = Integer.parseInt(y.substring(y.indexOf(',')+1, y.length()-1));
        
        return Math.sqrt(Math.pow(x1-y1, 2) + Math.pow(x2-y2, 2));
    }
    
    public double ERP(String p, String q){

        String[] pSeq = p.split(";");
        String[] qSeq = q.split(";");
        
        double[][] distance = new double[pSeq.length + 1][qSeq.length + 1];
        distance[0][0] = 0.0;
        
        String g = "(0,0)";
        
        for (int i = 1; i <= pSeq.length; i++)
            distance[i][0] = distance[i-1][0] + df(pSeq[i-1], g);
        for (int j = 1; j <= qSeq.length; j++)
            distance[0][j] = distance[0][j-1] + df(g, qSeq[j-1]);
        for (int i = 1; i <= pSeq.length; i++)
            for (int j = 1; j <= qSeq.length; j++)
                distance[i][j] = minimum(distance[i-1][j] + df(pSeq[i-1], g), 
                                         distance[i][j-1] + df(g, qSeq[j-1]), 
                                         distance[i-1][j-1] + df(pSeq[i-1], qSeq[j-1]));
        
        return distance[pSeq.length][qSeq.length];
    }
}