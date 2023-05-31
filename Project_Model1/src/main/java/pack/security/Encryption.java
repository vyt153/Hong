package pack.security;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;

public class Encryption {
	private	final	static	String	ALGORITHM	=	"AES/CBC/PKCS5Padding";
	private	final	static	String	SECRET_KEY	=	"raaqaaavaaajaaagaaaeaaaadaaaabaa";
	//	비밀키는	문자열 	내용에	관계없는	32자리	값.	보기.	"ABCDEFGHIJKLMNOPQRSTUVWXYZ012345"
	//	공백	32칸도	가능하며	32자리 	값에	의해	암호화	코드가 	정의됨.	보기.	"																																"
	
	HttpSession session = null;
	
	// 암호화 메서드
	private	final	String	initVec	=	SECRET_KEY.substring(0,	16);			
	//	initVec,	initialization	vector,	초기화	벡터
	public	String	encrypt(String	txtParam, HttpSession session)	throws	Exception	{
		Cipher	cipher	=	Cipher.getInstance(ALGORITHM);
		SecretKeySpec	keySpec	=	new	SecretKeySpec(SECRET_KEY.getBytes(),	"AES");
		IvParameterSpec	ivParamSpec	=	new	IvParameterSpec(initVec.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE,	keySpec,	ivParamSpec);
		byte[]	encryptCode	=	cipher.doFinal(txtParam.getBytes("UTF-8"));
		String chkParam = Base64.getEncoder().encodeToString(encryptCode);
		session.setAttribute("chkAuth", chkParam);
		return	chkParam;
}
//	복호화 	메서드	시작,	반환값이	원본 	데이터입니다.
	public	String	decrypt(String	cipherTxt)	throws	Exception	{
		Cipher	cipher	=	Cipher.getInstance(ALGORITHM);
		SecretKeySpec	keySpec	=	new	SecretKeySpec(SECRET_KEY.getBytes(),	"AES");
		IvParameterSpec	ivParamSpec	=	new	IvParameterSpec(initVec.getBytes());
		cipher.init(Cipher.DECRYPT_MODE,	keySpec,	ivParamSpec);
		byte[]	decodeBytes	=	Base64.getDecoder().decode(cipherTxt);
		byte[]	decryptCode	=	cipher.doFinal(decodeBytes);
		return	new	String(decryptCode,	"UTF-8");
}
}
