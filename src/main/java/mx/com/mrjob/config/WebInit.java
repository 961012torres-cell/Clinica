package mx.com.mrjob.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {


	// Indica cuál será la configuración principal de Spring
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class};
	}

	// Indica cuál será la configuración de Spring MVC
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	// Define las URL que serán administradas por Spring
		// Todo lo que venga después del contexto será atendido por Spring MVC
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};  //SIRVE PARA INDICAR QUE TODO LO QUE ESTE DESPUES DEL CONTEXTO CON "/" VAN A SER URL O SERVICIOS
	}

}

