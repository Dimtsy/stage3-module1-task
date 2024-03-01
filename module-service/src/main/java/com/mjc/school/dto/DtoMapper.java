package com.mjc.school.dto;

import com.mjc.school.model.News;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DtoMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public DtoMapper() {
    }

    public News dtoNews(NewsDto dto) {
        return new News(dto.getId(), dto.getTitle(), dto.getContent(), dto.getCreatedDate(),
                dto.getLastUpdatedDate(), dto.getAuthorId()
        );
    }

    public NewsDto newsDTO(News model){
        return modelMapper.map(model, NewsDto.class);
    }

    public List<NewsDto> newsDTOList(List<News> newsList){
        List<NewsDto> allDTO = new ArrayList<>();
        for (News news : newsList) allDTO.add(newsDTO(news));
        return allDTO;
    }

}