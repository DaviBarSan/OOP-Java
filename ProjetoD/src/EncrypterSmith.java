import java.util.stream.Collectors;

public class EncrypterSmith {

    public static String encrypt(int n, int p, String message) {
        return encryptingAnStringByASCIICode(p, rotation(n, message));
    }
    public static String rotation(int n, String input){

        if (n < 0){
            n = Math.abs(n);
            String rotatedString = input.substring(n,input.length()) + input.substring(0, n);
            return rotatedString;
        }
        int indexRotation = input.length() - n;
        String rotatedString =  input.substring(indexRotation, input.length()) + input.substring(0,indexRotation);
        return rotatedString;
    }
    public static String encryptingAnStringByASCIICode(int p, String input){
        String encryptedString = input.chars().mapToObj(token -> String.valueOf(((char) (token + p)))).collect(Collectors.joining());
        return encryptedString;
    }

    public static String decrypt(String encryptedMessage){
        int[] keys = findKey(encryptedMessage, "bug");
        int n = keys[0];
        int p = keys[1];
        String decryptedMessage = decryptWithKnownKeys(n, p, encryptedMessage);
        return decryptedMessage;
    }
    public static String decryptWithKnownKeys(int n, int p , String encryptedMessage){
        return decryptingAnStringByASCIICode(p, undoRotation(n, encryptedMessage));
    }

    public static String decryptingAnStringByASCIICode(int p, String input){
        String dencryptedString = input.chars().mapToObj(token -> String.valueOf(((char) (token - p)))).collect(Collectors.joining());
        return dencryptedString;
    }
    public static String undoRotation(int n, String input){
         String unrotatedString = rotation((-n), input);
         return unrotatedString;
    }
    //Setting by default that n = key[0] and p = key[1];
    public static int[] findKey(String encryptedMessage, String keyWord){
        int[] keys = new int[2];
        outloop:
        for (int n = -9; n <= 9; n++) {
            for (int p = -4; p <= 4; p++) {
                String decryptedMessage = decryptWithKnownKeys(n, p, encryptedMessage);
                String keyMatcher = decryptedMessage.substring(0, 3).toLowerCase();
                if (keyMatcher.equals(keyWord)) {
                    keys[0] = n;
                    keys[1] = p;
                    break outloop;
                }
            }
        }
        return keys;
    }
}
