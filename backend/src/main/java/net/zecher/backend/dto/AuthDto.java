package net.zecher.backend.dto;

import java.util.Objects;

public class AuthDto {

    private String userName;
    private String password;

    public AuthDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthDto authDto = (AuthDto) o;
        return Objects.equals(userName, authDto.userName) && Objects.equals(password, authDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}
