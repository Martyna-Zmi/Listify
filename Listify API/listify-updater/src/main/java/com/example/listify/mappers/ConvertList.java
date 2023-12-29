package com.example.listify.mappers;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ConvertList implements IConvertList{
    public List<String> toConvert;
    public ConvertList(){

    }
    @Override
    public IConvertList setToConvert(List<String> toConvert) {
        this.toConvert = toConvert;
        return this;
    }
    @Override
    public String convert() {
        StringBuilder toReturn = new StringBuilder();
        for (String item:
             toConvert) {
            if(!Objects.equals(toConvert.get(0), item)){
                toReturn.append(",");
            }
            toReturn.append(item);
        }
        return toReturn.toString();
    }

}
