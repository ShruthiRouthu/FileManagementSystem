package filemanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shruthi Routhu
 */
public class JsonFormatter implements FormatStrategy {
    
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private static final String STREET_ADDRESS = "StreetAddress";
    private static final String CITY = "City";
    private static final String STATE = "State";
    private static final String ZIP_CODE = "ZipCode";
    
    private static final String VALIDATION_MSG = "Input to method is invalid!" ;

    @Override
    public final List<String> encode(final List<Map<String, Object>> myFormatData) throws IllegalArgumentException{
        
        if( myFormatData== null || myFormatData.size() == 0) {
            throw new IllegalArgumentException(VALIDATION_MSG);
        }
        
        List<String> writeData = new ArrayList<String>();
        int no = myFormatData.size();
        Map<String, Object> tempMap ;
        StringBuilder line;
        
        writeData.add("[");
        
        for(int i=0; i<no; i++){
                        
            tempMap = myFormatData.get(i);
            line = new StringBuilder();
            
            writeData.add("{");
            
            line.append("\"");
            line.append(FIRST_NAME);
            line.append("\":");
            line.append(tempMap.get(FIRST_NAME));
            line.append(",");
            writeData.add(line.toString());
            
            line = new StringBuilder();
            line.append("\"");
            line.append(LAST_NAME);
            line.append("\":");
            line.append(tempMap.get(LAST_NAME));
            line.append(",");
            writeData.add(line.toString());
           
            line = new StringBuilder();
            line.append("\"");
            line.append(STREET_ADDRESS);
            line.append("\":");        
            line.append(tempMap.get(STREET_ADDRESS));
            line.append(",");
            writeData.add(line.toString());
            
            line = new StringBuilder();
            line.append("\"");
            line.append(CITY);
            line.append("\":");           
            line.append(tempMap.get(CITY));
            line.append(",");
            writeData.add(line.toString());
            
            line = new StringBuilder();
            line.append("\"");
            line.append(STATE);
            line.append("\":");           
            line.append(tempMap.get(STATE));
            line.append(",");
            writeData.add(line.toString());
            
            line = new StringBuilder();
            line.append("\"");
            line.append(ZIP_CODE);
            line.append("\":");
            line.append(tempMap.get(ZIP_CODE));
            writeData.add(line.toString());
            
            
            if(i == (no-1)) {
                writeData.add("}");
            }else{
                writeData.add("},");
            }
            System.out.println(writeData.toString());
        }
        
        writeData.add("]");
        
        return writeData;
    }

    @Override
    public final List<Map<String, Object>> decode(final List<String> rawData) throws IllegalArgumentException {
        if( rawData== null || rawData.size() == 0) {
            throw new IllegalArgumentException(VALIDATION_MSG);
        }
            
        List<Map<String, Object>> decodedData = new ArrayList<Map<String, Object>>();
        
        // Removing first and last lines i.e [,] from rawData
        rawData.remove(0);
        rawData.remove(rawData.size()-1);
        
             
        // Calculating number of personsLOL in rawData
        int no = rawData.size()/8;
        
        // 2-D ArrayList in which lines for each personLines are put into a seperate array
        List<List<String>> personsLOL = new ArrayList<List<String>>(); 
        
        
        // Logic to sepaerate lines for each personLines into a seperate array
        for(int i=0; i< no; i++){ 
            List<String> tempList = new ArrayList<String>();
            //tempList.clear();
            int readPosition = (i)*8 + 1;
            int endPosition = readPosition+5;
           
            for(int j = readPosition; j <= endPosition; j++){
                tempList.add(rawData.get(j));
            }

            personsLOL.add(tempList);

        }

        // Logic to get value from each line and  to store them in a map
        String line;
        String[] lineParts ;
        String value;
        Map<String,Object> map ;
        List<String> values;
        
        for(List<String> personLines : personsLOL){
            
            map = new HashMap<String,Object>();
            values = new ArrayList<String>();
           
            int size = personLines.size();
            
            for(int i=0; i < size; i++ ){
                line = personLines.get(i);
                lineParts = line.split(":");
               
                value = lineParts[lineParts.length-1];
                value = value.replace('/' , ' ');
                value = value.replace(',', ' ');
                
                values.add(value);
              
            }
                        
           
            map.put(FIRST_NAME, values.get(0));
            map.put(LAST_NAME, values.get(1));
            map.put(STREET_ADDRESS, values.get(2));
            map.put(CITY, values.get(3));
            map.put(STATE, values.get(4));
            map.put(ZIP_CODE, values.get(5));
                   
            decodedData.add(map);
    
        }
      
        return decodedData;
    }
  
}
