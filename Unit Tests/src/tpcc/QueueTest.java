package tpcc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class QueueTest {
	
	private Queue uut;

	@Before
	public void setUpBefore() throws Exception {
		uut = new Queue();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testEnqueue_Invalid_IT_Lower() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 < InstructionType < 100");
		
		uut.enqueue(new InstructionMessage(-1, 1, 1, 0, 1));
	}

	@Test
	public void testEnqueue_Invalid_IT_Upper() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 < InstructionType < 100");
		
		uut.enqueue(new InstructionMessage(100, 1, 1, 0, 1));
	}

	@Test
	public void testEnqueue_Invalid_PC() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 < ProductCode");
		
		uut.enqueue(new InstructionMessage(1, -1, 1, 0, 1));
	}

	@Test
	public void testEnqueue_Invalid_Q() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 < Quantity");
		
		uut.enqueue(new InstructionMessage(1, 1, -1, 0, 1));
	}

	@Test
	public void testEnqueue_Invalid_U_Lower() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 <= UOM < 256");
		
		uut.enqueue(new InstructionMessage(1, 1, 1, -1, 1));
	}

	@Test
	public void testEnqueue_Invalid_U_Upper() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 <= UOM < 256");
		
		uut.enqueue(new InstructionMessage(1, 1, 1, 256, 1));
	}

	@Test
	public void testEnqueue_Invalid_T() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("0 < TimeStamp");
		
		uut.enqueue(new InstructionMessage(1, 1, 1, 0, -1));
	}

	@Test
	public void testEnqueue_Valid() {
		uut.enqueue(new InstructionMessage(1, 1, 1, 0, 1));
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(1, uut.count());
	}

	@Test
	public void testDequeue_Basic() {
		final InstructionMessage msg0 = new InstructionMessage(1, 1, 1, 0, 1);
		uut.enqueue(msg0);
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(1, uut.count());
		
		InstructionMessage msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(msg0, msg);
		
		assertTrue("Should be empty", uut.isEmpty());
		assertEquals(0, uut.count());
	}

	@Test
	public void testPeek_Basic() {
		final InstructionMessage msg0 = new InstructionMessage(1, 1, 1, 0, 1);
		uut.enqueue(msg0);
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(1, uut.count());
		
		InstructionMessage msg = uut.dequeue();
		assertNotNull(msg);
		assertEquals(msg0, msg);
		
		assertFalse("Should not be empty", uut.isEmpty());
		assertEquals(1, uut.count());
	}

	@Test
	public void testCount_Basic() {
		assertEquals(0, uut.count());
	}

	@Test
	public void testIsEmpty_Basic() {
		assertTrue("Should be empty", uut.isEmpty());
	}

}