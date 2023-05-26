/*Authors : Yulia Moshan 319565610
 * 			Gil Pasi 	 206500936
 * Algorithm planning - assignment 3 
 * */
package driver;

import java.io.IOException;
import java.util.ArrayList;

import utilities.ATM;
import utilities.Graph;
import utilities.Journey;
import utilities.Route;
import excel.io.ReadExcel;
import jxl.read.biff.BiffException;


public class Runner {

    public static void main(String[] args) {
    	ReadExcel reader = new ReadExcel();
    	reader.setInputFile("atm.xls");
    	

    	try {
    		
    		//=================Extracting data =========================
    		final int DATA_TYPES_COUNT = 3;
    		final int RECORDS_COUNT = reader.getSheetRows();

    		final int HOUR_SIZE = 60;
    		Object [][] data = new Object [RECORDS_COUNT][DATA_TYPES_COUNT];//String and integers array
    		
    		reader.extractEssentials(data);
    		
    		ArrayList <ATM> unvistedAtms = new ArrayList<>();
    		for(int i = 1 ; i < data.length ; i++) {
    			Object [] curAtmData = data[i];
    			
    			//Ignore invalid data
    			if(curAtmData[0]=="" ||curAtmData[1]=="" ||curAtmData[2]=="")continue;
    			
    			String city = (String)curAtmData[0];
    			double x = Double.parseDouble((String)curAtmData[1]);
    			double y = Double.parseDouble((String)curAtmData[2]);
    		}
    		
    		// ====================Algorithm============================
    		
    		
    		ATM a1 =  new ATM("באר שבע " , 35 ,34);
    		ATM a2 =  new ATM("באר שבע " , 35 ,34);
    		ATM a3 =  new ATM("באר שבע " , 35 ,34);
    		ATM a4 =  new ATM("באר שבע " , 35 ,34);
    		ATM center = ATM.GEOGRAFIC_CENTER;
    		
    		Graph g = new Graph();
    		g.addATM(a1);
    		g.addATM(a2);
    		g.addATM(a3);
    		g.addATM(a4);
    		g.addATM(center);
    		
    		new Route (a1 , a2);
    		new Route (a1 , a3);
    		new Route (a1 , a4);
    		new Route (a1 , center);
    		new Route (a2 , a3);
    		new Route (a2 , a4);
    		new Route (a2 , center);
    		new Route (a3 , a4);
    		new Route (a3 , center);
    		new Route (a4 , center);
    		System.out.println(g);
    		
    		Graph g2 = new Graph();
    		g2.graphName = "second";
    		g2.addATM(a1.createIsolatedATM());
    		g2.addATM(a2.createIsolatedATM());
    		g2.addATM(a3.createIsolatedATM());
    		g2.addATM(a4.createIsolatedATM());
    		g2.addATM(center.createIsolatedATM());
    		
    		g2.addRoute(1, 2);
    		g2.addRoute(1, 3);
    		g2.addRoute(3, 4);
    		g2.addRoute(4, 5);

    		System.out.println(g2);

    		System.out.println(g2.isDegenerated());

    		
    		
//    		System.out.println(g.isDegenerated());
    		
    		


//
//    		ArrayList<Journey>journeys = Journey.allPossibleJourneys();
//    		
//    		System.out.println(journeys);
//    		System.out.println(journeys.size());
//    		
//    		
//    		
//    		for (int i = 0 ; i < journeys.size() ; i++) {
//    			if (journeys.get(i).isDegenerated())
//    				System.out.println(journeys.get(i));
//    		}


		} catch (IOException | IndexOutOfBoundsException | BiffException e) {
			e.printStackTrace();
		}
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

    

    
    public static ATM removeByAtm (ArrayList<ATM> atms , ATM value) {
    	for(int i = 0 ; i < atms.size() ; i++)
    		if(atms.get(i)==value)
    			return atms.remove(i);
    	
    	return null;
    }
}
