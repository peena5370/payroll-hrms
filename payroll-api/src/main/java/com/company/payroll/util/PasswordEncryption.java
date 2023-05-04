package com.company.payroll.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class PasswordEncryption {
	private static final SecureRandom RANDOM = new SecureRandom();
	private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int DEFAULT_ITERATIONS = 310000;
	private static final int DEFAULT_SALT_LENGTH = 32;
	private static final SecretKeyFactoryAlgorithm SKFA = SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512;
	private static final int INIT_STRENGTH = 12;
	private static final int DEFAULT_PROCESSOR_COST = (int) Math.pow(2, 14);
	private static final int DEFAULT_RAM_COST = 8;
	private static final int PARALLELIZATION = 1;
	private static final int DEFAULT_KEY_LENGTH = 32;
    
    /**
     * Method to generate the salt value.
     * @param	lentgh			Salt value length
     * @return	salt string
     */
    public static String getSaltvalue(int length) {  
        StringBuilder finalval = new StringBuilder(length);  
  
        for (int i = 0; i < length; i++) {  
            finalval.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));  
        }  
        return new String(finalval);  
    }
    
    /**
     * Method to generate the hash value
     * @param	password		Array character password
     * @param	salt
     * 			Salt value
     */
    public static byte[] hash(char[] password, byte[] salt) {  
        PBEKeySpec spec = new PBEKeySpec(password, salt, DEFAULT_ITERATIONS, DEFAULT_KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {  
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            return skf.generateSecret(spec).getEncoded();  
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {  
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);  
        } finally {  
            spec.clearPassword();
        }  
    }  
  
    /**
     * Method to encrypt the password using the original password and salt value.
     * @param	password		Get string password
     * @param	salt			Get salt value
     * @return	Base64 string
     */
    public static String generateSecurePassword(String password, String salt) {  
        String finalval = null;  
  
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());  
   
        finalval = Base64.getEncoder().encodeToString(securePassword);  
   
        return finalval;  
    }
    
    /**
     * Method to verify if both password matches or not 
     * @param	providedPassword 	Get password string
     * @param	securePassword 		Get Encrypted password
     * @param	salt 				Get salt value
     * @return	boolean
     */
    public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {  
        boolean finalval = false;  
          
        // Generate New secure password with the same salt
        String newSecurePassword = generateSecurePassword(providedPassword, salt);  
          
        // Check if two passwords are equal
        finalval = newSecurePassword.equalsIgnoreCase(securedPassword);
        return finalval;  
    }
    
    /**
	 * 
	 * Created 22 Apr 2023
	 * @param cipher
	 * @param keySize	key length(128, 192, 256) in bits
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static SecretKey generateSecretKey(String cipher, int keySize) 
			throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(cipher);
		keyGenerator.init(keySize, SecureRandom.getInstanceStrong());
		
		return keyGenerator.generateKey();
	}
	
	/**
	 * 
	 * Created 22 Apr 2023
	 * @param secretKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static Map<String, PasswordEncoder> generatePasswordEncoder(String secretKey) 
			throws NoSuchAlgorithmException {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		// TODO 
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder(secretKey, DEFAULT_SALT_LENGTH, DEFAULT_ITERATIONS, SKFA));
		encoders.put("pbkdf2@SpringSecurity_v5_8", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
		encoders.put("scrypt@SpringSecurity_v5_8", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
		encoders.put("argon2@SpringSecurity_v5_8", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
		
		return encoders;
	}
	
	/**
	 * Getting password secret key
	 * @param password
	 * @param salt
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static SecretKey getKeyFromPassword(String password, String salt) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), DEFAULT_ITERATIONS, DEFAULT_SALT_LENGTH);
		SecretKey originalKey = new SecretKeySpec(skf.generateSecret(pbeKeySpec).getEncoded(), "AES");

        return originalKey; 
	}
	
	/**
	 * Method of converting secret key to string
	 * Created 22 Apr 2023
	 * @param secretKey
	 * @return
	 */
	public static String convertSecretKeyToString(SecretKey secretKey) {
		byte[] rawData = secretKey.getEncoded();
		String encodedKey = Base64.getEncoder().encodeToString(rawData);
		
		return encodedKey;
	}
	
	/**
	 * Method of converting string to secret key
	 * Created 22 Apr 2023
	 * @param encodedKey
	 * @return
	 */
	public static SecretKey convertStringToSecretKey(String encodedKey) {
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		
		return originalKey;
	}

	/**
	 * Generate random salt value
	 * @param size
	 * @return
	 */
	public static byte[] generateRandomSalt(int size) {
		byte[] salt = new byte[size];
		RANDOM.nextBytes(salt);
		
		return salt;
	}
	
	/**
	 * Generate random string with selected string length
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {  
        StringBuilder stringBuilder = new StringBuilder(length);  
  
        for (int i = 0; i < length; i++) {  
            stringBuilder.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));  
        }  
        
        return new String(stringBuilder);  
    }
	
	/**
	 * 
	 * Created 22 Apr 2023
	 * @param password
	 * @return
	 */
	public static String encodePasswordAsBCrypt(String password) {
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(INIT_STRENGTH, new SecureRandom());

	    return encoder.encode(password); 
	} 
	
	/**
	 * 
	 * Created 22 Apr 2023
	 * @param rawPassword
	 * @param encryptedPassword
	 * @return
	 */
	public static Boolean bCryptPasswordMatched(String rawPassword, String encryptedPassword) {
	    boolean bool = false;
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(INIT_STRENGTH, new SecureRandom());

	    if(encoder.matches(rawPassword, encryptedPassword)) {
	      bool = true;
	    }

	    return bool;
	}
	
	/**
	 *
	 * Created 22 Apr 2023
	 * @param password
	 * @param secretKey
	 * @return
	 */
	public static String encodePasswordAsPbkdf2(String password, String secretKey) {
	    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(secretKey, DEFAULT_SALT_LENGTH, 
	    															DEFAULT_ITERATIONS, SKFA);
		pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);

	    return pbkdf2PasswordEncoder.encode(password);
	}
	
	/**
	 * 
	 * Created 22 Apr 2023
	 * @param rawPassword
	 * @param hashedPassword
	 * @param secretKey
	 * @return
	 */
	public static Boolean pbkdf2PasswordMatched(String rawPassword, String hashedPassword, String secretKey) {
	    boolean bool = false;
	    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(secretKey, DEFAULT_SALT_LENGTH, 
	    															DEFAULT_ITERATIONS, SKFA);
	    pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
	    
	    if(pbkdf2PasswordEncoder.matches(rawPassword, hashedPassword)) {
	      bool = true;
	    }

	    return bool;
	}
	
	/**
	 * 
	 * Created 22 Apr 2023
	 * @param plainPassword
	 * @return
	 */
	public static String encodePasswordAsSCrypt(String plainPassword) {
	    SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder(DEFAULT_PROCESSOR_COST, DEFAULT_RAM_COST, 
	    															PARALLELIZATION, DEFAULT_KEY_LENGTH, DEFAULT_SALT_LENGTH);

	    return sCryptPasswordEncoder.encode(plainPassword);
	}
	
	/**
	 * 
	 * Created 22 Apr 2023
	 * @param rawPassword
	 * @param hashedPassword
	 * @return
	 */
	public static boolean scryptPasswordMatched(String rawPassword, String hashedPassword) {
	    boolean bool = false;
	    SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder(DEFAULT_PROCESSOR_COST, DEFAULT_RAM_COST, 
	    															PARALLELIZATION, DEFAULT_KEY_LENGTH, DEFAULT_SALT_LENGTH);

	    if(sCryptPasswordEncoder.matches(rawPassword, hashedPassword)) {
	      bool = true;
	    }

	    return bool;
	}
}
