package com.revature;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/site", Location.CLASSPATH);
		});
		
		app.start();
		
		app.get("hello", (ctx) -> {
			ctx.result("hello");
		});
    }
}
