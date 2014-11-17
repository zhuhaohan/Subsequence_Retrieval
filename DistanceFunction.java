/*********************************************/
/* Created by Haohan Zhu @ Boston University */
/*********************************************/

/*This class is to compute distance between two strings*/
/*The input can be a sequence of characters, like DNA sequences ro Protein sequnces*/
/*Or the input can be a sequence of single Hexadecimal numbers, like sequences of pitches in songs*/
/*Or the input can be a sequence of sets of numbers, like trajectories*/

public class DistanceFunction {
    
    public static double distance(String p, String q, int function){
    
        double distance = 0.0;
        
        LevenshteinDistance ld = new LevenshteinDistance();
        ERP_Hex erp_hex = new ERP_Hex();
        DiscreteFrechetDistance_Hex dfd_hex = new DiscreteFrechetDistance_Hex();
        ERP_2D erp_2d = new ERP_2D();
        DiscreteFrechetDistance_2D dfd_2d = new DiscreteFrechetDistance_2D();
        
        if(function == 0)
            distance = ld.LD(p, q);
        else if(function == 1)
            distance = erp_hex.ERP(p, q);
        else if(function == 2)
            distance = dfd_hex.DFD(p, q);
        else if(function == 3)
            distance = erp_2d.ERP(p, q);
        else if(function == 4)
            distance = dfd_2d.DFD(p, q);
        else
            ;
        
        return distance;
    }
    
}
