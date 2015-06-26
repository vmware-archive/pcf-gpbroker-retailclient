package io.pivotal.fe.retaildata.client.cloudfoundry;

import io.pivotal.fe.retaildata.client.cloud.GreenplumServiceInfo;

import org.springframework.cloud.cloudfoundry.RelationalServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;


public class GreenplumServiceInfoCreator extends RelationalServiceInfoCreator<GreenplumServiceInfo> {
	public GreenplumServiceInfoCreator() {
		super(new Tags("greenplum"), GreenplumServiceInfo.GREENPLUM_SCHEME);
	}

	@Override
	public GreenplumServiceInfo createServiceInfo(String id, String url) {
		return new GreenplumServiceInfo(id, url);
	}
}