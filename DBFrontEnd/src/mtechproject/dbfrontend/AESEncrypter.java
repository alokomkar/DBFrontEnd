package mtechproject.dbfrontend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESEncrypter {
	
	public static void callmain(String file){
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
		
		long start, start1;
    	double elapsedTimeInSec,elapsedTimeInSec2,totaltime;
        
		s.start(); // timer starts
		start = System.nanoTime(); // requires java 1.5
		File newfile = new File(file);
		try {
			encrypter.encrypt(new FileInputStream(newfile.getAbsolutePath()),
					new  FileOutputStream("E:/AES/"+newfile.getName()+"enc"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Decrypt
		elapsedTimeInSec = (System.nanoTime() - start) * 1.0e-9;
		start1 = System.nanoTime(); // requires java 1.5
		try {
			encrypter.decrypt
			(new FileInputStream("E:/AES/"+newfile.getName()+"enc"),
					new FileOutputStream("E:/DECAES/"+newfile.getName()+"enc"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 elapsedTimeInSec2 = (System.nanoTime() - start) * 1.0e-9;
		 
		s.stop(); // timer stops
		System.out.println("Encryption Time:"+elapsedTimeInSec);
		System.out.println("Decryption Time:"+elapsedTimeInSec2);
	}

}
