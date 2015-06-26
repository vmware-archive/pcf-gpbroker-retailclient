package io.pivotal.fe.retaildata.client.cloudfoundry;

import io.pivotal.fe.retaildata.client.cloud.GreenplumServiceInfo;

import org.springframework.cloud.service.relational.DataSourceCreator;


public class GreenplumDataSourceCreator extends DataSourceCreator<GreenplumServiceInfo> {
	
	private static final String[] DRIVERS = new String[]{"com.pivotal.jdbc.GreenplumDriver"};
	private static final String VALIDATION_QUERY = "SELECT 1";
	
	public GreenplumDataSourceCreator() {		
	    super("spring-cloud.greenplum.driver", DRIVERS, VALIDATION_QUERY);    
	}
}