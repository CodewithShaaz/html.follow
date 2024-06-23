import java.util.Scanner;

public class RC4 {
    private byte[] S = new byte[256];
    private int[] T = new int[256];
    private int keylen;

    public RC4(byte[] key) {
        keylen = key.length;
        if (keylen < 1 || keylen > 256) {
            throw new IllegalArgumentException("Key length must be between 1 and 256 bytes");
        }
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
            T[i] = key[i % keylen];
        }
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + T[i]) & 0xFF;
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }
    }

    public byte[] encryptDecrypt(byte[] data) {
        int i = 0, j = 0;
        byte[] result = new byte[data.length];
        for (int k = 0; k < data.length; k++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            result[k] = (byte) (data[k] ^ S[(S[i] + S[j]) & 0xFF]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a plaintext message: ");
        String plaintext = scanner.nextLine();

        System.out.println("Enter a key: ");
        String key = scanner.nextLine();

        RC4 rc4 = new RC4(key.getBytes());
        byte[] encrypted = rc4.encryptDecrypt(plaintext.getBytes());
        System.out.println("Encrypted message (hex): " + bytesToHex(encrypted));

        RC4 rc4Decrypt = new RC4(key.getBytes());
        byte[] decrypted = rc4Decrypt.encryptDecrypt(encrypted);
        System.out.println("Decrypted message: " + new String(decrypted));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
