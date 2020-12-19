package api.repositories;

import api.domains.Transferencia;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransferenciaRepository extends CrudRepository<Transferencia, Long> {
	List<Transferencia> findByidCliente(String nombreCliente);
	List<Transferencia> findByFechayHora(Timestamp fechayHora);
	List<Transferencia> findByCuentaOrigen(Integer cuentaOrigen);
	List<Transferencia> findByCuentaDestino(Integer cuentaDestino);

}
