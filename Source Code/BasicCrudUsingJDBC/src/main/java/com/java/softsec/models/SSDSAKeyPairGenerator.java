package com.java.softsec.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class SSDSAKeyPairGenerator {
	
	private static JdbcTemplate jdbcTemplate;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	
	public PublicKey getPublicKey() {
		return publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void generatePair(String privateKeyFilePath, String userId){
		try {
			KeyPairGenerator g = KeyPairGenerator.getInstance("DSA");
			g.initialize(1024, new SecureRandom());
			KeyPair p = g.generateKeyPair();
			this.publicKey = p.getPublic();
			this.privateKey = p.getPrivate();

				try {
					if(privateKeyFilePath != null)
						writePrivate(privateKeyFilePath,this.privateKey);
					if(userId != null)
						writePublic(userId, this.publicKey);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void writePrivate(String filepath, PrivateKey pvkey) throws IOException{
		
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(pvkey.getEncoded());
		FileOutputStream file = new FileOutputStream(filepath);
		file.write(spec.getEncoded());
		file.close();
	}
	
public static void writePublic(String userId, PublicKey pubkey){
		
		X509EncodedKeySpec spec = new X509EncodedKeySpec(pubkey.getEncoded());
		String publickey_str = bytesToHex(spec.getEncoded());
		
		// TODO
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/software_sec1");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		/*add publickey_str to database with userId */
		jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(publickey_str);
		String sql="INSERT INTO keystore(exUserId,pubKey) Values (?,?);";
		jdbcTemplate.update(sql,userId,publickey_str);
	}
	
	public static PrivateKey loadPrivate(String filepath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException{
		File file = new File(filepath);
		FileInputStream ins = new FileInputStream(filepath);
		byte[] encoded_key = new byte[(int)file.length()];
		ins.read(encoded_key);
		ins.close();
		
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(encoded_key);
		KeyFactory kf = KeyFactory.getInstance("DSA");
		return kf.generatePrivate(spec);
	}
	
public static PublicKey loadPublic(String userId) throws NoSuchAlgorithmException, InvalidKeySpecException{
		
		String publickey_str = "";
		/* get publickey_str from database */

		// TODO
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/software_sec1");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		String sql="Select pubKey from keystore where exUserId= ?;";
		
				
		publickey_str = (String)jdbcTemplate.queryForObject(
						sql, new Object[] { userId }, String.class);
		
		byte[] encoded_key = hexStringToByteArray(publickey_str);
		KeyFactory kf = KeyFactory.getInstance("DSA");
		X509EncodedKeySpec spec = new X509EncodedKeySpec(encoded_key);
		return kf.generatePublic(spec);
	}
	
	// NOTE of Reference, the following function was taken from:
	// http://stackoverflow.com/a/140861
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
		
	// NOTE of Reference, the following function was taken from:
	// http://stackoverflow.com/a/9855338
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static byte[] signData(byte[] data, PrivateKey pk) throws NoSuchAlgorithmException, SignatureException{
		Signature signature;
		signature = Signature.getInstance("SHA/DSA");
		try {
			signature.initSign(pk);
			signature.update(data);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return signature.sign();
	}
	
	public static boolean verify_signature(byte[] data, byte[] signature, PublicKey pk) throws NoSuchAlgorithmException, SignatureException{
		Signature sig = Signature.getInstance("DSA");
		try {
			sig.initVerify(pk);
			sig.update(data);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sig.verify(signature);
	}
}

