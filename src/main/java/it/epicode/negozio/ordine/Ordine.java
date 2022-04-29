package it.epicode.negozio.ordine;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import it.epicode.negozio.cliente.Cliente;
import it.epicode.negozio.prodotti.Prodotti;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
/**
 * Classe che gestisce la persistenza su database della classe ordine in relazione con prodotto e cliente
 * 
 * 
 * @author Giovanni Guarnieri
 * 
 * 
 * 
 * 
 */
public class Ordine {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "il campo data non può essere vuoto")
	private String data;
	@ManyToOne
	@JoinColumn(name="id_prodotto")
	private Prodotti prodotto ;
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	//ciao
}
