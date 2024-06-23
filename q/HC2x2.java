import java.util.Scanner;

public class HC2x2 {
    static void getKeyMatrix(String key, int keyMatrix[][]) {
        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                keyMatrix[i][j] = (key.charAt(k)) % 65;
                k++;
            }
        }
    }

    static void encrypt(int cipherMatrix[][], int keyMatrix[][], int messageVector[][]) {
        int x, i, j;
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 1; j++) {
                cipherMatrix[i][j] = 0;
                for (x = 0; x < 2; x++) {
                    cipherMatrix[i][j] += keyMatrix[i][x] * messageVector[x][j];
                }
                cipherMatrix[i][j] = cipherMatrix[i][j] % 26;
            }
        }
    }

    static void HillCipher(String message, String key) {
        int[][] keyMatrix = new int[2][2];
        getKeyMatrix(key, keyMatrix);

        int[][] messageVector = new int[2][1];
        for (int i = 0; i < 2; i++) {
            messageVector[i][0] = (message.charAt(i)) % 65;
        }

        int[][] cipherMatrix = new int[2][1];
        encrypt(cipherMatrix, keyMatrix, messageVector);

        String cipherText = "";
        for (int i = 0; i < 2; i++) {
            cipherText += (char)(cipherMatrix[i][0] + 65);
        }

        System.out.println("Ciphertext: " + cipherText);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Plain text (2 characters):");
        String msg = sc.nextLine();
        System.out.println("Enter key (4 characters):");
        String key = sc.nextLine();

        HillCipher(msg, key);
    }
}
