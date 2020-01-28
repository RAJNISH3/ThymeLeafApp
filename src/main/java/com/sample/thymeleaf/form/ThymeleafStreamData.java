package com.sample.thymeleaf.form;

import java.io.InputStream;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ThymeleafStreamData {

    private InputStream data;
    private String id;
}


