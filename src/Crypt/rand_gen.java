package Crypt;

import java.util.Random;
import org.bouncycastle.util.encoders.Hex;

public class rand_gen {
	public static String gen_hex(String[] Args)
	{
		byte[]  resBuf = new byte[48];
		new Random().nextBytes(resBuf);
		String  resStr = new String(Hex.encode(resBuf));
		return(resStr);
	}

}
