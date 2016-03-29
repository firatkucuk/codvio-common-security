
package com.codvio.security.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Base64;



public class SecurityUtils {



    //~ --- [METHODS] --------------------------------------------------------------------------------------------------

    public static byte[] base64decode(final String text) {

        final Base64.Decoder decoder = Base64.getDecoder();

        return decoder.decode(text);
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public static String base64encode(final byte[] data) {

        final Base64.Encoder encoder = Base64.getEncoder();

        return encoder.encodeToString(data);
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public static String base64encode(final String text) {

        final Base64.Encoder encoder = Base64.getEncoder();

        return encoder.encodeToString(text.getBytes());
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public static String generateId(final int length) {

        final StringBuilder text     = new StringBuilder();
        final String        possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < length; i++) {
            final int index = (int) Math.floor(Math.random() * possible.length());
            text.append(possible.charAt(index));
        }

        return text.toString();
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public static String maskCardNumber(final String cardNumber) {

        final String firstPart  = cardNumber.substring(0, 4);
        final String maskedPart = "-****-****-";
        final String lastPart   = cardNumber.substring(cardNumber.length() == 16 ? 12 : 15);

        return firstPart + maskedPart + lastPart;
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public static String md5(final String text) {

        try {
            final byte[]        bytesOfMessage = text.getBytes("UTF-8");
            final MessageDigest md             = MessageDigest.getInstance("MD5");
            final byte[]        digest         = md.digest(bytesOfMessage);

            return new String(digest);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public static String md5Hex(final String text) {

        try {
            final byte[]        bytesOfMessage = text.getBytes("UTF-8");
            final MessageDigest md             = MessageDigest.getInstance("MD5");
            final byte[]        digest         = md.digest(bytesOfMessage);

            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public static String sha1(final String text) {

        try {
            final byte[]        bytesOfMessage = text.getBytes("UTF-8");
            final MessageDigest md             = MessageDigest.getInstance("SHA1");
            final byte[]        digest         = md.digest(bytesOfMessage);

            return new String(digest);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public static String sha1_b64(final String text) {

        try {
            final byte[]        bytesOfMessage = text.getBytes();
            final MessageDigest md             = MessageDigest.getInstance("SHA1");
            final byte[]        digest         = md.digest(bytesOfMessage);

            return base64encode(digest);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }



    //~ ----------------------------------------------------------------------------------------------------------------

    public static String sha1Hex(final String text) {

        try {
            final byte[]        bytesOfMessage = text.getBytes("UTF-8");
            final MessageDigest md             = MessageDigest.getInstance("SHA1");
            final byte[]        digest         = md.digest(bytesOfMessage);

            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
