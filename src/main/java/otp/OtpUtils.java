package otp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.InputDTO;
import dto.OutPutDTO;
import dto.SessionDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
public class OtpUtils {

    public void processInputFile(String inputFile, String outPutFile, String baseURL) {

        String line = "";
        String splitBy = ",";
        InputDTO inputDTO = null;
        OutPutDTO outPutDTO = null;
        int count = 0;
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            FileWriter csvWriter = new FileWriter(outPutFile);
            List <String> headers = Arrays.asList("AccountNumber","SESSION_ID", "SESSION_TYPE", "OPERATION", "SESSION_OBJECT", "TIME_CREATED", "EXPIRY_TIME", "TENANT_ID", "OTP_URL");
            csvWriter.append(String.join(", ", headers));
            csvWriter.append("\n");

            line = br.readLine(); // skip headers
            while ((line = br.readLine()) != null) {
                String[] otpData = line.split(splitBy);    // use comma as separator
                inputDTO = new InputDTO();
                inputDTO.setAccountNumber(otpData[0].trim());
                inputDTO.setExpiryTime(otpData[1].trim());
                inputDTO.setUserId(otpData[2].trim());
                inputDTO.setUSCustomer(otpData[3].trim());
                inputDTO.setEmailStatus(otpData[4].trim());

                SessionDTO sessionDTO = getSessionDTO(inputDTO);
                String jsonString = new ObjectMapper().writeValueAsString(sessionDTO);

                outPutDTO = new OutPutDTO();
                outPutDTO.setAccountNumber(inputDTO.getAccountNumber());

                outPutDTO.setSessionId(getSHA(inputDTO.getUserId()));
                outPutDTO.setSessionType("EMAIL_OTP");
                outPutDTO.setOperation("STORE");
                outPutDTO.setSessionObject(getHexString(jsonString));
                outPutDTO.setTimeCreated(String.valueOf(sessionDTO.getGeneratedTime()));
                outPutDTO.setExpiryTime(String.valueOf(sessionDTO.getExpiryTime()));
                outPutDTO.setTenantId("-1234");
                outPutDTO.setOtpUrl(generateOtpURL(sessionDTO, inputDTO, baseURL));
                
                wriDataToOutPutFile(csvWriter, outPutDTO);
                count++;

            }
            csvWriter.flush();
            csvWriter.close();
            br.close();
            System.out.println("Successfully generate "+ count + " OTP records..!");

        }
        catch (IOException e)
        {
            if (inputDTO != null) {
                System.out.println("error occurred while processing data. The last read data line :\n" + inputDTO.toString());
            }
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("error occurred while getting hash value of userID. The last read data line :\n" + inputDTO.toString());
            throw new RuntimeException(e);
        }

    }

    private void wriDataToOutPutFile(FileWriter csvWriter, OutPutDTO outPutDTO) {

        try {
            csvWriter.append(outPutDTO.getAccountNumber() + ", ");
            csvWriter.append(outPutDTO.getSessionId() + ", ");
            csvWriter.append(outPutDTO.getSessionType() + ", ");
            csvWriter.append(outPutDTO.getOperation() + ", ");
            csvWriter.append(outPutDTO.getSessionObject() + ", ");
            csvWriter.append(outPutDTO.getTimeCreated() + ", ");
            csvWriter.append(outPutDTO.getExpiryTime() + ", ");
            csvWriter.append(outPutDTO.getTenantId() + ", ");
            csvWriter.append(outPutDTO.getOtpUrl() + "\n");
            csvWriter.flush();

        } catch (IOException e) {
            System.out.println("Error while writing otp data to output file..!");
            throw new RuntimeException(e);
        }
    }

    private String generateOtpURL(SessionDTO sessionDTO, InputDTO inputDTO, String baseUrl) {
        
        return baseUrl + "?userId=" + inputDTO.getUserId() +
                "&transactionId=" + sessionDTO.getTransactionId() +
                "&emailOTP=" + sessionDTO.getOtpToken() +
                "&USCustomer=" + inputDTO.getUSCustomer() +
                "&emailStatus=" + inputDTO.getEmailStatus() +
                "&accountNumber=" + inputDTO.getAccountNumber();
    }

    String getNewUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    String getNewOTP() {
        Random random = new Random();
        // +100000 for keep fixed length
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private long getMillisecond(String dateInput) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateInput);
        } catch (ParseException e) {
            System.out.println("Error while converting " + dateInput + " to millisecond");
            throw new RuntimeException("Error while converting " + dateInput + " to millisecond", e);
        }
        return date.getTime();
    }

    SessionDTO getSessionDTO(InputDTO inputData) {

        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setOtpToken(getNewOTP());
        sessionDTO.setGeneratedTime(System.currentTimeMillis());
        sessionDTO.setExpiryTime(getMillisecond(inputData.getExpiryTime()));
        sessionDTO.setTransactionId(getNewUUID());
        sessionDTO.setUserId(inputData.getUserId());

        return sessionDTO;
    }

    private String getHexString(String jsonString) {

        StringBuffer sb = new StringBuffer();
        char ch[] = jsonString.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            String hexString = Integer.toHexString(ch[i]);
            sb.append(hexString);
        }
        return "X'aced00057400d2" + sb.toString() +"'";
    }

    public String getSHA(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        BigInteger number = new BigInteger(1, md.digest(input.getBytes(StandardCharsets.UTF_8)));
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

}
