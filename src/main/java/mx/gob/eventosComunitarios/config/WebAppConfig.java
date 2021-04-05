package mx.gob.eventosComunitarios.config;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import mx.gob.eventosComunitarios.dao.CambioInterface;
import mx.gob.eventosComunitarios.dao.EventoInterface;
import mx.gob.eventosComunitarios.dao.HorarioInterface;
import mx.gob.eventosComunitarios.dao.InscripcionEquipoInterface;
import mx.gob.eventosComunitarios.dao.InscripcionPersonaInterface;
import mx.gob.eventosComunitarios.dao.ModalidadInterface;
import mx.gob.eventosComunitarios.dao.OfertaInterface;
import mx.gob.eventosComunitarios.dao.PersonaInterface;
import mx.gob.eventosComunitarios.dao.RolInterface;
import mx.gob.eventosComunitarios.dao.TipoInterface;
import mx.gob.eventosComunitarios.dao.UsuarioInterface;
import mx.gob.eventosComunitarios.service.CambioService;
import mx.gob.eventosComunitarios.service.EventoService;
import mx.gob.eventosComunitarios.service.HorarioService;
import mx.gob.eventosComunitarios.service.InscripcionEquipoService;
import mx.gob.eventosComunitarios.service.InscripcionPersonaService;
import mx.gob.eventosComunitarios.service.ModalidadService;
import mx.gob.eventosComunitarios.service.OfertaService;
import mx.gob.eventosComunitarios.service.PersonaService;
import mx.gob.eventosComunitarios.service.RolService;
import mx.gob.eventosComunitarios.service.TipoService;
import mx.gob.eventosComunitarios.service.UsuarioService;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "mx.gob.eventosComunitarios.controller")
public class WebAppConfig implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		// indicar donde estan las VISTAS
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		return templateResolver;
	} 
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(this.templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver viewReolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(this.templateEngine());
		return viewResolver;
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/eventosCom?serverTimezone=UTC");
		datasource.setUsername("root");
		datasource.setPassword("root");
		return datasource;
	}
	
	@Bean
	public EventoInterface getEventos() {
		return new EventoService(this.getDataSource());
	}
	
	@Bean
	public UsuarioInterface getUsuarios() {
		return new UsuarioService(this.getDataSource());
	}
	
	@Bean
	public CambioInterface getCambio() {
		return new CambioService(getDataSource());
	}
	
	@Bean
	public HorarioInterface getHorario() {
		return new HorarioService(getDataSource());
	}
	
	@Bean
	public InscripcionEquipoInterface getEquipo() {
		return new InscripcionEquipoService(getDataSource());
	}
	
	@Bean
	public InscripcionPersonaInterface getPersonaInscripcion() {
		return new InscripcionPersonaService(getDataSource());
	}
	
	@Bean
	public ModalidadInterface getModalidad() {
		return new ModalidadService(getDataSource());
	}
	
	@Bean
	public OfertaInterface getOferta() {
		return new OfertaService(getDataSource());
	}
	
	@Bean
	public PersonaInterface getPersona() {
		return new PersonaService(getDataSource());
	}
	
	@Bean
	public RolInterface getRol() {
		return new RolService(getDataSource());
	}
	
	@Bean
	public TipoInterface getTipo() {
		return new TipoService(getDataSource());
	}
}
