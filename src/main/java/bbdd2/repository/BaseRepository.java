package bbdd2.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface BaseRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {
	
	Optional<T> findById(ID id);
	
	<S extends T> S save(S entity);
}