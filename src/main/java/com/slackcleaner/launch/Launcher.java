/**
 * 
 */
package com.slackcleaner.launch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
		// get context from properties file
		Context ctx = new Context();
		ctx.setToken(args[1]);
		printTitle(ctx.getVersion());

		if (METHOD_CLEAN.equals(args[0])) {	
			ctx.setChannel(args[2]);

			Clean clean = new Clean();
			clean.processClean(0, ctx);
		} else if (METHOD_CLIST.equals(args[0])) {
			Clist clist = new Clist();
			//build a list of channelIds
			List<String> listChannel = new ArrayList<>();
			listChannel = new LinkedList<String>(Arrays.asList(args)); 
			listChannel.remove(0); //remove method argument
			listChannel.remove(0); //remove token
			for (String channelId : listChannel) {
				clist.processClist(0, ctx, channelId,null);
			}

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
