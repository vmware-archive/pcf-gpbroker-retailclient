package io.pivotal.fe.retaildata.client.cloud;

import org.springframework.cloud.service.ServiceInfo.ServiceLabel;
import org.springframework.cloud.service.common.RelationalServiceInfo;

@ServiceLabel("greenplum")  
public class GreenplumServiceInfo extends RelationalServiceInfo {  
  
	private static final String JDBC_URL_TYPE = "greenplum";

	public static final String GREENPLUM_SCHEME = JDBC_URL_TYPE;

    public GreenplumServiceInfo(String id, String url)   
    {         
     super(id, url, "JDBC_URL_TYPE");  
    }  
    
	@Override
	public String getJdbcUrl() {
		if (getUriInfo().getRawUriString().startsWith("jdbc")) {
			return getUriInfo().getRawUriString();
		}
		return String.format("jdbc:pivotal:%s://%s:%d;DatabaseName=%s;user=%s;password=%s",
				"greenplum", 
				getHost(), getPort(), getPath(), getUserName(),getPassword());
	}


}   