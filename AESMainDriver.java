package com.log.aes;

import java.io.File;

import org.apache.hadoop.mapreduce.Mapper.Context;

public class AESMainDriver {

	// this class takes the argument data path,encrypted out path , decrypted
	// path , flag is whether encryption need or decryption need ( Enc or Dec )
	// and key is encryption key

	public static void CallAlgorithms(String path, String Enpath, String Decpath, String flag, Context context,
			String SecurityKey) {

		// this methode handles the encryption/ decryption call based on the
		// flag passing from encryption mapper or decryption mapper

		// Key is uploaded by client/user , based on the user key only
		// performing the AES 128 encryption algorithm

		String key = SecurityKey;

		/******************************************************************************************
		 * DONT DELETE CLUSTER
		 ***********************************************************************************************/

		try {

			if (flag.equalsIgnoreCase("Enc")) {
				AESMapper.aesEncryption(key, path, Enpath, flag);
				context.getCounter(LogCounters.TOTAL_FILES_ENCRYPTED).increment(1);
			}
			if (flag.equalsIgnoreCase("Dec")) {
				AESMapper.aesDecryption(key, Enpath, Decpath, flag);
				context.getCounter(LogCounters.TOTAL_FILES_DECRYPTED).increment(1);
			}
		} catch (CustomException execption) {
			System.out.println(execption.getMessage());
			execption.printStackTrace();
		}

		/******************************************************************************************
		 * DONT DELETE CLUSTER
		 ***********************************************************************************************/

	}

}