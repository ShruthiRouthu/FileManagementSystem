package filemanagementsystem;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shruthi Routhu
 */
public class FileService {
    
    private static final String VALIDATION_MSG = "Input argument cannot be null" ;
    
    private TextReader textReader;
    private TextWriter textWriter;

    // CONSTRUCTOR
    public FileService(final TextReader textReader, final TextWriter textWriter) throws IllegalArgumentException {
        setTextReader(textReader);
        setTextWriter(textWriter);
    }
    
    // METHODS
    public final List<Map<String,Object>> read(final File file,final FormatStrategy formatStrategy) throws IllegalArgumentException {
        
        if( !file.exists() || (formatStrategy == null)){
            throw new IllegalArgumentException(VALIDATION_MSG); 
        }
        return textReader.read(file, formatStrategy);
    }
    
    public final void write(final File file, FormatStrategy formatStrategy ,final List<Map<String,Object>> myData) throws IllegalArgumentException{
        
        if( !file.exists() || (formatStrategy == null) || (myData == null)){
            throw new IllegalArgumentException(VALIDATION_MSG); 
        }
        textWriter.write(file, formatStrategy, myData);
    }
    
    // SETTERS
    public final void setTextReader(final TextReader textReader) throws IllegalArgumentException {
        if(textReader != null){
            this.textReader = textReader;
        }
        else{
            throw new IllegalArgumentException(VALIDATION_MSG);
        }
    }

    public final void setTextWriter(final TextWriter textWriter) throws IllegalArgumentException {
        if(textWriter != null){
            this.textWriter = textWriter;
        }
        else{
            throw new IllegalArgumentException(VALIDATION_MSG);
        }
    }

    // GETTERS  
    public final TextReader getTextReader() {
        return textReader;
    }
    
    public final TextWriter getTextWriter() {
        return textWriter;
    }
   
}
