package mtechproject.dbfrontend;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class RecursivePerformanceComparator {

	
	static int counter = 0;
    public void walk( String path ) {
    	KeyGenerator kgen = null;
		try {
			kgen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		kgen.init(128);
		SecretKey key           =   kgen.generateKey();

		// Create encrypter/decrypter class
		BrowseListener encrypter = new BrowseListener(key);


		BrowseListener s = new BrowseListener();
		

    	Connection con = null;
    	String St = null;
    	try {
			con = DBConnectFlogger.connect();
			//con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	long start, start1, start2, start3;
    	double elapsedTimeInSec,elapsedTimeInSec2,totaltime,elapsedTimeInSec3,elapsedTimeInSec4,totaltime2;
        File chosenfile = new File( path );
        File[] list = chosenfile.listFiles();
        for ( File f : list ) {
        	//Path file_dir = Paths.get(f.getParent());
    		//Path file = file_dir.resolve(f.getName());
    		//UserPrincipalLookupService lookupService = FileSystems.getDefault().getUserPrincipalLookupService();
    		
    		if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
               
               // System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
            	
                //System.out.println( "File:" + f.getAbsoluteFile() );
            	start = System.nanoTime(); // requires java 1.5
            	// Segment to monitor
            	 try {
					new FileEncrypter("DES/ECB/PKCS5Padding",f.getAbsolutePath()).encrypt();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	 elapsedTimeInSec = (System.nanoTime() - start) * 1.0e-9;
            	 start1 = System.nanoTime();
                 try {
					new FileEncrypter("DES/ECB/PKCS5Padding",f.getAbsolutePath()+".enc").decrypt();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 elapsedTimeInSec2 = (System.nanoTime() - start1) * 1.0e-9;
               
                 
                 start2 = System.nanoTime(); 
                // File newfile = new File(file);
         		try {
         			encrypter.encrypt(new FileInputStream(f.getAbsolutePath()),
         					new  FileOutputStream("E:/AES/"+f.getName()+"enc"));
         		} catch (FileNotFoundException e) {
         			// TODO Auto-generated catch block
         			e.printStackTrace();
         		}
         		elapsedTimeInSec3 = (System.nanoTime() - start2) * 1.0e-9;
         		
         		start3 = System.nanoTime();
         		// Decrypt
         		elapsedTimeInSec = (System.nanoTime() - start) * 1.0e-9;
         		start1 = System.nanoTime(); // requires java 1.5
         		try {
         			encrypter.decrypt
         			(new FileInputStream("E:/AES/"+f.getName()+"enc"),
         					new FileOutputStream("E:/DECAES/"+f.getName()));
         		} catch (FileNotFoundException e) {
         			// TODO Auto-generated catch block
         			e.printStackTrace();
         		}
         		elapsedTimeInSec4 = (System.nanoTime() - start2) * 1.0e-9;
                 
                 System.out.println("Encryption Time:"+elapsedTimeInSec);
                 System.out.println("Decryption Time:"+elapsedTimeInSec2);
                // File f1 = new File("c:\\sample.txt");
                 start = System.nanoTime();
                // Desktop.getDesktop().open(new File("c:\\sample.txt"));
                 boolean exists = f.exists();
                 
                 elapsedTimeInSec = (System.nanoTime() - start) * 1.0e-9;
                 totaltime = elapsedTimeInSec2 + elapsedTimeInSec;
                 totaltime2 = elapsedTimeInSec + elapsedTimeInSec4;
                 System.out.println("Decryption + Access Time : "+totaltime);
                 System.out.println("FileAccessTime Time:"+elapsedTimeInSec);
                 St = "Insert into performance_metrics values("+String.valueOf(totaltime)+","+String.valueOf(elapsedTimeInSec)+","+String.valueOf(totaltime2)+")";
                 try {
					con.createStatement().executeUpdate(St);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }

    public static void main(String[] args) {
        RecursivePerformanceComparator fw = new RecursivePerformanceComparator();
        fw.walk("E:\\DVFS - Copy" );
    }
}
