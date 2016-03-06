package com.home.languagelearning.storage.migration;

import android.Manifest;
import android.app.Activity;
import android.os.Environment;

import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.model.ExcelParams;
import com.home.languagelearning.model.FileData;
import com.home.languagelearning.storage.BaseAsyncQueryHandler;
import com.home.languagelearning.storage.Contract;
import com.home.languagelearning.storage.mappers.CardMapper;
import com.home.languagelearning.storage.utils.FileManager;

import java.util.List;

/**
 * Created by dmitry.kazakov on 3/5/2016.
 */
public class BackupManager implements BaseAsyncQueryHandler.QueryListener {

    private static final int PERMISSION_REQUEST_CODE = 100;

    static String[] permissions = new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public void export(Activity context, boolean debug) {
        BaseAsyncQueryHandler asyncQueryHandler = new BaseAsyncQueryHandler(context.getContentResolver());
        asyncQueryHandler.setListener(this, new CardMapper());
        asyncQueryHandler.startQuery(0, null,
                Contract.contentUriNoNotify(Contract.CardsTable.class),
                null, null, null, null);
    }

    public void importWords() {
        FileData<ChineseToEnglishCard> fileData = getFileData();

        FileManager fileManager = new FileManager();
        fileData = fileManager.read(fileData);
        return;
    }

    @Override
    public void onQueryComplete(List result) {
        FileData<ChineseToEnglishCard> fileData = getFileData();
        fileData.setData(result);

        FileManager fileManager = new FileManager();
        fileManager.write(fileData);
    }

    private FileData<ChineseToEnglishCard> getFileData() {
        ExcelParams excelParams = new ExcelParams();
        excelParams.setSheet("known words");
        excelParams.setPositionOfSheet(0);

        FileData<ChineseToEnglishCard> fileData = new FileData<>();
        fileData.setExcelParams(excelParams);
        fileData.setDirectory(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString());
        fileData.setName("learning_words_backup.xls");

        return fileData;
    }
}
