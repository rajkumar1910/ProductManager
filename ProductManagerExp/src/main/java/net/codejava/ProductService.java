package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	
	public List<Product> listAll() {
		return repo.findAll();
	}
	
	public void save(Product product) {
		repo.save(product);
	}

	public Product get(int product_item_id) {
		return repo.findById(product_item_id).get();
	}

	public void delete(int product_item_id) {
		repo.deleteById(product_item_id);
	}

	@Query("UPDATE part_number SET product_item_id = :product_item_id, part_number = :part_number, part_classification = :part_classification,part_quantity = :part_quantity,notes = :notes WHERE product_item_id = :product_item_id") 
	public void updatePart(@Param("product_item_id") int product_item_id, @Param("part_number") int part_number, @Param("part_classification") String part_classification, @Param("part_quantity") int part_quantity, @Param("notes") String notes) {
	}

	public List listAllParts() {
		
		return (List) repo.listAllParts();
	}

	
	
}
