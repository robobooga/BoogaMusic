package boogaMusic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class Engine {

	public static void main(String[] args) {

		Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask() {
	    	  @Override
	    	  public void run() {
	    		  try {
	    			  String line, processedLine="";
	    			  String [] strArr = new String [6];
    			    
	    			  
		    		  Process p = Runtime.getRuntime().exec((System.getenv("windir") +"\\system32\\"+"tasklist.exe /v /FO LIST"));
		    		  BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    		  while ((line = input.readLine()) != null) {
		    			  // if it's spotify.exe
		    			  if (line.startsWith("Image Name:") && line.substring(11).trim().equalsIgnoreCase("spotify.exe")) {
		    				  // skip 5 lines
		    				  for(int i=0;i<8;i++)
		    					  processedLine = input.readLine();

		    				  processedLine = processedLine.substring(13).trim();
		    				  if (!processedLine.equalsIgnoreCase("AngleHiddenWindow") && !processedLine.equalsIgnoreCase("OleMainThreadWndName"))
		    					  System.out.println(processedLine);
		    			  } // if spotify
		    		  } // while loop
		    		  input.close();
		  		} catch (Exception err) {
				    err.printStackTrace();
				}
	    	  } // run
	    	}, 500 , 20*1000);

	} // main

}
