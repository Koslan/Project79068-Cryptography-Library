package p79068.crypto.hash;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import p79068.hash.HashValue;
import p79068.hash.Hasher;
import p79068.util.random.Random;


public final class BlockHasherTest {
	
	@Test
	public void testMultipleOfBlockSize() {
		byte[] message = new byte[487];  // Some arbitrary prime number
		Random.DEFAULT.uniformBytes(message);
		HashValue expectedHash = Md.MD5_FUNCTION.getHash(message);
		
		Hasher hasher = Md.MD5_FUNCTION.newHasher();
		hasher.update(message, 0 * 64, 64);
		hasher.update(message, 1 * 64, 64);
		hasher.update(message, 2 * 64, 64);
		hasher.update(message, 3 * 64, 64);
		hasher.update(message, 4 * 64, 64);
		hasher.update(message, 5 * 64, 64);
		hasher.update(message, 6 * 64, 64);
		hasher.update(message, 7 * 64, 39);
		assertEquals(expectedHash, hasher.getHash());
	}
	
	
	@Test
	public void testPartialFill0() {
		byte[] message = new byte[487];
		Random.DEFAULT.uniformBytes(message);
		HashValue expectedHash = Md.MD5_FUNCTION.getHash(message);
		
		Hasher hasher = Md.MD5_FUNCTION.newHasher();
		hasher.update(message, 0, 24);
		hasher.update(message, 24, 463);
		assertEquals(expectedHash, hasher.getHash());
	}
	
	
	@Test
	public void testPartialFill1() {
		byte[] message = new byte[487];
		Random.DEFAULT.uniformBytes(message);
		HashValue expectedHash = Md.MD5_FUNCTION.getHash(message);
		
		Hasher hasher = Md.MD5_FUNCTION.newHasher();
		hasher.update(message, 0, 13);
		hasher.update(message, 13, 7);
		hasher.update(message, 20, 467);
		assertEquals(expectedHash, hasher.getHash());
	}
	
	
	@Test
	public void testPartialFill2() {
		byte[] message = new byte[487];
		Random.DEFAULT.uniformBytes(message);
		HashValue expectedHash = Md.MD5_FUNCTION.getHash(message);
		
		Hasher hasher = Md.MD5_FUNCTION.newHasher();
		hasher.update(message, 0, 31);
		hasher.update(message, 31, 33);
		hasher.update(message, 64, 423);
		assertEquals(expectedHash, hasher.getHash());
	}
	
	
	@Test
	public void testRandomPartitions() {
		for (int i = 0; i < 100; i++)
			testRandomPartitions(Random.DEFAULT.uniformInt(2000), 64 * 3);
	}
	
	
	private static void testRandomPartitions(int messageLength, int maxPartitionLen) {
		byte[] message = new byte[messageLength];
		Random.DEFAULT.uniformBytes(message);
		HashValue expectedHash = Md.MD5_FUNCTION.getHash(message);
		
		for (int i = 0; i < 100; i++) {
			Hasher hasher = Md.MD5_FUNCTION.newHasher();
			int off = 0;
			while (off < message.length) {
				int temp = Random.DEFAULT.uniformInt(Math.min(maxPartitionLen + 1, message.length - off + 1));
				hasher.update(message, off, temp);
				off += temp;
			}
			assertEquals(expectedHash, hasher.getHash());
		}
	}
	
}