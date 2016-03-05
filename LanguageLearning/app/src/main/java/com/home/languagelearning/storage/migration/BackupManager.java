package com.home.languagelearning.storage.migration;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.home.languagelearning.App;
import com.home.languagelearning.model.ExcelParams;
import com.home.languagelearning.model.FileData;

import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Created by dmitry.kazakov on 3/5/2016.
 */
public class BackupManager {

    private static final int PERMISSION_REQUEST_CODE = 100;

    static String[] permissions = new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public void export(Activity context) {
        // TODO ask for permissions
        int  permission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, permissions, PERMISSION_REQUEST_CODE);
            // TODO consider case when user doesn't give permissions
        }
        try {


            ExcelParams excelParams = new ExcelParams();
            excelParams.setSheet("known words");
            excelParams.setPositionOfSheet(0);

            FileData fileData = new FileData();
            fileData.setExcelParams(excelParams);
            fileData.setDirectory(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString());
            fileData.setName("output.xls");

            WritableWorkbook workbook = Workbook.createWorkbook(fileData.getFile());
            WritableSheet sheet = workbook.createSheet(fileData.getExcelParams().getSheet(), fileData.getExcelParams().getPositionOfSheet());
            Label label = new Label(0, 2, "A label record");
            sheet.addCell(label);

            workbook.write();
            workbook.close();
        } catch (IOException e) {
            Log.e(App.TAG, "Failed to operate with workbook", e);
        } catch (WriteException e) {
            Log.e(App.TAG, "Failed to write into workbook", e);
        }
    }
}
