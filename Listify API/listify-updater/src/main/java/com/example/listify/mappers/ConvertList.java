package com.example.listify.mappers;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ConvertList<T> implements IConvertList{
    public final List<T> toConvert;
    public ConvertList(List<T> toConvert){
        this.toConvert = toConvert;
    }
    @Override
    public String convert() {
        StringBuilder toReturn = new StringBuilder();
        for (T item:
             toConvert) {
            if(toConvert.get(0)!=item){
                toReturn.append(",");
            }
            toReturn.append(item);
        }
        return toReturn.toString();
    }

}
