/* Encryption with key 2048-bit. */


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSA_Encryption
{
    static String plainText = "Plain text which need to be encrypted by Java RSA Encryption";
    
    public static void main(String[] args) throws Exception
    {
        // Get an instance of the RSA key generator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        
        // Generate the KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        // Get the public and private key
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        
        System.out.println("Original Text  : "+plainText);
        
        // Encryption
        byte[] cipherTextArray = encrypt(plainText, publicKey);
        String encryptedText = Base64.getEncoder().encodeToString(cipherTextArray);
        System.out.println("Encrypted Text : "+encryptedText);
        
        // Decryption
        String decryptedText = decrypt(cipherTextArray, privateKey);
        System.out.println("DeCrypted Text : "+decryptedText);
    }
    
    public static byte[] encrypt (String plainText,PublicKey publicKey ) throws Exception
    {
        // Get Cipher Instance RSA With PKCS1 Padding Padding
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        
        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        
        //Perform Encryption
        byte[] cipherText = cipher.doFinal(plainText.getBytes()) ;

        return cipherText;
    }
    
    public static String decrypt (byte[] cipherTextArray, PrivateKey privateKey) throws Exception
    {
        //Get Cipher Instance RSA With PKCS1 Padding
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        
        //Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        //Perform Decryption
        byte[] decryptedTextArray = cipher.doFinal(cipherTextArray);
        
        return new String(decryptedTextArray);
    }
}