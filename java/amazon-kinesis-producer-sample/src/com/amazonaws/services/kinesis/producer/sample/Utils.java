/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Amazon Software License (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/asl/
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.services.kinesis.producer.sample;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
   
	private static Log log = LogFactory.getLog(Utils.class);
	private static final Random RANDOM = new Random();
	private static String[] devices = {"ACePZXUBN","ZyRZCblyi","zUbYvZuOg","rrUfcCXrj","WgrKfkmsz","FfTXTXspY","pFIfVWCmM","gawDnwogK","koRBAMVjx","oziCBQrYc","UcjnKUVZi","tjsJQYGat","YrzAYSXdo","STKLEBEMT","ttyakNQMz","RKUFsPhNU","StVAPypHS","BSqdBGMts","deIaAXSOi","QIOugHGSU","ALGGnFZkS","HLpxZpwqd","uOWAjSIfk","EWPfrsjWq","ezDVjZGeq","YmFvHhZbT","UUsmjBHNb","UhXWnzgdI","extKZIAeH","epNQEovpT"};
	/**
	 * @return A random unsigned 128-bit int converted to a decimal string.
	 */
	public static String randomExplicitHashKey() {
		return new BigInteger(128, RANDOM).toString(10);
	}

	/**
	 * Generates a blob containing a UTF-8 string. The string begins with the
	 * sequence number in decimal notation, followed by a space, followed by
	 * padding.
	 * 
	 * @param sequenceNumber
	 *            The sequence number to place at the beginning of the record
	 *            data.
	 * @param totalLen
	 *            Total length of the data. After the sequence number, padding
	 *            is added until this length is reached.
	 * @return ByteBuffer containing the blob
	 */
	public static ByteBuffer generateData(long sequenceNumber, int totalLen) {
		StringBuilder sb = new StringBuilder();
		sb.append(Long.toString(sequenceNumber));
		sb.append(" ");
		while (sb.length() < totalLen) {
			sb.append("a");
		}
		try {
			return ByteBuffer.wrap(sb.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}


	public static String getDeviceId() {
		String deviceId = "";
		deviceId  = devices[(RANDOM.nextInt(30))];
		return deviceId;
	}
	

	public static ByteBuffer generateDataBeMad() {
		Record rec = new Record();
		rec.setChannel("BEMAD");
		rec.setDeviceId(getDeviceId());
		rec.setCurrentDate(new Date());
		StringBuilder sb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(rec);
            sb.append(json);
            log.info("JSON = " + json);
                    } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
		try {log.error("####DATOS: " + sb.toString());
			return ByteBuffer.wrap(sb.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
