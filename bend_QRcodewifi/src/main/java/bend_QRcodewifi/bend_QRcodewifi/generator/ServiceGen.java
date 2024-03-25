package bend_QRcodewifi.bend_QRcodewifi.generator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


@Service
public class ServiceGen {


    @Value("${qrcode.output.directory}")
    private String outLocation;


    public byte[] generateQRCode(CredenzRequest credenziale) throws IOException, WriterException {

        String codeString = "";
        switch(credenziale.getType()){
            case "WPA":
                codeString = "WIFI:T:WPA;S:" + credenziale.getRete() + ";P:" + credenziale.getPassword() + ";;";
                break;
            case "WPA2":
                codeString = "WIFI:T:WPA2;S:" + credenziale.getRete() + ";P:" + credenziale.getPassword() + ";;";
                break;
            case "WEP":
                codeString = "WIFI:T:WEP;S:" + credenziale.getRete() + ";P:" + credenziale.getPassword() + ";;";
                break;
            default:
                codeString = "WIFI:T:WPA;S:" + credenziale.getRete() + ";P:" + credenziale.getPassword() + ";;";
                break;
            }
        BitMatrix bitMatrix = generateBitMatrix(codeString);
        byte[] qrCodeBytes = encodeBitMatrixToBytes(bitMatrix);
        saveQRCodeToFile(bitMatrix, outLocation);
        return qrCodeBytes;
    }

    private BitMatrix generateBitMatrix(String codeString) throws WriterException {

        Map<EncodeHintType, Object> hints = new HashMap<>();                         // Configura gli hint per la codifica
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        return new MultiFormatWriter().encode(codeString, BarcodeFormat.QR_CODE, 200, 200, hints);      // Genera il QR code come BitMatrix
    }

    private byte[] encodeBitMatrixToBytes(BitMatrix bitMatrix) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();           // Codifica il BitMatrix in un array di byte
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        return outputStream.toByteArray();
    }

    private void saveQRCodeToFile(BitMatrix bitMatrix, String location) throws IOException {

        File directory = new File(location);                                        // Crea la directory se non esiste
        if (!directory.exists()) {
            directory.mkdirs();
        }                                // Salva il QR code in un file nella directory specificata
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", new FileOutputStream(location + File.separator + "wifi_qr_code.png"));
    }
}