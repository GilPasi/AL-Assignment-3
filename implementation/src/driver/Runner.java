/*Authors : Yulia Moshan 319565610
 * 			Gil Pasi 	 206500936
 * Algorithm planning - assignment 3 
 * */
package driver;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import utilities.ATM;
import excel.io.ReadExcel;
import jxl.read.biff.BiffException;


public class Runner {

    public static void main(String[] args) {
    	ReadExcel reader = new ReadExcel();
    	reader.setInputFile("atm.xls");
    	

    	try {
    		final int DATA_TYPES_COUNT = 3;
    		final int RECORDS_COUNT = reader.getSheetRows();

    		final int HOUR_SIZE = 60;
    		Object [][] data = new Object [RECORDS_COUNT][DATA_TYPES_COUNT];
    		int journeyTime = 0;//In minutes
    		
    		reader.extractEssentials(data);
    		
    		ArrayList <ATM> unvistedAtms = new ArrayList<>();
    		for(int i = 1 ; i < data.length ; i++) {
    			Object [] curAtmData = data[i];
    			
    			//Ignore invalid data
    			if(curAtmData[0]=="" ||curAtmData[1]=="" ||curAtmData[2]=="")continue;
    			
    			String city = (String)curAtmData[0];
    			double x = Double.parseDouble((String)curAtmData[1]);
    			double y = Double.parseDouble((String)curAtmData[2]);
    			unvistedAtms.add(new ATM(city , x , y));
    		}

    		
    		String route = "";
    		ATM cur = unvistedAtms.remove(0);
    		
    		while(!unvistedAtms.isEmpty()) {
    			ATM nn = nearestNeighbor(unvistedAtms , cur);
    			route += "-" + nn.id;
    			cur = removeByAtm(unvistedAtms, nn);
    			
    		}
    		
    	printLongString(route);
    	System.out.println("the route ^^^");
    	System.out.println("Journey time : " + journeyTime / HOUR_SIZE + " hours");



    		

    		
    		
		} catch (IOException | IndexOutOfBoundsException | BiffException e) {
			e.printStackTrace();
		}
    }
    
    public double getTime (ATM src , ATM dst) {
		final int  EXTRA_SPEED = 70 , INTRA_SPEED  = 30 ,//kph
		FILL_TIME = 5,CITIES_TRANSFER_TIME = 6 , REFILL_TIME = 120;//mins
    	
    	if(src.city.equals(dst.city))
    		return src.distance(dst) / INTRA_SPEED + FILL_TIME;
    	else {
    		return src.distance(dst) / EXTRA_SPEED + CITIES_TRANSFER_TIME + FILL_TIME;
    	}
    				
    	
    	return 0;
    }
    
    public static void printLongString  (String str) {
    	
		int strLen = str.length();
		final int ROW_SIZE = 100;
		int base = -ROW_SIZE , limit = 0;
		
		while(limit < strLen) {
			base += ROW_SIZE;

			if(limit + ROW_SIZE  > strLen)
				limit += strLen % ROW_SIZE;
			else
				limit += ROW_SIZE;

			System.out.println(str.substring(base , limit - 1));
			
			
			
			System.out.println();
		}
    	
    	
    }
    
    public static ATM nearestNeighbor (ArrayList<ATM> unvisitedNegibors , ATM src ) {
    	ATM nn = unvisitedNegibors.get(0);//Nearest neighbor
    	double nd = src.distance(nn);//Nearest distance
    	for(int i = 1 ; i < unvisitedNegibors.size(); i++) {
    		ATM cur = unvisitedNegibors.get(i);
    		//Update
    		if(src.distance(cur) < nd) {
    			nn = cur;
    			nd = src.distance(cur);
    		}
    	}
    	return nn;

    }
    
    public static ATM removeByAtm (ArrayList<ATM> atms , ATM value) {
    	for(int i = 0 ; i < atms.size() ; i++)
    		if(atms.get(i)==value)
    			return atms.remove(i);
    	
    	return null;
    }
}
