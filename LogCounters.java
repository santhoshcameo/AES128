package com.log.aes;

public enum LogCounters {

	// implemented the reporting design pattern to display consolidated counters after every hadoop job
	
	TOTAL_LOG_LINES,
	TOTAL_JSON_OUT,
	TOTAL_HIVE_COLUMNS,
	TOTAL_INVALID_RECORDS,
	TOTAL_FILES_ENCRYPTED,
	TOTAL_FILES_DECRYPTED,
	TOTAL_INPUT_FILES, 
	FILEREAD_EXCEPTION
}
