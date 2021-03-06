package filemanagementsystem;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shruthi Routhu
 */
public class TextWriter {
    
     private static final String VALIDATION_MSG = "Input argument cannot be null" ; 
     
     public final void write(final File file, FormatStrategy formatStrategy ,final List<Map<String,Object>> myFormatData) throws IllegalArgumentException {
         
        if( !file.exists() || (formatStrategy == null) || (myFormatData == null)){
            throw new IllegalArgumentException(VALIDATION_MSG); 
        } 
        
        // send  data to formatStrategy.encode() to convert data to  desired format
        List<String> writeData = formatStrategy.encode(myFormatData);
         
        //write this data to file
        PrintWriter writer = null ;
            try{
                writer = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
                for(String line : writeData){
                    writer.println(line);
                }
              
            }catch(IOException ioe){
                System.out.println(ioe.getMessage());
            }
            finally{
                writer.close();
            }
        
    }
    
}
