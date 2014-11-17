/*********************************************/
/* Created by Haohan Zhu @ Boston University */
/*********************************************/

/*This class is to segment sequences by sliding windows*/
/*Each sequence is segmented into length of 20+-lamda0*/

import java.io.*;

public class SlideSegmentation 
{
   public static void main(String args[])
  {
      int counter = 0;
      String query = "MVRCWYVVWHPPLGLPQTDPMANPHYCPSVTKPHKRSRLNLCKSTCRWKRPRGSRVTLTVP";
      counter = SlideSegmentation.SlideSegmentation(query, 20, 2);
      System.out.println("Length of query is: " + query.length());
      System.out.println("Total number of subsequences is: " + counter);
  }  
    
  public static int SlideSegmentation(String query, int lamda, int lamda0) //Segment sequences 'query' and length of each segement is lamda+-lamda0.
  {
    int counter = 0; //Count of subsequences
    try
    {
      FileOutputStream fwrite = new FileOutputStream("subQuery");//For Proteins
      DataOutputStream out = new DataOutputStream(fwrite);
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

      //Segment sequence for every lamda0
      for(int i=-lamda0; i<=lamda0; i++){
          for(int j = 0; j < query.length(); j++){ //Segment according to sliding windows
              bw.write("<" + i + "-" + j + ">" + "\n"); //Record the lamda0 and id for the subsequence
              counter++;
              if (j+lamda+i < query.length()) // If the length of subsequnce is less than lamda+lamda0
                  bw.write(query.substring(j, j+lamda+i) + "\n"); // Record rest of the sequence
              else
                  bw.write(query.substring(j) + "\n"); // Record next lamda points of the sequence
          }
      }
      bw.flush();
      out.close(); //Close the output stream
    }catch(Exception e){
      System.err.println("Error: " + e.getMessage());
      return -1;
    }
    return counter; // Return number of subsequences proccessed by this call.
  }
}