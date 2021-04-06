package com.spd.baraholka.user.controller;

import com.spd.baraholka.annotation.image.ImageContent;
import com.spd.baraholka.annotation.image.ImageSize;
import com.spd.baraholka.annotation.user.UserExist;
import com.spd.baraholka.image.service.ImageService;
import com.spd.baraholka.user.controller.dto.*;
import com.spd.baraholka.user.service.UserProfileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserProfileService userService;
    private final ImageService imageService;

    public UserController(UserProfileService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") @UserExist int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/settings/general")
    public int updateUserGeneralSettings(@PathVariable("id") @UserExist int id, @RequestParam("openAdsInNewTab") boolean openAdsInNewTab) {
        return userService.updateUserGeneralSettings(id, openAdsInNewTab);
    }

    @PutMapping
    public EditUserMainInfoDTO updateUserMainInfo(@RequestBody @Valid EditUserMainInfoDTO mainInfoDTO) {
        return userService.updateUserMainInfo(mainInfoDTO);
    }

    @GetMapping
    public List<UserShortViewDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/settings/blocking")
    public FullBlockDetailDTO blockUser(@RequestBody @Valid BlockDetailDTO blockDetailDTO) {
        return userService.blockUser(blockDetailDTO);
    }

    @PutMapping("/settings/blocking/{id}")
    public ShortViewBlockDetailDTO unBlockUser(@PathVariable("id") @UserExist int userId, @RequestParam("isNotify") boolean isNotify) {
        return userService.unBlockUser(userId, isNotify);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me")
    public UserDTO showMe() {
        return userService.getCurrentUser();
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Upload a user avatar to the server", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The avatar was successfully uploaded."),
            @ApiResponse(code = 400, message = "Provided file is not an image or file extension does not correspond to an image."),
            @ApiResponse(code = 400, message = "Provided image is too large and exceeds maximum permitted size."),
            @ApiResponse(code = 403, message = "Forbidden")
    })
    @PostMapping(value = "/me/avatar", consumes = "multipart/form-data")
    public String saveUserAvatar(@RequestPart
                                 @ImageSize(maxMB = "${user.avatar.maxsize.mb}")
                                 @ImageContent MultipartFile image) {
        UserDTO loggedInUser = userService.getCurrentUser();
        int userId = loggedInUser.getId();
        String avatarFileName = imageService.generateAvatarFileName(image);
        String newAvatarUrl = imageService.uploadImage(avatarFileName, image);
        String oldAvatarUrl = Objects.requireNonNull(userService.getUserById(userId)).getImageUrl();
        userService.updateAvatar(userId, newAvatarUrl);
        imageService.deleteImage(oldAvatarUrl);
        return newAvatarUrl;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/{id}/roles")
    public void grantRole(@PathVariable("id") @UserExist int id, @RequestParam Role role) {
        User user = userService.getUserEntityById(id);
        userService.grantRole(user, role);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}/roles")
    public void revokeRole(@PathVariable("id") @UserExist int id, @RequestParam Role role) {
        User user = userService.getUserEntityById(id);
        userService.revokeRole(user, role);
    }
}
