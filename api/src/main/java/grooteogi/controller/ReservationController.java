package grooteogi.controller;

import grooteogi.dto.ReservationDto;
import grooteogi.dto.ReservationDto.SendSmsResponse;
import grooteogi.response.BasicResponse;
import grooteogi.service.ReservationService;
import grooteogi.utils.Session;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

  private final ReservationService reservationService;

  @GetMapping("/host")
  public ResponseEntity<BasicResponse> getHostReservation(
      @RequestParam(name = "sort", required = false) String sort)  {
    Session session = (Session) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();

    List<ReservationDto.Responses> reservationList = reservationService.getHostReservation(
        session.getId(), sort);
    return ResponseEntity.ok(BasicResponse.builder()
        .message("get host reservation success").data(reservationList).build());
  }

  @GetMapping("/{reservationId}")
  public ResponseEntity<BasicResponse> getReservation(@PathVariable Integer reservationId) {
    ReservationDto.Responses response = reservationService.getReservation(reservationId);
    return ResponseEntity.ok(BasicResponse.builder()
        .message("get reservation success").data(response).build());
  }

  @GetMapping("/apply")
  public ResponseEntity<BasicResponse> getUserReservation(
      @RequestParam(name = "sort", required = false) String sort)  {
    Session session = (Session) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();

    List<ReservationDto.Responses> reservations = reservationService.getUserReservation(
        session.getId(), sort);
    return ResponseEntity.ok(BasicResponse.builder()
        .message("get apply reservation success").data(reservations).build());
  }

  @PostMapping
  public ResponseEntity<BasicResponse> createReservation(
      @RequestBody ReservationDto.Request request) {
    Session session = (Session) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();

    ReservationDto.Response createdReservation = reservationService.createReservation(request,
        session.getId());
    return ResponseEntity.ok(BasicResponse.builder()
        .message("create reservation success").data(createdReservation).build());
  }

  @DeleteMapping("/{reservationId}")
  public ResponseEntity<BasicResponse> deleteReservation(@PathVariable Integer reservationId) {
    this.reservationService.deleteReservation(reservationId);
    return ResponseEntity.ok(BasicResponse.builder()
        .message("delete reservation success").build());
  }

  @PatchMapping("/{reservationId}")
  public ResponseEntity<BasicResponse> modifyStatus(@PathVariable Integer reservationId) {
    ReservationDto.Response response = this.reservationService.modifyStatus(reservationId);
    return ResponseEntity.ok(BasicResponse.builder()
        .message("modify reservation status success").data(response).build());
  }
  
  @PostMapping("/send-sms")
  public ResponseEntity<BasicResponse> sendSms(@RequestParam String phoneNumber) {
    SendSmsResponse response = this.reservationService.sendSms(phoneNumber);
    return ResponseEntity.ok(BasicResponse.builder()
        .message("send sms code success").data(response).build());
  }

  @PostMapping("/check-sms")
  public ResponseEntity<BasicResponse> checkVerifySms(
      @RequestBody ReservationDto.CheckSmsRequest request) {
    reservationService.checkVerifySms(request);

    return ResponseEntity.ok(
        BasicResponse.builder().message("check sms success").build());
  }


}
