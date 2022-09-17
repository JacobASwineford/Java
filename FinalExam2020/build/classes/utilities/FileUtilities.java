
package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class FileUtilities {
    final static Charset ENCODING = StandardCharsets.UTF_8;
    /**
     * Returns the <code>File</code> selected by the user or null if the user
     * selects the cancel option instead of selecting a file. 
     * 
     * @return The <code>File</code> selected by the user or null. 
     */
    public static File selectFile() {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a file "); // optional setting
        // int returnValue = fileChooser.showOpenDialog(null); 
       // The JFileChooser below will have no parent window and show the message 
       // "Select File" instead of "Open"
        int returnValue = fileChooser.showDialog(null,"Select File"); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }   
        return file; 
    }          
    
    /**
     * Returns the <code>File</code> selected by the user to be opened or null if the user
     * selects the cancel option instead of selecting a file. 
     * 
     * @return The <code>File</code> selected by the user or null. 
     */
    public static File openFile() {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select the file to open");
        int returnValue = fileChooser.showOpenDialog(null); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }   
        return file; 
    }  
   /**
     * Returns the <code>File</code> selected by the user to be opened or null if the user
     * selects the cancel option instead of selecting a file. 
     * 
     * @param title The title for the open file dialog window. 
     * @return The <code>File</code> selected by the user or null. 
     */
    public static File openFile(String title) {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);
        int returnValue = fileChooser.showOpenDialog(null); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }   
        return file; 
    }
    
        /**
     * Returns the <code>File</code> selected by the user to be saved or null if the user
     * selects the cancel option instead of selecting a file. 
     * 
     * @return The <code>File</code> selected by the user or null. 
     */
    public static File saveFile() {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }   
        return file; 
    }   
    
    /**
     * Returns the <code>Path</code> to the directory selected by the user or null if the user
     * selects the cancel option instead of selecting a directory. 
     * 
     * @return The <code>Path</code> to the directory selected by the user or null. 
     */ 
    public static Path selectDirectory(){
        Path  directoryPath = null;
        JFileChooser directoryChooser = new JFileChooser();
        directoryChooser.setDialogTitle("Select a Directory");
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//      int returnValue = directoryChooser.showOpenDialog(null); // no parent window
        int returnValue = directoryChooser.showDialog(directoryChooser, "Select Directory"); // no parent window
        String path = null;
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            directoryPath = directoryChooser.getSelectedFile().toPath()   ;
        }   
        return directoryPath;
    }
    
    // returns File object or null
    public static File openFileByPath(String filePath){
        File file = new File(filePath);
        if(file.canRead()) return file; 
        else return null;
    }
    
    // returns File object or null
    public static File saveFile(String filePath){
        File file = new File(filePath); // Locate the file
        if(file.canWrite()) return file; // If we can write to the file we are good
        try {
            if (file.createNewFile())return file; // See if we can create a new file
            else return null;
        } catch (IOException ex) {
           return null;
        }
    }
    
      List<String> readSmallTextFile(String aFileName) throws IOException {
     Path path = Paths.get(aFileName);
     return Files.readAllLines(path, ENCODING);
  }
  
  void writeSmallTextFile(List<String> aLines, String aFileName) throws IOException {
    Path path = Paths.get(aFileName);
    Files.write(path, aLines, ENCODING);
  }


  void writeLargerTextFile(String aFileName, List<String> aLines) throws IOException {
    Path path = Paths.get(aFileName);
    try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
      for(String line : aLines){
        writer.write(line);
        writer.newLine();
      }
    }
  }
  
   private static void cleanFile() {
        
        File infile = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select the common passwords file"); // optional setting
        int returnValue = fileChooser.showDialog(null,"Open File"); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            infile = fileChooser.getSelectedFile();
        }
        File file = null;
        fileChooser = new JFileChooser();
        returnValue = fileChooser.showSaveDialog(null); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }   
        BufferedWriter writer=null;
        try {
             writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException ex) {
            System.out.println( "Error opening the common passwords file.");
            System.exit(1);
        }
        try {
            Scanner commonPasswordsInput = new Scanner(infile);
            int count = 0;
            String word = null;
            while(commonPasswordsInput.hasNext()){
                 word = commonPasswordsInput.next().trim();

                 writer.write(word.split("/")[0]+"\n");
                if(count==0)System.out.println("The first word was %"+word+"% and changed to %"+word.split("/")[0]+"%" );
                count++;
            }
            writer.close();
            System.out.println("Common Passwords has "+count+" entries in it" );
            System.out.println("The last word was %"+word+"% and changed to %"+word.split("/")[0]+"%" );
        } catch (FileNotFoundException ex) {
            System.out.println( "Error opening the common passwords file.");
            ex.printStackTrace(); // show the entire error message
        } catch (IOException ex) {
            System.out.println( "Error writing file.");
            ex.printStackTrace(); // show the entire error message
        }

    }
    

    public static void main (String [] args){
        // First set the look and feel to match the system executing the code.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Debug.println( "Error setting the GunGame look and feel.");
            ex.printStackTrace(); // show the entire error message
        }
        
        //Test the code to select a file.
        File file = selectFile();
        if(file !=null) {
            System.out.println("The file selected was "+ file.getAbsolutePath());
        } else {
            System.out.println("The user did not select a file");
        }
        
        //Test the code to open a file.
        file = openFile();
        if(file !=null) {
            System.out.println("The file selected to be opened was "+ file.getAbsolutePath());
        } else {
            System.out.println("The user did not select a file");
        }
       
        //Test the code to save a file.
        file = saveFile();
        if(file !=null) {
            System.out.println("The file selected to be saved was "+ file.getAbsolutePath());
            if (file.exists()){
                System.out.println("This file does exist. ");
            } else {
                System.out.println("This file does not exist. ");
            }
        } else {
            System.out.println("The user did not select a file");
        }
        
        //Test the code to obtain a directory
        Path  path = selectDirectory();
        if (Files.exists(path)) { // if path exists, output info about it
         // display directory  information -- some would work for files as well
      	System.out.printf("%n%s exists%n", path.getFileName());
      	System.out.printf("%s a directory%n", 
      		Files.isDirectory(path) ? "Is" : "Is not");
      	System.out.printf("%s an absolute path%n", 
      		path.isAbsolute() ? "Is" : "Is not");
        
        try {
                System.out.printf("Last modified: %s%n",
                        Files.getLastModifiedTime(path));
                System.out.printf("Size: %s%n", Files.size(path));
            } catch (IOException ex) {
                Debug.println( "IO Exception \n" + ex);
        }

      	System.out.printf("Path: %s%n", path);
      	System.out.printf("Absolute path: %s%n", path.toAbsolutePath());

         if (Files.isDirectory(path)){  // output directory listing
            System.out.printf("%nDirectory contents:%n");
            
            // object for iterating through a directory's contents
            DirectoryStream<Path> directoryStream;
            try {
                  directoryStream = Files.newDirectoryStream(path);
   
                  for (Path p : directoryStream)
                       System.out.println(p);
                } catch (IOException ex) {
                    Debug.println( "IO Exception \n" + ex);
           }
         } 
      } 
      else {// not a valid path
         System.out.printf("%s does not exist%n", path);
      }  
        
    }// End of main
    
}

