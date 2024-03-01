package com.mjc.school.impl;

import com.mjc.school.exeption.HandlingException;
import com.mjc.school.model.Author;
import com.mjc.school.model.News;
import com.mjc.school.reader.impl.DataReaderImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.*;


public class NewsData {
    private final DataReaderImpl dataReader = new DataReaderImpl();
    private final ArrayList<News> newsArrayList;
    private static NewsData newsData;
    private static final int NUMBER_NEWS = 20;
    private final String FILE_NEWS = "module-repository/src/main/resources/news.txt";
    private final String FILE_CONTENT = "module-repository/src/main/resources/content.txt";
    private final String FILE_AUTHOR = "module-repository/src/main/resources/author.txt";
    private final String BEGIN_TIME = "2024-02-01 00:00:00";
    private final String END_TIME = "2024-03-31 00:58:00";

    private NewsData() {
        newsArrayList = readerNewsFromFile();
    }

    public static NewsData getNewsData() {
        if (newsData == null) {
            newsData = new NewsData();
        }
        return newsData;
    }

    private DateFormat dateFormatZone() {
        TimeZone tz = TimeZone.getTimeZone("Europe/Minsk");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(tz);
        return df;
    }

    private long getRandomTimeBetweenTwoDates() {
        long diff = Timestamp.valueOf(END_TIME).getTime() - Timestamp.valueOf(BEGIN_TIME).getTime() + 1;
        return Timestamp.valueOf(BEGIN_TIME).getTime() + (long) (Math.random() * diff);
    }

    private LocalDateTime getDatesRandom() {
        return LocalDateTime.parse(dateFormatZone().format(new Date(getRandomTimeBetweenTwoDates())));
    }

    public LocalDateTime getDates() {
        return LocalDateTime.parse(dateFormatZone().format(new Date()));
    }

    public List<Author> getAuthorAll()  {
        List<String> bufferAuthor = null;
        try {
            bufferAuthor = dataReader.readFile(FILE_AUTHOR);
        } catch (HandlingException e) {
            e.printStackTrace();
        }
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < bufferAuthor.size(); i++) {
            authors.add(new Author((long) i + 1, bufferAuthor.get(i)));
        }
        return authors;
    }

    public List<News> getNewsListAll() {
        return newsArrayList;
    }

    private ArrayList<News> readerNewsFromFile() {
        Random rand = new Random();
        ArrayList<News> news = new ArrayList<>();

        List<String> bufferNews = null;
        List<String> bufferContent = null;
        try {
            bufferNews = dataReader.readFile(FILE_NEWS);
            bufferContent = dataReader.readFile(FILE_CONTENT);
        } catch (HandlingException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= NUMBER_NEWS; i++) {
            String title = bufferNews.get(rand.nextInt(bufferNews.size()));
            String content = bufferContent.get(rand.nextInt(bufferContent.size()));

            Long idAuthor = (long) rand.nextInt(getAuthorAll().size()) + 1;

            LocalDateTime nowAsISO = getDatesRandom();
            News news1 = new News(i, title, content, nowAsISO, nowAsISO, idAuthor);
            news.add(news1);
        }
        return news;
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "newsArrayList=" + newsArrayList +
                '}';
    }
}
