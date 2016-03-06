package com.home.languagelearning.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry.kazakov on 3/5/2016.
 */
public class FileData<T> {
    private String name;
    private String directory;

    private ExcelParams excelParams;
    private List<T> data = new ArrayList<>();

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

    public List<T> getData() {
        return data;
    }

    public void setData(T data) {
        this.data.add(data);
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
