package p79068.hash;

import java.io.File;
import java.io.IOException;


/**
 * A hash function, which creates hashers and generates hash values.
 * <p>Example - one-shot hashing:</p>
 * <pre>byte[] message = (...);
 *byte[] hash = Sha.SHA1_FUNCTION.getHash(message).toBytes();</pre>
 * <p>Example - incremental hashing:</p>
 * <pre>Hasher hasher = Crc.CRC32_FUNCTION.newHasher();
 *while (...) {
 *    byte[] buffer = (...);
 *    hasher.update(buffer);
 *}
 *String hash = hasher.getHash().toHexString();</pre>
 * @see Hasher
 * @see HashValue
 */
public interface HashFunction {
	
	// Informational methods
	
	/**
	 * Returns the name of this hash function.
	 * @return the name of this hash function
	 */
	public String getName();
	
	/**
	 * Returns the length of the hash values produced by this hash function, in bytes.
	 * @return the length of the hash values produced by this hash function, in bytes
	 */
	public int getHashLength();
	
	/**
	 * Returns a string representation of this hash function. The string format is subjected to change.
	 * @return a string representation of this hash function
	 */
	public String toString();
	
	
	// Factory method
	
	/**
	 * Returns a new hasher of this hash function, which is used to compute a hash value incrementally.
	 * @return a new hasher of this hash function
	 */
	public Hasher newHasher();
	
	
	// Hashing convenience methods
	
	/**
	 * Computes and returns the hash value of the specified byte array.
	 * @param b the byte array to hash
	 * @return the hash value of the specified byte array
	 * @throws NullPointerException if {@code b} is {@code null}
	 */
	public HashValue getHash(byte[] b);
	
	/**
	 * Computes and returns the hash value of the specified byte array range.
	 * @param b the byte array to hash
	 * @param off the offset into {@code b}
	 * @param len the length of the subrange in {@code b}
	 * @return the hash value of the specified byte array range
	 * @throws NullPointerException if {@code b} is {@code null}
	 */
	public HashValue getHash(byte[] b, int off, int len);
	
	/**
	 * Computes and returns the hash value of the specified file.
	 * @param file the file to hash
	 * @return the hash value of the specified file
	 * @throws NullPointerException if {@code file} is {@code null}
	 * @throws IOException if an I/O exception occurs
	 */
	public HashValue getHash(File file) throws IOException;
	
}
