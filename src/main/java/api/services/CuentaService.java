package api.services;

import api.domains.Cuenta;
import api.domains.Cliente;
import api.repositories.ClienteRepository;
import api.repositories.CuentaRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@CrossOrigin(origins = "http://localhost:3000")
@Service
public class CuentaService {
	
	@Autowired
    private ClienteRepository clienteRepository;
	@Autowired
	private CuentaRepository cuentaRepository;
 
	/*
	 * 
	 * 
	 * 
	 */
    public String addCuenta (Long idCliente, String moneda) {
		if(clienteRepository.existsById(idCliente)) {
			Cuenta c = new Cuenta(idCliente,moneda);
			cuentaRepository.save(c);
		}else return "Error. idCliente no existente";
		return "Cuenta creada exitosamente";
	}
    
    

}
