import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA {
    private BigInteger n, d, e;
    private int bitlen = 1024;

    public RSA(BigInteger e, BigInteger d, BigInteger n) {
        this.e = e;
        this.d = d;
        this.n = n;
    }

    public RSA(int bitlen) {
        this.bitlen = bitlen;
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        d = e.modInverse(m);
    }

    public synchronized BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public synchronized BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(d, n);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA(1024);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a plaintext message: ");
        String plaintext = sc.nextLine();

        // Encrypt the message
        BigInteger plaintextBigInt = new BigInteger(plaintext.getBytes());
        BigInteger encrypted = rsa.encrypt(plaintextBigInt);
        System.out.println("Encrypted message: " + encrypted);

        // Decrypt the message
        BigInteger decrypted = rsa.decrypt(encrypted);
        String decryptedText = new String(decrypted.toByteArray());
        System.out.println("Decrypted message: " + decryptedText);

        sc.close();
    }
}
