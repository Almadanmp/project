package pt.ipp.isep.dei.project.security;

class LoginViewModel {
    private String username;
    private String password;

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }
}