package p79068.crypto.hash;

import p79068.util.hash.Hasher;
import p79068.util.hash.HashFunction;


/**
 * The Whirlpool hash function (version 2).
 * <p>Revisions of the Whirlpool function:</p>
 * <ul>
 *  <li>Version 0: The original Whirlpool algorithm (Whirlpool-0)</li>
 *  <li>Version 1: S-box changed (Whirlpool-1)</li>
 *  <li>Version 2: Linear mixing changed (current version, just Whirlpool)</li>
 * </ul>
 * <p>Mutability: <em>Immutable</em><br>
 *  Instantiability: <em>Singleton</em></p>
 * @see HashFunction
 * @see Whirlpool0
 * @see Whirlpool1
 */
public class Whirlpool extends BlockHashFunction {
	
	/**
	 * The singleton instance of the Whirlpool hash function.
	 */
	public final static Whirlpool FUNCTION = new Whirlpool();
	
	
	
	/**
	 * Returns a new hasher of this hash function.
	 * @return a new hasher of this hash function
	 */
	public Hasher newHasher() {
		return new FastWhirlpoolHasher(this);
	}
	
	
	/**
	 * Returns the name of this hash function: <code>Whirlpool</code>.
	 */
	public String getName() {
		return "Whirlpool";
	}
	
	
	/**
	 * Returns the length of hash values produced by this hash function: <code>64</code> bytes (512 bits).
	 */
	public int getHashLength() {
		return 64;
	}
	
	
	/**
	 * Returns the block length of this hash function: <code>64</code> bytes (512 bits).
	 */
	public int getBlockLength() {
		return 64;
	}
	
	
	
	private Whirlpool() {}
	
	
	
	private static final int ROUNDS = 10;
	
	private static final byte[] SUB;
	
	private static final byte[][] MUL;
	
	private static final byte[][] RCON;
	
	
	static {
		if (ROUNDS < 1 || ROUNDS > 32)
			throw new AssertionError("Invalid number of rounds");
		SUB = makeSub();
		RCON = makeRcon(SUB);
		MUL = makeMul();
	}
	
	
	// Identical to the one in Whirlpool-1
	private static byte[] makeSub() {
		byte[] sub = new byte[256];
		
		int[] e = {0x1, 0xB, 0x9, 0xC, 0xD, 0x6, 0xF, 0x3, 0xE, 0x8, 0x7, 0x4, 0xA, 0x2, 0x5, 0x0};  // The E mini-box
		int[] r = {0x7, 0xC, 0xB, 0xD, 0xE, 0x4, 0x9, 0xF, 0x6, 0x3, 0x8, 0xA, 0x2, 0x5, 0x1, 0x0};  // The R mini-box
		
		int[] einv = new int[16];  // The inverse of E
		for (int i = 0; i < e.length; i++)
			einv[e[i]] = i;
		
		for (int i = 0; i < sub.length; i++) {
			int left = e[i >>> 4];
			int right = einv[i & 0xF];
			int temp = r[left ^ right];
			sub[i] = (byte)(e[left ^ temp] << 4 | einv[right ^ temp]);
		}
		
		return sub;
	}
	
	
	private static byte[][] makeRcon(byte[] sub) {
		byte[][] rcon = new byte[ROUNDS][64];
		for (int i = 0; i < rcon.length; i++) {
			for (int j = 0; j < 8; j++)  // The leading 8 bytes (top row) are taken from the S-box
				rcon[i][j] = sub[8 * i + j];
			for (int j = 8; j < 64; j++)  // The remaining 7 rows are zero
				rcon[i][j] = 0;
		}
		return rcon;
	}
	
	
	private static byte[][] makeMul() {
		byte[][] mul = new byte[8][256];
		int[] c = {0x01, 0x05, 0x09, 0x08, 0x05, 0x01, 0x03, 0x01};
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < 256; j++)
				mul[i][j] = (byte)WhirlpoolUtil.multiply(j, c[i]);
		}
		return mul;
	}
	
}