package io.cucumber;

import com.saucelabs.saucerest.SauceREST;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class SauceUtils {
    /**
     * A singleton instance of the Sauce REST client
     */
    private SauceREST sauceClient;

    /**
     * The Sauce Credentials are gathered and sent via Capabilities, therefore the class that
     * constructs the browser should be the one to construct the Sauce API client using
     * the same credentials. That client is passed to this class for use in updating test metadata
     * (which is the primary purpose of the REST client)
     * @param sauceREST
     */
    public SauceUtils(SauceREST sauceREST) {
        this.sauceClient = sauceREST;
    }

    void updateResults(boolean testResults, String sessionId)
            throws JSONException {
        Map<String, Object> updates = new HashMap<>();
        updates.put("passed", testResults);
        sauceClient.updateJobInfo(sessionId, updates);
    }
}