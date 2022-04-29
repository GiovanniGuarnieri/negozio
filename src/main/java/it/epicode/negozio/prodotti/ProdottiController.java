package it.epicode.negozio.prodotti;

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
 * classe REST della classe Prodotti
 */
@RestController
@RequestMapping("/prodotti")
@Tag(name = "Prodotto rest services", description = "implementazioni delle api rest dei prodotto")
public class ProdottiController {
	@Autowired
	ProdottiRepository pr;
	
	/**
	 * inserimento nel db di un prodotto
	 * @param c
	 * @param errori
	 * @return
	 */
	
	@Operation (summary = "Inserisce un	Prodotto nel db", description = "inserisce un prodotto nel db ")
	@PostMapping(path = "/inserisci" ,produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity inserisciProdotto(@Valid @RequestBody Prodotti c, BindingResult errori) {

		if(errori.hasErrors()) {
			List<String> descrizioneDiErrore = new ArrayList<String>();
			for(ObjectError e : errori.getAllErrors()) {
				descrizioneDiErrore.add(e.getDefaultMessage());
			}
			return new ResponseEntity(descrizioneDiErrore , HttpStatus.BAD_REQUEST);

		}
		pr.save(c);
		return ResponseEntity.ok("Prodotto inserito");
	}
	
	
	/**
	 * ricerca di un prodotto nel db attraverso l'id passato in input
	 * 
	 * @param id
	 * @return
	 */
	@Operation (summary = "cerca un	Prodotto nel db", description = "cerca un prodotto nel db tramite id ")
	@GetMapping("/cercaPerId/{id}")
	public ResponseEntity cercaPerId(@PathVariable("id") int id) {
		List<Prodotti> c = pr.findById(id);
		if(c.size()>0) {
			return ResponseEntity.ok(c);
		}
		else { 
			return new ResponseEntity("nessun prodotto trovato", HttpStatus.NOT_FOUND);
		}




	}
	
	
	/**
	 * ritorna tutti i prodotti presenti nel db 
	 * @return
	 */
	@Operation (summary = "ritorna tutti i Prodotti nel db", description = "ritorna tutti i prodotti presenti nel db ")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity tuttiIProdotti() {
		return ResponseEntity.ok(pr.findAll());
	}
	
	
	
	/**
	 * rimozione dei prodotti presenti nel db attraverso l'id passato in input
	 * @param id
	 * @return
	 */
	@Operation (summary = "elimina un Prodotto nel db", description = "elimina un prodotto nel db tramite l'id ")
	@DeleteMapping("/eliminaProdotto/{id}")
	public ResponseEntity cancellaProdotto(@PathVariable ("id") int id ) {
		if(pr.existsById(id)) {
		pr.deleteById(id);

		return ResponseEntity.ok("Prodotto eliminato");}
		else {
			return  new ResponseEntity("Prodotto non trovato ", HttpStatus.NOT_FOUND);
		}

		
		
	}

}
