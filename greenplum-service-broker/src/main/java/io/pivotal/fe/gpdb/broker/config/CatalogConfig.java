package io.pivotal.fe.gpdb.broker.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudfoundry.community.servicebroker.model.Catalog;
import org.cloudfoundry.community.servicebroker.model.Plan;
import org.cloudfoundry.community.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfig {

	@Bean
	public Catalog catalog() {
		return new Catalog(Arrays.asList(new ServiceDefinition("greenplum",
				"greenplum", "Provides Greenplum DB connection strings", true, false,
				Arrays.asList(new Plan("retail-demo", "retail-demo",
						"This is retail-demo plan for test db on vShere EMEA env.",
						getRetailDemoPlanMetadata(), true)), 
						Arrays.asList(
						"greenplum", "uri"), getServiceDefinitionMetadata(),
				null, null)));
	}

	/* Used by Pivotal CF console */

	private Map<String, Object> getServiceDefinitionMetadata() {
		Map<String, Object> sdMetadata = new HashMap<String, Object>();
		sdMetadata.put("displayName", "greenplum");
		sdMetadata
				.put("imageUrl",
						"https://dtb5pzswcit1e.cloudfront.net/assets/images/product_logos/icon_gpdb%402x.png");
		sdMetadata.put("longDescription", "GPDB-Broker Service");
		sdMetadata.put("providerDisplayName", "Pivotal");
		sdMetadata.put("documentationUrl", "http://www.pivotal.io");
		sdMetadata.put("supportUrl", "http://www.pivotal.io");
		return sdMetadata;
	}

	private Map<String, Object> getRetailDemoPlanMetadata() {
		Map<String, Object> planMetadata = new HashMap<String, Object>();
		planMetadata.put("costs", getCosts());
		planMetadata.put("bullets", getLocationXBullets());
		return planMetadata;
	}
	
	private List<Map<String, Object>> getCosts() {
		Map<String, Object> costsMap = new HashMap<String, Object>();

		Map<String, Object> amount = new HashMap<String, Object>();
		amount.put("usd", new Double(0.0));

		costsMap.put("amount", amount);
		costsMap.put("unit", "MONTHLY");

		return Arrays.asList(costsMap);
	}

	private List<String> getLocationXBullets() {
		return Arrays.asList("GPDB connection String", "for test db",
				"on vSphere EMEA");
	}
}