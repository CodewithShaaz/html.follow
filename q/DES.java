import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.*;
public class DES {

    public static void main(String[] args) throws Exception {
        // Generate a DES key
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGen.generateKey();

        // Initialize the cipher for encryption
        Cipher encryptCipher = Cipher.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt a string
        System.out.print("enter plaintext here : ");
        Scanner sc = new Scanner(System.in);
        String plaintext = sc.nextLine();
        byte[] encryptedBytes = encryptCipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted: " + encryptedString);

        // Initialize the cipher for decryption
        Cipher decryptCipher = Cipher.getInstance("DES");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the encrypted string
        byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedString));
        String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);
        System.out.println("Decrypted: " + decryptedString);
    }
}