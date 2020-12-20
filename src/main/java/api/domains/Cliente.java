package api.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCliente;
	private String nombreCliente;
	private Integer cuit;
		
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public Integer getCUIT() {
		return cuit;
	}
	public void setCUIT(Integer cuit) {
		this.cuit = cuit;
	}
	
}
