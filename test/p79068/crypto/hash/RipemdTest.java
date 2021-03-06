package p79068.crypto.hash;

import org.junit.Test;
import p79068.hash.HashFunction;


public final class RipemdTest extends CryptoHashFunctionTest {
	
	protected HashFunction[] getHashFunctionsToTest() {
		return new HashFunction[] {
			Ripemd.RIPEMD_FUNCTION,
			Ripemd.RIPEMD128_FUNCTION,
			Ripemd.RIPEMD160_FUNCTION,
			Ripemd.RIPEMD256_FUNCTION,
			Ripemd.RIPEMD320_FUNCTION,
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
	
	
	
	@Test public void testRipemd() {
		testAscii(Ripemd.RIPEMD_FUNCTION, new String[][] {
			{msg0, "9F73AA9B372A9DACFB86A6108852E2D9"},
			{msg1, "486F74F790BC95EF7963CD2382B4BBC9"},
			{msg2, "3F14BAD4C2F9B0EA805E5485D3D6882D"},
			{msg3, "5F5C7EBE1ABBB3C7036482942D5F9D49"},
			{msg4, "FF6E1547494251A1CCA6F005A6EAA2B4"},
			{msg6, "FF418A5AED3763D8F2DDF88A29E62486"},
			{msg7, "DFD6B45F60FE79BBBDE87C6BFC6580A5"},
		});
	}
	
	
	@Test public void testRipemd128() {
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
	
	
	@Test public void testRipemd160() {
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
	
	
	@Test public void testRipemd256() {
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
	
	
	@Test public void testRipemd320() {
		testAscii(Ripemd.RIPEMD320_FUNCTION, new String[][] {
			{msg0, "22D65D5661536CDC75C1FDF5C6DE7B41B9F27325EBC61E8557177D705A0EC880151C3A32A00899B8"},
			{msg1, "CE78850638F92658A5A585097579926DDA667A5716562CFCF6FBE77F63542F99B04705D6970DFF5D"},
			{msg2, "DE4C01B3054F8930A79D09AE738E92301E5A17085BEFFDC1B8D116713E74F82FA942D64CDBC4682D"},
			{msg3, "3A8E28502ED45D422F68844F9DD316E7B98533FA3F2A91D29F84D425C88D6B4EFF727DF66A7C0197"},
			{msg4, "CABDB1810B92470A2093AA6BCE05952C28348CF43FF60841975166BB40ED234004B8824463E6B009"},
			{msg5, "D034A7950CF722021BA4B84DF769A5DE2060E259DF4C9BB4A4268C0E935BBC7470A969C9D072A1AC"},
			{msg6, "ED544940C86D67F250D232C30B7B3E5770E0C60C8CB9A4CAFE3B11388AF9920E1B99230B843C86A4"},
			{msg7, "557888AF5F6D8ED62AB66945C6D2A0A47ECD5341E915EB8FEA1D0524955F825DC717E4A008AB2D42"},
		});
	}
	
}
