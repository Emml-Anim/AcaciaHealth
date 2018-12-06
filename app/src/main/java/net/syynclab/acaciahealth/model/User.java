package net.syynclab.acaciahealth.model;

public class User {
    private String Name;
    private String Password;
    private String Phone;
    private String Email;
    private String Balance;
    private String PolicyNumber;
    private String UserPicture;

    public User() {
    }

    public User(String name, String password, String phone, String email, String balance, String policyNumber, String userPicture) {
        Name = name;
        Password = password;
        Phone = phone;
        Email = email;
        Balance = balance;
        PolicyNumber = policyNumber;
        UserPicture = userPicture;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getPolicyNumber() {
        return PolicyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        PolicyNumber = policyNumber;
    }

    public String getUserPicture() {
        return UserPicture;
    }

    public void setUserPicture(String userPicture) {
        UserPicture = userPicture;
    }
}

