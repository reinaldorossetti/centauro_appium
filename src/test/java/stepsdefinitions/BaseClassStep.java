package stepsdefinitions;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

public class BaseClassStep implements ConcurrentEventListener {

    private static String testDescription;
    public String getStepText() {
        return testDescription;
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestStepStarted.class, new EventHandler<TestStepStarted>() {
            @Override
            public void receive(TestStepStarted event) {
                if (event.getTestStep() instanceof PickleStepTestStep) {
                    final PickleStepTestStep test_step = (PickleStepTestStep) event.getTestStep();
                    testDescription = test_step.getStep().getText();
                }
            }
        });
    }
}