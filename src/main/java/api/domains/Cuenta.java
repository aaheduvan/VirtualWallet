package api.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cuenta")
public class Cuenta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cvu;
	private String alias;
	private Long idCliente;
	private String moneda;
	private Float balance;
	
	public Cuenta() {
	}
	
	public Cuenta(Long idCliente,String moneda) {
		this.alias="";
		//generar alias aleatoriamente
		this.idCliente=idCliente;
		this.moneda=moneda;
		this.balance=(float)0;
	}
	
	public Integer getCvu() {
		return cvu;
	}
	public void setCvu(Integer cvu) {
		this.cvu = cvu;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	} 

	
}
