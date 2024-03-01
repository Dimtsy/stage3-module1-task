package com.mjc.school.impl;

import com.mjc.school.dto.NewsDto;
import com.mjc.school.dto.DtoMapper;
import com.mjc.school.exeption.InfoException;

import java.util.List;

public class NewsServiceImpl {
    private final NewsDataRepository newsDataSourceRepository = new NewsDataRepository();
    private final DtoMapper dtoMapper = new DtoMapper();


    public List<NewsDto> allNews() {
        return dtoMapper.newsDTOList(newsDataSourceRepository.readAll());
    }

    public NewsDto newsBId(long id) throws InfoException {
        if (newsDataSourceRepository.existNews(id)) throw new InfoException("ERROR_CODE:XXX13 ERROR_MESSAGE:News with id "+ (id+1) +" does not exist.");
        return dtoMapper.newsDTO(newsDataSourceRepository.readId(id));
    }

    public NewsDto createNews(NewsDto dto) throws InfoException {
        if(dto.getTitle().length() < 5 || dto.getTitle().length() > 30) throw new InfoException("ERROR_CODE:XXX14 ERROR_MESSAGE:News title can not be less than 5 and more than 30 symbols. News title is: "+dto.getTitle());
        if(dto.getContent().length() < 5 || dto.getContent().length() > 255) throw new InfoException("ERROR_CODE:XXX22 ERROR_MESSAGE:News content can not be less than 5 and more than 255 symbols. News content is: "+dto.getContent());
       if (newsDataSourceRepository.existAuthor(dto.getAuthorId())) throw new InfoException("ERROR_CODE:XXX22 ERROR_MESSAGE:Author Id does not exist. Author Id is: "+dto.getAuthorId());
        return dtoMapper.newsDTO(newsDataSourceRepository.createNews(dtoMapper.dtoNews(dto)));
    }

    public NewsDto updateNewsId(NewsDto dto) throws InfoException {
        if(dto.getTitle().length() < 5 || dto.getTitle().length() > 30) throw new InfoException("ERROR_CODE:XXX14 ERROR_MESSAGE:News title can not be less than 5 and more than 30 symbols. News title is: "+dto.getTitle());
        if(dto.getContent().length() < 5 || dto.getContent().length() > 255) throw new InfoException("ERROR_CODE:XXX22 ERROR_MESSAGE:News content can not be less than 5 and more than 255 symbols. News content is: "+dto.getContent());
        if (newsDataSourceRepository.existNews(dto.getId())) throw new InfoException("ERROR_CODE:XXX13 ERROR_MESSAGE:News with id "+ dto.getId() +" does not exist.");
        if (newsDataSourceRepository.existAuthor(dto.getAuthorId())) throw new InfoException("ERROR_CODE:XXX22 ERROR_MESSAGE:Author Id does not exist. Author Id is: "+dto.getAuthorId());
        return dtoMapper.newsDTO(newsDataSourceRepository.updateNewsId(dtoMapper.dtoNews(dto)));
    }

    public boolean removeNewsId(long id) throws InfoException {
        if (newsDataSourceRepository.existNews(id)) throw new InfoException("ERROR_CODE:XXX13 ERROR_MESSAGE:News with id "+ (id+1) +" does not exist.");
        return newsDataSourceRepository.deleteId(id);
    }

    public String toString(NewsDto dto) {
        return "NewsDtoResponse[id=" + dto.getId()
                + ", title=" + dto.getTitle()
                + ", content=" + dto.getContent()
                + ", createDate=" + dto.getCreatedDate()
                + ", lastUpdatedDate=" + dto.getLastUpdatedDate()
                + ", authorId=" + dto.getAuthorId()
                + "]";
    }


    public String toString(List<NewsDto> dtoNews) {
        StringBuilder stringBuilder = new StringBuilder();
        for (NewsDto news : dtoNews){
            stringBuilder.append(toString(news));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}