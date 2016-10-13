package br.com.ws.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import br.com.dao.PassageiroDAO;
import br.com.dao.SimpleEntityManager;
import br.com.pojos.Passageiro;

@Path("/passageiro")
public class PassageiroService {

	SimpleEntityManager sem = SimpleEntityManager.getInstance();
	PassageiroDAO pDAO = new PassageiroDAO(sem.getEntityManager());
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Passageiro consultar(@PathParam("id") Integer idPassageiro){
		int exceptionNumber = 500;
		try{
			Passageiro passageiro = pDAO.consultaId(idPassageiro);
			if(passageiro == null){
				exceptionNumber = 404;
				throw new Exception("Nenhum passageiro com esse id!");
			}
			return passageiro;
		} catch (Exception e){
			throw new WebApplicationException(exceptionNumber);
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Passageiro> listar(){
		int exceptionNumber = 500;
		try {
			List<Passageiro> passageiros = pDAO.listarPassageiros();
			if(passageiros.isEmpty()){
				exceptionNumber = 404;
				throw new Exception("Nenhum passageiro cadastrado!");
			}
			return passageiros;
		} catch (Exception e) {
			throw new WebApplicationException(exceptionNumber);
		}
	}
	
}
