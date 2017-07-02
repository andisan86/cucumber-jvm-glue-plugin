package id.alphait.plugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by andisan on 7/2/17.
 */
public class FeatureReader {
    private String featureDir = "";
    private List<String> givenStepsInFeatureFile = new ArrayList<String>();
    private List<String> whenStepsInFeatureFile = new ArrayList<String>();
    private List<String> thenStepsInFeatureFile = new ArrayList<String>();
    private List<String> andStepsInFeatureFile = new ArrayList<String>();
    private List<String> butStepsInFeatureFile = new ArrayList<String>();

    public FeatureReader() {}

    public FeatureReader(String featureDir) {
        this.featureDir = featureDir;
        List<Path> featureFilesPath = getFeatureFilesPath();
        readAndFilterFeatureFile(featureFilesPath);
    }

    private List<Path> getFeatureFilesPath() {
        List<Path> featureFiles = new ArrayList<Path>();
        try {
            featureFiles = Files.walk(Paths.get(featureDir))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return featureFiles;
    }

    private void readAndFilterFeatureFile(List<Path> featureFilesPath) {
        for (Path featureFilePath : featureFilesPath) {
            try (Stream<String> stream = Files.lines(featureFilePath)) {
                filterGivenStep(stream);
                filterWhenStep(stream);
                filterThenStep(stream);
                filterAndStep(stream);
                filterButStep(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> filterGivenStep(Stream<String> stream) {
        givenStepsInFeatureFile = stream
                .filter(line -> !line.startsWith("Given"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return givenStepsInFeatureFile;
    }

    private List<String> filterWhenStep(Stream<String> stream) {
        whenStepsInFeatureFile = stream
                .filter(line -> !line.startsWith("When"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return whenStepsInFeatureFile;
    }

    private List<String> filterAndStep(Stream<String> stream) {
        andStepsInFeatureFile = stream
                .filter(line -> !line.startsWith("And"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return andStepsInFeatureFile;
    }

    private List<String> filterThenStep(Stream<String> stream) {
        thenStepsInFeatureFile = stream
                .filter(line -> !line.startsWith("Then"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return thenStepsInFeatureFile;
    }

    private List<String> filterButStep(Stream<String> stream) {
        butStepsInFeatureFile = stream
                .filter(line -> !line.startsWith("But"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return butStepsInFeatureFile;
    }

    public List<String> getGivenSteps() {
        return givenStepsInFeatureFile;
    }

    public List<String> getWhenSteps() {
        return whenStepsInFeatureFile;
    }

    public List<String> getThenSteps() {
        return thenStepsInFeatureFile;
    }

    public List<String> getAndSteps() {
        return andStepsInFeatureFile;
    }

    public List<String> getButSteps() {
        return butStepsInFeatureFile;
    }

    public String getFeatureDir() {
        return this.featureDir;
    }

    public void setFeatureDir(String featureDir) {
        this.featureDir = featureDir;
    }
}
