package com.mjc.school.repository.reader;

import com.mjc.school.repository.exeption.ReadException;

import java.util.List;

public interface DataReader {
    List<String> readFile(String path) throws ReadException;
}
