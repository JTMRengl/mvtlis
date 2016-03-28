package util;

import javax.annotation.Resource;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component("serverIp")
public class ServerIp {

	@Resource(name = "messageSourceAccessor")
	private  MessageSourceAccessor mr;
	public  String getServer(){
		String serverIp =  mr.getMessage("server.ip") + ":" +  mr.getMessage("server.port");
		return serverIp;
	}
	
}
