package com.epam.nikitasidorevich.banksystem.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ActionManager {
    public static final String PARAM_NAME_ACTION = "action";
    public static final String ACTION_VIEW_BANKS = "viewBanks";

    private static ActionManager instance = null;

    HashMap<String, Action> actions = new HashMap<String, Action>();

    private ActionManager() {
        actions.put(ACTION_VIEW_BANKS, new ViewBanksAction());
    }

    public static synchronized ActionManager getInstance() {
        if (instance == null) {
            instance = new ActionManager();
        }
        return instance;
    }

    public Action getAction(HttpServletRequest request) {
        String actionName = request.getParameter(PARAM_NAME_ACTION);
        Action action = actions.get(actionName);
        return action;
    }
}