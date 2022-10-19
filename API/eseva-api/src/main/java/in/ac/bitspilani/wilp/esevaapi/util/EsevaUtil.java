package in.ac.bitspilani.wilp.esevaapi.util;

import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;

public class EsevaUtil {

    private EsevaUtil()
    {
        //Intentionally left blank
    }

    public static Blob convertToBlobFromMultiPart(MultipartFile multipartFile) throws SQLException, IOException {
        byte[] bytes = multipartFile.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        return blob;
    }

    public static String generateHash(String secret)
    {
        try {
            MessageDigest messageDigest= MessageDigest.getInstance("MD5");
            messageDigest.update(secret.getBytes(StandardCharsets.UTF_8));

            byte[] digest = messageDigest.digest();
            return DatatypeConverter
                    .printHexBinary(digest).toUpperCase();

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String validateNullStringInMap(Object value)
    {
        if(value==null)
            return null;
        else
            return value.toString();
    }
}
