package api.controllers;

import api.domains.Transferencia;
import api.repositories.TransferenciaRepository;
import api.services.TransferenciaService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/transferencia")
public class TransferenciaController {
	
	@Autowired
    private TransferenciaRepository transferenciaRepository;
	@Autowired
    private TransferenciaService transferenciaService;
 
    @GetMapping(value = "/get", params = {"id"})
    public Optional<Transferencia> getTransferencia(@RequestParam (value="id") Long idTransferencia) {
    	return transferenciaRepository.findById(idTransferencia);
    }
    
    @GetMapping(value = "/get", params = {"timeStamp"})
    public List<Transferencia> getTransferenciaByTimestamp(@RequestParam (value="id") Timestamp timeStampTransferencia) {
    	return transferenciaRepository.findByFechayHora(timeStampTransferencia);
    }
    
    @GetMapping(value = "/get", params = {"cvu"})
    public List<Transferencia> getAllTransferenciasCuenta(@RequestParam (value="cvu") Integer cvu) {
    	List<Transferencia> listaTransf = new ArrayList<Transferencia>();
    	listaTransf.addAll(transferenciaRepository.findByIdCuentaOrigen(cvu));
    	listaTransf.addAll(transferenciaRepository.findByIdCuentaDestino(cvu));
    	return listaTransf;
    }
         
    @GetMapping("/getAll")
    public Iterable<Transferencia> getAll() {
    	// This returns a JSON or XML with the users
    	return transferenciaRepository.findAll();
    }
    
    @DeleteMapping(value = "/delete", params = {"id"})
    public String delete(@RequestParam("id") Long id) {
        if(transferenciaRepository.existsById(id)) {
        	transferenciaRepository.deleteById(id);
        	return "Borrado";
        }else return "Error en el borrado";
    }
    
    @PostMapping(value = "/transfer", params = {"cuentaOrigen","cuentaDestino","importe"})
    public String addTransferencia(@RequestParam (value="cuentaOrigen") Integer idCuentaOrigen,
    								@RequestParam (value="cuentaDestino")Integer idCuentaDestino,
    								@RequestParam (value="importe")Float importe) {
    	return transferenciaService.addTransferencia(idCuentaOrigen, idCuentaDestino, importe); 
    }
    
    @PostMapping(value = "/reverse", params = {"idTransferencia"})
    public String reverseTransferencia(@RequestParam (value="idTransferencia") Long idTransferencia) {
    	return transferenciaService.revertirTransferencia(idTransferencia); 
    }

}
