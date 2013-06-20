package p79068.crypto.hash;

import p79068.Assert;
import p79068.crypto.Zeroizable;
import p79068.crypto.Zeroizer;
import p79068.hash.AbstractHasher;
import p79068.hash.HashValue;


class KeccakHasher extends AbstractHasher implements Zeroizable {
	
	private long[] state;
	
	private final int blockSize;
	
	private int blockByteIndex;
	
	
	
	public KeccakHasher(Keccak hashFunc) {
		super(hashFunc);
		state = new long[25];
		int c = hashFunc.getHashLength() * 8 * 2;
		int r = 1600 - c;
		blockSize = r / 8;
		blockByteIndex = 0;
	}
	
	
	
	@Override
	public void update(byte[] b, int off, int len) {
		Assert.assertRangeInBounds(b.length, off, len);
		int j = blockByteIndex;
		if (j < 0 || j >= blockSize)
			throw new AssertionError();
		for (int i = off, end = off + len; i < end; i++) {
			state[j >>> 3] ^= (b[i] & 0xFFL) << ((j & 7) << 3);
			j++;
			if (j == blockSize) {
				absorb();
				j = 0;
			}
		}
		blockByteIndex = j;
	}
	
	
	@Override
	public HashValue getHash() {
		KeccakHasher hasher = clone();
		byte[] temp = new byte[hasher.blockSize - hasher.blockByteIndex];
		temp[0] = 0x01;
		temp[temp.length - 1] ^= 0x80;
		hasher.update(temp);
		
		byte[] result = new byte[hasher.hashFunction.getHashLength()];
		for (int i = 0; i < result.length; i++)
			result[i] = (byte)(hasher.state[i / 8] >>> (i % 8 * 8));
		return new HashValue(result);
	}
	
	
	
	@Override
	public KeccakHasher clone() {
		if (state == null)
			throw new IllegalStateException("Already zeroized");
		KeccakHasher result = (KeccakHasher)super.clone();
		result.state = result.state.clone();
		return result;
	}
	
	
	public void zeroize() {
		if (state == null)
			throw new IllegalStateException("Already zeroized");
		state = Zeroizer.clear(state);
	}
	
	
	
	private static final long[] RC = {
		0x0000000000000001L, 0x0000000000008082L, 0x800000000000808AL, 0x8000000080008000L,
		0x000000000000808BL, 0x0000000080000001L, 0x8000000080008081L, 0x8000000000008009L,
		0x000000000000008AL, 0x0000000000000088L, 0x0000000080008009L, 0x000000008000000AL,
		0x000000008000808BL, 0x800000000000008BL, 0x8000000000008089L, 0x8000000000008003L,
		0x8000000000008002L, 0x8000000000000080L, 0x000000000000800AL, 0x800000008000000AL,
		0x8000000080008081L, 0x8000000000008080L, 0x0000000080000001L, 0x8000000080008008L,
	};
	
	private static final int[][] R = {
		{ 0,  1, 62, 28, 27},
		{36, 44,  6, 55, 20},
		{ 3, 10, 43, 25, 39},
		{41, 45, 15, 21,  8},
		{18,  2, 61, 56, 14},
	};
	
	
	private void absorb() {
		long[] a = state;
		long[] b = new long[25];
		long[] c = new long[5];
		long[] d = new long[5];
		for (long rc : RC) {
			// Theta step
			for (int x = 0; x < 5; x++) {
				int t = 0;
				for (int y = 0; y < 5; y++)
					t ^= a[y * 5 + x];
				c[x] = t;
			}
			
			for (int x = 0; x < 5; x++)
				d[x] = c[(x + 4) % 5] ^ Long.rotateLeft(c[(x + 1) % 5], 1);
			
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y < 5; y++)
					a[y * 5 + x] ^= d[x];
			}
			
			// Rho and pi steps
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y < 5; y++)
					b[(x * 2 + y * 3) % 5 * 5 + y] = Long.rotateLeft(a[y * 5 + x], R[y][x]);
			}
			
			// Chi step
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y < 5; y++)
					a[y * 5 + x] = b[y * 5 + x] ^ (~b[y * 5 + (x + 1) % 5] & b[y * 5 + (x + 2) % 5]);
			}
			
			// Iota step
			a[0] ^= rc;
		}
	}
	
}
