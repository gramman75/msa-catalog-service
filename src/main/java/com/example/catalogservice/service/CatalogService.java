package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.entity.CatalogEntity;
import com.example.catalogservice.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository catalogRepository;
    private final ModelMapper modelMapper;

    public List<CatalogDto> getCatalogs() {
        List<CatalogEntity> catalogs = catalogRepository.findAll();
        List<CatalogDto> result = new ArrayList<>();
        catalogs.forEach( catalog ->{
            CatalogDto map = modelMapper.map(catalog, CatalogDto.class);
            result.add(map);
        });

        return result;
    }

}
