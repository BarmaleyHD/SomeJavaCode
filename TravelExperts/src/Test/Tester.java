package Test;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Main.Customer;

public class Tester {
	final int NUMBER_OF_OBJ = 4;
	String expected = "Dima: 1";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		List<Customer> cusList = new ArrayList<Customer>();
		for (int i = 0; i<NUMBER_OF_OBJ; i++){
			Customer c = new Customer("Dima: " + i);
			cusList.add(c);
		}
		// Check object name 
		assertEquals(expected, cusList.get(1).getCusName());
		
		// Check if list is not empty
		assertFalse(cusList.isEmpty());				
		
		//Check if list has right size
		assertEquals(NUMBER_OF_OBJ, cusList.size());
	}

}
