package com.home.languagelearning.model;

/**
 * Created by dmitry.kazakov on 3/5/2016.
 */
public class ExcelParams {
    private String sheet;
    private int positionOfSheet;

    public String getSheet() {
        return sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public int getPositionOfSheet() {
        return positionOfSheet;
    }

    public void setPositionOfSheet(int positionOfSheet) {
        this.positionOfSheet = positionOfSheet;
    }
}
