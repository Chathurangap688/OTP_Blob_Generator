package dto;

import java.io.Serializable;
import java.util.Objects;

public class SessionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String otpToken;
    private long generatedTime;
    private long expiryTime;
    private String transactionId;
    private String fullQualifiedUserName;
    private String userId;

    public SessionDTO() {
    }

    public String getOtpToken() {
        return this.otpToken;
    }

    public void setOtpToken(String otpToken) {
        this.otpToken = otpToken;
    }

    public long getGeneratedTime() {
        return this.generatedTime;
    }

    public void setGeneratedTime(long generatedTime) {
        this.generatedTime = generatedTime;
    }

    public long getExpiryTime() {
        return this.expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFullQualifiedUserName() {
        return this.fullQualifiedUserName;
    }

    public void setFullQualifiedUserName(String fullQualifiedUserName) {
        this.fullQualifiedUserName = fullQualifiedUserName;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof SessionDTO)) {
            return false;
        } else {
            SessionDTO that = (SessionDTO)o;
            return this.getGeneratedTime() == that.getGeneratedTime() && this.getExpiryTime() == that.getExpiryTime() && this.getOtpToken().equals(that.getOtpToken()) && this.getTransactionId().equals(that.getTransactionId()) && this.getFullQualifiedUserName().equals(that.getFullQualifiedUserName()) && this.getUserId().equals(that.getUserId());
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getOtpToken(), this.getGeneratedTime(), this.getExpiryTime(), this.getTransactionId(), this.getFullQualifiedUserName(), this.getUserId()});
    }

    public String toString() {
        return "SessionDTO{otpToken='" + this.otpToken + '\'' + ", generatedTime=" + this.generatedTime + ", expiryTime=" + this.expiryTime + ", transactionId='" + this.transactionId + '\'' + ", fullQualifiedUserName='" + this.fullQualifiedUserName + '\'' + ", userId='" + this.userId + '\'' + '}';
    }
}
