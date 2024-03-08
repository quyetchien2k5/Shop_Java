package Model;

public class User {

    private int IdUser;
    private String NameUser;
    private int Age;
    private String Gender;
    private String Address;
    private String Email;
    private int PhoneNumber;
    private String Password;
    private Shop UserShop;

    public User() {
    }

    public User(int idUser, String nameUser, int age, String gender, String address, String email, int phoneNumber, String password, Shop userShop) {
        IdUser = idUser;
        NameUser = nameUser;
        Age = age;
        Gender = gender;
        Address = address;
        Email = email;
        PhoneNumber = phoneNumber;
        Password = password;
        UserShop =userShop;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public String getNameUser() {
        return NameUser;
    }

    public void setNameUser(String nameUser) {
        NameUser = nameUser;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Shop getUserShop() {
        return UserShop;
    }

    public void setUserShop(Shop userShop) {
        UserShop = userShop;
    }
}
