/* 
 * Each int64 represents a row of state.
 */


package p79068.crypto.hash;

import p79068.Assert;
import p79068.crypto.Zeroizer;
import p79068.crypto.cipher.AbstractCipherer;
import p79068.crypto.cipher.BlockCipher;
import p79068.math.LongBitMath;


final class FastWhirlpoolCipherer extends AbstractCipherer {
	
	private long[] key;
	
	private FastWhirlpoolCore hasher;
	
	
	
	public FastWhirlpoolCipherer(BlockCipher cipher, byte[] key, int rounds, int[] sbox, int[] c, int[] cInv) {
		super(cipher, key);
		hasher = new FastWhirlpoolCore(rounds, sbox, c, cInv);
		setKey(key);
	}
	
	
	
	@Override
	public void encrypt(byte[] b, int off, int len) {
		crypt(b, off, len, true);
	}
	
	
	@Override
	public void decrypt(byte[] b, int off, int len) {
		crypt(b, off, len, false);
	}
	
	
	private void crypt(byte[] b, int off, int len, boolean encrypt) {
		if (cipher == null)
			throw new IllegalStateException("Already zeroized");
		Assert.assertRangeInBounds(b.length, off, len);
		if (len % 64 != 0)
			throw new IllegalArgumentException("Invalid block length");
		
		long[] tempmsg = new long[8];
		long[] tempkey = new long[8];
		long[] temp = new long[8];
		for (int i = off, end = off + len; i < end; i += 64) {
			System.arraycopy(key, 0, tempkey, 0, 8);
			toInt64sBigEndian(b, i, tempmsg);
			if (encrypt)
				hasher.encrypt(tempmsg, tempkey);
			else
				hasher.decrypt(tempmsg, tempkey, temp);
			toBytesBigEndian(tempmsg, b, i);
		}
	}
	
	
	@Override
	public FastWhirlpoolCipherer clone() {
		FastWhirlpoolCipherer result = (FastWhirlpoolCipherer)super.clone();
		result.key = result.key.clone();
		return result;
	}
	
	
	@Override
	public void zeroize() {
		if (cipher == null)
			return;
		key = Zeroizer.clear(key);
		super.zeroize();
	}
	
	
	private void setKey(byte[] key) {
		if (key.length != 64)
			throw new AssertionError();
		this.key = LongBitMath.fromBytesBigEndian(key);
	}
	
	
	
	private static void toInt64sBigEndian(byte[] in, int off, long[] out) {
		for (int i = 0; i < out.length; i++, off += 8) {
			out[i] = (in[off + 0] & 0xFFL) << 56
			       | (in[off + 1] & 0xFFL) << 48
			       | (in[off + 2] & 0xFFL) << 40
			       | (in[off + 3] & 0xFFL) << 32
			       | (in[off + 4] & 0xFFL) << 24
			       | (in[off + 5] & 0xFFL) << 16
			       | (in[off + 6] & 0xFFL) <<  8
			       | (in[off + 7] & 0xFFL) <<  0;
		}
	}
	
	
	private static void toBytesBigEndian(long[] in, byte[] out, int off) {
		for (int i = 0; i < in.length; i++, off += 8) {
			out[off + 0] = (byte)(in[i] >>> 56);
			out[off + 1] = (byte)(in[i] >>> 48);
			out[off + 2] = (byte)(in[i] >>> 40);
			out[off + 3] = (byte)(in[i] >>> 32);
			out[off + 4] = (byte)(in[i] >>> 24);
			out[off + 5] = (byte)(in[i] >>> 16);
			out[off + 6] = (byte)(in[i] >>>  8);
			out[off + 7] = (byte)(in[i] >>>  0);
		}
	}
	
}
