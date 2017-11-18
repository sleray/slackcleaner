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

		
		// get context from properties file
		Context ctx = new Context();
		ctx.setToken(args[0]);
		ctx.setChannel(args[1]);


		
		Clean clean = new Clean();
		clean.processClean(0,ctx);


		
		
		
	}

}
