package com.titan.baselibrary.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by li on 2017/5/31.
 * Util工具类
 */

public class Util {

    /*拍照提取*/
    public static String takePhoto(Context context,File file,int requestcode){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                //改变Uri  com.xykj.customview.fileprovider注意和xml中的一致
                Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                if (photoURI != null) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                }
                ((Activity)context).startActivityForResult(intent, requestcode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // 指定存放拍摄照片的位置
            //File file = createImageFile();
            if (file != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                ((Activity)context).startActivityForResult(intent, requestcode);

            }
        }
        assert file != null;
        return file.getAbsolutePath();
    }

    /**
     * 复制asset文件到指定目录
     * @param oldPath asset下的路径
     * @param newPath SD卡下保存路径
     */
    private void CopyAssets(Context context,String oldPath,String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);// 获取assets目录下的所有文件及目录名
            for(String name : fileNames){
                if(name.contains(".")){
                    copyDatabase(context,newPath, oldPath+"/"+name,name);
                }else{
                    File file = new File(newPath+"/"+name);
                    file.mkdirs();
                    CopyAssets(context,oldPath+"/"+name, newPath+"/"+name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 复制默认数据到本地 */
    private void copyDatabase(Context context,String fileDir,String assetPath, String filename) {
        File file = new File(fileDir + "/" + filename);
        if (file.exists() && file.isFile()) {
            //UnZipTFolder(fileDir, filename);
            return;
        }
        try {
            InputStream db = context.getResources().getAssets().open(assetPath);
            if(db == null){
                return;
            }
            FileOutputStream fos = new FileOutputStream(fileDir + "/"+ filename);
            byte[] buffer = new byte[1024];//8129
            int count = 0;

            while ((count = db.read(buffer)) >= 0) {
                fos.write(buffer, 0, count);
            }

            fos.close();
            db.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //UnZipTFolder(fileDir, filename);
    }


    /**复制文件到平板中*/
    private void copyFile(Context context,String fileDir, String assetPath, String filename){
        File file = new File(fileDir + "/" + filename);
        if (file.exists() && file.isFile()) {
            //UnZipTFolder(fileDir, filename);
            return;
        }
        try {
            InputStream db = context.getResources().getAssets().open(assetPath);
            FileOutputStream fos = new FileOutputStream(fileDir + "/"+ filename);
            byte[] buffer = new byte[8129];
            int count = 0;

            while ((count = db.read(buffer)) >= 0) {
                fos.write(buffer, 0, count);
            }

            fos.close();
            db.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**复制文件夹到平板中*/
    public void copyFolder(Context context,String fileDir,String assetPath, String filename){
        File file = new File(fileDir + "/" + filename);
        if (file.exists() && file.isFile()) {
            //UnZipTFolder(fileDir, filename);
            return;
        }
        try {
            InputStream db = context.getResources().getAssets().open(assetPath);
            FileOutputStream fos = new FileOutputStream(fileDir + "/"+ filename);
            byte[] buffer = new byte[8129];
            int count = 0;

            while ((count = db.read(buffer)) >= 0) {
                fos.write(buffer, 0, count);
            }

            fos.close();
            db.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**图片转为String字符流*/
    public static String picToString(String pic){
        Bitmap bitmap = BitmapFactory.decodeFile(pic);
        return Util.Bitmap2StrByBase64(bitmap);
    }

    /**
     * 通过Base32将Bitmap转换成Base64字符串
     */
    public static String Bitmap2StrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 50, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

}
