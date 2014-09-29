package p79068.crypto.hash;

import org.junit.Test;

import p79068.hash.HashFunction;


public final class RipemdTest extends CryptoHashFunctionTest {
	
	protected HashFunction[] getHashFunctionsToTest() {
		return new HashFunction[] {
			Ripemd.RIPEMD160_FUNCTION,
		};
	}
	
	
	private static final String msg0 = "";
	private static final String msg1 = "a";
	private static final String msg2 = "abc";
	private static final String msg3 = "message digest";
	private static final String msg4 = "abcdefghijklmnopqrstuvwxyz";
	private static final String msg5 = "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq";
	private static final String msg6 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final String msg7 = "12345678901234567890123456789012345678901234567890123456789012345678901234567890";
	
	
	
	@Test
	public void testRipemd160() {
		testAscii(Ripemd.RIPEMD160_FUNCTION, new String[][] {
			{msg0, "9C1185A5C5E9FC54612808977EE8F548B2258D31"},
			{msg1, "0BDC9D2D256B3EE9DAAE347BE6F4DC835A467FFE"},
			{msg2, "8EB208F7E05D987A9B044A8E98C6B087F15A0BFC"},
			{msg3, "5D0689EF49D2FAE572B881B123A85FFA21595F36"},
			{msg4, "F71C27109C692C1B56BBDCEB5B9D2865B3708DBC"},
			{msg5, "12A053384A9C0C88E405A06C27DCF49ADA62EB2B"},
			{msg6, "B0E20B6E3116640286ED3A87A5713079B21F5189"},
			{msg7, "9B752E45573D4B39F4DBD3323CAB82BF63326BFB"},
		});
	}
	
}
