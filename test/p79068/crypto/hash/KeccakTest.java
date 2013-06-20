package p79068.crypto.hash;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import p79068.hash.HashFunction;


public final class KeccakTest {
	
	private static String[] ASCII_MESSAGES = {
		"",
		"The quick brown fox jumps over the lazy dog",
		"The quick brown fox jumps over the lazy dog.",
	};
	
	private static String[] HEX_MESSAGES = {
		"13BD2811F6ED2B6F04FF3895ACEED7BEF8DCD45EB121791BC194A0F806206BFFC3B9281C2B308B1A729CE008119DD3066E9378ACDCC50A98A82E20738800B6CDDBE5FE9694AD6D",
		"1EED9CBA179A009EC2EC5508773DD305477CA117E6D569E66B5F64C6BC64801CE25A8424CE4A26D575B8A6FB10EAD3FD1992EDDDEEC2EBE7150DC98F63ADC3237EF57B91397AA8A7",
		"F13C972C52CB3CC4A4DF28C97F2DF11CE089B815466BE88863243EB318C2ADB1A417CB1041308598541720197B9B1CB5BA2318BD5574D1DF2174AF14884149BA9B2F446D609DF240CE335599957B8EC80876D9A085AE084907BC5961B20BF5F6CA58D5DAB38ADB",
		"E35780EB9799AD4C77535D4DDB683CF33EF367715327CF4C4A58ED9CBDCDD486F669F80189D549A9364FA82A51A52654EC721BB3AAB95DCEB4A86A6AFA93826DB923517E928F33E3FBA850D45660EF83B9876ACCAFA2A9987A254B137C6E140A21691E1069413848",
		"B771D5CEF5D1A41A93D15643D7181D2A2EF0A8E84D91812F20ED21F147BEF732BF3A60EF4067C3734B85BC8CD471780F10DC9E8291B58339A677B960218F71E793F2797AEA349406512829065D37BB55EA796FA4F56FD8896B49B2CD19B43215AD967C712B24E5032D065232E02C127409D2ED4146B9D75D763D52DB98D949D3B0FED6A8052FBB",
		"B32D95B0B9AAD2A8816DE6D06D1F86008505BD8C14124F6E9A163B5A2ADE55F835D0EC3880EF50700D3B25E42CC0AF050CCD1BE5E555B23087E04D7BF9813622780C7313A1954F8740B6EE2D3F71F768DD417F520482BD3A08D4F222B4EE9DBD015447B33507DD50F3AB4247C5DE9A8ABD62A8DECEA01E3B87C8B927F5B08BEB37674C6F8E380C04",
		"EA40E83CB18B3A242C1ECC6CCD0B7853A439DAB2C569CFC6DC38A19F5C90ACBF76AEF9EA3742FF3B54EF7D36EB7CE4FF1C9AB3BC119CFF6BE93C03E208783335C0AB8137BE5B10CDC66FF3F89A1BDDC6A1EED74F504CBE7290690BB295A872B9E3FE2CEE9E6C67C41DB8EFD7D863CF10F840FE618E7936DA3DCA5CA6DF933F24F6954BA0801A1294CD8D7E66DFAFEC",
		"157D5B7E4507F66D9A267476D33831E7BB768D4D04CC3438DA12F9010263EA5FCAFBDE2579DB2F6B58F911D593D5F79FB05FE3596E3FA80FF2F761D1B0E57080055C118C53E53CDB63055261D7C9B2B39BD90ACC32520CBBDBDA2C4FD8856DBCEE173132A2679198DAF83007A9B5C51511AE49766C792A29520388444EBEFE28256FB33D4260439CBA73A9479EE00C63",
	};
	
	
	
