package p79068.crypto.hash;

import p79068.hash.Hasher;


/**
 * The SHA hash function family, described in FIPS Publication 180.
 */
public final class Sha extends AbstractBlockHashFunction {
	
	/**
	 * The SHA-1 hash function. {@code name = "SHA-1"}, {@code hashLength = 20}, {@code blockLength = 64}.
	 */
	public final static Sha SHA1_FUNCTION = new Sha("SHA-1", 20, 64);
	
	
	/**
	 * The SHA-256 hash function. {@code name = "SHA-256"}, {@code hashLength = 32}, {@code blockLength = 64}.
	 */
	public final static Sha SHA256_FUNCTION = new Sha("SHA-256", 32, 64);
	
	
	/**
	 * The SHA-384 hash function. {@code name = "SHA-384"}, {@code hashLength = 48}, {@code blockLength = 128}.
	 */
	public final static Sha SHA384_FUNCTION = new Sha("SHA-384", 48, 128);
	
	
	/**
	 * The SHA-512 hash function. {@code name = "SHA-512"}, {@code hashLength = 64}, {@code blockLength = 128}.
	 */
	public final static Sha SHA512_FUNCTION = new Sha("SHA-512", 64, 128);
	
	
	
	private Sha(String name, int hashLen, int blockLen) {
		super(name, hashLen, blockLen);
	}
	
	
	
	/**
	 * Returns a new hasher of this hash function.
	 * @return a new hasher of this hash function
	 */
	@Override
	public Hasher newHasher() {
		if      (this == SHA1_FUNCTION  ) return new BlockHasher(this, new FastSha1Core());
		else if (this == SHA256_FUNCTION) return new BlockHasher(this, new Sha256Core());
		else if (this == SHA384_FUNCTION) return new BlockHasher(this, new Sha512Core(false));
		else if (this == SHA512_FUNCTION) return new BlockHasher(this, new Sha512Core(true));
		else throw new AssertionError();
	}
	
}
