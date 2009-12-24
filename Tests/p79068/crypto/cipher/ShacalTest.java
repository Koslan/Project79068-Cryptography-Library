package p79068.crypto.cipher;

import org.junit.Test;
import p79068.crypto.CryptoUtils;


public class ShacalTest {
	
	@Test
	public void testShacal1() {
		CryptoUtils.testCipher(new Shacal1(64), "000102030405060708090A0B0C0D0E0F101112131415161718191A1B1C1D1E1F202122232425262728292A2B2C2D2E2F303132333435363738393A3B3C3D3E3F", "C42D18E512208847172CC63361967CD901F71472", "00112233445566778899AABBCCDDEEFF10213243");
		CryptoUtils.testCipher(new Shacal1(64), "2BD6459F82C5B300952C49104881FF482BD6459F82C5B300952C49104881FF482BD6459F82C5B300952C49104881FF482BD6459F82C5B300952C49104881FF48", "333AB9DA661D4BE70313EAE17F4846031A8CE016", "EA024714AD5C4D84EA024714AD5C4D84EA024714");
	}
	
	
	@Test
	public void testShacal2() {
		CryptoUtils.testCipher(new Shacal2(64), "000102030405060708090A0B0C0D0E0F101112131415161718191A1B1C1D1E1F202122232425262728292A2B2C2D2E2F303132333435363738393A3B3C3D3E3F", "98BCC10405AB0BFC686BECECAAD01AC19B452511BCEB9CB094F905C51CA45430", "00112233445566778899AABBCCDDEEFF102132435465768798A9BACBDCEDFE0F");
		CryptoUtils.testCipher(new Shacal2(64), "2BD6459F82C5B300952C49104881FF482BD6459F82C5B300952C49104881FF482BD6459F82C5B300952C49104881FF482BD6459F82C5B300952C49104881FF48", "481F122A75F2C4C3395140B5A951EBBA06D96BDFD9D8FF4FB59CBD1287808D5A", "EA024714AD5C4D84EA024714AD5C4D84EA024714AD5C4D84EA024714AD5C4D84");
	}
	
}