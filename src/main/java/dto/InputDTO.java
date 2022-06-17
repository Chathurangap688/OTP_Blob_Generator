package dto;

public class InputDTO {

    String accountNumber;
    String expiryTime;
    String userId;
    String USCustomer;
    String EmailStatus;

    public String getAccountNumber() {

        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {

        this.accountNumber = accountNumber;
    }

    public String getExpiryTime() {

        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {

        this.expiryTime = expiryTime;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getUSCustomer() {

        return USCustomer;
    }

    public void setUSCustomer(String USCustomer) {

        this.USCustomer = USCustomer;
    }

    public String getEmailStatus() {

        return EmailStatus;
    }

    public void setEmailStatus(String emailStatus) {

        EmailStatus = emailStatus;
    }
}
