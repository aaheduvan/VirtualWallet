package api.repositories;

import api.domains.Cuenta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CuentaRepository extends CrudRepository<Cuenta, Integer> {
	Optional<Cuenta> findByAlias(String alias);
	List<Cuenta> findByidCliente(Long idCliente);
	List<Cuenta> findByMoneda(String moneda);
	@Query(value = "SELECT *" + 
					"FROM Cuenta" + 
					"WHERE balance=(SELECT MAX(balance) FROM Cuenta);", nativeQuery = true)
	Optional<Cuenta> findMaxBalance();
	@Query(value = "SELECT *" + 
			"FROM Cuenta" + 
			"WHERE balance=(SELECT MIN(balance) FROM Cuenta);", nativeQuery = true)
	Optional<Cuenta> findMinBalance();
}
