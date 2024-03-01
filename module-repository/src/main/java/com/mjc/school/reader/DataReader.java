package com.mjc.school.reader;

import com.mjc.school.exeption.HandlingException;

import java.util.List;

public interface DataReader {
    List<String> readFile(String path) throws HandlingException;
}
