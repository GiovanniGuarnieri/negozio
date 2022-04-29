package it.epicode.negozio.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * classe REST della Classe Cliente
 */


@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente rest services", description = "implementazioni delle api rest dei Clienti")
public class ClienteController {
	@Autowired
	ClienteRepository cp;
	/**
	 * inserimento a database di un Cliente
	 * associato ad un metodo POST
	 * @param c
	 * @return
	 */
	@Operation (summary = "Inserisce un cliente nel db", description = "inserisce un cliente nel db con nome cognome e partitaIva")
	@ApiResponse(responseCode = "200" , description = "Calciatore inserito con successo nel db !")
	@ApiResponse(responseCode ="500" , description = "ERRORE NEL SERVER")
	@PostMapping(path = "/inserisci" ,produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity inserisciCliente(@Valid @RequestBody Cliente c, BindingResult errori) {

		if(errori.hasErrors()) {
			List<String> descrizioneDiErrore = new ArrayList<String>();
			for(ObjectError e : errori.getAllErrors()) {
				descrizioneDiErrore.add(e.getDefaultMessage());
			}
			return new ResponseEntity(descrizioneDiErrore , HttpStatus.BAD_REQUEST);

		}
		cp.save(c);
		return ResponseEntity.ok("Cliente inserito");
	}
	/**
	 * ricerca nel database di un Cliente attraverso il cognome dato in input
	 * associato ad un metodo GET
	 * @param cognome
	 * @return
	 */
	@Operation (summary = "cerca cliente tramite cognome", description = "cerca un cliente nel db tramite cognome ")
	@GetMapping("/cercaPerCognome/{cognome}")
	public ResponseEntity cercaPerCognome(@PathVariable("cognome") String cognome) {
		List<Cliente> c = cp.findByCognome(cognome);
		if(c.size()>0) {
			return ResponseEntity.ok(c);
		}
		else { 
			return new ResponseEntity("nessun Cliente trovato", HttpStatus.NOT_FOUND);
		}




	}
	/**
	 * ricerca nel database di un Cliente attraverso la partita Iva data in input
	 * associato ad un metodo GET
	 * @param partitaIva
	 * @return
	 */
	@Operation (summary = "cerca un cliente nel db con partitaIva", description = "cerca un cliente nel db attraverso la partitaIva")
	@GetMapping("/cercaPerPartitaIva/{partitaIva}")
	public ResponseEntity cercaPerPartitaIva(@PathVariable("partitaIva") String partitaIva) {
		List<Cliente> c = cp.findByPartitaIva(partitaIva);
		if(c.size()>0) {
			return ResponseEntity.ok(c);
		}
		else { 
			return new ResponseEntity("nessun Cliente trovato", HttpStatus.NOT_FOUND);
		}




	}
	/**
	 * ritorno di tutti i cliente presenti nel database 
	 * associato ad un metodo GET
	 * 
	 * @return
	 */
	@Operation (summary = "ritorna tutti i  clienti nel db", description = "ritorna tutti i clienti nel db ")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity tuttiIClienti() {
		return ResponseEntity.ok(cp.findAll());
	}
	/**
	 * rimozione di un cliente attraverso l'id dato in input
	 * @param id
	 * @return
	 */
	@Operation (summary = "elimina un cliente nel db", description = "elimina  un cliente nel db attraverso l'id")
	@DeleteMapping("/eliminaCliente/{id}")
	public ResponseEntity cancellaCliente(@PathVariable ("id") int id ) {
		if(cp.existsById(id)) {
			cp.deleteById(id);

			return ResponseEntity.ok("Cliente eliminato");}
		else {
			return  new ResponseEntity("Cliente non trovato ", HttpStatus.NOT_FOUND);
		}


		/**
		 * Modifica di un cittadino presente nel db attraverso il 
		 * mapping PUT
		 */

	}



}
