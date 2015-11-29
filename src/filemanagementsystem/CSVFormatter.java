package filemanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shruthi Routhu
 */
public class CSVFormatter implements FormatStrategy {
    
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private static final String STREET_ADDRESS = "StreetAddress";
    private static final String CITY = "City";
    private static final String STATE = "State";
    private static final String ZIP_CODE = "ZipCode";
    private static final String VALIDATION_MSG = "Input to method is invalid!" ;
    
    @Override
    public final List<String> encode(final List<Map<String, Object>> myFormatData)  throws IllegalArgumentException {
        if( myFormatData== null || myFormatData.isEmpty()) {
            throw new IllegalArgumentException(VALIDATION_MSG);
        }
        
        List<String> writeData = new ArrayList<>();
        int no = myFormatData.size();
        Map<String, Object> tempMap ;
        StringBuilder line;
        
//        line = new StringBuilder();
//        line.append(FIRST_NAME + ",");
//        line.append(LAST_NAME + ",");
//        line.append(STREET_ADDRESS + ",");
//        line.append(CITY + ",");
//        line.append(STATE + ",");
//        line.append(ZIP_CODE);
        
        for(int i=0; i<no; i++){
                        
            tempMap = myFormatData.get(i);
            line = new StringBuilder();
                       
            line.append(tempMap.get(FIRST_NAME));
            line.append(",");
          
            line.append(tempMap.get(LAST_NAME));
            line.append(",");
          
            line.append(tempMap.get(STREET_ADDRESS));
            line.append(",");
          
            line.append(tempMap.get(CITY));
            line.append(",");
                       
            line.append(tempMap.get(STATE));
            line.append(",");
           
            line.append(tempMap.get(ZIP_CODE));
            
            writeData.add(line.toString());
           
           // System.out.println(writeData.toString());
        }
     
        return writeData;
    }

    @Override
    public final List<Map<String, Object>> decode(final List<String> rawData) throws IllegalArgumentException{
        if( rawData== null || rawData.size() == 0) {
            throw new IllegalArgumentException(VALIDATION_MSG);
        }
       
        List<Map<String, Object>> decodedData = new ArrayList<>();
        
        // Removing first line from rawData
        rawData.remove(0);       
                  
        // Logic to get value from each line and  to store them in a map
        
        String[] lineParts ;
        Map<String,Object> map ;
        
        
        for(String personLine : rawData){
           
            map = new HashMap<>();
            personLine = personLine.trim();
            
            if(!personLine.isEmpty()){
                lineParts = personLine.split(",");
                
                if(lineParts.length == 6){
                    map.put(FIRST_NAME, lineParts[0]);
                    map.put(LAST_NAME, lineParts[1]);
                    map.put(STREET_ADDRESS, lineParts[2]);
                    map.put(CITY, lineParts[3]);
                    map.put(STATE, lineParts[4]);
                    map.put(ZIP_CODE, lineParts[5]);
                }       
                decodedData.add(map);
            }
        }
      
        return decodedData;
    }
    
    
}
