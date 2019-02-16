package bbdd2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bbdd2.model.Car;
import bbdd2.repository.CarRepository;
import bbdd2.repository.CartRepository;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired private CarRepository carRepository;
	
	@Override
	public Car findByName(String name) {
		return carRepository.findByName(name);
	}
}
