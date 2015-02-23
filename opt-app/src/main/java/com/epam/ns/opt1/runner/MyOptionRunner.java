package com.epam.ns.opt1.runner;

import com.epam.ns.opt1.option.Option;
import com.epam.ns.opt1.option.impl.FindPalindromesOptionImpl;
import com.epam.ns.opt1.option.impl.SortArgumentsOptionImpl;
import com.epam.ns.opt1.option.impl.SumArgumentsOptionImpl;

public class MyOptionRunner extends OptionRunner {

    @Override
    protected Option createOption(String type) {
        switch (type) {
            case "-sumArgumentsOption" :
                return new SumArgumentsOptionImpl();
            case "-sortArgumentsOption" :
                return new SortArgumentsOptionImpl();
            case "-findPalindromesOption" :
                return new FindPalindromesOptionImpl();
            default:
                //TODO : throw custom exception
                return null;
        }
    }
}
