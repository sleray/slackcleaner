/**
 * 
 */
package slackCleaner;


import org.junit.Assert;
import org.junit.Test;

import com.slackcleaner.launch.Context;



/**
 * @author sleray
 *
 */
public class ContextTest {

	@Test
	public void testContext() {
		Context ctx = new Context();
		//test if the Ctx is not null (obvious)
		Assert.assertNotNull(ctx);
		//test to get at least one propertie from the context
		Assert.assertNotNull(ctx.getApiDelete());
	}

}
