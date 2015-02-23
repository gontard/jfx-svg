package org.gontard.jfx.svg;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import org.gontard.jfx.svg.equal.MainEqualityTester;
import org.gontard.jfx.svg.util.JavaFXThreadingRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class JfxSvgRendererTest {
    @Parameters(name = "{0}")
    public static Iterable<Object[]> data() throws Exception {
        Path fixturePath = fixturesPath();
        Path resourcesPath = fixturePath;
        return StreamSupport.stream(Files.newDirectoryStream(resourcesPath).spliterator(), false)
                .filter(path -> path.toString().endsWith("svg"))
                .map(child -> new Object[] { child.getFileName().toString() })
                .collect(Collectors.toList());
    }

    private static Path fixturesPath() throws URISyntaxException {
        return Paths.get(JfxSvgRendererTest.class.getResource("/jfx/fixtures").toURI());
    }

    private final Path testedSvg;
    private final Path expectedFxml;
    private final MainEqualityTester mainEqualityTester = new MainEqualityTester();

    @Rule
    public JavaFXThreadingRule runInJfxThread = new JavaFXThreadingRule();

    public JfxSvgRendererTest(String svgName) throws Exception {
        Path fixturesPath = fixturesPath();
        this.testedSvg = fixturesPath.resolve(svgName);
        this.expectedFxml = fixturesPath.resolve(svgName.substring(0, svgName.length() - 4) + ".fxml");
    }

    @Test
    public void should_load_correctly() throws Exception {
        // [Given]
        Node fxmlNode = loadFxml();

        // [When]
        Node svgNode = loadSvg();

        // [Then]
        mainEqualityTester.assertEqual(fxmlNode, svgNode);
    }

    private Node loadSvg() throws Exception {
        InputStream stream = Files.newInputStream(testedSvg);
        JfxSvgRenderer svgRenderer = new JfxSvgRenderer();
        return svgRenderer.load(stream);
    }

    private Node loadFxml() throws Exception {
        assertTrue(expectedFxml.getFileName() + " does not exists", Files.exists(expectedFxml));
        return FXMLLoader.load(expectedFxml.toUri().toURL());
    }
}
