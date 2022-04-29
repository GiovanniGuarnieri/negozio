package it.epicode.negozio.cliente;

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
 * Classe che gestisce la persistenza su database della classe Cliente
 * 
 * 
 * @author Giovanni Guarnieri
 * 
 * 
 * 
 * 
 */
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "il campo nome è obbligatorio!")
	private String nome;
	@NotNull(message = "il campo cognome è obbligatorio!")
	private String cognome;
	private String partitaIva;
	@JsonIgnore
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
	private List<Ordine> ordini;
//ciao
}
