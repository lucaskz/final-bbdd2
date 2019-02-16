package bbdd2.service;

import bbdd2.model.MotorCycle;

public interface MotorCycleService {
	
	MotorCycle findByName(String name);
	
	MotorCycle save(MotorCycle motorCycle);
}
