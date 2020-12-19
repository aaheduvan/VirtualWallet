package api.controllers;

import api.domains.Cuenta;
import api.repositories.CuentaRepository;

import java.util.ArrayList;
import java.util.List;
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
@RequestMapping(path="/cuenta")
public class CuentaController {
	
	@Autowired
    private CuentaRepository cuentaRepository;	
 
    @GetMapping(value = "/get", params = {"cvu"})
    public Optional<Cuenta> getCuenta(@RequestParam (value="cvu") Integer cvu) {
    	return cuentaRepository.findById(cvu);
    }
    
    @GetMapping(value = "/get", params = {"alias"})
    public Optional<Cuenta> getCuentaByAlias(@RequestParam (value="alias") String alias) {
    	return cuentaRepository.findByAlias(alias);
    }
    
    @GetMapping(value = "/getMax")
    public Optional<Cuenta> getCuentaMax() {
    	return cuentaRepository.findMaxBalance();
    } 
    
    @GetMapping(value = "/getMin")
    public Optional<Cuenta> getCuentaMin() {
    	return cuentaRepository.findMinBalance();
    } 
    
    @GetMapping("/getAll")
    public Iterable<Cuenta> getAll() {
    	// This returns a JSON or XML with the users
    	return cuentaRepository.findAll();
    }
    
    @PostMapping(value= "/add", params = {"cvu","alias","idCliente","moneda","balance"}) // Map ONLY GET Requests
    public String addNewCuenta ( @RequestParam (value="cvu") Integer cvu, 
								 @RequestParam (value="alias") String alias,
								 @RequestParam (value="idCliente") Long idCliente, 
								 @RequestParam (value="moneda") String moneda,
								 @RequestParam (value="balance", required=false) Float balance) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		Cuenta c = new Cuenta();
		c.setCvu(cvu);
		c.setAlias(alias);
		c.setIdCliente(idCliente);
		c.setMoneda(moneda);
		c.setBalance(balance);
		cuentaRepository.save(c);
		return "Saved";
	}
    
    @DeleteMapping(value = "/delete", params = {"cvu"})
    public String delete(@RequestParam("cvu") Integer cvu) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(cvu);
        if(cuenta.isPresent()) {
        	cuentaRepository.deleteById(cvu);
        	return "Borrado";
        }else        
        	return "Error en el borrado";
    }
    
    @PutMapping(value = "/update", params = {"cvu","alias","idCliente","moneda","balance"})
    public String update(@RequestParam (value="cvu") Integer cvu, 
			 			@RequestParam (value="alias", required=false) String alias,
			 			@RequestParam (value="idCliente", required=false) Long idCliente, 
			 			@RequestParam (value="moneda", required=false) String moneda,
			 			@RequestParam (value="balance", required=false) Float balance) {
        if(cuentaRepository.existsById(cvu)) {
        	Cuenta update = new Cuenta();
        	update.setCvu(cvu);
        	update.setAlias(alias);
        	update.setIdCliente(idCliente);
        	update.setMoneda(moneda);
        	if(balance!=null)
        		update.setBalance(balance);
        	cuentaRepository.save(update);
        	return "Actualizado";
        }else        
        	return "Error en la actualizacion";
    }

}
