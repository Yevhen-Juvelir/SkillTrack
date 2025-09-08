package skilltrack.skilltrack.controller;

import org.springframework.web.bind.annotation.*;
import skilltrack.skilltrack.dto.UserImageRequest;
import skilltrack.skilltrack.service.UserImageService;

@RestController
@RequestMapping("api/users/avatar")
public class GetUserAvatar {

    UserImageService userImageService;

    public GetUserAvatar(UserImageService userImageService) {
        this.userImageService = userImageService;
    }

    @PostMapping("")
    public void SaveUserAvatar(@RequestBody UserImageRequest userImageRequest) {

        String image_url = userImageRequest.getAvatarUrl();
        String user_email = userImageRequest.getEmail();
        System.out.println(user_email);
        userImageService.updateUserImage(user_email, image_url);

    }

    @GetMapping("getavatar")
    public String SendUserAvatar(@RequestParam String email) {
        String avatarUrl =  userImageService.getUserImage(email);
        System.out.println(avatarUrl);
        return avatarUrl != null ? avatarUrl : "";
    }




}
