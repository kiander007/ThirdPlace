package Frame;

import twitter4j.Status;

import java.io.Serializable;

public class CompanyTweet implements Serializable {
    Status status;
    String companyName;

    public CompanyTweet(Status status, String companyName) {
        this.status = status;
        this.companyName = companyName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
