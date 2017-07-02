package id.alphait.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo( name = "gluegenerator")
public class GlueGenerator extends AbstractMojo {
    static String testFeatureDir = "src/test/resources/id/alphait/plugin";

    public void execute() throws MojoExecutionException {
        FeatureReader fr = new FeatureReader(testFeatureDir);
        getLog().info(fr.getGivenSteps().toString());
    }
}
