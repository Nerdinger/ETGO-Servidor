package br.com.pojos;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("EMPRESA")
@Table(name = "EMPRESA")
@NamedQueries({
	@NamedQuery(name = "listarEmpresas", query = "select t from Empresa as t"),
	@NamedQuery(name = "buscarEmpresaPorUserName", query = "SELECT p FROM Empresa p WHERE LOWER(p.userName) LIKE :userName"),
	@NamedQuery(name = "verificarExistenciaEmailEmpresa", query = "SELECT COUNT(p.email) FROM Empresa p WHERE p.email LIKE :email")
})
public class Empresa extends Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -464061049636228661L;
	
	@Column(name = "CNPJ", unique = true, nullable = false)
	private String cnpj;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EMPRESA", nullable = true)
	private List <Passageiro> passageiro;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EMPRESA", nullable = true)
	private List <Transacao> transacao;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Empresa(Integer idUsuario, String userName, String password, String nome, String telefone,
			String email, Double saldo, String cnpj, List<Passageiro> passageiro, List<Transacao> transacao) {
		super(idUsuario, userName, password, nome, telefone, email, saldo);
		this.cnpj = cnpj;
		this.passageiro = passageiro;
		this.transacao = transacao;
	}

	public Empresa(){
		
	}
}
