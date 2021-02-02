/**
* Author: Jordan Stafford
* Problem Statement: This program tests the isPowTwo function in the Power class
*/

package TestCases;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PowerTest {

	@Test
	public void test() {
		
		Power test = new Power() ;
		
		/**
		 * This function tests all powers of 2 that can fit into an integer value in Java
		 */
		for (int i = 1 ; i < 31 ; i++) {//This for loop excludes 2^0 and 2^31
			//See if function detects all powers of 2
			boolean isPow = test.isPowTwo((Math.pow(2, i))) ;
			assertEquals(true,isPow) ;
			//See if function will detect non-powers of 2
			isPow = test.isPowTwo((Math.pow(3, i))) ;
			assertEquals(false,isPow) ;
		}
	}

}
