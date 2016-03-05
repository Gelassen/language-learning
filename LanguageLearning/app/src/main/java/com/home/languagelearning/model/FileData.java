package com.home.languagelearning.model;

import java.io.File;

/**
 * Created by dmitry.kazakov on 3/5/2016.
 */
public class FileData {
    private String name;
    private String directory;

    private ExcelParams excelParams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public File getFile() {
        if (name == null || directory == null)
            throw new IllegalStateException("Unsupported state exception");
        return new File(directory, name);
    }

    public ExcelParams getExcelParams() {
        return excelParams;
    }

    public void setExcelParams(ExcelParams excelParams) {
        this.excelParams = excelParams;
    }
}
