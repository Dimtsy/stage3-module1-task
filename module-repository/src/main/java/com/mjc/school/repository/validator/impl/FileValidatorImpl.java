package com.mjc.school.repository.validator.impl;

import com.mjc.school.repository.validator.FileValidator;

import java.io.File;

public class FileValidatorImpl implements FileValidator {
    @Override
    public boolean validateFilePath(String path) {
        if (path == null || path.isEmpty() || path.trim().isEmpty()) {
            return false;
        }
        boolean flag = false;
        File file = new File(path);
        if (file.exists()) {
            if (file.length() > 0) {
                flag = true;
            }
        }
        return flag;
    }

}
