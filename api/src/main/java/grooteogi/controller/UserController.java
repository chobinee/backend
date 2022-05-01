package grooteogi.controller;

import grooteogi.domain.User;
import grooteogi.dto.ProfileDto;
import grooteogi.dto.PwDto;
import grooteogi.dto.response.BasicResponse;
import grooteogi.service.ProfileService;
import grooteogi.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

  private final UserService userService;
  private final ProfileService profileService;

  @GetMapping
  public ResponseEntity<BasicResponse> getAllUser() {
    List<User> userList = userService.getAllUser();
    return ResponseEntity.ok(BasicResponse.builder().count(userList.size()).data(userList).build());
  }

  @GetMapping("/{userId}")
  public ResponseEntity<BasicResponse> getUser(@PathVariable Integer userId) {
    User user = userService.getUser(userId);

    return ResponseEntity.ok(BasicResponse.builder().data(user).build());
  }

  @GetMapping("/{userId}/profile")
  public ResponseEntity<BasicResponse> getUserProfile(@PathVariable Integer userId) {
    User user = profileService.getUserProfile(userId);

    return ResponseEntity.ok(BasicResponse.builder().data(user).build());
  }

  @PatchMapping("/{userId}/profile")
  public ResponseEntity<BasicResponse> modifyUserProfile(@PathVariable Integer userId,
      @RequestBody ProfileDto profileDto) {

    User user = profileService.modifyUserProfile(userId, profileDto);
    return ResponseEntity.ok(BasicResponse.builder().data(user).build());
  }

  @PatchMapping("/{userId}/password")
  public ResponseEntity<BasicResponse> modifyUserPw(@PathVariable Integer userId,
      @RequestBody PwDto pwDto) {

    userService.modifyUserPw(userId, pwDto);
    return ResponseEntity.ok(
        BasicResponse.builder().message("modify user password success").build());
  }
}
