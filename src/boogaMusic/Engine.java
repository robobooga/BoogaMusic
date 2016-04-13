package boogaMusic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

public class Engine {

	public static void main(String[] args) {

		Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask() {
	    	  @Override
	    	  public void run() {
	    		  extractSpotify();
	    	  }
	    	}, 500 , 20*1000);

	} // main
	public static void extractSpotify(){
		try {
			String line, processedLine="";
			//Process p = Runtime.getRuntime().exec("C:\\Windows\\SysWOW64\\chcp.com 65001");
			Process p = Runtime.getRuntime().exec((System.getenv("windir") +"\\system32\\"+"tasklist.exe /v /FO LIST"));
	  		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-16"));
	  		while ((line = input.readLine()) != null) {
	  			// if it's spotify.exe
	  			if (line.startsWith("Image Name:") && line.substring(11).trim().equalsIgnoreCase("spotify.exe")) {
	  				// skip 5 lines
	  				for(int i=0;i<8;i++)
	  					processedLine = input.readLine();
	
	  				processedLine = processedLine.substring(13).trim();
	  				System.out.println(processedLine);
	  				if (!processedLine.equalsIgnoreCase("AngleHiddenWindow") && !processedLine.equalsIgnoreCase("OleMainThreadWndName"))
	  					writeToFile(processedLine);
	  			} // if spotify
	  		} // while loop
	  		input.close();
	  		  
		} catch (Exception err) {
		    err.printStackTrace();
		}
	}
	
	public static void writeToFile(String line) {
		
		try {
			PrintWriter writer;
			
			writer = new PrintWriter("boogasnipsnip.txt", "UTF-16");
			writer.println(line);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
