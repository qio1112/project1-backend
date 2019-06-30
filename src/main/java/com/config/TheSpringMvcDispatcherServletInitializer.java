package com.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.routes.getPage;

import model.entities.User;

public class TheSpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() { // first thing to upload
		return new Class[] {AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {User.class};//  getPage.class
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
