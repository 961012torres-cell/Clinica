package mx.com.mrjob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.mrjob.dto.CitaDto;
import mx.com.mrjob.dto.ResponseDto;
import mx.com.mrjob.repository.CitaRepository;

@Service
public class CitaServiceImpl implements CitaService {

	@Autowired
	private CitaRepository citaRepository;

	@Override
	public ResponseDto getCitas() {

		ResponseDto response = new ResponseDto();

		try {
			List<CitaDto> citas = citaRepository.getCitas();

			if (citas != null && !citas.isEmpty()) {
				response.setCode(1);
				response.setMessage("Registros de citas obtenidos");
				response.setContent(citas);
			} else {
				response.setCode(-1);
				response.setMessage("No se obtuvieron citas");
			}

		} catch (NullPointerException exception) {
			response.setCode(-10);
			response.setMessage("Algún dato viene nulo");
		} catch (Exception exception) {
			response.setCode(-100);
			response.setMessage("Error al consultar las citas");
		}

		return response;
	}

	@Override
	public ResponseDto getCitaById(Integer idCita) {

		ResponseDto response = new ResponseDto();

		try {
			CitaDto cita = citaRepository.getCitaById(idCita);

			if (cita != null) {
				response.setCode(1);
				response.setMessage("Cita obtenida correctamente");
				response.setContent(cita);
			} else {
				response.setCode(-1);
				response.setMessage("No se encontró la cita");
			}

		} catch (NullPointerException exception) {
			response.setCode(-10);
			response.setMessage("Algún dato viene nulo");
		} catch (Exception exception) {
			response.setCode(-100);
			response.setMessage("Error al consultar la cita, Verifique el Id enviado e intente nuevamente");
		}

		return response;
	}

	@Override
	public ResponseDto insertCita(CitaDto cita) {

	    ResponseDto response = new ResponseDto();

	    StringBuilder errores = new StringBuilder();

	    errores.append(validaciones(cita));

	    if (cita != null) {

	        if (citaRepository.existePaciente(cita.getIdPaciente()) == 0) {
	            errores.append("El paciente no existe. ");
	        }

	        if (citaRepository.existeConsultorio(cita.getIdConsultorio()) == 0) {
	            errores.append("El consultorio no existe. ");
	        }

	        if (citaRepository.existeDoctor(cita.getIdDoctor()) == 0) {
	            errores.append("El doctor no existe. ");
	        }
	    }

	    if (!errores.toString().isEmpty()) {
	        response.setCode(-10);
	        response.setMessage("Errores de validación: " + errores.toString());
	        return response;
	    }

	    try {

	        Integer insertResponse = citaRepository.insertCita(cita);

	        if (insertResponse == 1) {
	            response.setCode(1);
	            response.setMessage("Se insertó correctamente, Cita guardada");
	            return response;
	        }

	        response.setCode(-1);
	        response.setMessage("No se insertaron registros, Verifique la información enviada");
	        return response;

	    } catch (Exception exception) {

	        response.setCode(-100);
	        response.setMessage("Error al insertar cita. " + exception.getMessage());
	        return response;
	    }
	}

	@Override
	public ResponseDto updateCita(CitaDto cita) {

	    ResponseDto response = new ResponseDto();

	    StringBuilder errores = new StringBuilder();

	    errores.append(validaciones(cita));

	    if (cita != null) {

	        errores.append(validarIdCita(cita.getIdCita()));

	        if (citaRepository.existePaciente(cita.getIdPaciente()) == 0) {
	            errores.append("El paciente no existe. ");
	        }

	        if (citaRepository.existeConsultorio(cita.getIdConsultorio()) == 0) {
	            errores.append("El consultorio no existe. ");
	        }

	        if (citaRepository.existeDoctor(cita.getIdDoctor()) == 0) {
	            errores.append("El doctor no existe. ");
	        }
	    }
	   
	    if (!errores.toString().isEmpty()) {
	        response.setCode(-10);
	        response.setMessage("Errores de validación: " + errores.toString());
	        return response;
	    }

	    try {

	        Integer updateResponse = citaRepository.updateCita(cita);

	        if (updateResponse == 1) {
	            response.setCode(1);
	            response.setMessage("La cita con ID " + cita.getIdCita() + " fue actualizada correctamente.");
	            response.setContent(cita);
	            return response;
	        }

	        response.setCode(-1);
	        response.setMessage("No se actualizaron registros");
	        response.setContent("Verifique la información enviada");
	        return response;

	    } catch (Exception exception) {

	        response.setCode(-100);
	        response.setMessage("Error al actualizar cita");
	        response.setContent(exception.getMessage());
	        return response;
	    }
	}

