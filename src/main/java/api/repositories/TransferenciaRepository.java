package api.repositories;

import api.domains.Transferencia;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransferenciaRepository extends CrudRepository<Transferencia, Long> {
	List<Transferencia> findByFechayHora(Timestamp fechayHora);
	List<Transferencia> findByIdCuentaOrigen(Integer cuentaOrigen);
	List<Transferencia> findByIdCuentaDestino(Integer cuentaDestino);

}
