package p79068.crypto.cipher.mode;

import p79068.crypto.cipher.BlockCipher;
import p79068.crypto.cipher.Cipherer;
import p79068.lang.BoundsChecker;


final class PcbcModeCipherer extends Cipherer {
	
	private Cipherer cipherer;
	private int blockLength;
	
	private byte[] prevPlainXorCipher;
	
	
	
	PcbcModeCipherer(PcbcModeCipher cipher, byte[] initVector, BlockCipher blockCipher, byte[] cipherKey) {
		super(cipher, initVector);
		blockLength = blockCipher.getBlockLength();
		if (initVector.length != blockLength)
			throw new AssertionError();
		cipherer = blockCipher.newCipherer(cipherKey);
		prevPlainXorCipher = initVector.clone();
	}
	
	
	
	public void encrypt(byte[] b, int off, int len) {
		if (cipher == null)
			throw new IllegalStateException("Already zeroized");
		BoundsChecker.check(b.length, off, len);
		if (len % blockLength != 0)
			throw new IllegalArgumentException("Invalid block length");
		for (len += off; off < len; off += blockLength) {
			for (int i = 0; i < blockLength; i++) {
				byte temp = b[off + i];
				b[off + i] ^= prevPlainXorCipher[i];
				prevPlainXorCipher[i] = temp;
			}
			cipherer.encrypt(b, off, blockLength);
			for (int i = 0; i < blockLength; i++)
				prevPlainXorCipher[i] ^= b[off + i];
		}
	}
	
	
	public void decrypt(byte[] b, int off, int len) {
		if (cipher == null)
			throw new IllegalStateException("Already zeroized");
		BoundsChecker.check(b.length, off, len);
		if (len % blockLength != 0)
			throw new IllegalArgumentException("Invalid block length");
		byte[] ciphertext = new byte[blockLength];
		for (len += off; off < len; off += blockLength) {
			for (int i = 0; i < blockLength; i++)
				ciphertext[i] = b[off + i];
			cipherer.decrypt(b, off, blockLength);
			for (int i = 0; i < blockLength; i++) {
				b[off + i] ^= prevPlainXorCipher[i];
				prevPlainXorCipher[i] = (byte)(b[off + i] ^ ciphertext[i]);
			}
		}
	}
	
	
	public void zeroize() {
		if (cipher == null)
			throw new IllegalStateException("Already zeroized");
		for (int i = 0; i < prevPlainXorCipher.length; i++)
			prevPlainXorCipher[i] = 0;
		prevPlainXorCipher = null;
		cipherer.zeroize();
		cipherer = null;
		super.zeroize();
	}
	
}