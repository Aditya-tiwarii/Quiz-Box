package com.example.android.quizbox;

public class UserHelperClass {

    String name,email,phoneno;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String email, String phoneno) {
        this.name = name;
        this.email = email;
        this.phoneno = phoneno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
