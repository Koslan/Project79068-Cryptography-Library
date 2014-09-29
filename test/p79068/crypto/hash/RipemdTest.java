package p79068.crypto.hash;

import org.junit.Test;

import p79068.hash.HashFunction;


public final class RipemdTest extends CryptoHashFunctionTest {
	
	protected HashFunction[] getHashFunctionsToTest() {
		return new HashFunction[] {
			Ripemd.RIPEMD128_FUNCTION,
			Ripemd.RIPEMD160_FUNCTION,
			Ripemd.RIPEMD256_FUNCTION,
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
	public void testRipemd128() {
		testAscii(Ripemd.RIPEMD128_FUNCTION, new String[][] {
			{msg0, "CDF26213A150DC3ECB610F18F6B38B46"},
			{msg1, "86BE7AFA339D0FC7CFC785E72F578D33"},
			{msg2, "C14A12199C66E4BA84636B0F69144C77"},
			{msg3, "9E327B3D6E523062AFC1132D7DF9D1B8"},
			{msg4, "FD2AA607F71DC8F510714922B371834E"},
			{msg5, "A1AA0689D0FAFA2DDC22E88B49133A06"},
			{msg6, "D1E959EB179C911FAEA4624C60C5C702"},
			{msg7, "3F45EF194732C2DBB2C4A2C769795FA3"},
		});
	}
	
	
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
	
	
	@Test
	public void testRipemd256() {
		testAscii(Ripemd.RIPEMD256_FUNCTION, new String[][] {
			{msg0, "02BA4C4E5F8ECD1877FC52D64D30E37A2D9774FB1E5D026380AE0168E3C5522D"},
			{msg1, "F9333E45D857F5D90A91BAB70A1EBA0CFB1BE4B0783C9ACFCD883A9134692925"},
			{msg2, "AFBD6E228B9D8CBBCEF5CA2D03E6DBA10AC0BC7DCBE4680E1E42D2E975459B65"},
			{msg3, "87E971759A1CE47A514D5C914C392C9018C7C46BC14465554AFCDF54A5070C0E"},
			{msg4, "649D3034751EA216776BF9A18ACC81BC7896118A5197968782DD1FD97D8D5133"},
			{msg5, "3843045583AAC6C8C8D9128573E7A9809AFB2A0F34CCC36EA9E72F16F6368E3F"},
			{msg6, "5740A408AC16B720B84424AE931CBB1FE363D1D0BF4017F1A89F7EA6DE77A0B8"},
			{msg7, "06FDCC7A409548AAF91368C06A6275B553E3F099BF0EA4EDFD6778DF89A890DD"},
		});
	}
	
}
