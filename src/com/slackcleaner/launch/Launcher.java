/**
 * 
 */
package com.slackcleaner.launch;

/**
 * @author sleray
 *
 */
public class Launcher {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("########################");
		System.out.println("#  Slackcleaner v0.1   #");
		System.out.println("########################");
		Clean clean = new Clean();
		clean.processClean(0);

		
		
		
	}

}
