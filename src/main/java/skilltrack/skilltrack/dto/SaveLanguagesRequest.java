package skilltrack.skilltrack.dto;

import java.util.List;

public class SaveLanguagesRequest {
    String user_email;
    List<LanguagesRequest> languages;


    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public List<LanguagesRequest> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguagesRequest> languages) {
        this.languages = languages;
    }
}
