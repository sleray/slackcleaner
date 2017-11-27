/**
 * 
 */
package slackCleaner;

import static org.junit.Assert.*;

import org.junit.Test;

import com.slackcleaner.launch.Launcher;

/**
 * @author sleray
 *
 */
public class LauncherTest {

	/**
	 * Test method for {@link com.slackcleaner.launch.Launcher#printTitle()}.
	 */
	@Test
	public void testPrintTitle() {
		Launcher.printTitle("JUnit testing");
		//useless JUnit Test but i needed it to test title formatting :)
		assertTrue(true);
	}

}
