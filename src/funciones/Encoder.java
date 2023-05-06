package funciones;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Luis Felipe Usma C
 */
public class Encoder {

    static Cipher cipher;
    String secretKey = "ProgramacionAvanza";

    public String encrypt(String plainText)
            throws Exception {

        cipher = Cipher.getInstance("AES");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] llavePassword = md5.digest(secretKey.getBytes("utf-8"));
        byte[] BytesKey = Arrays.copyOf(llavePassword, 24);
        SecretKey secretKey = new SecretKeySpec(BytesKey, "AES");
        byte[] plainTexByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypteByte = cipher.doFinal(plainTexByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedTex = encoder.encodeToString(encrypteByte);
        return encryptedTex;

    }

    public String decrypt(String encryptedText)
            throws Exception {
        cipher = Cipher.getInstance("AES");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] llavePassword = md5.digest(secretKey.getBytes("utf-8"));
        byte[] BytesKey = Arrays.copyOf(llavePassword, 24);
        SecretKey secretKey = new SecretKeySpec(BytesKey, "AES");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte  = cipher.doFinal(encryptedTextByte);
        String decrytedText = new String(decryptedByte);
        return decrytedText;
        
        
       
    }
}
