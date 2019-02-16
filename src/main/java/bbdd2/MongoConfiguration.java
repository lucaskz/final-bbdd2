package bbdd2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.WriteConcernResolver;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {
	
	@Bean
	public MongoClient mongoClient() {
		return new MongoClient("localhost");
	}
	
	@Override
	public String getDatabaseName() {
		return "database";
	}
	
	@Bean
	public WriteConcernResolver writeConcernResolver() {
		return action -> WriteConcern.ACKNOWLEDGED;
		
	}
}