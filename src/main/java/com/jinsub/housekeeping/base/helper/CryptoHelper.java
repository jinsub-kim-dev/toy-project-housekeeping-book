package com.jinsub.housekeeping.base.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoHelper {

    private static final String ALGORITHM = "SHA-256";

    public static String getSha256HashedString(String msg) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        md.update(msg.getBytes());

        return bytesToHex(md.digest());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
