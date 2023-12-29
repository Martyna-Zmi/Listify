package com.example.listify.mappers;

import org.springframework.stereotype.Component;

import java.util.List;


public interface IConvertList {
    IConvertList setToConvert(List<String> toConvert);
    String convert();
}
