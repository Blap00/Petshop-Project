package com.littlepetshop.mvc.models;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestController
@RequestMapping
public class Catalogo {

    @RequestMapping
    public List<CatalogoItem> getCatalogo(String userId)
}