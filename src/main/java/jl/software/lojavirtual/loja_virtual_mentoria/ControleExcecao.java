package jl.software.lojavirtual.loja_virtual_mentoria;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jl.software.lojavirtual.loja_virtual_mentoria.model.dto.ObjetoErroDTO;

@RestControllerAdvice
@ControllerAdvice
public class ControleExcecao extends ResponseEntityExceptionHandler{ // interceptar excecoes e erros do projeto
	
	@ExceptionHandler(ExceptionMentoriaJava.class) // controlando id errado
	public ResponseEntity<Object> handleExceptionCustom(ExceptionMentoriaJava ex){
		ObjetoErroDTO objetoErroDTO=new ObjetoErroDTO();
		
		objetoErroDTO.setErro(ex.getMessage());
		objetoErroDTO.setCode(HttpStatus.OK.toString());
		
		return new ResponseEntity<Object>(objetoErroDTO,HttpStatus.OK);
	}
	
	
	// Captura exceções do projeto
	@ExceptionHandler({Exception.class, RuntimeException.class,Throwable.class})
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ObjetoErroDTO objetoErroDTO=new ObjetoErroDTO();
		String msg="";
		
		if(ex instanceof MethodArgumentNotValidException) { // muito bom para exceções que não aparece no console, debugue aqui
			List<ObjectError> list= ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			
			for(ObjectError objectError: list) {
				msg+=objectError.getDefaultMessage() + "\n";
			}
		}else {
			msg=ex.getMessage();
		}
		objetoErroDTO.setErro(msg);
		objetoErroDTO.setCode(status.value() + " ==>" + status.getReasonPhrase());
		
		ex.printStackTrace();
		return new ResponseEntity<Object>(objetoErroDTO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// CAPTURA ERRO NA PARTE DO BANCO
	@ExceptionHandler({DataIntegrityViolationException.class,
		ConstraintViolationException.class, SQLException.class}) // pegando exceções mais comuns de banco
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {
		
		ObjetoErroDTO objetoErroDTO=new ObjetoErroDTO();
		String msg="";
		
		if(ex instanceof DataIntegrityViolationException) {
			msg="Erro de integridade no banco :" + ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
		}else if(ex instanceof ConstraintViolationException) {
			msg="Erro de chave estrangeira :" + ((ConstraintViolationException) ex).getCause().getCause().getMessage();
		}else if(ex instanceof SQLException) {
			msg="Erro de SQL no banco : " +((SQLException) ex).getCause().getCause().getMessage();
		}else {
			ex.getMessage();
		}
		
		objetoErroDTO.setErro(msg);
		objetoErroDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		ex.printStackTrace();
		return new ResponseEntity<Object>(objetoErroDTO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
