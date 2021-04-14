package com.david.smartselect.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class ResourceCreatedEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	private HttpServletResponse httpServletResponse;
	private Long codigo;

	public ResourceCreatedEvent(Object source, HttpServletResponse httpServletResponse
			, Long codigo) {
		super(source);
		this.httpServletResponse = httpServletResponse;
		this.codigo = codigo;
		}
	

	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	public Long getCodigo() {
		return codigo;
	}
}