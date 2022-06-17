package otp;

public class OtpBase {
    public static void main(String[] args) {

        String inputFile = "";
        String outPutFile = "";
        String baseUrl = "";
        if (args.length > 0) {
            inputFile = args[0];
        } else {
            System.out.println("Please re run the program with input file. \nEx: java -jar email_otp_generate_tool-1.0-0.jar email-otp-intermediate.csv");
            return;
        }
        if (args.length > 1) {
            outPutFile = args[1];
        } else {
            outPutFile = "email-otp-output.csv";
            System.out.println("You didn't set an output file name. Set default value for output file name: " + outPutFile);
        }
        if (args.length > 2) {
            baseUrl = args[2];
        } else {
            baseUrl = "https://unity.uat.unitybyhardrock.com/unitywebapi/userverification";
            System.out.println("You didn't set a base OTP URL. Set default value for OTP base URL: " + baseUrl);
        }
        OtpUtils otpUtils = new OtpUtils();
        otpUtils.processInputFile(inputFile, outPutFile, baseUrl);
    }


}
