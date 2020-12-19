package api.services;

import api.domains.Cuenta;
import api.domains.Transferencia;
import api.repositories.CuentaRepository;
import api.repositories.TransferenciaRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@CrossOrigin(origins = "http://localhost:3000")
@Service
public class TransferenciaService {
	
	@Autowired
    private TransferenciaRepository transferenciaRepository;
	@Autowired
	private CuentaRepository cuentaRepository;
 
	/*
	 * 
	 * 
	 * 
	 */
    public String addTransferencia (Integer idCuentaOrigen, Integer idCuentaDestino, Float importe) {
		if(cuentaRepository.existsById(idCuentaOrigen)) {
			if(cuentaRepository.existsById(idCuentaDestino)) {
				Cuenta origen = cuentaRepository.findById(idCuentaOrigen).get();
				Cuenta destino = cuentaRepository.findById(idCuentaDestino).get();
				if(origen.getMoneda()==destino.getMoneda()) {
					if(importe <= origen.getBalance()) {
						Transferencia t = new Transferencia();
						t.setIDCuentaOrigen(idCuentaOrigen);
						t.setIDCuentaDestino(idCuentaDestino);
						t.setFechayHora(new Timestamp(System.currentTimeMillis()));
						t.setImporte(importe);
						t.setMoneda(origen.getMoneda());
						t.setTipoDeOperacion("Transferencia");
						transferenciaRepository.save(t);
						origen.setBalance(origen.getBalance()-importe);
						cuentaRepository.save(origen);
						destino.setBalance(destino.getBalance()+importe);
						cuentaRepository.save(destino);
					}else return "Error: Fondos Insuficientes";
				}else return "Error de tipo de moneda: Cuentas no compatibles";
			}else return "Error. CVU de cuenta destino no existente";
		}else return "Error. CVU de cuenta origen no existente";
		return "Transferencia exitosa";
	}
    
    public String revertirTransferencia( Long idTransferencia ) {
    	if(transferenciaRepository.existsById(idTransferencia)) {
    		Transferencia transf = transferenciaRepository.findById(idTransferencia).get();
    		if(cuentaRepository.existsById(transf.getIDCuentaDestino())) {
    			Cuenta origen = cuentaRepository.findById(transf.getIDCuentaOrigen()).get();
    			Cuenta destino = cuentaRepository.findById(transf.getIDCuentaDestino()).get();
    			if(transf.getImporte()<=destino.getBalance()) {
    				Transferencia t = new Transferencia();
					t.setIDCuentaOrigen(transf.getIDCuentaDestino());
					t.setIDCuentaDestino(transf.getIDCuentaOrigen());
					t.setFechayHora(new Timestamp(System.currentTimeMillis()));
					t.setImporte(transf.getImporte());
					t.setMoneda(transf.getMoneda());
					t.setTipoDeOperacion("Reversion de Transferencia");
					transferenciaRepository.save(t);
					origen.setBalance(origen.getBalance()+transf.getImporte());
					cuentaRepository.save(origen);
					destino.setBalance(destino.getBalance()-transf.getImporte());
					cuentaRepository.save(destino);
    			}else return "Error: Saldo insuficiente para realizar la operacion";
    		}else return "Error: La cuenta ya no existe";
    	}else return "Error: Id de Transferencia invalido"; 		
    	return "Reversion de Transferencia exitosa";
    }

}
