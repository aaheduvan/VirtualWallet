package api.repositories;

import api.domains.Cuenta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CuentaRepository extends CrudRepository<Cuenta, Integer> {
	Optional<Cuenta> findByAlias(String alias);
	boolean existsByAlias(String alias);
	List<Cuenta> findByidCliente(Long idCliente);
	List<Cuenta> findByMoneda(String moneda);
	@Query(value = "SELECT * FROM cuenta WHERE balance = (SELECT max(balance) FROM cuenta)", nativeQuery = true)
	Optional<Cuenta> findMaxBalance();
	@Query(value = "SELECT * FROM cuenta WHERE balance=(SELECT min(balance) FROM cuenta)", nativeQuery = true)
	Optional<Cuenta> findMinBalance();
}
