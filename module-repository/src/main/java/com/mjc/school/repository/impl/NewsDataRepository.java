package com.mjc.school.repository.impl;
import com.mjc.school.repository.model.ModelNews;

import java.time.LocalDateTime;
import java.util.List;
public class NewsDataRepository {
      private final DataSourceNews newsRepository = DataSourceNews.getNewsData() ;

    public NewsDataRepository()  {
    }

    public List<ModelNews> readByAll() {
        return newsRepository.getNewsListAll();
    }

    public boolean existNews(long id) {
        return id > newsRepository.getNewsListAll().size() || id < 0;
    }

    public boolean existAuthor(long id) {
        return id > newsRepository.getAuthorAll().size() || id < 0;
    }

    public ModelNews readById(Long id) {
        return newsRepository.getNewsListAll().get(id.intValue());
    }
    public ModelNews updateNewsId(ModelNews news) {
        news.setLastUpdatedDate(newsRepository.getDates());
        LocalDateTime localDateTime = newsRepository.getNewsListAll().get((int) news.getId()-1).getCreatedDate();
        news.setCreatedDate(localDateTime);
        newsRepository.getNewsListAll().set((int) news.getId()-1,news);
        return news;
    }

    public ModelNews createNews(ModelNews news) {
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
