package io.pivotal.fe.gpdb.broker.service;

import java.util.HashMap;
import java.util.Map;

import org.cloudfoundry.community.servicebroker.exception.ServiceBrokerException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceBindingExistsException;
import org.cloudfoundry.community.servicebroker.model.ServiceInstance;
import org.cloudfoundry.community.servicebroker.model.ServiceInstanceBinding;
import org.cloudfoundry.community.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DBBrokerServiceInstanceBindingService implements
		ServiceInstanceBindingService {

	private ServiceInstanceBinding serviceInstanceBinding = null;
	
    @Value("${retail_demo_db_url}")
    private String retail_demo_db_uri;
    	
	@Override
	public ServiceInstanceBinding createServiceInstanceBinding(
			String bindingId, ServiceInstance serviceInstance,
			String serviceId, String planId, String appGuid)
			throws ServiceInstanceBindingExistsException, ServiceBrokerException {
	
		ServiceInstanceBinding binding = null;
				
		Map<String,Object> credentials = new HashMap<String,Object>();
		if(planId.contentEquals("retail-demo"))
		{
			credentials.put("uri", retail_demo_db_uri);
		}
		
		if(binding == null)
		{
			binding = new ServiceInstanceBinding(bindingId, serviceInstance.getId(), credentials, null, appGuid);
		}
				
		return binding;
	}

	protected ServiceInstanceBinding getServiceInstanceBinding(String id) {
		return serviceInstanceBinding;
	}

	@Override
	public ServiceInstanceBinding deleteServiceInstanceBinding(
			String bindingId, ServiceInstance instance, 
			String serviceId, String planId)
			throws ServiceBrokerException {
		ServiceInstanceBinding binding = getServiceInstanceBinding(bindingId);
		if (binding!= null) {
		}
		return binding;
	}

}
