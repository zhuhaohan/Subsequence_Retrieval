/*********************************************/
/* Created by Haohan Zhu @ Boston University */
/*********************************************/

/*This class is to get a single sequence from a file*/
/*Then randomly change this sequence to another one*/

import java.io.*;
import java.util.Random;

public class GetSingeSequence 
{
  public static String getSequence(String fileName, int number)//Read a single sequence with the 'number' from file 'fileName'
  {
    String query = "";//Record sequence
    int counter = 0;
    try
    {
      FileInputStream fstream = new FileInputStream(fileName);
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));

      String strLine = "";
      
      //Read File Line By Line
      while ((strLine = br.readLine()) != null){
      // If this line is a subsequence
        if(strLine.charAt(0)!='<'){
            counter++;
        }
        if (counter == number){
            query = strLine;
            break;
        }
      }
      //Close the input and output stream
      in.close();
    }catch(Exception e){
      System.err.println("Error: " + e.getMessage());
    }
    return query;
  }
  public static String changeSequence(String sequence)//Change a single sequence to another one (for Proteins)
  {
    String proteinChar = "ARNDCEQGHILKMFPSTWYV"; // list of amino acid
    String query = "";//Record sequence
    Random rmg = new Random();
    double rm = 0.0;
    int rmc = 0;
    double chance = 0.25;

    for(int i =0; i<sequence.length();i++)//Change character by character
    {
      rm = rmg.nextDouble();
      if (rm <= chance){ //?% chance to change a character
          rmc = rmg.nextInt(20);
          query = query + proteinChar.charAt(rmc);// randomly choose a substituted amino acid
      }
      else{
          query = query + sequence.charAt(i);// Not change
      }
    }
    return query;
  }
}