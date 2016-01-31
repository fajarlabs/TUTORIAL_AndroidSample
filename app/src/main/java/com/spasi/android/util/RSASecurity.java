package com.spasi.android.util;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by Spasi-Ideapad on 1/30/2016.
 */

// References
// http://stackoverflow.com/questions/20102570/storing-rsa-private-key-android
// http://stackoverflow.com/questions/12471999/rsa-encryption-decryption-in-android
// http://stackoverflow.com/questions/7216969/getting-rsa-private-key-from-pem-base64-encoded-private-key-file
// http://stackoverflow.com/questions/23808405/android-get-privatekey-from-string
// http://stackoverflow.com/questions/22518608/error0d0680a8asn1-encoding-routinesasn1-check-tlenwrong-tag-when-importing-r
// http://stackoverflow.com/questions/11584405/error-restoring-rsa-private-key-on-android-jelly-bean
// http://stackoverflow.com/questions/7216969/getting-rsa-private-key-from-pem-base64-encoded-private-key-file#7221381
// http://stackoverflow.com/questions/11787571/how-to-read-pem-file-to-get-private-and-public-key

public class RSASecurity {

    /**
     * Encode RSA
     * @param plain
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static Map encode(final String plain) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        KeyPairGenerator kpg = null;
        KeyPair kp = null;
        PublicKey publicKey = null;
        PrivateKey privateKey;
        byte[] encryptedBytes = null, decryptedBytes = null;
        Cipher cipher = null;

        kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        kp = kpg.genKeyPair();
        publicKey = kp.getPublic();
        privateKey = kp.getPrivate(); // Not Used

        try {
            cipher = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptedBytes = cipher.doFinal(plain.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        // Encoded to base64
        String cipherText = Base64.encodeToString(encryptedBytes, Base64.NO_PADDING);
        String publicKeyBase64 = Base64.encodeToString(kp.getPublic().getEncoded(), Base64.NO_PADDING);
        String privateKeyBase64 = Base64.encodeToString(kp.getPrivate().getEncoded(), Base64.NO_PADDING);

        // Add format RSA
        String privateKeyBase64_2 = "";
        privateKeyBase64_2 += "-----BEGIN RSA PRIVATE KEY-----\n";
        privateKeyBase64_2 += privateKeyBase64 + "\n";
        privateKeyBase64_2 += "-----END RSA PRIVATE KEY-----";

        // Final private key to base64
        String privateKeyBase64_2_encode = Base64.encodeToString(privateKeyBase64_2.getBytes(), Base64.NO_PADDING);

        // Encapsulated data
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("cipherText", cipherText);
        result.put("publicKeyBase64", publicKeyBase64); // not yet encrypt to base64
        result.put("privateKeyBase64", privateKeyBase64_2_encode); // not yet encrypt to base64
        return result;
    }

    /**
     * Decode RSA
     * @param b64EncryptedStr
     * @param b64PrivateKey
     * @return
     */
    public static String decode(String b64EncryptedStr, String b64PrivateKey) {
        Cipher cipher = null;
        String result = null;
        byte[] decodedKey = Base64.decode(b64PrivateKey.getBytes(), Base64.NO_PADDING);
        byte[] decodedStr = Base64.decode(b64EncryptedStr.getBytes(), Base64.NO_PADDING);

        String decodedPrivKey = new String(decodedKey);
        String decodedParsePriveKey = decodedPrivKey.replace("-----BEGIN RSA PRIVATE KEY-----\n","");
        decodedParsePriveKey = decodedParsePriveKey.replace("-----END RSA PRIVATE KEY-----","");

        try {
            // Decode again after remove first tag and end tag from decodedParsePriveKey  .
            byte[] decodeFinalPrivateKey = Base64.decode(decodedParsePriveKey.getBytes(),Base64.NO_PADDING);

            // Using PKCS and no padding
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodeFinalPrivateKey);
            // Create key factory instance
            KeyFactory kf = KeyFactory.getInstance("RSA");
            // Generate key factory
            PrivateKey privateKey = kf.generatePrivate(keySpec);

            cipher = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding", "BC");
            // Decrypt mode
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // Decrypt
            byte[] plainText = cipher.doFinal(decodedStr);
            // Convert to String
            result = new String(plainText);
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        return result;
    }

}
