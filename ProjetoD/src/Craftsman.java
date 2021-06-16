import java.util.Scanner;

public class Craftsman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        String message = sc.next();
        if (n == 0 && p == 0) {
            String decryptedMessage = EncrypterSmith.decrypt(message);
            System.out.println(decryptedMessage);
        }else {
            String encryptedMessage = EncrypterSmith.encrypt(n, p, message);
            System.out.println(encryptedMessage);
        }
        sc.close();
    }
}
