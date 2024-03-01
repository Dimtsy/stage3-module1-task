package com.mjc.school.reader.impl;

import com.mjc.school.exeption.HandlingException;
import com.mjc.school.reader.DataReader;
import com.mjc.school.validator.impl.FileValidatorImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataReaderImpl implements DataReader {

    @Override
    public List<String> readFile(String path) throws HandlingException {
        FileValidatorImpl fileValidate = new FileValidatorImpl();
        if (!fileValidate.validateFilePath(path)) {
            throw new HandlingException("File is not exist or is empty or incorrect path");
        }
        List<String> lines;
        Path pathFile = Paths.get(path);
        try (Stream<String> lineStream = Files.lines(pathFile)) {
            lines = lineStream.collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException e) {
            throw new HandlingException("Reading file is fail ", e);
        }

        return lines;
    }
}
