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
		Context ctx = new Context();
		printTitle(ctx.getVersion());

		if (METHOD_CLEAN.equals(args[0])) {
			// get context from properties file
			
			ctx.setToken(args[1]);
			ctx.setChannel(args[2]);

			Clean clean = new Clean();
			clean.processClean(0, ctx);
		} else if (METHOD_CLIST.equals(args[0])) {
			// TODO Clist feature

		} else {
			System.out.println("Run with those arguments : ");
			System.out.println("Launcher clean token channelId");
			System.out.println("or : ");
			System.out.println("Launcher clist token [channelId1, channelId2,...]");
		}

	}

	public static void printTitle(String version) {
		System.out.println("    _____ _            _     _____ _                ");            
		System.out.println("   / ____| |          | |   / ____| |          ");                 
		System.out.println("  | (___ | | __ _  ___| | _| |    | | ___  __ _ _ __   ___ _ __"); 
		System.out.println("   \\___ \\| |/ _` |/ __| |/ / |    | |/ _ \\/ _` | '_ \\ / _ \\ '__|");
		System.out.println("   ____) | | (_| | (__|   <| |____| |  __/ (_| | | | |  __/ |");   
		System.out.println("  |_____/|_|\\__,_|\\___|_|\\_\\\\_____|_|\\___|\\__,_|_| |_|\\___|_| ... "+version); 
		
	}

}
