/*Authors : Yulia Moshan 319565610
 * 			Gil Pasi 	 206500936
 * Algorithm planning - assignment 3 
 * */
package driver;

import java.io.IOException;
import java.util.ArrayList;
import excel.io.ReadExcel;
import jxl.read.biff.BiffException;
import utilities.Coordinate;
import utilities.Map;
import utilities.Path;


public class Runner {
    public static void main(String[] args) {
    	ReadExcel reader = new ReadExcel();
    	reader.setInputFile("atm.xls");
    	
    	final int MACHINES_COUNT = 6; // <= data.length()
    	Map map = new Map(MACHINES_COUNT);
    	map.setCoordinate(Coordinate.GEOGRAFIC_CENTER);
    	


    	try {
    		
    		//=================Extracting data =========================
    		final int DATA_TYPES_COUNT = 3;
    		final int RECORDS_COUNT = reader.getSheetRows();

    		Object [][] data = new Object [RECORDS_COUNT][DATA_TYPES_COUNT];//String and integers array
    		
    		reader.extractEssentials(data);
    		
    		int coordinateIndex = 1; //Skip the geographic center
    		for(int i = 1 ; coordinateIndex < MACHINES_COUNT ; i++) {//i = 1 skip the headers
    			Object [] curAtmData = data[i];
    			
    			//Ignore invalid data
    			if(curAtmData[0]=="" ||curAtmData[1]=="" ||curAtmData[2]=="")continue;
    			
    			String city = (String)curAtmData[0];
    			double x = Double.parseDouble((String)curAtmData[1]);
    			double y = Double.parseDouble((String)curAtmData[2]);
    			
    			map.setCoordinate(coordinateIndex++, x,y , city);


  
    			
    		}
    		
    		// ====================Algorithm============================

    		final int HOUR_SIZE = 60;

    		Map bestJourney = findBestJourney(map);
    		
    		System.out.println("lightest journey is:\n" + bestJourney  );
    		double time = bestJourney.totalTime() / HOUR_SIZE;
    		
            System.out.println("Minimum time: "+String.format("%.2f",time) + " hours");

    		


    		


		} catch (IOException | IndexOutOfBoundsException | BiffException e) {
			e.printStackTrace();
		}
    }
    
    

    //====================== Algorithm high view ============================
    public static Map findBestJourney(Map m) {
    	ArrayList<String> permutations = (ArrayList<String>) m.findPermutations();
    	double minWeight = Double.MAX_VALUE;
    	Map minGraph = null;
    	for(String currentPermutation : permutations) {
    		Map currentMap = m.stringToMap(currentPermutation);
    		
    		if(currentMap.totalWeight() < minWeight && currentMap.isDegenerated()) {
    			minGraph = currentMap;
    			minWeight = currentMap.totalWeight();
    		}
    	}
    	return minGraph;
    }
    
    public void injectRefilling (Map map) {
    	/**This function gets a  completed journey and injects refilling stops every 100 
    	 * machines  (according to the demands).It does so by adding a bridge vertex in the 
    	 * as described in the next scheme:
    	 * 		
    	 * 		v(i) -> v(i+1)   ==>   v(i) -> GEOGRAFPHIC_CENTER -> v(i+1)
    	 * This ensures the requirement and also the algorithms correctness , thanks 
    	 * to the invoking method "findBestJourney".
    	 * */
    	
    	final int INC_FACTOR = 100 ;
    	int nextInject = INC_FACTOR ;
    	int passedMachines = 0;
    	
    	//No need for injection
    	if(map.getPathCount() < INC_FACTOR)
    		return;
    	
    	
		final int FIRST = 0 ;
		
		ArrayList<ArrayList<Path>> leftpaths = map.getPaths();
		
		Path curPath = null;
		int curMachine = 0;

		//Find the first vertex
		for(int i = 0 ; i < leftpaths.size() ; i++) 
			if(!leftpaths.get(i).isEmpty()) {
				curPath = leftpaths.get(i).remove(FIRST);
				curMachine = i;
				break;
			}
    	
    	
    	
    	
    	while (nextInject < map.getPathCount()) {
    		
			passedMachines ++;
			if(passedMachines == nextInject) {
				nextInject += INC_FACTOR;
				
				int previousMachine =  Map.inferPrevious(curPath, curMachine);
				map.getPaths().get(previousMachine);
				map.addPath(Map.inferPrevious(curPath, curMachine), Map.inferNext(curPath, curMachine));
			}
			
			curMachine = Map.inferNext(curPath, curMachine);
			
			Map.removePath(curPath.getU(), curPath.getV(), leftpaths.get(curMachine));
			
			if(leftpaths.get(curMachine).isEmpty())
				break;
			
			else {
				curPath = leftpaths.get(curMachine).remove(FIRST);
			}
		
    	}
    	
    	
    	

		
		//Scan from a vertex to his neighbors
		

    	
    	
    }
    //=====================================================================
}