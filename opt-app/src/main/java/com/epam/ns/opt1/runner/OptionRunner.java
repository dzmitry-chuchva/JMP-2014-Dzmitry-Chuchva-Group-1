package com.epam.ns.opt1.runner;

import com.epam.ns.opt1.option.Option;

public abstract class OptionRunner {

    public void executeOption(String type, String[] args) {
        Option option = createOption(type);
        option.run(args);
    }

    protected abstract Option createOption(String type);
}
