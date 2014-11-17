/*********************************************/
/* Created by Haohan Zhu @ Boston University */
/*********************************************/

/*This class is to get subsequences from a file generated after segmentation*/
/*For example: Get subsequences from file 'subProteins0', 'subSongs', 'subTracks'*/
/*Subsequences are stored in an ArrayList*/

import java.io.*;
import java.util.ArrayList;

public class GetSequences 
{
  public static ArrayList<String> getSequences(String fileName, int number)//Read 'number' subsequences from file 'fileName'
  {
    ArrayList<String> proteinList = new ArrayList<String>();//Record subsequences
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
            proteinList.add(strLine); // Add this subsequence into ArrayList
            counter++;
        }
        if (counter > number)
            break;
      }
      //Close the input and output stream
      in.close();
    }catch(Exception e){
      System.err.println("Error: " + e.getMessage());
    }
    
    return proteinList;
  }
}