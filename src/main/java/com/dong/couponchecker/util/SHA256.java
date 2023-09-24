package com.dong.couponchecker.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    // TODO: salt 추가 및 개선 필요
    static public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    static private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
