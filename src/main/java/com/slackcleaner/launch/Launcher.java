/**
 * 
 */
package com.slackcleaner.launch;

/**
 * @author sleray
 *
 */
public class Launcher {

	public static String METHOD_CLEAN = "clean";
	public static String METHOD_CLIST = "clist";
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("########################");
		System.out.println("#  Slackcleaner v0.2   #");
		System.out.println("########################");


		
		if (METHOD_CLEAN.equals(args[0])) {
		// get context from properties file
		Context ctx = new Context();		
		ctx.setToken(args[1]);
		ctx.setChannel(args[2]);


		
		Clean clean = new Clean();
		clean.processClean(0,ctx);
		} else if (METHOD_CLIST.equals(args[0])) {
			//TODO Clist feature
			
		} else {
			System.out.println("Run with those arguments : ");
			System.out.println("Launcher clean token channelId");
			System.out.println("or : ");
			System.out.println("Launcher clist token [channelId1, channelId2,...]");
		}


		
		
		
	}

}
