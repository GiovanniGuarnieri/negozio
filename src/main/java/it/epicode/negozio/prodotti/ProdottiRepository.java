package it.epicode.negozio.prodotti;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProdottiRepository extends CrudRepository<Prodotti, Integer> {

	
	
	/**
	 * ricerca di un prodotto tramite l'id
	 * @param id
	 * @return
	 */
	public List<Prodotti> findById(int id);
}
