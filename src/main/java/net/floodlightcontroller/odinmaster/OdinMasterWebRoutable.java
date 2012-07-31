package net.floodlightcontroller.odinmaster;

import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import net.floodlightcontroller.restserver.RestletRoutable;

public class OdinMasterWebRoutable implements RestletRoutable {

	@Override
	public String basePath() {
		return "/odin";
	}

	@Override
	public Restlet getRestlet(Context context) {
		Router router = new Router(context);
		router.attach("/clients/json", ClientManagerResource.class);
		router.attach("/agents/json", AgentManagerResource.class);
		router.attach("/handoff/json", LvapHandoffResource.class);
		return router;
	}
}