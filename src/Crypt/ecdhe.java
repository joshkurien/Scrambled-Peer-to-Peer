package Crypt;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

import javax.crypto.KeyAgreement;

public class ecdhe {
	
	private static KeyAgreement KeyAgree;
	private static KeyPair Pair; //Holds Private And Public key pair for this session
	
  public static void main(String[] args) throws Exception {
    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDH", "BC");
    EllipticCurve curve = new EllipticCurve(new ECFieldFp(new BigInteger(
        "fffffffffffffffffffffffffffffffeffffffffffffffff", 16)), new BigInteger(
        "fffffffffffffffffffffffffffffffefffffffffffffffc", 16), new BigInteger(
        "fffffffffffffffffffffffffffffffefffffffffffffffc", 16));

    ECParameterSpec ecSpec = new ECParameterSpec(curve, new ECPoint(new BigInteger(
        "fffffffffffffffffffffffffffffffeffffffffffffffc", 16), new BigInteger(
        "fffffffffffffffffffffffffffffffefffffffffffffffc", 16)), new BigInteger(
        "fffffffffffffffffffffffffffffffefffffffffffffffc", 16), 1);

    keyGen.initialize(ecSpec, new SecureRandom());

    KeyAgreement aKeyAgree = KeyAgreement.getInstance("ECDH", "BC");
    KeyPair aPair = keyGen.generateKeyPair();
    KeyAgreement bKeyAgree = KeyAgreement.getInstance("ECDH", "BC");
    KeyPair bPair = keyGen.generateKeyPair();

    aKeyAgree.init(aPair.getPrivate());
    bKeyAgree.init(bPair.getPrivate());

    aKeyAgree.doPhase(bPair.getPublic(), true);
    bKeyAgree.doPhase(aPair.getPublic(), true);

    MessageDigest hash = MessageDigest.getInstance("SHA1", "BC");

   /* System.out.println(aPair.getPrivate());
    System.out.println(aPair.getPublic());
    System.out.println(bPair.getPrivate());
    System.out.println(bPair.getPublic());
    	*/
    
    System.out.println(new String(hash.digest(aKeyAgree.generateSecret())));
    System.out.println(new String(hash.digest(bKeyAgree.generateSecret())));
  }
  
  public static KeyPair keygen() throws Exception
  {
	  Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

	    KeyPairGenerator keyGen;
			keyGen = KeyPairGenerator.getInstance("ECDH", "BC");
		
	    EllipticCurve curve = new EllipticCurve(new ECFieldFp(new BigInteger(
	        "fffffffffffffffffffffffffffffffeffffffffffffffff", 16)), new BigInteger(
	        "fffffffffffffffffffffffffffffffefffffffffffffffc", 16), new BigInteger(
	        "fffffffffffffffffffffffffffffffefffffffffffffffc", 16));

	    ECParameterSpec ecSpec = new ECParameterSpec(curve, new ECPoint(new BigInteger(
	        "fffffffffffffffffffffffffffffffeffffffffffffffc", 16), new BigInteger(
	        "fffffffffffffffffffffffffffffffefffffffffffffffc", 16)), new BigInteger(
	        "fffffffffffffffffffffffffffffffefffffffffffffffc", 16), 1);

	    keyGen.initialize(ecSpec, new SecureRandom());

	    KeyAgree = KeyAgreement.getInstance("ECDH", "BC");
	    Pair = keyGen.generateKeyPair();

	    KeyAgree.init(Pair.getPrivate());
	    return Pair;
  }

  public static byte[] shared_secret(PublicKey rem_key,KeyPair local) throws Exception
  {
	  byte[] s_key;
	  Pair = local;
	  KeyAgree.doPhase(rem_key, true);
	  
	  MessageDigest hash = MessageDigest.getInstance("SHA1", "BC");
	  
	  s_key = hash.digest(KeyAgree.generateSecret());
	  	  
	  System.out.println(new String(s_key));   //for printing the shared secret
	  
	  return s_key;
  }

}