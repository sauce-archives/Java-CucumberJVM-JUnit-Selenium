package io.cucumber;

import com.saucelabs.saucerest.SauceREST;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SauceUtils {
    /**
     * A singleton instance of the Sauce REST client
     */
    private SauceREST sauceRESTClient;

    public SauceUtils(SauceREST sauceREST) {
        this.sauceRESTClient = sauceREST;
    }

    void updateResults(boolean testResults, String sessionId)
            throws JSONException {
        Map<String, Object> updates = new HashMap<>();
        updates.put("passed", testResults);
        sauceRESTClient.updateJobInfo(sessionId, updates);
    }
}