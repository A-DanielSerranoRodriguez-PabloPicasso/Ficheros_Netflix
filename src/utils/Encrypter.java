package utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypter {
	/**
	 * Encrypts the password with a SHA-256 hash
	 * 
	 * @param input String that represents the password
	 * @return byte[]
	 * @throws NoSuchAlgorithmException If the algorithm requested is not available
	 */
	private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
		// Static getInstance method is called with hashing SHA
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		// digest() method called
		// to calculate message digest of an input
		// and return array of byte
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Returns the string conversion of the hash of the passed text
	 * 
	 * @param input String
	 * @return String
	 */
	public static String toHexString(String input) {
		// Convert byte array into signum representation
		BigInteger number;
		try {
			number = new BigInteger(1, getSHA(input));

			// Convert message digest into hex value
			StringBuilder hexString = new StringBuilder(number.toString(16));
			// Pad with leading zeros
			while (hexString.length() < 32) {
				hexString.insert(0, '0');
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}

}
