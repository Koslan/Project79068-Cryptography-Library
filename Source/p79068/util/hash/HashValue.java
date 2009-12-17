package p79068.util.hash;

import java.util.Arrays;


/**
 * Represents a hash value produced by a hash function.
 * <p>Mutability: <em>Immutable</em><br>
 *  Instantiability: Via <code>{@link HashFunction}.getHash()</code> or <code>{@link Hasher#getHash()}</code></p>
 * @see HashFunction
 * @see Hasher
 */
public final class HashValue implements Comparable<HashValue> {
	
	private final HashFunction hashFunction;
	private final byte[] hashValue;
	
	
	HashValue(HashFunction hashFunc, byte[] hashVal) {
		if (hashFunc == null || hashVal == null || hashVal.length != hashFunc.getHashLength())
			throw new AssertionError();
		hashFunction = hashFunc;
		hashValue = hashVal.clone();
	}
	
	
	
	/**
	 * Returns this hash value as an array of bytes.
	 */
	public byte[] toBytes() {
		return hashValue.clone();
	}
	
	
	/**
	 * Returns this hash value as a hexadecimal string (in uppercase). For example, <samp>"1337C0DE"</samp>.
	 */
	public String toHexString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < hashValue.length; i++)
			sb.append(hexDigits[hashValue[i] >>> 4 & 0xF]).append(hexDigits[hashValue[i] & 0xF]);
		return sb.toString();
	}
	
	
	/**
	 * Returns the hash function that produced this hash value.
	 */
	public HashFunction getHashFunction() {
		return hashFunction;
	}
	
	
	/**
	 * Returns the length of this hash value, in bytes. This is equivalent to <code>getHashFunction().getHashLength()</code>.
	 */
	public int getLength() {
		return hashValue.length;
	}
	
	
	public boolean equals(Object o) {
		if (!(o instanceof HashValue))
			return false;
		HashValue hash = (HashValue)o;
		return hashFunction.equals(hash.hashFunction) && Arrays.equals(hashValue, hash.hashValue);
	}
	
	
	public int compareTo(HashValue hv) {
		if (!hashFunction.equals(hv.hashFunction))
			throw new IllegalArgumentException("Hash functions are different");
		if (hashValue.length != hv.hashValue.length)
			throw new AssertionError("Hash lengths are different");
		for (int i = 0; i < hashValue.length && i < hv.hashValue.length; i++) {
			if (hashValue[i] != hv.hashValue[i])
				return (hashValue[i] & 0xFF) - (hv.hashValue[i] & 0xFF);
		}
		return 0;
	}
	
	
	/**
	 * Returns the hash code for this hash value.
	 * @return the hash code for this hash value
	 */
	public int hashCode() {
		int result = 0;
		for (int i = 0; i < Math.min(hashValue.length, 4); i++)
			result = result << 8 | (hashValue[i] & 0xFF);
		return result;
	}
	
	
	/**
	 * Returns a string representation of this hash value. Currently, the hash function's name and the hash value are returned. This is subjected to change.
	 * @return a string representation of this hash value
	 */
	public String toString() {
		return String.format("%s: %s", hashFunction.getName(), toHexString());
	}
	
	
	
	private static final char[] hexDigits = "0123456789ABCDEF".toCharArray();
	
}