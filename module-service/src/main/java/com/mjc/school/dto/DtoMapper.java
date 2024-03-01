package com.mjc.school.dto;

import com.mjc.school.repository.model.ModelNews;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DtoMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public DtoMapper() {
    }

    public ModelNews dtoNews(NewsDto dto) {
        return new ModelNews(dto.getId(), dto.getTitle(), dto.getContent(), dto.getCreatedDate(),
                dto.getLastUpdatedDate(), dto.getAuthorId()
        );
    }

    public NewsDto newsDTO(ModelNews model){
        return modelMapper.map(model, NewsDto.class);
    }

    public List<NewsDto> newsDTOList(List<ModelNews> newsList){
        List<NewsDto> allDTO = new ArrayList<>();
        for (ModelNews news : newsList) allDTO.add(newsDTO(news));
        return allDTO;
    }

}