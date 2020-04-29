package org.kostenko.quarkus.vaadin.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import javax.servlet.annotation.WebInitParam;
import org.kostenko.quarkus.vaadin.ui.view.LoginView;
import org.kostenko.quarkus.vaadin.ui.view.MainView;

@Theme("dashboard")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.UI_WITH_MENU);
        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {
                System.out.println("BrowserWindowResized");
            }
        });
        updateContent();
    }

    public void updateContent() {
        Boolean isAuthenticated = (Boolean) VaadinSession.getCurrent().getAttribute("isAuthenticated");
        if (isAuthenticated != null && isAuthenticated) {
            setContent(new MainView());
            removeStyleName("loginview");
            getNavigator().navigateTo(getNavigator().getState());
        } else {
            setContent(new LoginView());
            addStyleName("loginview");
        }
    }

    /**
     * VaadinServlet configuration
     */
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true, initParams = {
        @WebInitParam(name = "org.atmosphere.websocket.suppressJSR356", value = "true")}
    )
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
