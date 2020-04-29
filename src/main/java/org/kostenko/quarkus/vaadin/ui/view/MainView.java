package org.kostenko.quarkus.vaadin.ui.view;

import org.kostenko.quarkus.vaadin.ui.MyNavigator;
import org.kostenko.quarkus.vaadin.ui.MyMenu;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class MainView extends HorizontalLayout {

    public MainView() {
        setSizeFull();
        addStyleName("mainview");
        setSpacing(false);

        addComponent(new MyMenu());

        ComponentContainer content = new CssLayout();
        content.addStyleName("view-content");
        content.setSizeFull();
        addComponent(content);
        setExpandRatio(content, 1.0f);

       new MyNavigator(content);
    }
}
