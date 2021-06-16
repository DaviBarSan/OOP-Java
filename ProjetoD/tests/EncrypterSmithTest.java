import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EncrypterSmithTest {
    @Test
    void whenGetInputNandStringDoTheRotation(){
        int n = -3;
        String input = "ABCDEFGH";
        assertEquals("DEFGHABC", EncrypterSmith.rotation(n, input));
        int m = 2;
        String input2 = "ABCDEFGH";
        assertEquals("GHABCDEF", EncrypterSmith.rotation(m, input2));
        }

    @Test
    void whenGetAnInputReturnEncryptedString(){
        String input = "X15";
        int p = 2;
        assertEquals("Z37", EncrypterSmith.encryptingAnStringByASCIICode(p, input));

        String inputII = "IBM:111";
        int o = -1;
        assertEquals("HAL9000", EncrypterSmith.encryptingAnStringByASCIICode(o, inputII));
    }

    @Test
    void undoRotationEncryptedString(){
        String input = "DEFGHABC";
        int n = -3;
        assertEquals("ABCDEFGH", EncrypterSmith.undoRotation(n, input));

    }
    @Test
    void findingKeyByEncrypted0(){
        String ecryptedMessage = "t{dwiKpecejgOgoq";
        String flag = "bug";
        int[] keys = {2, 2};
        assertArrayEquals(keys, EncrypterSmith.findKey(ecryptedMessage, flag));
    }
    @Test
    void findingKeyByEncrypted1(){
        String ecryptedMessage = "Rd>Qppa@lkqoliibo_";
        String flag = "bug";
        int[] keys = {-1,-3};
        assertArrayEquals(keys,(EncrypterSmith.findKey(ecryptedMessage, flag)));
    }

    @Test
    void decryptingAnMessageWithKnownKeys(){
        int n = 2;
        int p = 2;
        String ecryptedMessage = "t{dwiKpecejgOgoq";
        assertEquals("bugIncacheMemory", EncrypterSmith.decryptWithKnownKeys(n,p,ecryptedMessage));
    }

    @Test
    void decryptingAMessageWihtUnkownKeys(){
        String ecryptedMessage = "Rd>Qppa@lkqoliibo_";
        int[] keys = EncrypterSmith.findKey(ecryptedMessage, "bug");
        String decryptedMessage = EncrypterSmith.decryptWithKnownKeys(keys[0], keys[1], ecryptedMessage);
        assertEquals("bUgATssdController", decryptedMessage);
    }
}