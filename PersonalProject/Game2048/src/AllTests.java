/**
 * Author: Jie Zhang - zhanj265
 * Revised: 13th April, 2021
 * 
 * Description: Testing all of the modules
 */

package src;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   TestTileT.class,	
   TestBoardT.class
})

public class AllTests
{
}
