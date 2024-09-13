package fit.iuh.edu.websocket;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

	@Override
	public String encode(Message message) throws EncodeException {
		JsonObject jsonObject = Json.createObjectBuilder().add("username", message.getUserName())
				.add("message", message.getMessage()).build();
		return jsonObject.toString();
	}

	@Override
	public void init(EndpointConfig ec) {
		System.out.println("Initializing message encoder");
	}

	@Override
	public void destroy() {
		System.out.println("Destroying encoder...");
	}

}
