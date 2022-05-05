package com.vglaznev.shorturlservice.generator;

import java.security.SecureRandom;
import java.util.Base64;

//TODO: rename class (choose correct name)
public class UniqueStringGenerator {
    private static final SecureRandom random = new SecureRandom();
    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    private static final int ID_DEFAULT_LENGTH = 8;

    /**
     * Generate a unique string of length 8 in base 64 alphabet.
     * Amount of unique strings that method can produce is 64^8 (approx. 2e+14).
     * However, method use random function to generate strings, so collisions can occur.
     * (before all 2e+14 unique strings are used)
     *
     * @return unique string
     */
    public static String generate() {
        //base64 use 6 bit for character and byte is 8 bit
        //that's why we generate 6 bytes (48 bits) for string of length 8 (48 bits)
        byte[] bytes = new byte[ID_DEFAULT_LENGTH - 2];
        random.nextBytes(bytes);
        byte[] randomBytesInBase64 = encoder.encode(bytes);
        return new String(randomBytesInBase64);
    }
}
