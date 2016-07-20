package com.androidbuts.parser;

import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;


/**
 * Created by Pratik Butani
 */
public class JSONParser {

    /**
     * Upload URL of your folder with php file name...
     * You will find this file in php_upload folder in this project
     * You can copy that folder and paste in your htdocs folder...
     */
    private static final String URL_UPLOAD_IMAGE = "http://192.168.1.102/renter/index.php/api/proparty_add";

    /**
     * Upload Image
     *
     * @param sourceImageFile
     * @return
     */
    public static JSONObject uploadImage(String sourceImageFile) {

        try {
            File sourceFile = new File(sourceImageFile);

            Log.d("TAG", "File...::::" + sourceFile + " : " + sourceFile.exists());

            final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*");

            String filename = sourceImageFile.substring(sourceImageFile.lastIndexOf("/") + 1);

            RequestBody requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("zip_code", "382721")
                    .addFormDataPart("city", "kalol")
                    .addFormDataPart("name", "property name")
                    .addFormDataPart("state", "Gujrat")
                    .addFormDataPart("unit_num", "202121")
                    .addFormDataPart("address", "address og ar")
                    .addFormDataPart("proparty_type_id", "2")
                    .addFormDataPart("user_id", "21")
                    .addFormDataPart("move_month", "January")
                    .addFormDataPart("move_year", "2015")
                    .addFormDataPart("image", filename, RequestBody.create(MEDIA_TYPE_PNG, sourceFile))
                    .build();

            Request request = new Request.Builder()
                    .url(URL_UPLOAD_IMAGE)
                    .post(requestBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            return new JSONObject(response.body().string());

        } catch (UnknownHostException | UnsupportedEncodingException e) {
            Log.e("TAG", "Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            Log.e("TAG", "Other Error: " + e.getLocalizedMessage());
        }
        return null;
    }
}
