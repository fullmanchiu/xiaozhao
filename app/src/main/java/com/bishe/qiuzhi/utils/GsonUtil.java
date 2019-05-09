package com.bishe.qiuzhi.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.bishe.qiuzhi.module.resume.model.ResumeModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * josn 工具类
 * json和对象的转换
 */
public class GsonUtil {
    public static String getJsonStrFromFile(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static List<?> analysisJson(Context context, String fileName) {
        String myjson = getJsonStrFromFile(context, fileName);
        return new Gson().fromJson(myjson, new TypeToken<List<?>>() {
        }.getType());
    }
}
