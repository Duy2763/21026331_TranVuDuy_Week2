package fit.iuh.edu.websocket;

import java.io.IOException;
import java.net.URI;

import jakarta.websocket.ContainerProvider;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;

public class ChatClientEnpoint {
	Session session = null;
	private MessageHandler handler;

	public ChatClientEnpoint(URI endpointURI) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, endpointURI);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		try {
			session.getBasicRemote().sendText("Opening connection");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public void addMessageHandler(MessageHandler msgHandler) {
		this.handler = msgHandler;
	}

	@OnMessage
	public void processMessage(String message) {
		System.out.println("Received message in client: " + message);
	}

	public void sendMessage(String message) {
		try {
			this.session.getBasicRemote().sendText(message);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static interface MessageHandler {

		public void handleMessage(String message);
	}
}
