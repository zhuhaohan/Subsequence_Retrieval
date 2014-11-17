/*********************************************/
/* Created by Haohan Zhu @ Boston University */
/*********************************************/

/*This class is to segment sequences in 'protein?' */
/*Each sequence is segmented into length of 20*/

import java.io.*;

public class ProteinSegmentation
{
   public static void main(String args[])
  {
    //For proteins
    for(int i =0; i<1; i++){
        int counter = 0;
        String fileName = "Proteins"+i;
        counter = Segmentation.Segmentation(fileName, 20);
        System.out.println("Total number of subsequences is: " + counter);
    }
  }  
    
  public static int Segmentation(String fileName, int lamda) //Segment sequences in file 'fileName' and length of each segement is lamda.
  {
    int counter = 0; //Count of subsequences
    int totalNum = 0; // Count of sequences
    try
    {
      FileInputStream fread = new FileInputStream(fileName);
      DataInputStream in = new DataInputStream(fread);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));

      String strLine = ""; // Each line from the original file
      int numSeq = 0;

      FileOutputStream fwrite = new FileOutputStream("subProteins"+fileName.charAt(fileName.length()-1));//For Proteins
      DataOutputStream out = new DataOutputStream(fwrite);
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

      //Read File Line By Line
      while ((strLine = br.readLine()) != null){
        if(strLine.charAt(0)=='<'){
            numSeq = Integer.valueOf(strLine.substring(1, strLine.length()-1)).intValue();//Get the id of origianl sequence
            totalNum++;
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
      bw.flush();
      in.close(); //Close the input stream
      out.close(); //Close the output stream
    }catch(Exception e){
      System.err.println("Error: " + e.getMessage());
      return -1;
    }
     System.out.println("Total number of sequences is: " + totalNum);
    return counter; // Return number of subsequences proccessed by this call.
  }
}
//100000 proteins used
//2135585 subProteins generated
