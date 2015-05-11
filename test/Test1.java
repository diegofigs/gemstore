

import static org.junit.Assert.assertTrue;
import models.BSTImp;
import models.StringComparator;

import org.junit.Before;
import org.junit.Test;

public class Test1 {
	private BSTImp<String,String> T ;

	@Before
	public void setUp() throws Exception {
		T = new BSTImp<String,String>(new StringComparator());
	}

	@Test
	public void testAdd() {
		this.T.add("Joe", "Joe");
		this.T.add("Mel", "Mel");
		this.T.add("Al", "Al");
		System.out.println("Tree: ");
		this.T.print(System.out);
		assertTrue(this.T.get("Joe") != null);
		assertTrue(this.T.size() == 3);
	}
	
	@Test
	public void testAdd2(){
		this.T.add("Joe", "Joe");
		this.T.add("Mel", "Mel");
		this.T.add("Al", "Al");
		this.T.add("Joe", "Joe");
		
		System.out.println("Tree: ");
		this.T.print(System.out);
		assertTrue(this.T.get("Joe") != null);
		assertTrue(this.T.size() == 4);
	}

	@Test
	public void testAdd3() {
		assertTrue(this.T.get("Lu") == null);
	}
	
	@Test
	public void testAdd4() {
		this.T.add("Joe", "Joe");
		this.T.add("Mel", "Mel");
		this.T.add("Al", "Al");
		this.T.add("Joe", "Joe");
		
		assertTrue(this.T.get("Al").equals("Al"));
	}
}
