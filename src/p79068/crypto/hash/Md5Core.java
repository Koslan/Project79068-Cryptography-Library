package p79068.crypto.hash;

import java.math.BigInteger;
import java.util.Arrays;

import p79068.Assert;
import p79068.crypto.Zeroizer;
import p79068.hash.HashValue;
import p79068.math.IntegerBitMath;


final class Md5Core extends BlockHasherCore {
	
	private int[] state;
	
	
	
	public Md5Core() {
		state = new int[]{0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476};
	}
	
	
	
	@Override
	public Md5Core clone() {
		if (state == null)
			throw new IllegalStateException("Already zeroized");
		Md5Core result = (Md5Core)super.clone();
		result.state = result.state.clone();
		return result;
	}
	
	
	@Override
	public void zeroize() {
		if (state == null)
			throw new IllegalStateException("Already zeroized");
		state = Zeroizer.clear(state);
	}
	
	
	
	private static final int[] t = {
		0xD76AA478, 0xE8C7B756, 0x242070DB, 0xC1BDCEEE,
		0xF57C0FAF, 0x4787C62A, 0xA8304613, 0xFD469501,
		0x698098D8, 0x8B44F7AF, 0xFFFF5BB1, 0x895CD7BE,
		0x6B901122, 0xFD987193, 0xA679438E, 0x49B40821,
		0xF61E2562, 0xC040B340, 0x265E5A51, 0xE9B6C7AA,
		0xD62F105D, 0x02441453, 0xD8A1E681, 0xE7D3FBC8,
		0x21E1CDE6, 0xC33707D6, 0xF4D50D87, 0x455A14ED,
		0xA9E3E905, 0xFCEFA3F8, 0x676F02D9, 0x8D2A4C8A,
		0xFFFA3942, 0x8771F681, 0x6D9D6122, 0xFDE5380C,
		0xA4BEEA44, 0x4BDECFA9, 0xF6BB4B60, 0xBEBFBC70,
		0x289B7EC6, 0xEAA127FA, 0xD4EF3085, 0x04881D05,
		0xD9D4D039, 0xE6DB99E5, 0x1FA27CF8, 0xC4AC5665,
		0xF4292244, 0x432AFF97, 0xAB9423A7, 0xFC93A039,
		0x655B59C3, 0x8F0CCC92, 0xFFEFF47D, 0x85845DD1,
		0x6FA87E4F, 0xFE2CE6E0, 0xA3014314, 0x4E0811A1,
		0xF7537E82, 0xBD3AF235, 0x2AD7D2BB, 0xEB86D391
	};
	
	
	private static final int[] s = {
		 7, 12, 17, 22,
		 5,  9, 14, 20,
		 4, 11, 16, 23,
		 6, 10, 15, 21
	};
	
	
	
	@Override
	public void compress(byte[] message, int off, int len) {
		Assert.assertRangeInBounds(message.length, off, len);
		if (len % 64 != 0)
			throw new AssertionError();
		
		int[] schedule = new int[16];
		
		// For each block of 64 bytes
		for (int i = off, end = off + len; i < end; ) {
			
			// Pack bytes into int32s in little endian
			for (int j = 0; j < 16; j++, i += 4) {
				schedule[j] =
					  (message[j + 0] & 0xFF) <<  0
					| (message[j + 1] & 0xFF) <<  8
					| (message[j + 2] & 0xFF) << 16
					| (message[j + 3] & 0xFF) << 24;
			}

			// The 64 rounds
			int a = state[0];
			int b = state[1];
			int c = state[2];
			int d = state[3];
			for (int j = 0; j < 64; j++) {
				int f;
				int k;
				if      ( 0 <= j && j < 16) { f = (b & c) | (~b & d);  k = j;                }  // Can be optimized to f = d ^ (b & (c ^ d))
				else if (16 <= j && j < 32) { f = (d & b) | (~d & c);  k = (5 * j + 1) % 16; }  // Can be optimized to f = c ^ (d & (b ^ c))
				else if (32 <= j && j < 48) { f = b ^ c ^ d;           k = (3 * j + 5) % 16; }
				else if (48 <= j && j < 64) { f = c ^ (b | (~d));      k = 7 * j % 16;       }
				else throw new AssertionError();
				
				int temp = a + f + t[j] + schedule[k];
				int rot = s[j / 16 * 4 + j % 4];
				temp = b + Integer.rotateLeft(temp, rot);
				a = d;
				d = c;
				c = b;
				b = temp;
			}
			state[0] += a;
			state[1] += b;
			state[2] += c;
			state[3] += d;
		}
	}
	
	
	@Override
	public HashValue getHashDestructively(byte[] block, int blockLength, BigInteger length) {
		block[blockLength] = (byte)0x80;
		blockLength++;
		Arrays.fill(block, blockLength, block.length, (byte)0);
		if (blockLength + 8 > block.length) {
			compress(block);
			Arrays.fill(block, (byte)0);
		}
		length = length.shiftLeft(3);  // Length is now in bits
		for (int i = 0; i < 8; i++)
			block[block.length - 8 + i] = length.shiftRight(i * 8).byteValue();
		compress(block);
		return new HashValue(IntegerBitMath.toBytesLittleEndian(state));
	}
	
}
