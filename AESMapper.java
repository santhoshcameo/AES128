package com.log.aes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class AESMapper {
	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES";
	
	/***************************** DONT DELETE CLUSTER ****************************************************/

	// basesd on the encryption / decryption data  read  from HDFS and performing its logic
	
	public static void aesEncryption(String key, String fileLocInput, String fileLocOut, String flag)
			throws CustomException {
		PerfromEncryDecryp(Cipher.ENCRYPT_MODE, key, fileLocInput, fileLocOut, flag);
	}

	public static void aesDecryption(String key, String fileLocOut, String fileDecPath, String flag)
			throws CustomException {
		PerfromEncryDecryp(Cipher.DECRYPT_MODE, key, fileLocOut, fileDecPath, flag);
	}

	private static void PerfromEncryDecryp(int flagCiph, String Filekey, String fileLocInput, String fileLocOut,
			String flag) throws CustomException {
// user data read and converting into cipher blocks using AES128
		Path pt = null;
		try {
			Key mySec = new SecretKeySpec(Filekey.getBytes(), ALGORITHM);
			Cipher aescipher = Cipher.getInstance(TRANSFORMATION);
			aescipher.init(flagCiph, mySec);

			
			// checking encryption or decrytpion based on flag
			if (flag.equalsIgnoreCase("Enc")) {

				pt = new Path(fileLocInput);

			}

			if (flag.equalsIgnoreCase("Dec")) {

				pt = new Path(fileLocInput);
			}

			FileSystem fs = FileSystem.get(new Configuration());

			byte[] newBytes = IOUtils.toByteArray(fs.open(pt));

			byte[] oBytes = aescipher.doFinal(newBytes);

			if (flag.equalsIgnoreCase("Enc")) {

				pt = new Path(fileLocOut);
			}

			if (flag.equalsIgnoreCase("Dec")) {

				pt = new Path(fileLocOut);
			}

			FSDataOutputStream newOut = fs.create(pt);
			newOut.write(oBytes);
			newOut.close();

		} catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException ex) {
			throw new CustomException("Exception happened, Please see log", ex);
		}
	}
	/***************************** DONT DELETE CLUSTER ****************************************************/
	
	
	
	
	
	
	

}