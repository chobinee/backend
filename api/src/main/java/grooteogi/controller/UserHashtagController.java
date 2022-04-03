//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package grooteogi.controller;

import grooteogi.domain.UserHashtag;
import grooteogi.dto.response.BasicResponse;
import grooteogi.service.UserHashtagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserHashtagController {

  private final UserHashtagService userHashtagService;

  @PostMapping("/hashtag")
  public ResponseEntity<BasicResponse> saveUserHashtag(@RequestParam int userId, int[] hashtagId) {
    List<UserHashtag> hashtagList = userHashtagService.saveUserHashtag(userId, hashtagId);
    return ResponseEntity.ok(
        BasicResponse.builder().status(HttpStatus.OK.value()).data(hashtagList).build());
  }

  @DeleteMapping("/hashtag")
  public ResponseEntity<BasicResponse> deleteUserHashtag(@RequestParam int userId,
      int[] hashtagId) {
    List<UserHashtag> hashtagList = userHashtagService.deleteUserHashtag(userId, hashtagId);
    return ResponseEntity.ok(
        BasicResponse.builder().status(HttpStatus.OK.value()).data(hashtagList).build());
  }

  @GetMapping("/hashtag")
  public ResponseEntity<BasicResponse> getAllUserHashtag() {
    List<UserHashtag> hashtagList = userHashtagService.getAllUserHashtag();
    return ResponseEntity.ok(
        BasicResponse.builder().status(HttpStatus.OK.value()).data(hashtagList).build());
  }

  @GetMapping("/{userId}/hashtag")
  public ResponseEntity<BasicResponse> getUserHashtag(@PathVariable int userId) {
    List<UserHashtag> hashtagList = userHashtagService.getUserHashtag(userId);
    return ResponseEntity.ok(
        BasicResponse.builder().status(HttpStatus.OK.value()).data(hashtagList).build());
  }
}
