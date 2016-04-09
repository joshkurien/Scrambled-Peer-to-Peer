package Crypt;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import org.bouncycastle.util.encoders.Base64;



public class key_string {
	 public static String base64Encode(byte[] token) {
		    byte[] encodedBytes = Base64.encode(token);
		    return new String(encodedBytes, Charset.forName("UTF-8"));
		}


		public static byte[] base64Decode(String token) {
		    byte[] decodedBytes = Base64.decode(token.getBytes());
		    return decodedBytes;
		}
	
	public static PrivateKey loadPrivateKey(String key64) throws GeneralSecurityException {
	    byte[] clear = base64Decode(key64);
	    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
	    KeyFactory fact = KeyFactory.getInstance("DSA");
	    PrivateKey priv = fact.generatePrivate(keySpec);
	    Arrays.fill(clear, (byte) 0);
	    return priv;
	}


	public static PublicKey loadPublicKey(String stored) throws GeneralSecurityException {
	    byte[] data = base64Decode(stored);
	    X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
	    KeyFactory fact = KeyFactory.getInstance("DSA");
	    return fact.generatePublic(spec);
	}

	public static String savePrivateKey(PrivateKey priv) throws GeneralSecurityException {
	    KeyFactory fact = KeyFactory.getInstance("DSA");
	    PKCS8EncodedKeySpec spec = fact.getKeySpec(priv,
	            PKCS8EncodedKeySpec.class);
	    byte[] packed = spec.getEncoded();
	    String key64 = base64Encode(packed);

	    Arrays.fill(packed, (byte) 0);
	    return key64;
	}


	public static String savePublicKey(PublicKey publ) throws GeneralSecurityException {
	    KeyFactory fact = KeyFactory.getInstance("DSA");
	    X509EncodedKeySpec spec = fact.getKeySpec(publ,
	            X509EncodedKeySpec.class);
	    return base64Encode(spec.getEncoded());
	}


	public static void main(String[] args) throws Exception {
	    KeyPairGenerator gen = KeyPairGenerator.getInstance("DSA");
	    KeyPair pair = gen.generateKeyPair();

	    String pubKey = savePublicKey(pair.getPublic());
	    PublicKey pubSaved = loadPublicKey(pubKey);
	    System.out.println(pair.getPublic()+"\n"+pubSaved);

	    String privKey = savePrivateKey(pair.getPrivate());
	    PrivateKey privSaved = loadPrivateKey(privKey);
	    System.out.println(pair.getPrivate()+"\n"+privSaved);
	}
	
}
