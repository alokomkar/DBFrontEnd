package mtechproject.samples;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class ReadExcel{
	public static void main(String[]args){
		short a=0;  
		short b=1;  
		short c=2;
		int i=0;   
		ArrayList list1=new ArrayList();
		ArrayList list2=new ArrayList();
		ArrayList list3=new ArrayList();
		String   value1="", value2="",value3="";  
		String filename ="E:/Flogger 2012-13.xls";     
		if(filename != null && !filename.equals("")){
			try{    
				FileInputStream fs =new FileInputStream(filename);    
				HSSFWorkbook wb = new HSSFWorkbook(fs);   
				for(int k = 0; k < wb.getNumberOfSheets(); k++){    
					int j=i+1;    
					HSSFSheet sheet = wb.getSheetAt(k);    
					int rows  = sheet.getPhysicalNumberOfRows();    
					for(int r =     1; r < rows; r++){    
						HSSFRow row   = sheet.getRow(r);    
						int cells = row.getPhysicalNumberOfCells();    
						HSSFCell cell1  = row.getCell(a);      
						value1 = cell1.getStringCellValue();   
						HSSFCell cell2  = row.getCell(b);     
						value2 = cell2.getStringCellValue();    
						HSSFCell cell3  = row.getCell(c);     
						value3 = cell3.getStringCellValue();    

						list1.add(value1);
						list2.add(value2);
						list3.add(value3);
					}       
					i++;   
				}    
			}catch(Exception e){  

			}

		}
		final String DB_URL = "jdbc:mysql://localhost:3306/floggerdb";
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, "root", "phoenix6832");
			stmt = conn.createStatement();
			
			
			String sql = null;
			for(int j=0;j<list1.size();j++){
				//System.out.println(list1.get(j).toString()+"\t"+list2.get(j).toString());
				sql = "Insert into flogger_2012 values('"+list1.get(j).toString()+"','"+list2.get(j).toString()+"','"+list3.get(j).toString()+"')";
				stmt.executeUpdate(sql);
				
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}
}