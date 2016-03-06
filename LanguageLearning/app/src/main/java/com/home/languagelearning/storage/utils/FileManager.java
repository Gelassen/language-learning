package com.home.languagelearning.storage.utils;

import android.os.Environment;
import android.util.Log;

import com.home.languagelearning.App;
import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.model.ExcelParams;
import com.home.languagelearning.model.FileData;

import java.io.IOException;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;

/**
 * Created by dmitry.kazakov on 3/5/2016.
 */
public class FileManager {

    public boolean write(FileData<ChineseToEnglishCard> fileData) {
        if (!isWritebale()) return false;

        // TODO implement me
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(fileData.getFile());
            ExcelParams excelParams = fileData.getExcelParams();
            WritableSheet sheet = workbook.createSheet(
                    excelParams.getSheet(),
                    excelParams.getPositionOfSheet()
            );

            List<ChineseToEnglishCard> cards = fileData.getData();
            for (int idx = 0; idx < cards.size(); idx++) {
                writeCard(sheet, cards.get(idx), idx);
            }
            workbook.write();
        } catch (IOException e) {
            Log.e(App.TAG, "Failed to obtain workbook", e);
        } catch (WriteException e) {
            Log.e(App.TAG, "Failed to write into sheet", e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    Log.e(App.TAG, "Failed to close workbook", e);
                } catch (WriteException e) {
                    Log.e(App.TAG, "Failed to close workbook", e);
                }
            }
        }

        return true;
    }

    public FileData<ChineseToEnglishCard> read(FileData<ChineseToEnglishCard> fileData) {
        try {
            Workbook workbook = Workbook.getWorkbook(fileData.getFile());
            Sheet sheet = workbook.getSheet(fileData.getExcelParams().getSheet());
            final int size = sheet.getRows();
            for (int idx = 0; idx < size; idx++) {
                ChineseToEnglishCard card = readCard(sheet, idx);
                fileData.setData(card);
            }
        } catch (IOException e) {
            Log.e(App.TAG, "Failed to obtain workbook", e);
        } catch (BiffException e) {
            Log.e(App.TAG, "Failed to obtain workbook", e);
        }
        return fileData;
    }

    private boolean isWritebale() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private Sheet writeCard(WritableSheet sheet, ChineseToEnglishCard card, int rowPosition) throws WriteException {
        int column = 0;

        jxl.write.Number id = new Number(column++, rowPosition, card.getId());
        Label origin = new Label(column++, rowPosition, card.getOrigin());
        Label translation = new Label(column++, rowPosition, card.getTranslation());
        jxl.write.Number isKnown = new Number(column, rowPosition, card.isKnown() ? 1 : 0);

        sheet.addCell(id);
        sheet.addCell(origin);
        sheet.addCell(translation);
        sheet.addCell(isKnown);

        return sheet;
    }

    private ChineseToEnglishCard readCard(Sheet sheet, int rowPosition) {
        int column = 0;
        try {
            Cell id = sheet.getCell(column++, rowPosition);
            Cell origin = sheet.getCell(column++, rowPosition);
            Cell translation = sheet.getCell(column++, rowPosition);
            Cell isKnown = sheet.getCell(column, rowPosition);

            ChineseToEnglishCard card = new ChineseToEnglishCard();
            card.setId(Integer.valueOf(id.getContents()));
            card.setOrigin(origin.getContents());
            card.setTranslation(translation.getContents());
            card.markAsKnown(isKnown.getContents().equals("1"));

            return card;
        } catch (Exception ex) {
            Log.e(App.TAG, "Failed to obtain data", ex);
        }

        return new ChineseToEnglishCard();
    }
}
