package mx.com.mrjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.mrjob.dto.PacientesDto;
import mx.com.mrjob.dto.ResponseDto;
import mx.com.mrjob.service.PacientesService;

@Controller
@RequestMapping("pacientes")
public class PacientesController {

	@Autowired
	private PacientesService pacientesService;

	// Obtiene todos los pacientes
	@ResponseBody
	@RequestMapping(value = "/getPacientes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ResponseDto> getPacientes() {

		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = pacientesService.getPacientes();

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

	// Obtiene un paciente por ID
	@ResponseBody
	@RequestMapping(value = "/getPacientesById", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ResponseDto> getPacientesById(@RequestParam("id") Integer idPaciente) {

		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = pacientesService.getPacientesById(idPaciente);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

	// Inserta un nuevo paciente
	@ResponseBody
	@RequestMapping(value = "/insertPaciente", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ResponseDto> insertPaciente(@RequestBody PacientesDto nuevoPaciente) {

		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = pacientesService.insertPaciente(nuevoPaciente);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

	// Actualiza un paciente existente
	@ResponseBody
	@RequestMapping(value = "/updatePaciente", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<ResponseDto> updatePaciente(@RequestBody PacientesDto paciente) {

		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = pacientesService.updatePaciente(paciente);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

	// Elimina un paciente
	@ResponseBody
	@RequestMapping(value = "/deletePaciente", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<ResponseDto> deletePaciente(@RequestParam("id") Integer idPaciente) {

	    final HttpHeaders httpHeaders = new HttpHeaders();

	    PacientesDto paciente = new PacientesDto();
	    paciente.setIdPaciente(idPaciente);

	    ResponseDto response = pacientesService.deletePaciente(paciente);

	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

	    return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}
}