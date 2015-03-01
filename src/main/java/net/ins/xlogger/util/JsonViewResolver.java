package net.ins.xlogger.util;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Locale;

public class JsonViewResolver implements ViewResolver {

    private boolean usePrettyPrint = false;

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(usePrettyPrint);
        return view;
    }

    public boolean isUsePrettyPrint() {
        return usePrettyPrint;
    }

    public void setUsePrettyPrint(boolean usePrettyPrint) {
        this.usePrettyPrint = usePrettyPrint;
    }
}
