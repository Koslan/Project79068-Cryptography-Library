package p79068.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import p79068.math.ArithmeticOverflowException;


public final class DateTest {
	
	@Test
	public void testIsLeapYear() {
		assertFalse(Date.isLeapYear(1998));
		assertFalse(Date.isLeapYear(2001));
		assertFalse(Date.isLeapYear(2007));
		
		assertTrue(Date.isLeapYear(2004));
		assertTrue(Date.isLeapYear(1980));
		
		assertFalse(Date.isLeapYear(1800));
		assertFalse(Date.isLeapYear(1900));
		assertFalse(Date.isLeapYear(2100));
		assertFalse(Date.isLeapYear(2200));
		assertFalse(Date.isLeapYear(2300));
		
		assertTrue(Date.isLeapYear(1600));
		assertTrue(Date.isLeapYear(2000));
		assertTrue(Date.isLeapYear(2400));
	}
	
	
	@Test
	public void testIsLeapYearBce() {
		assertFalse(Date.isLeapYear(-1));
		assertFalse(Date.isLeapYear(-2));
		assertFalse(Date.isLeapYear(-15));
		
		assertTrue(Date.isLeapYear(-4));
		assertTrue(Date.isLeapYear(-36));
		
		assertFalse(Date.isLeapYear(-100));
		assertFalse(Date.isLeapYear(-300));
		
		assertTrue(Date.isLeapYear(0));
		assertTrue(Date.isLeapYear(-400));
	}
	
	
	@Test
	public void testIsLeapYearEdgeCases() {
		assertFalse(Date.isLeapYear(Integer.MAX_VALUE - 0));
		assertFalse(Date.isLeapYear(Integer.MAX_VALUE - 1));
		assertFalse(Date.isLeapYear(Integer.MAX_VALUE - 2));
		assertTrue(Date.isLeapYear(Integer.MAX_VALUE - 3));
		
		assertTrue(Date.isLeapYear(Integer.MIN_VALUE + 0));
		assertFalse(Date.isLeapYear(Integer.MIN_VALUE + 1));
		assertFalse(Date.isLeapYear(Integer.MIN_VALUE + 2));
		assertFalse(Date.isLeapYear(Integer.MIN_VALUE + 3));
		assertTrue(Date.isLeapYear(Integer.MIN_VALUE + 4));
	}
	
	
	@Test
	public void testMonthLength() {
		assertEquals(31, Date.monthLength(2000, 1));
		assertEquals(29, Date.monthLength(2000, 2));
		assertEquals(31, Date.monthLength(2000, 3));
		assertEquals(30, Date.monthLength(2000, 4));
		assertEquals(31, Date.monthLength(2000, 5));
		assertEquals(30, Date.monthLength(2000, 6));
		assertEquals(31, Date.monthLength(2000, 7));
		assertEquals(31, Date.monthLength(2000, 8));
		assertEquals(30, Date.monthLength(2000, 9));
		assertEquals(31, Date.monthLength(2000, 10));
		assertEquals(30, Date.monthLength(2000, 11));
		assertEquals(31, Date.monthLength(2000, 12));
		
		assertEquals(31, Date.monthLength(2001, 1));
		assertEquals(28, Date.monthLength(2001, 2));
		assertEquals(31, Date.monthLength(2001, 3));
		assertEquals(30, Date.monthLength(2001, 4));
	}
	
	
	@Test
	public void testMonthLengthLenient() {
		assertEquals(29, Date.monthLength(1999, 14));
		assertEquals(29, Date.monthLength(2001, -10));
		assertEquals(28, Date.monthLength(2000, 14));
		assertEquals(31, Date.monthLength(37, 581));
	}
	
	
	@Test
	public void testMonthLengthEdgeCases() {
		assertEquals(30, Date.monthLength(Integer.MIN_VALUE, Integer.MIN_VALUE));  // April, non-leap year
		assertEquals(31, Date.monthLength(Integer.MIN_VALUE, Integer.MAX_VALUE));  // July, non-leap year
		assertEquals(30, Date.monthLength(Integer.MAX_VALUE, Integer.MIN_VALUE));  // April, leap year
		assertEquals(31, Date.monthLength(Integer.MAX_VALUE, Integer.MAX_VALUE));  // July, non-leap year
		
		assertEquals(28, Date.monthLength(Integer.MIN_VALUE + 0, Integer.MIN_VALUE + 10));
		assertEquals(28, Date.monthLength(Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 10));
		assertEquals(29, Date.monthLength(Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 10));
		
		assertEquals(28, Date.monthLength(Integer.MIN_VALUE + 0, Integer.MAX_VALUE - 5));
		assertEquals(28, Date.monthLength(Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 5));
		assertEquals(29, Date.monthLength(Integer.MIN_VALUE + 2, Integer.MAX_VALUE - 5));
		
		assertEquals(28, Date.monthLength(Integer.MAX_VALUE - 0, Integer.MIN_VALUE + 10));
		assertEquals(29, Date.monthLength(Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 10));
		
		assertEquals(28, Date.monthLength(Integer.MAX_VALUE - 0, Integer.MAX_VALUE - 5));
		assertEquals(29, Date.monthLength(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 5));
	}
	
	
	@Test
	public void testDayOfWeekByEpoch() {
		assertEquals(4, Date.dayOfWeek(-2));
		assertEquals(5, Date.dayOfWeek(-1));
		assertEquals(6, Date.dayOfWeek( 0));  // 2000-01-01
		assertEquals(0, Date.dayOfWeek( 1));
		assertEquals(1, Date.dayOfWeek( 2));
	}
	
	
	@Test
	public void testDayOfWeekByEpochEdgeCases() {
		assertEquals(4, Date.dayOfWeek(Integer.MIN_VALUE + 0));
		assertEquals(5, Date.dayOfWeek(Integer.MIN_VALUE + 1));
		assertEquals(6, Date.dayOfWeek(Integer.MIN_VALUE + 2));
		
		assertEquals(0, Date.dayOfWeek(Integer.MAX_VALUE - 0));
		assertEquals(6, Date.dayOfWeek(Integer.MAX_VALUE - 1));
		assertEquals(5, Date.dayOfWeek(Integer.MAX_VALUE - 2));
	}
	
	
	@Test
	public void testDayOfWeek() {
		assertEquals(6, Date.dayOfWeek(2000,  1,  1));
		assertEquals(0, Date.dayOfWeek(2000,  1,  2));
		assertEquals(1, Date.dayOfWeek(2000,  1,  3));
		assertEquals(2, Date.dayOfWeek(2000,  2, 29));
		assertEquals(3, Date.dayOfWeek(2000,  3,  1));
		
		assertEquals(5, Date.dayOfWeek(1997,  4,  4));
		assertEquals(5, Date.dayOfWeek(1997,  6,  6));
		assertEquals(5, Date.dayOfWeek(1997,  8,  8));
		assertEquals(5, Date.dayOfWeek(1997, 10, 10));
		assertEquals(5, Date.dayOfWeek(1997, 12, 12));
		
		assertEquals(0, Date.dayOfWeek(2100,  5,  9));
		assertEquals(0, Date.dayOfWeek(2100,  9,  5));
		assertEquals(0, Date.dayOfWeek(2100,  7, 11));
		assertEquals(0, Date.dayOfWeek(2100, 11,  7));
	}
	
	
	@Test
	public void testDayOfWeekCountingForward() {
		int y = 2000;  // 2000-01-01 is a Saturday (6)
		int m = 1;
		int d = 1;
		int i = 6;  // Internal day of week counter
		while (y <= 2400) {
			assertEquals(i, Date.dayOfWeek(y, m, d));
			d++;
			if (d > Date.monthLength(y, m)) {
				m++;
				d = 1;
				if (m > 12) {
					y++;
					m = 1;
				}
			}
			i = (i + 1) % 7;
		}
	}
	
	
	@Test
	public void testDayOfWeekCountingBackward() {
		int y = 2000;  // 2000-01-01 is a Saturday (6)
		int m = 1;
		int d = 1;
		int i = 6;  // Internal day of week counter
		while (y >= 1600) {
			assertEquals(i, Date.dayOfWeek(y, m, d));
			d--;
			if (d == 0) {
				m--;
				if (m == 0) {
					y--;
					m = 12;
				}
				d = Date.monthLength(y, m);
			}
			i = (i + 6) % 7;
		}
	}
	
	
	@Test
	public void testDayOfWeekEdgeCases() {
		assertEquals(2, Date.dayOfWeek(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
		assertEquals(4, Date.dayOfWeek(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE));
	}
	
	
	@Test
	public void testDaysSinceEpoch() {
		assertEquals(-730, Date.daysSinceEpoch(1998, 1, 1));
		assertEquals(-365, Date.daysSinceEpoch(1999, 1, 1));
		assertEquals(   0, Date.daysSinceEpoch(2000, 1, 1));
		assertEquals(   5, Date.daysSinceEpoch(2000, 1, 6));
		assertEquals(  31, Date.daysSinceEpoch(2000, 2, 1));
		assertEquals(  60, Date.daysSinceEpoch(2000, 3, 1));
		assertEquals(  91, Date.daysSinceEpoch(2000, 4, 1));
		assertEquals( 366, Date.daysSinceEpoch(2001, 1, 1));
		assertEquals( 731, Date.daysSinceEpoch(2002, 1, 1));
	}
	
	
	@Test
	public void testDaysSinceEpochCountingForward() {
		int y = 2000;  // 2000-01-01 is the epoch, i.e. day 0
		int m = 1;
		int d = 1;
		int i = 0;  // Internal days since epoch counter
		while (y <= 2400) {
			assertEquals(i, Date.daysSinceEpoch(y, m, d));
			d++;
			if (d > Date.monthLength(y, m)) {
				m++;
				d = 1;
				if (m > 12) {
					y++;
					m = 1;
				}
			}
			i++;
		}
	}
	
	
	@Test
	public void testDaysSinceEpochCountingBackward() {
		int y = 2000;  // 2000-01-01 is the epoch, i.e. day 0
		int m = 1;
		int d = 1;
		int i = 0;  // Internal days since epoch counter
		while (y >= 1600) {
			assertEquals(Date.daysSinceEpoch(y, m, d), i);
			d--;
			if (d <= 0) {
				m--;
				if (m <= 0) {
					y--;
					m = 12;
				}
				d = Date.monthLength(y, m);
			}
			i--;
		}
	}
	
	
	// Days since epoch to calendar date, forwards
	@Test
	public void testToCalendarDateForwards() {
		int i = 0;
		int y = 2000;  // Internal calendar date counters
		int m = 1;
		int d = 1;
		while (y <= 2400) {
			Date date = new Date(i);
			assertEquals(date.year, y);
			assertEquals(date.month, m);
			assertEquals(date.day, d);
			d++;
			if (d > Date.monthLength(y, m)) {
				m++;
				d = 1;
				if (m > 12) {
					y++;
					m = 1;
				}
			}
			i++;
		}
	}
	
	
	// Days since epoch to calendar date, backwards
	@Test
	public void testToCalendarDateBackwards() {
		int i = 0;
		int y = 2000;  // Internal calendar date counters
		int m = 1;
		int d = 1;
		while (y >= 1600) {
			Date date = new Date(i);
			assertEquals(date.year, y);
			assertEquals(date.month, m);
			assertEquals(date.day, d);
			d--;
			if (d <= 0) {
				m--;
				if (m <= 0) {
					y--;
					m = 12;
				}
				d = Date.monthLength(y, m);
			}
			i--;
		}
	}
	
	
	// new Date(y,m,d), because it requires a conversion to epoch days and then back to calendar date.
	@Test
	public void testNewDate() {
		int y = -500;
		int m = 1;
		int d = 1;
		while (y <= 2500) {
			Date date = new Date(y, m, d);
			assertEquals(date.year, y);
			assertEquals(date.month, m);
			assertEquals(date.day, d);
			d++;
			if (d > Date.monthLength(y, m)) {
				m++;
				d = 1;
				if (m > 12) {
					y++;
					m = 1;
				}
			}
		}
	}
	
	
	@Test
	public void testNewDateEdgeCases() {
		{
			Date date = new Date(-2147483648);
			assertEquals(-5877611, date.year);
			assertEquals(6, date.month);
			assertEquals(22, date.day);
		} {
			Date date = new Date(2147483647);
			assertEquals(5881610, date.year, 5881610);
			assertEquals(7, date.month);
			assertEquals(11, date.day);
		}
	}
	
	
	@Test
	public void testNewDateOverflow() {
		int[][] cases = {
			{-5877611, 6, 21},
			{-5877611, 5, 30},
			{5881610, 7, 12},
			{5881610, 8, 1},
			{0, Integer.MIN_VALUE, 0},
			{0, Integer.MAX_VALUE, 0},
			{Integer.MIN_VALUE, 0, 0},
			{Integer.MAX_VALUE, 0, 0},
		};
		
		for (int[] thecase : cases) {
			try {
				new Date(thecase[0], thecase[1], thecase[2]);
				fail(String.format("%d %d %d", thecase[0], thecase[1], thecase[2]));
			} catch (ArithmeticOverflowException e) {}
		}
	}
	
	
	@Test
	public void testNewDateLenientRepresentations() {
		assertEquals(new Date(2000, 1, 1), new Date(2000, 2, -30));
		assertEquals(new Date(2000, 1, 1), new Date(2000, 0, 32));
		assertEquals(new Date(2000, 1, 1), new Date(1999, 12, 32));
		assertEquals(new Date(2000, 1, 1), new Date(1997, 1, 1096));
		assertEquals(new Date(2000, 1, 1), new Date(1993, 85, 1));
	}
	
}