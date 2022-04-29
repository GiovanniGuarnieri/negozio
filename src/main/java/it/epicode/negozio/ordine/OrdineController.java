package it.epicode.negozio.ordine;

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
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Classe REST della classe Ordine
 */


@RestController
@RequestMapping("/ordini")
@Tag(name = "Ordine rest services", description = "implementazioni delle api rest dei Ordine")
public class OrdineController {
	@Autowired
	OrdineRepository or;
	/**
	 * inserimento a database di un Ordine
	 * associato ad un metodo POST
	 * @param o
	 * @return
	 */
	@Operation (summary = "Inserisce un	ordine nel db", description = "inserisce un ordine nel db ")
	@PostMapping(path = "/inserisci" ,produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity inserisciProdotto(@Valid @RequestBody Ordine o, BindingResult errori) {

		if(errori.hasErrors()) {
			List<String> descrizioneDiErrore = new ArrayList<String>();
			for(ObjectError e : errori.getAllErrors()) {
				descrizioneDiErrore.add(e.getDefaultMessage());
			}
			return new ResponseEntity(descrizioneDiErrore , HttpStatus.BAD_REQUEST);

		}
		or.save(o);
		return ResponseEntity.ok("Ordine inserito");
	}
	
	
	
	
	
	/**
	 * ricerca un ordine tramite l'id dato in input
	 * @param id
	 * @return
	 */
	
	@Operation (summary = "ricerca un ordine tramite id", description = "ricerca un ordine tramite tramite id ")
	@GetMapping("/cercaPerIdOrdine/{id}")
	public ResponseEntity cercaPerId(@PathVariable("id") int id) {
		List<Ordine> c = or.findById(id);
		if(c.size()>0) {
			return ResponseEntity.ok(c);
		}
		else { 
			return new ResponseEntity("nessun ordine trovato", HttpStatus.NOT_FOUND);
		}
	}
	
	
	/**
	 * ritorna tutti gli ordini presenti nel db 	
	 * @return
	 */
	@Operation (summary = "ritorna tutti gli ordini presenti nel db", description = "ricerca tutti gli ordini presenti nel db ")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity tuttiGliOrdini() {
		return ResponseEntity.ok(or.findAll());
	}
	
	
	/**
	 * rimozione di un ordine nel db attraverso l'id dato in input 
	 * @param id
	 * @return
	 */
	@Operation (summary = "elimina un Ordine nel db", description = "elimina un ordine nel db ")
	@DeleteMapping("/eliminaOrdine/{id}")
	public ResponseEntity cancellaOrdine(@PathVariable ("id") int id ) {
		if(or.existsById(id)) {
		or.deleteById(id);

		return ResponseEntity.ok("Ordine eliminato");}
		else {
			return  new ResponseEntity("Ordine non trovato ", HttpStatus.NOT_FOUND);
		}

		
		
	}

}
