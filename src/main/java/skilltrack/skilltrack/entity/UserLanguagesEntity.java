package skilltrack.skilltrack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "UserLanguages")
public class UserLanguagesEntity {

    @Id
    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    // Языки программирования
    @Column(name = "javascript")
    private String javascript;

    @Column(name = "python")
    private String python;

    @Column(name = "java")
    private String java;

    @Column(name = "cpp")
    private String cpp;

    @Column(name = "csharp")
    private String csharp;

    @Column(name = "go")
    private String go;

    @Column(name = "ruby")
    private String ruby;

    @Column(name = "php")
    private String php;

    @Column(name = "typescript")
    private String typescript;

    @Column(name = "rust")
    private String rust;

    // Геттеры и сеттеры
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJavascript() {
        return javascript;
    }

    public void setJavascript(String javascript) {
        this.javascript = javascript;
    }

    public String getPython() {
        return python;
    }

    public void setPython(String python) {
        this.python = python;
    }

    public String getJava() {
        return java;
    }

    public void setJava(String java) {
        this.java = java;
    }

    public String getCpp() {
        return cpp;
    }

    public void setCpp(String cpp) {
        this.cpp = cpp;
    }

    public String getCsharp() {
        return csharp;
    }

    public void setCsharp(String csharp) {
        this.csharp = csharp;
    }

    public String getGo() {
        return go;
    }

    public void setGo(String go) {
        this.go = go;
    }

    public String getRuby() {
        return ruby;
    }

    public void setRuby(String ruby) {
        this.ruby = ruby;
    }

    public String getPhp() {
        return php;
    }

    public void setPhp(String php) {
        this.php = php;
    }

    public String getTypescript() {
        return typescript;
    }

    public void setTypescript(String typescript) {
        this.typescript = typescript;
    }

    public String getRust() {
        return rust;
    }

    public void setRust(String rust) {
        this.rust = rust;
    }
}
