package it.epicode.negozio.cliente;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
	/**
	 * ricerca di un cliente attraverso il cognome
	 * @param cognome
	 * @return
	 */
	public List<Cliente> findByCognome(String cognome);
	
	
	/**
	 * ricerca di un cliente attraverso la partitaIva
	 * @param partitaIva
	 * @return
	 */
	public List<Cliente> findByPartitaIva(String partitaIva);
	
}
