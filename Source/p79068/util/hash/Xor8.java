package p79068.util.hash;


/**
 * The XOR checksum function. The hash value is the XOR of all the bytes.
 * <p>Instantiability: <em>Singleton</em></p>
 * @see AbstractHashFunction
 */
public class Xor8 extends AbstractHashFunction {
	
	/**
	 * The singleton instance of the XOR hash function. {@code name = "XOR-8"}, {@code hashLength = 1}.
	 */
	public final static Xor8 FUNCTION = new Xor8();
	
	
	
	private Xor8() {
		super("XOR-8", 1);
	}
	
	
	
	/**
	 * Returns a new hasher of this hash function.
	 * @return a new hasher of this hash function
	 */
	@Override
	public Hasher newHasher() {
		return new Xor8Hasher(this);
	}
	
}