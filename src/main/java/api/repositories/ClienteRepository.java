package api.repositories;

import api.domains.Cliente;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	List<Cliente> findByNombreCliente(String nombreCliente);
}