	@Test
	public void testKeccak224() {
		testHashFunction(
			Keccak.KECCAK_224_FUNCTION,
			new String[] {
				"F71837502BA8E10837BDD8D365ADB85591895602FC552B48B7390ABD",
				"310AEE6B30C47350576AC2873FA89FD190CDC488442F3EF654CF23FE",
				"C59D4EAEAC728671C635FF645014E2AFA935BEBFFDB5FBD207FFDEAB",
			},
			new String[] {
				"8A2AE6B9AA7B1E08F8C7DC3BF5AE876660D30F79391714A175381091",
				"702B1906A63D0F924AFEC3BB5E5C5742E85F9834EA6F5306644811A1",
				"E2594A633B2DC671FD0DDFD3BF7238332C425520827C524FB0E19778",
				"234764AAE8C39B1571D7741BB176FF86246070EC9AC97A1B2EB35472",
				"2594153AC2DE681F4DEE340FA344EC388773A377D5B89E503254FD2E",
				"42275C296937745758FF2B7BEE9A897191AE87E42BD10198D9466C19",
				"BFE15BB51F680F2F489F0FDEB32F271090A09D1563F29FEAF92104E0",
				"6D735FB7579135F61B771B2BB0D81514CDE9C977ACCF6FEAF6EDEBF0",
			});
	}
	
	
	@Test
	public void testKeccak256() {
		testHashFunction(
			Keccak.KECCAK_256_FUNCTION,
			new String[] {
				"C5D2460186F7233C927E7DB2DCC703C0E500B653CA82273B7BFAD8045D85A470",
				"4D741B6F1EB29CB2A9B9911C82F56FA8D73B04959D3D9D222895DF6C0B28AA15",
				"578951E24EFD62A3D63A86F7CD19AAA53C898FE287D2552133220370240B572D",
			},
			new String[] {
				"6A769F93F255B078FE73AFF68F0422A279939920E4690B4AFF0E433CFA3D3DF3",
				"C06DD4261638C44AFCB186F0AF5DE20EA53AA63316FBB71728F874FF3DACEB0D",
				"52CBC5DBE49B009663C43F079DD180E38A77533778062A72A29E864A58522922",
				"3A8DFCFD1B362003DDFA17910727539E64B18021ABBA018B5F58D71F7A449733",
				"BD6F5492582A7C1B116304DE28314DF9FFFE95B0DA11AF52FE9440A717A34859",
				"E717A7769448ABBE5FEF8187954A88AC56DED1D22E63940AB80D029585A21921",
				"344D129C228359463C40555D94213D015627E5871C04F106A0FEEF9361CDECB6",
				"4CE7C2B935F21FC34C5E56D940A555C593872AEC2F896DE4E68F2A017060F535",
			});
	}
	
	
	@Test
	public void testKeccak384() {
		testHashFunction(
			Keccak.KECCAK_384_FUNCTION,
			new String[] {
				"2C23146A63A29ACF99E73B88F8C24EAA7DC60AA771780CCC006AFBFA8FE2479B2DD2B21362337441AC12B515911957FF",
				"283990FA9D5FB731D786C5BBEE94EA4DB4910F18C62C03D173FC0A5E494422E8A0B3DA7574DAE7FA0BAF005E504063B3",
				"9AD8E17325408EDDB6EDEE6147F13856AD819BB7532668B605A24A2D958F88BD5C169E56DC4B2F89FFD325F6006D820B",
			},
			new String[] {
				"B115A9968B054C934C396D8188BA0C33A23C7189CE88B1DE4A06CD319792D28647EAE1D88FB0B87443E46292A5C645E8",
				"C8FAEF757E6D7B0AF46DA1E57C71ABB4AAF7CC91C5CDC33BA8A738172B95DE087EC4C92692CB40EE3787BCE3206FB7EA",
				"A4E5EE130FC105818CD1A0DE74F1085B9B4D93889C509DC3A208B5230D39D8F304BB403F72BF0CF5E02C4C4A0831F328",
				"9FB5700502E01926824F46E9F61894F9487DBCF8AE6217203C85606F975566539376D6239DB04AEF9BF48CA4F191A90B",
				"8FFDF6A4752D17D496F8ADEE7116BD2AF0A4B726BB3F4C5F85BE2C9DFC34055A509E4FE016930D9951A7212553E2E908",
				"278E83CFF1FF6CC4B3AC41F3879DA87AE63B535B43815E273687A4CC519855B452CB6AF0198BB9FD0F3E43739BC0CDD7",
				"FE680250CAB1FBDB6AC8800DDC28E70100DF8DAAE38DA27004872AB05D40B15AE93EB44266E3014F0960038B28252C7B",
				"511B13E53FD353FA4D38EF0CF8F1AF30DA554828A5FD1C53EC41F73D9ACA6C54AC7972C933AF4A2FC7AB852CA63A1BA6",
			});
	}
	
	
	@Test
	public void testKeccak512() {
		testHashFunction(
			Keccak.KECCAK_512_FUNCTION,
			new String[] {
				"0EAB42DE4C3CEB9235FC91ACFFE746B29C29A8C366B7C60E4E67C466F36A4304C00FA9CAF9D87976BA469BCBE06713B435F091EF2769FB160CDAB33D3670680E",
				"D135BB84D0439DBAC432247EE573A23EA7D3C9DEB2A968EB31D47C4FB45F1EF4422D6C531B5B9BD6F449EBCC449EA94D0A8F05F62130FDA612DA53C79659F609",
				"AB7192D2B11F51C7DD744E7B3441FEBF397CA07BF812CCEAE122CA4DED6387889064F8DB9230F173F6D1AB6E24B6E50F065B039F799F5592360A6558EB52D760",
			},
			new String[] {
				"48FC282F37A3E1FB5DF4D2DA1F7197EC899AE573CA08DF550E61EE847EEB1D24C074FF46BCAEE224EC7D8CEA4256154F0C4D434E682834F6D827BFBDF75112F5",
				"6B4B0F126863552A6F40F45E295DC79B9BA2A88EA7C3B2F607AC1A8431A97844C2A7B664443FB23C05739DF5494FE9824DB80B7F3E67872142F17E2C5544E1EF",
				"5F968CC6ECF71C588A3C3BA68858BBFF96861F66C0733FD61FA91A479A49618DF22D9490219DF8008DC78840AE022C5D41AF2B890D0214E562DA8DF0CB3F8522",
				"E7149461F9CD00B71C216C50041B3EDA9707D7360D4C21740C44C212256A31DA398FE09708E450EA4E2826B7EC20BEF76CD2FBD9D096AF6F77F84ABC2E4FB093",
				"9EDEEB10EE1B7BB8F16A280D8CC3EDA5E909C554419DDC523B69ECEDF2ADF3B3C9BC66FEF365342471C458126F083A3B8E7C0C9D9D77E9F90196B71F9AADF492",
				"A6054FFC3D81591BE964C4B004A3A21142365B59EE98B2873D488293F93A8D7154BF72100012C60D3C9418F6AF8EA66372CB4703F5F6381DE6D4B9B98CFF1E90",
				"5DA83B7E221933CD67FA2AF8C9934DB74CE822212C99E0EE01F5220B4FE1E9B0388E42E328A1D174E6368F5773853042543A9B493A94B625980B73DF3F3FCCBB",
				"72DE9184BEB5C6A37EA2C395734D0D5412991A57CFFCC13FF9B5FA0F2046EE87C61811FE8EF2470239D5066C220173DE5EBE41885ED8ACAE397FB395E6CA9AEE",
			});
	}
	
	
	@Test
	public void testZeroize() {
		HashUtils.testZeroization(Keccak.KECCAK_224_FUNCTION);
		HashUtils.testZeroization(Keccak.KECCAK_256_FUNCTION);
		HashUtils.testZeroization(Keccak.KECCAK_384_FUNCTION);
		HashUtils.testZeroization(Keccak.KECCAK_512_FUNCTION);
	}
	
	
	
	private static void testHashFunction(HashFunction func, String[] asciiAnswers, String[] hexAnswers) {
		assertEquals(ASCII_MESSAGES.length, asciiAnswers.length);
		assertEquals(HEX_MESSAGES.length, hexAnswers.length);
		for (int i = 0; i < ASCII_MESSAGES.length; i++)
			HashUtils.testAscii(func, ASCII_MESSAGES[i], asciiAnswers[i]);
		for (int i = 0; i < HEX_MESSAGES.length; i++)
			HashUtils.testHex(func, HEX_MESSAGES[i], hexAnswers[i]);
	}
	
}
