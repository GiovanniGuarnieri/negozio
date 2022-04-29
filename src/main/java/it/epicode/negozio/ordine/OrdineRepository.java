package it.epicode.negozio.ordine;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface OrdineRepository extends CrudRepository<Ordine, Integer> {
	
	/**
	 * ricerca di un ordine attraverso l'id
	 * @param id
	 * @return
	 */
	public List<Ordine> findById(int id);

}
