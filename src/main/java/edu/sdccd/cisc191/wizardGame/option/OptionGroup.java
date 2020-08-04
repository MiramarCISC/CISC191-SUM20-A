package edu.sdccd.cisc191.wizardGame.option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionGroup {

    public final List<Option> options = new ArrayList<>();

    private final Map<String, Option> optionMap = new HashMap<>();

    public Option getOption(String id) {
        return optionMap.get(id);
    }

    public <T extends Option> T getOption(String id, Class<T> returnClass){
        return returnClass.cast(optionMap.get(id));
    }

    public void add(Option option){
        String id = option.getId();
        options.add(option);
        optionMap.put(id, option);
    }
}
