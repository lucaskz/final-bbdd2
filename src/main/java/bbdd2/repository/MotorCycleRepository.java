package bbdd2.repository;

import org.springframework.stereotype.Repository;
import bbdd2.model.MotorCycle;

@Repository
public interface MotorCycleRepository extends BaseRepository<MotorCycle, String> {
	
	MotorCycle findByName(String name);
	
	MotorCycle save(MotorCycle motorCycle);
	
}
