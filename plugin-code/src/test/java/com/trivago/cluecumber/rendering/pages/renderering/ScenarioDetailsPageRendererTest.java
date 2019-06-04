package com.trivago.cluecumber.rendering.pages.renderering;

import com.trivago.cluecumber.exceptions.CluecumberPluginException;
import com.trivago.cluecumber.json.pojo.Element;
import com.trivago.cluecumber.json.pojo.Step;
import com.trivago.cluecumber.properties.PropertyManager;
import com.trivago.cluecumber.rendering.pages.charts.ChartJsonConverter;
import com.trivago.cluecumber.rendering.pages.pojos.pagecollections.ScenarioDetailsPageCollection;
import freemarker.template.Template;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class ScenarioDetailsPageRendererTest {

    private ScenarioDetailsPageRenderer scenarioDetailsPageRenderer;

    @Before
    public void setup() {
        ChartJsonConverter chartJsonConverter = mock(ChartJsonConverter.class);
        PropertyManager propertyManager = mock(PropertyManager.class);
        scenarioDetailsPageRenderer = new ScenarioDetailsPageRenderer(chartJsonConverter, propertyManager);
    }

    @Test
    public void testContentRendering() throws CluecumberPluginException {
        Template template = mock(Template.class);
        Element element = new Element();
        List<Step> steps = new ArrayList<>();
        element.setSteps(steps);
        ScenarioDetailsPageCollection scenarioDetailsPageCollection = new ScenarioDetailsPageCollection(element);
        scenarioDetailsPageRenderer.getRenderedContent(scenarioDetailsPageCollection, template);
    }
}
