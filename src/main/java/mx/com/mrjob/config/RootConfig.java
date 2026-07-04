package mx.com.mrjob.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//Indica que esta clase contiene configuraciones de Spring
@ComponentScan(basePackages= {"mx.com.mrjob"}, //PAQUETE PRINCIPAL EN DONDE SPRING BUSCARA NUESTRAS CLASES CON ANOTACIONES
excludeFilters={
	@ComponentScan.Filter(type= FilterType.ANNOTATION, value= EnableWebMvc.class) 
})//FILTRO PARA SPRING TOME AQUELLAS CLASES QUE LLEVEN ANOTACIONES O LA ANOTACION @EnableWebMvc
//Excluye las clases que tengan la anotación @EnableWebMvc
//para evitar configuraciones duplicadas
public class RootConfig {}