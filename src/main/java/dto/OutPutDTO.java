package dto;

import java.util.Arrays;
import java.util.List;

public class OutPutDTO {

    String accountNumber;
    String sessionId;
    String sessionType;
    String operation;
    String sessionObject;
    String timeCreated;
    String expiryTime;
    String tenantId;
    String otpUrl;


    public String getAccountNumber() {

        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {

        this.accountNumber = accountNumber;
    }

    public String getSessionId() {

        return sessionId;
    }

    public void setSessionId(String sessionId) {

        this.sessionId = sessionId;
    }

    public String getSessionType() {

        return sessionType;
    }

    public void setSessionType(String sessionType) {

        this.sessionType = sessionType;
    }

    public String getOperation() {

        return operation;
    }

    public void setOperation(String operation) {

        this.operation = operation;
    }

    public String getSessionObject() {

        return sessionObject;
    }

    public void setSessionObject(String sessionObject) {

        this.sessionObject = sessionObject;
    }

    public String getTimeCreated() {

        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {

        this.timeCreated = timeCreated;
    }

    public String getExpiryTime() {

        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {

        this.expiryTime = expiryTime;
    }

    public String getTenantId() {

        return tenantId;
    }

    public void setTenantId(String tenantId) {

        this.tenantId = tenantId;
    }

    public String getOtpUrl() {

        return otpUrl;
    }

    public void setOtpUrl(String otpUrl) {

        this.otpUrl = otpUrl;
    }

    public List<String> getArrayData() {
        return Arrays.asList(
                this.accountNumber,
                this.sessionId,
                this.sessionType,
                this.operation,
                this.sessionObject,
                this.timeCreated,
                this.expiryTime,
                this.tenantId,
                this.otpUrl
        );
    }
}
