import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHA512 {

    public static String getSecurePassword(String password, byte[] salt) {

        /* Java provides inbuilt MessageDigest class for SHA-512 hashing */
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            /* We add salt to input using the update() method of MessageDigest */
            md.update(salt);
            /* Once we have added the salt we can generate the hashed password using the digest() method */
            /* Convert byte[] into a string as */
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    /* Next, we will be creating a new instance for the SecureRandom class and the nextByte() method generates the random salt. */
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        // same salt should be passed
        byte[] salt = getSalt();
        String password = getSecurePassword("Password", salt);
        System.out.println(" Password  -> " + password1);
    }
}