package p79068.crypto.hash;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;
import p79068.crypto.CryptoUtils;
import p79068.hash.HashFunction;
import p79068.hash.HashValue;
import p79068.hash.Hasher;


public final class Edonkey2000Test extends CryptoHashFunctionTest {
	
	protected HashFunction[] getHashFunctionsToTest() {
		return new HashFunction[] {
			Edonkey2000.ED2K_FUNCTION,
			Edonkey2000.NEW_ED2K_FUNCTION,
		};
	}
	
	
	private static final int BLOCK_LENGTH = 9728000;
	
	
	@Test public void testOldEdonkey2000() {
		HashFunction hf = Edonkey2000.ED2K_FUNCTION;
		test(hf, 0 * BLOCK_LENGTH +  0, "31D6CFE0D16AE931B73C59D7E0C089C0");
		test(hf, 0 * BLOCK_LENGTH + 89, "C48B812748FD84C7B4AE116C5CD24CE0");
		test(hf, 1 * BLOCK_LENGTH -  1, "AC44B93FC9AFF773AB0005C911F8396F");
		test(hf, 1 * BLOCK_LENGTH +  0, "FC21D9AF828F92A8DF64BEAC3357425D");
		test(hf, 1 * BLOCK_LENGTH +  1, "06329E9DBA1373512C06386FE29E3C65");
		test(hf, 2 * BLOCK_LENGTH - 31, "145E5CF749DCC29FE7FC766CF72DAD4F");
		test(hf, 2 * BLOCK_LENGTH +  0, "114B21C63A74B6CA922291A11177DD5C");
		test(hf, 2 * BLOCK_LENGTH + 25, "C06ED2428584B4117814B64D27A88F09");
	}
	
	
	@Test public void testNewEdonkey2000() {
		HashFunction hf = Edonkey2000.NEW_ED2K_FUNCTION;
		test(hf, 0 * BLOCK_LENGTH +  0, "31D6CFE0D16AE931B73C59D7E0C089C0");  // Same
		test(hf, 0 * BLOCK_LENGTH + 89, "C48B812748FD84C7B4AE116C5CD24CE0");  // Same
		test(hf, 1 * BLOCK_LENGTH -  1, "AC44B93FC9AFF773AB0005C911F8396F");  // Same
		test(hf, 1 * BLOCK_LENGTH +  0, "D7DEF262A127CD79096A108E7A9FC138");  // Different
		test(hf, 1 * BLOCK_LENGTH +  1, "06329E9DBA1373512C06386FE29E3C65");  // Same
		test(hf, 2 * BLOCK_LENGTH - 31, "145E5CF749DCC29FE7FC766CF72DAD4F");  // Same
		test(hf, 2 * BLOCK_LENGTH +  0, "194EE9E4FA79B2EE9F8829284C466051");  // Different
		test(hf, 2 * BLOCK_LENGTH + 25, "C06ED2428584B4117814B64D27A88F09");  // Same
	}
	
	
	
	private static void test(HashFunction hf, int len, String expectedHash) {
		byte[] actualHash = getHashOfZeros(hf, len).toBytes();
		byte[] expHash = CryptoUtils.hexToBytes(expectedHash);
		assertArrayEquals(expHash, actualHash);
	}
	
	
	private static HashValue getHashOfZeros(HashFunction hf, int len) {
		Hasher hasher = hf.newHasher();
		byte[] b = new byte[1 << 16];
		while (len > 0) {
			int n = Math.min(b.length, len);
			hasher.update(b, 0, n);
			len -= n;
		}
		return hasher.getHash();
	}
	
}
