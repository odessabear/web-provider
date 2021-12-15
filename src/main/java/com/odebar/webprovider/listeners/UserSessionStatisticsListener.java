package com.odebar.webprovider.listeners;



import com.odebar.webprovider.Constants;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

@WebListener
@SuppressWarnings("unchecked")
public class UserSessionStatisticsListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        List<String> actions = (List<String>) se.getSession().getAttribute(Constants.USER_ACTIONS_HISTORY);
        if (actions != null) {
            logCurrentActionHistory(se.getSession().getId(), actions);
        }
    }

    private void logCurrentActionHistory(String sessionId, List<String> actions) {
        System.out.println(sessionId + " ->\n\t" + String.join("\n\t", actions));
    }
}
