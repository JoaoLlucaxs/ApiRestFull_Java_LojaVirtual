package jl.software.lojavirtual.loja_virtual_mentoria;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextLoad implements ApplicationContextAware{

	// Foi criada para pegar o contexto de dependência do spring.. apenas uma classe utilitaria devido talvez não conseguir injetar diretamente
	
	@Autowired
	private static ApplicationContext applicationContext;
	
	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext=applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	

}
