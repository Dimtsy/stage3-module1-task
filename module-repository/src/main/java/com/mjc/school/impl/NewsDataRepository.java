package com.mjc.school.impl;
import com.mjc.school.model.News;

import java.time.LocalDateTime;
import java.util.List;
public class NewsDataRepository {
      private final NewsData newsRepository = NewsData.getNewsData() ;

    public NewsDataRepository()  {
    }

    public List<News> readAll() {
        return newsRepository.getNewsListAll();
    }

    public boolean existNews(long id) {
        return id > newsRepository.getNewsListAll().size() || id < 0;
    }

    public boolean existAuthor(long id) {
        return id > newsRepository.getAuthorAll().size() || id < 0;
    }

    public News readId(Long id) {
        return newsRepository.getNewsListAll().get(id.intValue());
    }
    public News updateNewsId(News news) {
        news.setLastUpdatedDate(newsRepository.getDates());
        LocalDateTime localDateTime = newsRepository.getNewsListAll().get((int) news.getId()-1).getCreatedDate();
        news.setCreatedDate(localDateTime);
        newsRepository.getNewsListAll().set((int) news.getId()-1,news);
        return news;
    }

    public News createNews(News news) {
        news.setId((long) newsRepository.getNewsListAll().size() + 1);
        news.setCreatedDate(newsRepository.getDates());
        news.setLastUpdatedDate(newsRepository.getDates());
        newsRepository.getNewsListAll().add(news);
        return news;
    }

    public Boolean deleteId(Long id) {
        newsRepository.getNewsListAll().remove(newsRepository.getNewsListAll().get(id.intValue()));
        return true;
    }


}
