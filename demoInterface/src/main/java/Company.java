public class Company {

    private String companyName;
    private String twitterName;

    public Company(String companyName, String twitterName){
        this.companyName = companyName;
        this.twitterName = twitterName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTwitterName() {
        return twitterName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setTwitterName(String twitterName) {
        this.twitterName = twitterName;
    }
}
