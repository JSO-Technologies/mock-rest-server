package com.jso.formation.mock.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.jso.formation.mock.filter.SessionManagerRequestFilter;
import com.jso.formation.mock.filter.SessionManagerResponseFilter;
import com.jso.formation.mock.service.book.BookService;
import com.jso.formation.mock.service.user.UserService;


public class MockResourceConfig extends ResourceConfig {
    public MockResourceConfig () {
    	//jackson
    	register(JacksonFeature.class);
    	
    	//filters
    	register(SessionManagerRequestFilter.class);
    	register(SessionManagerResponseFilter.class);
    	
    	//services
    	register(UserService.class);
    	register(BookService.class);
    }
}
