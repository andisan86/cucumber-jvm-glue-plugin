package id.alphait.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo( name = "gluegenerator")
public class GlueGenerator extends AbstractMojo {
    public void execute() throws MojoExecutionException {
        getLog().info( "Hello, world." );
    }
}
