package com.spd.baraholka.user.controller;

import com.spd.baraholka.annotation.image.ImageContent;
import com.spd.baraholka.annotation.image.ImageSize;
import com.spd.baraholka.annotation.user.UserExist;
import com.spd.baraholka.image.service.ImageService;
import com.spd.baraholka.user.controller.dto.UserDTO;
import com.spd.baraholka.user.controller.dto.UserShortViewDTO;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.service.UserService;
import com.spd.baraholka.user.service.UserSettingsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Validated
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;
    private final UserSettingsService userSettingsService;
    private final ImageService imageService;

    public UserController(UserService userService, UserSettingsService userSettingsService, ImageService imageService) {
        this.userService = userService;
        this.userSettingsService = userSettingsService;
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") @UserExist int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/general")
    public int updateUserGeneralSettings(@PathVariable("id") @UserExist int id, @RequestParam("openAdsInNewTab") boolean openAdsInNewTab) {
        return userSettingsService.updateUserGeneralSettings(id, openAdsInNewTab);
    }

    @GetMapping
    public List<UserShortViewDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Upload a user avatar to the server", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The avatar was successfully uploaded"),
            @ApiResponse(code = 400, message = "Provided file is not an image or file extension does not correspond to an image."),
            @ApiResponse(code = 400, message = "Provided image is too large and exceeds maximum permitted size."),
            @ApiResponse(code = 403, message = "Forbidden")
    })
    @PostMapping(value = "/me/avatar", consumes = "multipart/form-data")
    public String saveUserAvatar(@RequestPart
                                 @ImageSize(maxMB = "${user.avatar.maxsize.mb}")
                                 @ImageContent MultipartFile image) {
        User currentUser = userService.getCurrentUser();
        int userId = currentUser.getId();
        String avatarFileName = imageService.generateAvatarFileName(userId, image);
        String avatarUrl = imageService.uploadImage(avatarFileName, image);
        userService.updateAvatar(userId, avatarUrl);
        return avatarUrl;
    }
}
