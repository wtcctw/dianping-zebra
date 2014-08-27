package com.dianping.zebra.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.DirectoryScanner;

/**
 * @goal migrate
 */
public class DalMigrateMojo extends AbstractMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Hello World");

		DirectoryScanner scanner = new DirectoryScanner();
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		scanner.setBasedir(System.getProperty("user.dir"));
		String[] includes = { "**\\src\\main\\resources\\**\\*.xml" };
		String[] excludes = { "**\\pom.xml", "**\\log4j.xml", "**\\web.xml", "**\\dict.xml", "**\\client.xml" };
		scanner.setIncludes(includes);
		scanner.setExcludes(excludes);
		scanner.setCaseSensitive(true);

		scanner.scan();

		String[] files = scanner.getIncludedFiles();
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i]);
		}
		
		DefaultBeanDefinitionXmlReader reader = new DefaultBeanDefinitionXmlReader(files[0]);
		
		reader.print();
	}
}
