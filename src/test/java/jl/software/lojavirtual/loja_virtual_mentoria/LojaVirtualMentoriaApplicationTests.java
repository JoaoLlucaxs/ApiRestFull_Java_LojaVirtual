package jl.software.lojavirtual.loja_virtual_mentoria;

import jl.software.lojavirtual.loja_virtual_mentoria.controller.AcessoController;
import jl.software.lojavirtual.loja_virtual_mentoria.model.Acesso;
import jl.software.lojavirtual.loja_virtual_mentoria.repository.AcessoRepository;
import junit.framework.TestCase;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class LojaVirtualMentoriaApplicationTests  extends TestCase {

    @Autowired
    private AcessoController acessoController;
    
    @Autowired
    private AcessoRepository acessoRepository;
    
    @Autowired
    private WebApplicationContext applicationContext;
    
    
    //Teste do end-point salvar
    @Test
    public void testRestApiCadastroAcesso() throws JsonProcessingException, Exception {
    	DefaultMockMvcBuilder builder= MockMvcBuilders.webAppContextSetup(this.applicationContext);
    	MockMvc mockMvc=builder.build();
    	
    	Acesso acesso=new Acesso();
    	acesso.setDescricao("ACESSO_COMPRADOR" + Calendar.getInstance().getTimeInMillis());
    	
    	ObjectMapper objectMapper=new ObjectMapper();
    	
    	ResultActions retornoApi=mockMvc.perform(MockMvcRequestBuilders.post("/salvarAcesso")
    			.content(objectMapper.writeValueAsString(acesso))
    			.accept(org.springframework.http.MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON));
    	
    	System.out.println(retornoApi.andReturn().getResponse().getContentAsString());
    	
    	// Converter o retorno da API para um objeto Acesso
    	Acesso objetoRetorno=objectMapper
    			.readValue(retornoApi.andReturn().getResponse().getContentAsString(),Acesso.class);
    	
    	assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
    }
    
    @Test
    public void testRestApiDeleteAcesso() throws JsonProcessingException, Exception {
    	DefaultMockMvcBuilder builder= MockMvcBuilders.webAppContextSetup(this.applicationContext);
    	MockMvc mockMvc=builder.build();
    	
    	Acesso acesso=new Acesso();
    	acesso.setDescricao("ROLE_TESTE_DELETE");
    	
    	acesso=acessoRepository.save(acesso);
    	
    	ObjectMapper objectMapper=new ObjectMapper();
    	
    	ResultActions retornoApi=mockMvc.perform(MockMvcRequestBuilders.post("/deletarAcesso")
    			.content(objectMapper.writeValueAsString(acesso))
    			.accept(org.springframework.http.MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON));
    	
    	System.out.println("Retorno da Api " + retornoApi.andReturn().getResponse().getContentAsString());
    	System.out.println("Resposta da Api" + retornoApi.andReturn().getResponse().getStatus());
    	
    	assertEquals("Acesso removido", retornoApi.andReturn().getResponse().getContentAsString());
    	assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
    }
    
    
    @Test
    public void testRestApiDeletePorIdAcesso() throws JsonProcessingException, Exception {
    	DefaultMockMvcBuilder builder= MockMvcBuilders.webAppContextSetup(this.applicationContext);
    	MockMvc mockMvc=builder.build();
    	
    	Acesso acesso=new Acesso();
    	acesso.setDescricao("ROLE_TESTE_DELETE_ID");
    	
    	acesso=acessoRepository.save(acesso);
    	
    	ObjectMapper objectMapper=new ObjectMapper();
    	
    	ResultActions retornoApi=mockMvc.perform(MockMvcRequestBuilders.delete("/deletarAcessoPorId/" + acesso.getId())
    			.content(objectMapper.writeValueAsString(acesso))
    			.accept(org.springframework.http.MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON));
    	
    	System.out.println("Retorno da Api " + retornoApi.andReturn().getResponse().getContentAsString());
    	System.out.println("Resposta da Api" + retornoApi.andReturn().getResponse().getStatus());
    	
    	assertEquals("Acesso por id removido", retornoApi.andReturn().getResponse().getContentAsString());
    	assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
    }
    

    @Test
    public void testRestApiObterAcessoId() throws JsonProcessingException, Exception {
    	DefaultMockMvcBuilder builder= MockMvcBuilders.webAppContextSetup(this.applicationContext);
    	MockMvc mockMvc=builder.build();
    	
    	Acesso acesso=new Acesso();
    	acesso.setDescricao("ROLE_TESTE_OBTER_ACESSO_ID");
    	
    	acesso=acessoRepository.save(acesso);
    	
    	ObjectMapper objectMapper=new ObjectMapper();
    	
    	ResultActions retornoApi=mockMvc.perform(MockMvcRequestBuilders.get("/obterAcesso/" + acesso.getId())
    			.content(objectMapper.writeValueAsString(acesso))
    			.accept(org.springframework.http.MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON));
    	
    	
    	assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
    	
    	Acesso acessoRetorno=objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
    	
    	assertEquals(acesso.getDescricao(), acessoRetorno.getDescricao());
    	assertEquals(acesso.getId(), acessoRetorno.getId());
    }
    
    
    @Test
    public void testRestApiObterAcessoDesc() throws JsonProcessingException, Exception {
    	DefaultMockMvcBuilder builder= MockMvcBuilders.webAppContextSetup(this.applicationContext);
    	MockMvc mockMvc=builder.build();
    	
    	Acesso acesso=new Acesso();
    	acesso.setDescricao("ROLE_TESTE_OBTER_LIST");
    	
    	acesso=acessoRepository.save(acesso);
    	
    	ObjectMapper objectMapper=new ObjectMapper();
    	
    	ResultActions retornoApi=mockMvc.perform(MockMvcRequestBuilders.get("/buscarPorDesc/OBTER_LIST")
    			.content(objectMapper.writeValueAsString(acesso))
    			.accept(org.springframework.http.MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON));
    	
    	
    	assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
    	
    	List<Acesso> retornoApiList= objectMapper
    			.readValue(retornoApi.andReturn().getResponse().getContentAsString(), new TypeReference<List<Acesso>>() {
				});
    	
    	assertEquals(1,retornoApiList.size());
    	assertEquals(acesso.getDescricao(), retornoApiList.get(0).getDescricao());
    	
    	acessoRepository.deleteById(acesso.getId());
    	
    }
    
    @Test
    public void testCadastraAcesso() throws ExceptionMentoriaJava {
    	
    	String descacesso="ROLE_ADMIN"+ Calendar.getInstance().getTimeInMillis();
        Acesso acesso=new Acesso();
        
        acesso.setDescricao(descacesso);

       assertEquals(true, acesso.getId() == null);
      
       // Gravou no banco
      acesso= acessoController.salvarAcesso(acesso).getBody();

       assertEquals(true, acesso.getId() > 0);

       // validando dados salvos da forma correta
       assertEquals(descacesso,acesso.getDescricao());
       
       // Validar carregamento
       Acesso acesso2=acessoRepository.findById(acesso.getId()).get();
       
       assertEquals(acesso.getId(), acesso2.getId());
       
       // teste delete
       acessoRepository.deleteById(acesso2.getId());
       acessoRepository.flush(); // rodar esse sql de delete no banco de dados
       
       Acesso acesso3=acessoRepository.findById(acesso2.getId()).orElse(null);
       assertEquals(true, acesso3 == null);
       
       // teste de query
       acesso=new Acesso();
       acesso.setDescricao("ACESSO_ALUNO");
       
       acesso=acessoController.salvarAcesso(acesso).getBody();
       List<Acesso> acessos=acessoRepository.buscarAcesso("ALUNO".trim().toUpperCase());
       assertEquals(1, acessos.size());
       
       acessoRepository.deleteById(acesso.getId());
    }

}
