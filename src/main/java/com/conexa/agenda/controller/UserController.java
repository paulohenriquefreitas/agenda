package com.conexa.agenda.controller;

import com.conexa.agenda.dto.CustomResponseDto;
import com.conexa.agenda.dto.UserDataDTO;
import com.conexa.agenda.dto.UserRequestDTO;
import com.conexa.agenda.dto.UserResponseDTO;
import com.conexa.agenda.exception.CustomException;
import com.conexa.agenda.model.Medico;
import com.conexa.agenda.model.User;
import com.conexa.agenda.service.MedicoService;
import com.conexa.agenda.service.UserService;
import com.conexa.agenda.util.CustomResponse;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private MedicoService medicoService;

  @Autowired
  private CustomResponse customResponse;

  @PostMapping("/signin")
  @ApiOperation(value = "${UserController.signin}")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Something went wrong"),
      @ApiResponse(code = 422, message = "Invalid username/password supplied")})
  public ResponseEntity<Medico> login(@Validated @ApiParam("Signup User") @RequestBody UserRequestDTO userRequestDTO ) {

    Optional<Medico> optionalMedico =  medicoService.findByName(userRequestDTO.getUsername());
    if(!optionalMedico.isPresent()){
      throw new CustomException("Medico não encontrado", HttpStatus.NOT_FOUND);
    }
    String token = userService.signin(userRequestDTO);
    optionalMedico.get().setToken(token);
    optionalMedico.get().setAgendamentos(medicoService.getAgendamentosDoDiaAtual(optionalMedico.get().getAgendamentos(), LocalDate.now()));
    medicoService.save(optionalMedico.get());
    return new ResponseEntity<Medico>(optionalMedico.get(), HttpStatus.OK);
  }

  @ApiOperation(value = "${UserController.logout}")
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Something went wrong"),
          @ApiResponse(code = 422, message = "Invalid username/password supplied")})
  @PostMapping("/logoff")
  public ResponseEntity<?> logout(@RequestHeader(name = "token") String token) {
    userService.logoff(token.trim());
    return new ResponseEntity<CustomResponseDto>(customResponse.getCustomResponseDto(
            "Logoff realizado com sucesso.", HttpStatus.OK), HttpStatus.OK);
  }


}
