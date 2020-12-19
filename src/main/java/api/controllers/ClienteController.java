package api.controllers;

import api.domains.Cliente;
import api.repositories.ClienteRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/cliente")
public class ClienteController {
	
	@Autowired
    private ClienteRepository clienteRepository;	
 
    @GetMapping(value = "/get", params = {"id"})
    public Optional<Cliente> getCliente(@RequestParam (value="id") Long idCliente) {
    	return clienteRepository.findById(idCliente);
    }
    
    @GetMapping(value = "/get", params = {"nombreCliente"})
    public Iterable<Cliente> getIdClienteByNombre(@RequestParam (value="nombreCliente") String nombreCliente) {
    	return clienteRepository.findByNombreCliente(nombreCliente);
    }
    
    @GetMapping("/getAll")
    public Iterable<Cliente> getAll() {
    	// This returns a JSON or XML with the users
    	return clienteRepository.findAll();
    }
    
    @PostMapping(value= "/add", params = {"nombreCliente","cuit"}) // Map ONLY GET Requests
    public String addNewCliente ( @RequestParam (value="nombreCliente") String nombreCliente, 
								 @RequestParam (value="cuit") Integer cuit) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		Cliente n = new Cliente();
		n.setNombreCliente(nombreCliente);
		n.setCUIT(cuit);
		clienteRepository.save(n);
		return "Saved";
	}
    
    @DeleteMapping(value = "/delete", params = {"id"})
    public String delete(@RequestParam("id") Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()) {
        	clienteRepository.deleteById(id);
        	return "Borrado";
        }else        
        	return "Error en el borrado";
    }
    
    @PutMapping(value = "/update", params = {"idCliente","nombreCliente","cuit"})
    public String update(@RequestParam (value="idCliente") Long idCliente, 
    					 @RequestParam (value="nombreCliente") String nombreCliente, 
    					 @RequestParam (value="cuit") Integer cuit) {
        if(clienteRepository.existsById(idCliente)) {
        	Cliente update = new Cliente();
        	update.setIdCliente(idCliente);
        	update.setNombreCliente(nombreCliente);
        	if(cuit!=null)
        		update.setCUIT(cuit);
        	clienteRepository.save(update);
        	return "Actualizado";
        }else        
        	return "Error en la actualizacion";
    }

}
