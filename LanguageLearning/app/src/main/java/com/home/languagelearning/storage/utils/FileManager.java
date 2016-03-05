package com.home.languagelearning.storage.utils;

import android.os.Environment;

import com.home.languagelearning.model.FileData;

/**
 * Created by dmitry.kazakov on 3/5/2016.
 */
public class FileManager {

    public boolean write(FileData fileData) {
        if (!isWritebale()) return false;

        // TODO implement me
        return true;
    }

    private boolean isWritebale() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}
