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

import mx.com.mrjob.dto.CitaDto;
import mx.com.mrjob.dto.ResponseDto;
import mx.com.mrjob.service.CitaService;

@Controller
@RequestMapping("citas")
public class CitaController {

	@Autowired
	private CitaService citaService;

	@ResponseBody
	@RequestMapping(value = "/getCitas", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ResponseDto> getCitas() {

		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = citaService.getCitas();

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/getCitaById", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ResponseDto> getCitaById(@RequestParam("id") Integer idCita) {

		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = citaService.getCitaById(idCita);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/insertCita", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ResponseDto> insertCita(@RequestBody CitaDto nuevaCita) {

		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = citaService.insertCita(nuevaCita);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/updateCita", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<ResponseDto> updateCita(@RequestBody CitaDto cita) {

		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = citaService.updateCita(cita);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteCita", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<ResponseDto> deleteCita(@RequestParam("id") Integer idCita) {

		final HttpHeaders httpHeaders = new HttpHeaders();

		CitaDto cita = new CitaDto();
		cita.setIdCita(idCita);

		ResponseDto response = citaService.deleteCita(cita);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}
}