package com.ajes.repository;



import com.ajes.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {


	
	Optional<ImageModel >findByType(String type);

	

	Optional<ImageModel> findByName(String name);

}
