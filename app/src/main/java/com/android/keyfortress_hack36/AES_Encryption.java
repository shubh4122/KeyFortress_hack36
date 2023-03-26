package com.android.keyfortress_hack36;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.*;

public class AES_Encryption {


    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String KEY = "mysecretpassword"; // Replace with your own key

    public static void main(String[] args) throws Exception {
        String plaintext = "This is a secret message!"; // Replace with your own plaintext

        String encryptedText = encrypt(plaintext);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }

    public static String encrypt(String plaintext) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        String encryptedText = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        }
        return encryptedText;
    }

    public static String decrypt(String encryptedText) throws Exception {
        byte[] encryptedBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encryptedBytes = Base64.getDecoder().decode(encryptedText);
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
        return decryptedText;
    }

//    public KeyGenerator keygenerator;
//    public SecretKey myKey;
//    Cipher c;
//
//    public AES_Encryption() throws Exception {
//        // Key generation
//        keygenerator = KeyGenerator.getInstance("AES");
//        myKey = keygenerator.generateKey();
//        // Creating the cipher
//        c = Cipher.getInstance("AES/ECB/PKCS5Padding");
//    }
//
//    public byte[] performEncryption(String s) throws Exception {
//        // Initialize the cipher for encryption
//        c.init(Cipher.ENCRYPT_MODE, myKey);
//        // Data to be encrypted
//        byte[] text = s.getBytes();
//        // Encrypt the data
//        byte[] textEncrypted = c.doFinal(text);
//        return(textEncrypted);
//    }
//
//    public String performDecryption(byte[] s) throws Exception {
//        // Initialize the same cipher for decrypting data
//        c.init(Cipher.DECRYPT_MODE, myKey);
//        // Decrypt the data
//        byte[] textDecrypted = c.doFinal(s);
//        return(new String(textDecrypted));
//    }
}

