package br.com.desafio.livelo.ilia.events;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResourceCreateListener implements ApplicationListener<ResourceCreateEvent>{

	@Override
	public void onApplicationEvent(ResourceCreateEvent createEvent) {
		HttpServletResponse response = createEvent.getResponse();
		Long code = createEvent.getCode();
		addHeaderLocation(response, code);
	}

	private void addHeaderLocation(HttpServletResponse response, Long code) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(code).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
