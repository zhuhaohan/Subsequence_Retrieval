/*********************************************/
/* Created by Haohan Zhu @ Boston University */
/*********************************************/

/*This class is to segment sequences in 'Songs' */
/*Each sequence is segmented into length of 20*/

import java.io.*;

public class SongSegmentation
{
   public static void main(String args[])
  {
    //For songs 
    int counter = 0;
    counter = Segmentation.Segmentation("Songs", 20);
    System.out.println("Total number of subsequences is: " + counter);
  }  
    
  public static int Segmentation(String fileName, int lamda) //Segment sequences in file 'fileName' and length of each segement is lamda.
  {
    int counter = 0; //Count of subsequences
    try
    {
      FileInputStream fread = new FileInputStream(fileName);
      DataInputStream in = new DataInputStream(fread);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));

      String strLine = ""; // Each line from the original file
      int numSeq = 0;

      //FileOutputStream fwrite = new FileOutputStream("subproteins"+fileName.charAt(fileName.length()));//For Proteins
      FileOutputStream fwrite = new FileOutputStream("subSongs");//For Songs
      DataOutputStream out = new DataOutputStream(fwrite);
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

      //Read File Line By Line
      while ((strLine = br.readLine()) != null){
        if(strLine.charAt(0)=='<'){
            numSeq = Integer.valueOf(strLine.substring(1, strLine.length()-1)).intValue();//Get the id of origianl sequence
        }
        else{
            for(int i = 0; i < strLine.length(); i+=lamda){ //Segment every lamda points
                bw.write("<" + numSeq + "-" + i + ">" + "\n"); //Record the id of original sequence and id for the subsequence
                counter++;
                if (i+lamda < strLine.length()) // If the length of subsequnce is less than lamda
                    bw.write(strLine.substring(i, i+lamda) + "\n"); // Record rest of the sequence
                else
                    bw.write(strLine.substring(i) + "\n"); // Record next lamda points of the sequence
            }
        }
      }
      bw.flush(); //flush the buffer
      in.close(); //Close the input stream
      out.close(); //Close the output stream
      System.out.println("Maximal number of sequences is: " + numSeq);
    }catch(Exception e){
      System.err.println("Error: " + e.getMessage());
      return -1;
    }
    return counter; // Return number of sequences proccessed by this call.
  }
}
//10000 songs used
//433597 subSongs generated
