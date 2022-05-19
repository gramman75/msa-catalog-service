package com.example.catalogservice.controller;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog-service")
public class CatalogController {

    private final CatalogService catalogService;
    private final ModelMapper modelMapper;

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {

        List<ResponseCatalog> result = new ArrayList<>();
        List<CatalogDto> catalogs = catalogService.getCatalogs();

        catalogs.forEach( catalog ->{
            ResponseCatalog map = modelMapper.map(catalog, ResponseCatalog.class);
            result.add(map);
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

}
