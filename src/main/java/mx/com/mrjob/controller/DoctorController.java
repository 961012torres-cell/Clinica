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

import mx.com.mrjob.dto.DoctorDto;
import mx.com.mrjob.dto.ResponseDto;
import mx.com.mrjob.service.DoctorService;

@Controller
@RequestMapping("doctores")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Obtiene todos los doctores
    @ResponseBody
    @RequestMapping(value = "/getDoctores", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResponseDto> getDoctores() {

        final HttpHeaders httpHeaders = new HttpHeaders();

        ResponseDto response = doctorService.getDoctores();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
    }

   
    @ResponseBody
    @RequestMapping(value = "/getDoctorById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResponseDto> getDoctorById(@RequestParam("id") Integer idDoctor) {

        final HttpHeaders httpHeaders = new HttpHeaders();

        ResponseDto response = doctorService.getDoctorById(idDoctor);

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
    }

 
    @ResponseBody
    @RequestMapping(value = "/insertDoctor", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResponseDto> insertDoctor(@RequestBody DoctorDto nuevoDoctor) {

        final HttpHeaders httpHeaders = new HttpHeaders();

        ResponseDto response = doctorService.insertDoctor(nuevoDoctor);

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
    }

  
    @ResponseBody
    @RequestMapping(value = "/updateDoctor", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<ResponseDto> updateDoctor(@RequestBody DoctorDto doctor) {

        final HttpHeaders httpHeaders = new HttpHeaders();

        ResponseDto response = doctorService.updateDoctor(doctor);

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
    }

  
    @ResponseBody
    @RequestMapping(value = "/deleteDoctor", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<ResponseDto> deleteDoctor(@RequestParam("id") Integer idDoctor) {

        final HttpHeaders httpHeaders = new HttpHeaders();

        DoctorDto doctor = new DoctorDto();
        doctor.setIdDoctor(idDoctor);

        ResponseDto response = doctorService.deleteDoctor(doctor);

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
    }
}