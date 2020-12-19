package api.domains;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@Table(name = "transferencia")
public class Transferencia {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTransferencia;
	private Integer idCuentaOrigen;
	private Integer idCuentaDestino;
	private Float importe;
	private String moneda;
	private Timestamp fechayHora;
	private String tipoDeOperacion;
	
	public Long getIdTransferencia() {
		return idTransferencia;
	}
	public void setIdTransferencia(Long idTransferencia) {
		this.idTransferencia = idTransferencia;
	}
	public Integer getIDCuentaOrigen() {
		return idCuentaOrigen;
	}
	public void setIDCuentaOrigen(Integer idCuentaOrigen) {
		this.idCuentaOrigen = idCuentaOrigen;
	}
	public Integer getIDCuentaDestino() {
		return idCuentaDestino;
	}
	public void setIDCuentaDestino(Integer idCuentaDestino) {
		this.idCuentaDestino = idCuentaDestino;
	}
	public Float getImporte() {
		return importe;
	}
	public void setImporte(Float importe) {
		this.importe = importe;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Timestamp getFechayHora() {
		return fechayHora;
	}
	public void setFechayHora(Timestamp fechayHora) {
		this.fechayHora = fechayHora;
	}
	public String getTipoDeOperacion() {
		return tipoDeOperacion;
	}
	public void setTipoDeOperacion(String tipoDeOperacion) {
		this.tipoDeOperacion = tipoDeOperacion;
	}
}

