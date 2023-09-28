package com.dong.couponchecker.util;

import com.dong.couponchecker.exception.UnexpectedException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    // TODO: salt 추가 및 개선 필요
    static public String encrypt(String text) throws UnexpectedException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());

            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new UnexpectedException();
        }
    }

    static private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