	@Override
	public ResponseDto deleteCita(CitaDto cita) {

		ResponseDto response = new ResponseDto();

		StringBuilder errores = new StringBuilder();

		if (cita == null) {
			errores.append("No se recibieron datos de la cita. ");
		} else {
			errores.append(validarIdCita(cita.getIdCita()));
		}

		if (!errores.toString().isEmpty()) {
			response.setCode(-10);
			response.setMessage("Errores de validación");
			response.setContent(errores.toString());
			return response;
		}

		try {

			Integer deleteResponse = citaRepository.deleteCita(cita);

			if (deleteResponse == 1) {
				response.setCode(1);
				response.setMessage("La cita con ID " + cita.getIdCita() + " fue cambiada a estado INACTIVO.");
				return response;
			}

			response.setCode(-1);
			response.setMessage("No se encontró una cita ACTIVA con el ID " + cita.getIdCita() + ".");
			return response;

		} catch (Exception exception) {

			response.setCode(-100);
			response.setMessage("Error al eliminar cita");
			response.setContent(exception.getMessage());
			return response;
		}
	}

	private String validaciones(CitaDto cita) {

	    try {

	        StringBuilder errores = new StringBuilder();

	        if (cita == null) {
	            return "No se recibieron datos de la cita.";
	        }

	        errores.append(validarIdPaciente(cita.getIdPaciente()));
	        errores.append(validarIdDoctor(cita.getIdDoctor()));
	        errores.append(validarHoraCita(cita.getHoraCita()));
	        errores.append(validarSecuenciaHorario(cita.getHoraCita()));
	        errores.append(validarIdConsultorio(cita.getIdConsultorio()));

	        return errores.toString();

	    } catch (Exception exception) {
	        return "Ocurrió un error durante las validaciones de la cita.";
	    }
	}
	private String validarIdPaciente(int idPaciente) {

		if (idPaciente <= 0) {
			return "El ID del paciente es inválido. ";
		}

		return "";
	}

	private String validarHoraCita(String horaCita) {

		if (horaCita == null || horaCita.trim().isEmpty()) {
			return "La hora de la cita es obligatoria. ";
		}

		if (!horaCita.matches("^([01]\\d|2[0-3]):[0-5]\\d$")) {
			return "La hora debe tener el formato HH:MM. ";
		}

		return "";
	}
	
	private String validarIdDoctor(int idDoctor) {

	    if (idDoctor <= 0) {
	        return "El ID del doctor es inválido. ";
	    }

	    return "";
	}
	
	private String validarIdConsultorio(int idConsultorio) {

		if (idConsultorio <= 0) {
			return "El ID del consultorio es inválido. ";
		}

		return "";
	}

	private String validarIdCita(int idCita) {

		if (idCita <= 0) {
			return "El ID de la cita es inválido. ";
		}

		return "";
	}
	private String validarSecuenciaHorario(String horaCita) {

	    if (horaCita == null || horaCita.trim().isEmpty()) {
	        return "";
	    }

	    String minutos = horaCita.substring(3, 5);

	    if (!minutos.equals("00") && !minutos.equals("30")) {
	        return "Las citas solo pueden programarse cada 30 minutos. ";
	    }

	    return "";
	}
}