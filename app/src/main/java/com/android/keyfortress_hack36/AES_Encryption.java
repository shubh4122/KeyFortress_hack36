package com.android.keyfortress_hack36;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class AES_Encryption {
    public KeyGenerator keygenerator;
    public SecretKey myKey;
    Cipher c;

    public AES_Encryption() throws Exception {
        // Key generation
        keygenerator = KeyGenerator.getInstance("AES");
        myKey = keygenerator.generateKey();
        // Creating the cipher
        c = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    public byte[] performEncryption(String s) throws Exception {
        // Initialize the cipher for encryption
        c.init(Cipher.ENCRYPT_MODE, myKey);
        // Data to be encrypted
        byte[] text = s.getBytes();
        // Encrypt the data
        byte[] textEncrypted = c.doFinal(text);
        return(textEncrypted);
    }

    public String performDecryption(byte[] s) throws Exception {
        // Initialize the same cipher for decrypting data
        c.init(Cipher.DECRYPT_MODE, myKey);
        // Decrypt the data
        byte[] textDecrypted = c.doFinal(s);
        return(new String(textDecrypted));
    }
}

