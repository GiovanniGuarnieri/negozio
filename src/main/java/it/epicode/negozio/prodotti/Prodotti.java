package it.epicode.negozio.prodotti;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.epicode.negozio.ordine.Ordine;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
/**
 * Classe che gestisce la persistenza su database della classe Prodotto
 * 
 * 
 * @author Giovanni Guarnieri
 * 
 * 
 * 
 * 
 */
public class Prodotti {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "il campo nome è obbligatorio!")
	private String nome;
	@NotNull(message = "il campo prezzo è obbligatorio!")
	private double prezzo;
	@JsonIgnore
	@OneToMany(mappedBy = "prodotto",cascade = CascadeType.ALL)
	private List<Ordine>ordini;

}
