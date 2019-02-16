package bbdd2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bbdd2.model.MotorCycle;
import bbdd2.repository.MotorCycleRepository;
import bbdd2.repository.ProductRepository;

@Service
public class MotorCycleServiceImpl implements MotorCycleService {
	
	@Autowired
	private MotorCycleRepository motorCycleRepository;
	
	@Autowired private ProductRepository productRepository;
	
	@Override
	public MotorCycle findByName(String name) {
		return this.motorCycleRepository.findByName(name);
	}
	
	@Override
	public MotorCycle save(MotorCycle motorCycle) {
		return this.productRepository.save(motorCycle);
	}
}
