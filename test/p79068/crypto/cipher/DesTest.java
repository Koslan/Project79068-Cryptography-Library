package p79068.crypto.cipher;

import org.junit.Test;
import p79068.crypto.CryptoUtils;


public final class DesTest {
	
	@Test
	public void testKeyBits() {
		// NIST SP 800-17: Appendix B - Table 2
		String[][] testCases = {
			{"8001010101010101", "0000000000000000", "95A8D72813DAA94D"},
			{"4001010101010101", "0000000000000000", "0EEC1487DD8C26D5"},
			{"2001010101010101", "0000000000000000", "7AD16FFB79C45926"},
			{"1001010101010101", "0000000000000000", "D3746294CA6A6CF3"},
			{"0801010101010101", "0000000000000000", "809F5F873C1FD761"},
			{"0401010101010101", "0000000000000000", "C02FAFFEC989D1FC"},
			{"0201010101010101", "0000000000000000", "4615AA1D33E72F10"},
			{"0180010101010101", "0000000000000000", "2055123350C00858"},
			{"0140010101010101", "0000000000000000", "DF3B99D6577397C8"},
			{"0120010101010101", "0000000000000000", "31FE17369B5288C9"},
			{"0110010101010101", "0000000000000000", "DFDD3CC64DAE1642"},
			{"0108010101010101", "0000000000000000", "178C83CE2B399D94"},
			{"0104010101010101", "0000000000000000", "50F636324A9B7F80"},
			{"0102010101010101", "0000000000000000", "A8468EE3BC18F06D"},
			{"0101800101010101", "0000000000000000", "A2DC9E92FD3CDE92"},
			{"0101400101010101", "0000000000000000", "CAC09F797D031287"},
			{"0101200101010101", "0000000000000000", "90BA680B22AEB525"},
			{"0101100101010101", "0000000000000000", "CE7A24F350E280B6"},
			{"0101080101010101", "0000000000000000", "882BFF0AA01A0B87"},
			{"0101040101010101", "0000000000000000", "25610288924511C2"},
			{"0101020101010101", "0000000000000000", "C71516C29C75D170"},
			{"0101018001010101", "0000000000000000", "5199C29A52C9F059"},
			{"0101014001010101", "0000000000000000", "C22F0A294A71F29F"},
			{"0101012001010101", "0000000000000000", "EE371483714C02EA"},
			{"0101011001010101", "0000000000000000", "A81FBD448F9E522F"},
			{"0101010801010101", "0000000000000000", "4F644C92E192DFED"},
			{"0101010401010101", "0000000000000000", "1AFA9A66A6DF92AE"},
			{"0101010201010101", "0000000000000000", "B3C1CC715CB879D8"},
			{"0101010180010101", "0000000000000000", "19D032E64AB0BD8B"},
			{"0101010140010101", "0000000000000000", "3CFAA7A7DC8720DC"},
			{"0101010120010101", "0000000000000000", "B7265F7F447AC6F3"},
			{"0101010110010101", "0000000000000000", "9DB73B3C0D163F54"},
			{"0101010108010101", "0000000000000000", "8181B65BABF4A975"},
			{"0101010104010101", "0000000000000000", "93C9B64042EAA240"},
			{"0101010102010101", "0000000000000000", "5570530829705592"},
			{"0101010101800101", "0000000000000000", "8638809E878787A0"},
			{"0101010101400101", "0000000000000000", "41B9A79AF79AC208"},
			{"0101010101200101", "0000000000000000", "7A9BE42F2009A892"},
			{"0101010101100101", "0000000000000000", "29038D56BA6D2745"},
			{"0101010101080101", "0000000000000000", "5495C6ABF1E5DF51"},
			{"0101010101040101", "0000000000000000", "AE13DBD561488933"},
			{"0101010101020101", "0000000000000000", "024D1FFA8904E389"},
			{"0101010101018001", "0000000000000000", "D1399712F99BF02E"},
			{"0101010101014001", "0000000000000000", "14C1D7C1CFFEC79E"},
			{"0101010101012001", "0000000000000000", "1DE5279DAE3BED6F"},
			{"0101010101011001", "0000000000000000", "E941A33F85501303"},
			{"0101010101010801", "0000000000000000", "DA99DBBC9A03F379"},
			{"0101010101010401", "0000000000000000", "B7FC92F91D8E92E9"},
			{"0101010101010201", "0000000000000000", "AE8E5CAA3CA04E85"},
			{"0101010101010180", "0000000000000000", "9CC62DF43B6EED74"},
			{"0101010101010140", "0000000000000000", "D863DBB5C59A91A0"},
			{"0101010101010120", "0000000000000000", "A1AB2190545B91D7"},
			{"0101010101010110", "0000000000000000", "0875041E64C570F7"},
			{"0101010101010108", "0000000000000000", "5A594528BEBEF1CC"},
			{"0101010101010104", "0000000000000000", "FCDB3291DE21F0C0"},
			{"0101010101010102", "0000000000000000", "869EFD7F9F265A09"},
		};
		for (String[] tc : testCases)
			CryptoUtils.testCipher(Des.DES_64_CIPHER, tc[0], tc[1], tc[2]);
		
	}
	
	
	@Test
	public void testVaryingKey() {
		// NIST SP 800-17: Appendix B - Table 3
		String[][] testCases = {
			{"1046913489980131", "0000000000000000", "88D55E54F54C97B4"},
			{"1007103489988020", "0000000000000000", "0C0CC00C83EA48FD"},
			{"10071034C8980120", "0000000000000000", "83BC8EF3A6570183"},
			{"1046103489988020", "0000000000000000", "DF725DCAD94EA2E9"},
			{"1086911519190101", "0000000000000000", "E652B53B550BE8B0"},
			{"1086911519580101", "0000000000000000", "AF527120C485CBB0"},
			{"5107B01519580101", "0000000000000000", "0F04CE393DB926D5"},
			{"1007B01519190101", "0000000000000000", "C9F00FFC74079067"},
			{"3107915498080101", "0000000000000000", "7CFD82A593252B4E"},
			{"3107919498080101", "0000000000000000", "CB49A2F9E91363E3"},
			{"10079115B9080140", "0000000000000000", "00B588BE70D23F56"},
			{"3107911598080140", "0000000000000000", "406A9A6AB43399AE"},
			{"1007D01589980101", "0000000000000000", "6CB773611DCA9ADA"},
			{"9107911589980101", "0000000000000000", "67FD21C17DBB5D70"},
			{"9107D01589190101", "0000000000000000", "9592CB4110430787"},
			{"1007D01598980120", "0000000000000000", "A6B7FF68A318DDD3"},
			{"1007940498190101", "0000000000000000", "4D102196C914CA16"},
			{"0107910491190401", "0000000000000000", "2DFA9F4573594965"},
			{"0107910491190101", "0000000000000000", "B46604816C0E0774"},
			{"0107940491190401", "0000000000000000", "6E7E6221A4F34E87"},
			{"19079210981A0101", "0000000000000000", "AA85E74643233199"},
			{"1007911998190801", "0000000000000000", "2E5A19DB4D1962D6"},
			{"10079119981A0801", "0000000000000000", "23A866A809D30894"},
			{"1007921098190101", "0000000000000000", "D812D961F017D320"},
			{"100791159819010B", "0000000000000000", "055605816E58608F"},
			{"1004801598190101", "0000000000000000", "ABD88E8B1B7716F1"},
			{"1004801598190102", "0000000000000000", "537AC95BE69DA1E1"},
			{"1004801598190108", "0000000000000000", "AED0F6AE3C25CDD8"},
			{"1002911598100104", "0000000000000000", "B3E35A5EE53E7B8D"},
			{"1002911598190104", "0000000000000000", "61C79C71921A2EF8"},
			{"1002911598100201", "0000000000000000", "E2F5728F0995013C"},
			{"1002911698100101", "0000000000000000", "1AEAC39A61F0A464"},
		};
		for (String[] tc : testCases)
			CryptoUtils.testCipher(Des.DES_64_CIPHER, tc[0], tc[1], tc[2]);
	}
	
	
	@Test
	public void testPlaintextBits() {
		// NIST SP 800-17: Appendix B - Table 1
		String[][] testCases = {
			{"0101010101010101", "8000000000000000", "95F8A5E5DD31D900"},
			{"0101010101010101", "4000000000000000", "DD7F121CA5015619"},
			{"0101010101010101", "2000000000000000", "2E8653104F3834EA"},
			{"0101010101010101", "1000000000000000", "4BD388FF6CD81D4F"},
			{"0101010101010101", "0800000000000000", "20B9E767B2FB1456"},
			{"0101010101010101", "0400000000000000", "55579380D77138EF"},
			{"0101010101010101", "0200000000000000", "6CC5DEFAAF04512F"},
			{"0101010101010101", "0100000000000000", "0D9F279BA5D87260"},
			{"0101010101010101", "0080000000000000", "D9031B0271BD5A0A"},
			{"0101010101010101", "0040000000000000", "424250B37C3DD951"},
			{"0101010101010101", "0020000000000000", "B8061B7ECD9A21E5"},
			{"0101010101010101", "0010000000000000", "F15D0F286B65BD28"},
			{"0101010101010101", "0008000000000000", "ADD0CC8D6E5DEBA1"},
			{"0101010101010101", "0004000000000000", "E6D5F82752AD63D1"},
			{"0101010101010101", "0002000000000000", "ECBFE3BD3F591A5E"},
			{"0101010101010101", "0001000000000000", "F356834379D165CD"},
			{"0101010101010101", "0000800000000000", "2B9F982F20037FA9"},
			{"0101010101010101", "0000400000000000", "889DE068A16F0BE6"},
			{"0101010101010101", "0000200000000000", "E19E275D846A1298"},
			{"0101010101010101", "0000100000000000", "329A8ED523D71AEC"},
			{"0101010101010101", "0000080000000000", "E7FCE22557D23C97"},
			{"0101010101010101", "0000040000000000", "12A9F5817FF2D65D"},
			{"0101010101010101", "0000020000000000", "A484C3AD38DC9C19"},
			{"0101010101010101", "0000010000000000", "FBE00A8A1EF8AD72"},
			{"0101010101010101", "0000008000000000", "750D079407521363"},
			{"0101010101010101", "0000004000000000", "64FEED9C724C2FAF"},
			{"0101010101010101", "0000002000000000", "F02B263B328E2B60"},
			{"0101010101010101", "0000001000000000", "9D64555A9A10B852"},
			{"0101010101010101", "0000000800000000", "D106FF0BED5255D7"},
			{"0101010101010101", "0000000400000000", "E1652C6B138C64A5"},
			{"0101010101010101", "0000000200000000", "E428581186EC8F46"},
			{"0101010101010101", "0000000100000000", "AEB5F5EDE22D1A36"},
			{"0101010101010101", "0000000080000000", "E943D7568AEC0C5C"},
			{"0101010101010101", "0000000040000000", "DF98C8276F54B04B"},
			{"0101010101010101", "0000000020000000", "B160E4680F6C696F"},
			{"0101010101010101", "0000000010000000", "FA0752B07D9C4AB8"},
			{"0101010101010101", "0000000008000000", "CA3A2B036DBC8502"},
			{"0101010101010101", "0000000004000000", "5E0905517BB59BCF"},
			{"0101010101010101", "0000000002000000", "814EEB3B91D90726"},
			{"0101010101010101", "0000000001000000", "4D49DB1532919C9F"},
			{"0101010101010101", "0000000000800000", "25EB5FC3F8CF0621"},
			{"0101010101010101", "0000000000400000", "AB6A20C0620D1C6F"},
			{"0101010101010101", "0000000000200000", "79E90DBC98F92CCA"},
			{"0101010101010101", "0000000000100000", "866ECEDD8072BB0E"},
			{"0101010101010101", "0000000000080000", "8B54536F2F3E64A8"},
			{"0101010101010101", "0000000000040000", "EA51D3975595B86B"},
			{"0101010101010101", "0000000000020000", "CAFFC6AC4542DE31"},
			{"0101010101010101", "0000000000010000", "8DD45A2DDF90796C"},
			{"0101010101010101", "0000000000008000", "1029D55E880EC2D0"},
			{"0101010101010101", "0000000000004000", "5D86CB23639DBEA9"},
			{"0101010101010101", "0000000000002000", "1D1CA853AE7C0C5F"},
			{"0101010101010101", "0000000000001000", "CE332329248F3228"},
			{"0101010101010101", "0000000000000800", "8405D1ABE24FB942"},
			{"0101010101010101", "0000000000000400", "E643D78090CA4207"},
			{"0101010101010101", "0000000000000200", "48221B9937748A23"},
			{"0101010101010101", "0000000000000100", "DD7C0BBD61FAFD54"},
			{"0101010101010101", "0000000000000080", "2FBC291A570DB5C4"},
			{"0101010101010101", "0000000000000040", "E07C30D7E4E26E12"},
			{"0101010101010101", "0000000000000020", "0953E2258E8E90A1"},
			{"0101010101010101", "0000000000000010", "5B711BC4CEEBF2EE"},
			{"0101010101010101", "0000000000000008", "CC083F1E6D9E85F6"},
			{"0101010101010101", "0000000000000004", "D2FD8867D50D2DFE"},
			{"0101010101010101", "0000000000000002", "06E7EA22CE92708F"},
			{"0101010101010101", "0000000000000001", "166B40B44ABA4BD6"},
		};
		for (String[] tc : testCases)
			CryptoUtils.testCipher(Des.DES_64_CIPHER, tc[0], tc[1], tc[2]);
	}
	
	
	@Test
	public void testComprehensive() {
		String[][] testCases = {
			// From http://cryptomanager.com/tv.html
			{"752878397493CB70", "1122334455667788", "B5219EE81AA7499D"},
			{"752878397493CB70", "99AABBCCDDEEFF00", "2196687E13973856"},
			
			// NIST SP 800-17: Appendix A
			{"10316E028C8F3B4A", "0000000000000000", "82DCBAFBDEAB6602"},
			
			// NIST SP 800-17: Appendix B - Table 4
			{"7CA110454A1A6E57", "01A1D6D039776742", "690F5B0D9A26939B"},
			{"0131D9619DC1376E", "5CD54CA83DEF57DA", "7A389D10354BD271"},
			{"07A1133E4A0B2686", "0248D43806F67172", "868EBB51CAB4599A"},
			{"3849674C2602319E", "51454B582DDF440A", "7178876E01F19B2A"},
			{"04B915BA43FEB5B6", "42FD443059577FA2", "AF37FB421F8C4095"},
			{"0113B970FD34F2CE", "059B5E0851CF143A", "86A560F10EC6D85B"},
			{"0170F175468FB5E6", "0756D8E0774761D2", "0CD3DA020021DC09"},
			{"43297FAD38E373FE", "762514B829BF486A", "EA676B2CB7DB2B7A"},
			{"07A7137045DA2A16", "3BDD119049372802", "DFD64A815CAF1A0F"},
			{"04689104C2FD3B2F", "26955F6835AF609A", "5C513C9C4886C088"},
			{"37D06BB516CB7546", "164D5E404F275232", "0A2AEEAE3FF4AB77"},
			{"1F08260D1AC2465E", "6B056E18759F5CCA", "EF1BF03E5DFA575A"},
			{"584023641ABA6176", "004BD6EF09176062", "88BF0DB6D70DEE56"},
			{"025816164629B007", "480D39006EE762F2", "A1F9915541020B56"},
			{"49793EBC79B3258F", "437540C8698F3CFA", "6FBF1CAFCFFD0556"},
			{"4FB05E1515AB73A7", "072D43A077075292", "2F22E49BAB7CA1AC"},
			{"49E95D6D4CA229BF", "02FE55778117F12A", "5A6B612CC26CCE4A"},
			{"018310DC409B26D6", "1D9D5C5018F728C2", "5F4C038ED12B2E41"},
			{"1C587F1C13924FEF", "305532286D6F295A", "63FAC0D034D9F793"},
		};
		
		for (String[] tc : testCases)
			CryptoUtils.testCipher(Des.DES_64_CIPHER, tc[0], tc[1], tc[2]);
	}
	
}
