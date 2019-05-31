package com.wangj.core.util;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * ZIP解压工具
 *
 * @author WangJ jie581825@yeah.net 2017/11/29
 * @version 0.1
 *          Modified:
 *          Modified on 2017/11/29 by
 */
public class ZIPUtil {
    private static final String TAG = "ZipUtil";

    /**
     * 解压指定路径下的文件
     *
     * @param zipFilePath 待解压的压缩文件
     * @param outputDir   解压结果输出目录
     * @param isReWrite   存在同名文件时是否覆盖
     * @return true-解压成功；false-失败
     */
    public static boolean unZIP(String zipFilePath, String outputDir, boolean isReWrite) {
        InputStream inputStream = null;

        File zipFile = new File(zipFilePath);
        if (!zipFile.exists()) {
            LogUtil.e(TAG, "the zipFile Not found!");
            return false;
        }

        try {
            inputStream = new FileInputStream(zipFile);
            innerUnZIP(inputStream, outputDir, isReWrite);
            return true;
        } catch (IOException e) {
            LogUtil.e(TAG, e.getMessage());
            return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LogUtil.e(TAG, e.getMessage());
                }
            }
        }
    }

    /**
     * 解压assets下ZIP文件到指定目录
     *
     * @param context 上下文
     * @param zipFileName 压缩文件名
     * @param outputDir 输出目录
     * @param isReWrite 目标已存在时是否覆盖
     * @return true-成功；false-失败
     */
    public static boolean unZIPFromAssets(Context context, String zipFileName, String outputDir, boolean isReWrite) {
        InputStream inputStream = null;
        try {
            // 打开压缩文件
            inputStream = context.getAssets().open(zipFileName);
            innerUnZIP(inputStream, outputDir, isReWrite);
            return true;
        } catch (IOException e) {
            LogUtil.e(TAG, e.getMessage());
            return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LogUtil.e(TAG, e.getMessage());
                }
            }
        }
    }

    /**
     * 内部解压文件
     *
     * @param inputStream     解压输入流
     * @param outputDirectory 输出目录
     * @param isReWrite       目标文件已存在时是否覆盖
     * @throws IOException
     */
    private static void innerUnZIP(InputStream inputStream, String outputDirectory, boolean isReWrite) throws IOException {
        if (inputStream == null) {
            LogUtil.e(TAG, "the inputStream in method innerUnZIP() is null!");
            return;
        }
        // 创建解压目标目录
        File file = new File(outputDirectory);
        if (!file.exists()) {
            file.mkdirs();
        }

        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        // 读取一个进入点
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        // 使用 1M buffer
        byte[] buffer = new byte[1024 * 1024];
        // 解压时字节计数
        int count;
        // 如果进入点为空说明已经遍历完所有压缩包中文件和目录
        while (zipEntry != null) {
            // 如果是一个目录
            if (zipEntry.isDirectory()) {
                file = new File(outputDirectory + File.separator + zipEntry.getName());
                // 文件需要覆盖或者是文件不存在
                if (isReWrite || !file.exists()) {
                    file.mkdir();
                }
            } else {
                // 如果是文件
                file = new File(outputDirectory, zipEntry.getName());
                // 文件需要覆盖或者文件不存在，则解压文件
                if (isReWrite || !file.exists()) {
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    while ((count = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, count);
                    }
                    fileOutputStream.close();
                }
            }
            // 定位到下一个文件入口
            zipEntry = zipInputStream.getNextEntry();
        }
        zipInputStream.close();
    }
}