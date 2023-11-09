package seleniumdemo.model;

public class GuestUser {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String confirmEmail;
    private String address;
    private String country;


    public GuestUser(String firstName, String lastName, String email,
                     String confirmEmail, String phone, String address, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.confirmEmail = confirmEmail;
        this.phone = phone;
        this.address = address;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
