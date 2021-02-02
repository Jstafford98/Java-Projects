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
		 * This function tests all powers of 2 that can fit into the max integer value in Java (2147483647)
		 */
		for (int i = 1 ; i < 31 ; i++) {//This for loop excludes 2 to the powers of 0 and 31
			boolean isPow = test.isPowTwo((Math.pow(2, i))) ;
			assertEquals(true,isPow) ;
		}
	}

}
