

import static org.junit.Assert.assertTrue;
import models.BSTImp;
import models.StringComparator;

import org.junit.Before;
import org.junit.Test;

public class Test2 {
	
	private BSTImp<String,String> T ;


	@Before
	public void setUp() throws Exception {
		T = new BSTImp<String,String>(new StringComparator());

	}

	@Test
	public void test1() {
		this.T.add("Joe", "Joe");
		this.T.add("Joe", "Joe");
		this.T.add("Joe", "Joe");
		this.T.add("Joe", "Joe");
		System.out.println("Tree: ");
		this.T.print(System.out);
		assertTrue(this.T.get("Joe") != null);
		assertTrue(this.T.size() == 4);
	}

	@Test
	public void test2() {
		this.T.add("Moe", "Moe");
		this.T.add("Dal", "Dal");
		this.T.add("Cal", "Cal");
		this.T.add("Bob", "Bob");
		this.T.add("Amy", "Amy");
		this.T.add("Al", "Al");
		System.out.println("Tree: ");
		this.T.print(System.out);
		assertTrue(this.T.get("Bob") != null);
		assertTrue(this.T.size() == 6);
	}

	@Test
	public void test3() {
		
	}
}
