package p79068.crypto;

import static org.junit.Assert.assertArrayEquals;
import p79068.crypto.cipher.Cipher;
import p79068.crypto.cipher.Cipherer;
import p79068.util.random.Random;


public final class CryptoUtils {
	
	public static void testCipher(Cipher cipher, String key, String plaintext, String expectedCiphertext) {
		testCipher(cipher, hexToBytes(key), hexToBytes(plaintext), hexToBytes(expectedCiphertext));
	}
	
	
	public static void testCipher(Cipher cipher, byte[] key, byte[] plaintext, byte[] expectedCiphertext) {
		Cipherer cipherer;
		byte[] temp = plaintext.clone();
		
		cipherer = cipher.newCipherer(key);
		cipherer.encrypt(temp);
		assertArrayEquals(cipher.toString(), expectedCiphertext, temp);
		
		cipherer = cipher.newCipherer(key);
		cipherer.decrypt(temp);
		assertArrayEquals(cipher.toString(), plaintext, temp);
	}
	
	
	public static void testCipherInvertibility(Cipher cipher, int messageLength) {
		byte[] key = getRandomBytes(cipher.getKeyLength());
		byte[] message0 = getRandomBytes(messageLength);
		byte[] message1 = message0.clone();
		Cipherer cipherer = cipher.newCipherer(key);
		cipherer.encrypt(message1);
		cipherer = cipher.newCipherer(key);  // Reinitialize the cipher
		cipherer.decrypt(message1);
		assertArrayEquals(cipher.toString(), message0, message1);
	}
	
	
	
	public static byte[] hexToBytes(String s) {
		if (s.length() % 2 != 0)
			throw new IllegalArgumentException();
		byte[] b = new byte[s.length() / 2];
		for (int i = 0; i < b.length; i++)
			b[i] = (byte)Integer.parseInt(s.substring(i * 2, (i + 1) * 2), 16);
		return b;
	}
	
	
	public static String bytesToHex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(Integer.toString(b[i] >>> 4 & 0xF, 16));
			sb.append(Integer.toString(b[i] >>> 0 & 0xF, 16));
		}
		return sb.toString();
	}
	
	
	public static byte[] asciiToBytes(String s) {
		byte[] b = new byte[s.length()];
		for (int i = 0; i < b.length; i++) {
			char c = s.charAt(i);
			if (c >= 0x80)
				c = '?';
			b[i] = (byte)c;
		}
		return b;
	}
	
	
	public static String bytesToAscii(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			if ((b[i] & 0xFF) < 0x80)
				sb.append((char)(b[i] & 0xFF));
			else
				sb.append('?');
		}
		return sb.toString();
	}
	
	
	public static byte[] getRandomBytes(int length) {
		byte[] b = new byte[length];
		Random.DEFAULT.uniformBytes(b);
		return b;
	}
	
	
	
	private CryptoUtils() {}
	
}