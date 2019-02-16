package bbdd2.model;

import org.springframework.data.annotation.Version;

public class AvailableStock extends Stock {
	
	@Version
	private long version;
	
	public long getVersion() {
		return version;
	}
	
	public void setVersion(long version) {
		this.version = version;
	}
}
