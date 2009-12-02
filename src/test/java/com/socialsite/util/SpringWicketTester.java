package com.socialsite.util;

import javax.servlet.ServletContext;

import org.apache.wicket.protocol.http.MockServletContext;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * Spring enabled WicketTester allows a user to test applications that have been
 * wired using the SpringComponentInjector. This subclass of the WicketTester
 * sets up the spring web context, which is normally set up by the
 * org.springframework.web.context.ContextLoaderListener class.
 */
public class SpringWicketTester extends WicketTester
{
	/**
	 * The Spring web application context
	 */
	private XmlWebApplicationContext	spring;

	/**
	 * Instantiate the WicketTester with your application and a set of URLs to
	 * find the Spring XML configuration files. Ex:
	 * 
	 * <pre>
	 * new SpringWicketTester(new MyApp(), &quot;classpath:application.xml&quot;,
	 * 
	 * &quot;classpath:application-test.xml&quot;);
	 * </pre>
	 * 
	 * @param app
	 *            Your Wicket web application
	 * @param springConfigURLs
	 *            The set of URLs for the configuration files.
	 */
	public SpringWicketTester(final WebApplication app,
			final String... springConfigURLs)
	{
		this(app, null, springConfigURLs);
	}

	/**
	 * Instantiate the WicketTester with your application and a set of URLs to
	 * find the Spring XML configuration files. Ex:
	 * 
	 * <pre>
	 * new SpringWicketTester(new MyApp(), &quot;myapp&quot;,
	 * 
	 * &quot;classpath:application.xml&quot;,
	 * 
	 * &quot;classpath:application-test.xml&quot;);
	 * </pre>
	 * 
	 * @param app
	 *            Your Wicket web application
	 * @param context
	 *            The base url for the application
	 * @param springConfigURLs
	 *            The set of URLs for the configuration files.
	 */
	public SpringWicketTester(final WebApplication app, final String context,
			final String... springConfigURLs)
	{
		super(app, context);

		reconfigure(springConfigURLs);
	}

	/**
	 * Lazy loader for the creating the SpringContext. Required to work around
	 * the initialization in the constructor.
	 * 
	 * @return the single web application context for this tester
	 */
	private XmlWebApplicationContext getSpringContext()
	{
		if (null == spring)
		{
			spring = new XmlWebApplicationContext();
		}

		return spring;
	}

	/**
	 * Create the new ServletContext that will be used with this test session.
	 * This method configures the spring web context to be included in your
	 * Servlet context. It's the magic that makes everything happy.
	 * 
	 * @param path
	 *            the root context path for URLs
	 * 
	 * @return a configured ServletContext
	 */
	@Override
	public ServletContext newServletContext(final String path)
	{
		final ServletContext context = new MockServletContext(getApplication(),
			path);
		getSpringContext().setServletContext(context);

		context.setAttribute(
			WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
			spring);
		return context;
	}

	/**
	 * Reconfigure the tester with new spring configuration files. This method
	 * also calls the Spring <code>refresh</code> method to force the files to
	 * load.
	 * 
	 * @param springConfigURLs
	 *            The set of URLs for the configuration files.
	 */
	public void reconfigure(final String... springConfigURLs)
	{
		getSpringContext().setConfigLocations(springConfigURLs);
		getSpringContext().refresh();
	}
}