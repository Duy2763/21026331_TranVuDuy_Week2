package utils;

import com.google.gson.Gson;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("gson")
public class GsonProvider {
	private final Gson gson;
	
	public GsonProvider() {
		this.gson = new Gson();
	}
	
	public Gson getGson() {
		return gson;
	}
}
