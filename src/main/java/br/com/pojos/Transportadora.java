package br.com.pojos;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("TRANSPORTADORA")
@Table(name = "TRANSPORTADORA")
public class Transportadora extends Usuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7822016213524470548L;
	
	@Column(name = "CNPJ", unique = true, nullable = false)
	private String cnpj;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TRANSPORTADORA")
	private List<Onibus> onibus;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TRANSPORTADORA")
	private List<Motorista> motoristas;
	
	@ManyToMany
	@JoinTable(name = "TRANSPORTADORA_TEM_LINHAS", 
	joinColumns={@JoinColumn(name = "ID_TRANSPORTADORA")}, 
	inverseJoinColumns={@JoinColumn(name = "ID_LINHA")})
	private List<Linha> linhas;
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	
	public Transportadora(Integer idUsuario, String nome, String login, String telefone, String email, Conta conta,
			String cnpj) {
		super(idUsuario, nome, login, telefone, email, conta);
		this.cnpj = cnpj;
	}

	public Transportadora(){
		
	}
	
}