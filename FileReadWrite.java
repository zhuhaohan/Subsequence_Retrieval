/*********************************************/
/* Created by Haohan Zhu @ Boston University */
/*********************************************/

/*This class is to read the file 'uniref100.fasta' from European Bioinformatics Institute*/
/*Downgload page is "http://www.ebi.ac.uk/uniprot/database/download.html"*/
/*'uniref100.fasta' is a file consisting of 14926175 proteins*/
/*After rewrite proteins, one proteins has only two lines. The first line is the number of a protein and the second line is the sequence*/

import java.io.*;

public class FileReadWrite 
{
  public static final int NUMPERFILE = 100000; // Set number of sequences in each file

    public static void main(String args[])
  {
    //Initially, read and write the proteins.
    int numProtein = 0;
    numProtein = FileReadWrite.fileReadWrite("uniref100.fasta");
    System.out.println("Total number of sequences is: " + numProtein);
  }
  
  public static int fileReadWrite(String fileName)
  {
    int numProtein = 0; // The number of sequence
    int splitter = 0; // The split number of each file
    try
    {
      // Read the file from 'uniref100.fasta'
      FileInputStream fread = new FileInputStream(fileName);
      DataInputStream in = new DataInputStream(fread);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));

      String strLine = ""; // Each line from the original file
      String proteinInfo = ""; // Information of each protein 
      String proteinSeq = ""; // Sequence of each protein

      // Write the proteins into the files 'proteins*'
      FileOutputStream fwrite = new FileOutputStream("Proteins"+splitter);
      DataOutputStream out = new DataOutputStream(fwrite);
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

      //Read File Line By Line
      while ((strLine = br.readLine()) != null){
        if(strLine.charAt(0)=='>'){ // If this line is information of a protein
          if(!proteinSeq.equals("")){ // Avoid to print a blank line at the beginning
            bw.write(proteinSeq+"\n"); // Write sequence of the last protein recorded
            proteinSeq = "";
          }
	  numProtein++; // Increase the number of proteins
          if (numProtein > NUMPERFILE*(splitter+1)) // Create a new file to record proteins
          {
            bw.flush();
            splitter++;
            fwrite = new FileOutputStream("Proteins"+splitter);
            out = new DataOutputStream(fwrite);
            bw = new BufferedWriter(new OutputStreamWriter(out));
          }
          // Choose either of following two informations of a protein
          //proteinInfo = "<" + numProtein + strLine; // Build information of a protein by inserting the number
          proteinInfo = "<" + numProtein + ">"; // New information of a protein contains only number
          if (numProtein >= 0) // Only process the protein after the start number.
            bw.write(proteinInfo+"\n");  // Write sequence of the current protein
        }
        else{ // If this line is not information of a protein, it must be part of its sequence
          proteinSeq += strLine;  // Record the sequence line by line
        }
      }
      in.close(); //Close the input stream
      out.close(); //Close the output stream
    }catch(Exception e){
      System.err.println("Error: " + e.getMessage());
      return -1;
    }
    return numProtein; // Return number of sequences proccessed by this call.
  }
}
