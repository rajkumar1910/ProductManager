package net.codejava;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT * FROM part_number",nativeQuery = true)
	public List listAllParts(); 

}
