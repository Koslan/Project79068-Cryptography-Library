package p79068.crypto.hash;

import java.math.BigInteger;
import p79068.Assert;
import p79068.crypto.Zeroizable;
import p79068.crypto.Zeroizer;
import p79068.hash.AbstractHasher;
import p79068.hash.HashValue;


/**
 * A hasher that only applies the compression function after each block.
 * <p>The instance returned by a BlockHashFunction is not necessarily a BlockHasher.</p>
 * @see BlockHasherCore
 */
public final class BlockHasher extends AbstractHasher implements Zeroizable {
	
	/**
	 * The data of the current block.
	 */
	private byte[] block;
	
	/**
	 * The number of bytes filled in the current block. It is in the range [{@code 0}, {@code block.length}) initially and after each {@code update()} operation.
	 */
	private int blockFilled;
	
	/**
	 * The total length of the message, in bytes.
	 */
	private BigInteger length;
	
	
	private BlockHasherCore core;
	
	
	
	/**
	 * Constructs a new instance with the specified hash algorithm and block length.
	 */
	public BlockHasher(BlockHashFunction func, BlockHasherCore core) {
		super(func);
		Assert.assertNotNull(core);
		block = new byte[func.getBlockLength()];
		blockFilled = 0;
		length = BigInteger.ZERO;
		this.core = core;
	}
	
	
	
	/**
	 * Updates the current hash with the specified byte.
	 * @throws IllegalStateException if this object has been zeroized
	 */
	@Override
	public void update(byte b) {
		if (hashFunction == null)
			throw new IllegalStateException("Already zeroized");
		block[blockFilled] = b;
		blockFilled++;
		if (blockFilled == block.length) {
			core.compress(block);
			blockFilled = 0;
		}
		length = length.add(BigInteger.ONE);
	}
	
	
	/**
	 * Updates the current hash with the specified byte array.
	 * @throws NullPointerException if {@code b} is {@code null}
	 * @throws IndexOutOfBoundsException if {@code off} and {@code len} specify that indices out of array {@code b}'s range to be accessed
	 * @throws IllegalStateException if this object has been zeroized
	 */
	@Override
	public void update(byte[] b, int off, int len) {
		if (hashFunction == null)
			throw new IllegalStateException("Already zeroized");
		Assert.assertRangeInBounds(b.length, off, len);
		
		int blockLen = block.length;
		length = length.add(BigInteger.valueOf(len));  // Update length now, before len changes
		
		// Try to fill up current block
		if (blockFilled > 0) {
			int n = Math.min(blockLen - blockFilled, len);
			System.arraycopy(b, off, block, blockFilled, n);
			blockFilled += n;
			if (blockFilled == blockLen) {
				core.compress(block);
				off += n;
				len -= n;
			} else
				return;
		}
		
		// Process whole blocks
		if (len >= blockLen) {
			int n = len / blockLen * blockLen;
			core.compress(b, off, n);
			off += n;
			len -= n;
		}
		
		// Process remaining bytes (0 <= len < block.length)
		System.arraycopy(b, off, block, 0, len);
		blockFilled = len;
	}
	
	
	/**
	 * Returns the hash value.
	 * @throws IllegalStateException if this object has been zeroized
	 */
	@Override
	public HashValue getHash() {
		if (hashFunction == null)
			throw new IllegalStateException("Already zeroized");
		return core.clone().getHashDestructively(block.clone(), blockFilled, length);
	}
	
	
	/**
	 * Returns a new hasher with the same internal state as this one's. The returned object uses the same algorithm, but its type need not be the same as this one's.
	 * @return a clone of this object
	 * @throws IllegalStateException if this object has been zeroized
	 */
	@Override
	public BlockHasher clone() {
		if (hashFunction == null)
			throw new IllegalStateException("Already zeroized");
		BlockHasher result = (BlockHasher)super.clone();
		result.block = result.block.clone();
		result.core = result.core.clone();
		return result;
	}
	
	
	public void zeroize() {
		if (hashFunction == null)
			throw new IllegalStateException("Already zeroized");
		length = null;
		blockFilled = 0;
		block = Zeroizer.clear(block);
		hashFunction = null;
		core.zeroize();
		core = null;
	}
	
}
