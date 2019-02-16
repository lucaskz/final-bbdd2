package bbdd2.repository;

import bbdd2.model.Car;

public interface CarRepository extends BaseRepository<Car, String> {
	
	Car findByName(String name);
}
