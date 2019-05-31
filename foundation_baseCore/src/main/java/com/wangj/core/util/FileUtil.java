package com.wangj.core.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author WangJ jie581825@yeah.net 2017/11/24
 * @version 1.0
 *          Modified: 第一版功能完成
 *          Modified on 2017/11/27
 */
public class FileUtil {
    /* 由于TAG要在静态方法中使用，只能在每个工具类中设置TAG值 */
    private static final String TAG = "FileUtil";

    /**
     * 根据路径获取File、Directory
     * @param path
     * @return
     */
    public static File getFile(@NonNull String path) {
        File file = new File(path);
        LogUtil.d(TAG, "the path you given is a: " + (file.isFile() ? "File" : "Directory"));
        return file;
    }

    public static boolean isExist(String filePath) {
        File file = getFile(filePath);
        return file.exists();
    }

    /**
     * 从sdcard中删除文件
     */
    public static boolean removeFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 计算文件或者文件夹下所有文件的大小，单位B
     *
     * @param file 文件或文件夹
     * @return 文件总大小，单位B
     */
    // TODO 计算缓存数据容量，那些可以清除需要自己确定，容量累加的过程需要在子线程中进行，不然可能导致UI卡顿
    public static long computeFileSize(File file) {
        long sizeSum = 0;

        if (file.isFile()) {
            sizeSum += file.length();
        } else if (file.isDirectory()) {
            for (File item : file.listFiles()) {
                if (item.isFile()) {
                    LogUtil.d(TAG, item.getName() + " size " + item.length());
                    sizeSum += item.length();
                } else {
                    sizeSum += computeFileSize(item);
                }
            }
        } else {
            LogUtil.e(TAG, "compute happen Err：NOT is File or Directory!");
        }
        LogUtil.d(TAG, "total size: " + sizeSum);
        return sizeSum;
    }


    public static String formatSize(long longSize) {
        if (longSize < 1024) {
            return longSize + "B";
        }

        longSize = longSize / 1024;
        if (longSize < 1024) {
            return longSize + "KB";
        }

        longSize = longSize / 1024;
        if (longSize < 1024) {
            return longSize + "MB";
        }

        return longSize / 1024 + "GB";
    }

    /**
     * 判断SD卡是否被挂载
     *
     * @return true-有SD卡；false-无SD卡
     */
    public static boolean isSDCardMounted() {
        LogUtil.d(TAG, "ExternalStorageState:" + Environment.getExternalStorageState());
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡的根目录
     *
     * @return SD卡根目录的绝对路径，SD卡未加挂是返回null
     */
    public static String getSDCardBasePath() {
        if (isSDCardMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    /**
     * 获取SD卡的完整空间大小，单位B
     *
     * @return SD卡总空间大小，若-1说明未加挂SD卡
     */
    public static long getSDCardSize() {
        if (isSDCardMounted()) {
            StatFs fs = new StatFs(getSDCardBasePath());
            long count = fs.getBlockCountLong();
            long size = fs.getBlockSizeLong();
            return count * size;
        }
        return -1;
    }

    /**
     * 获取SD卡的剩余空间大小，单位B
     *
     * @return 若结果>0返回SD卡剩余空间大小，若-1说明未加挂SD卡
     */
    public static long getSDCardFreeSize() {
        if (isSDCardMounted()) {
            StatFs fs = new StatFs(getSDCardBasePath());
            long count = fs.getFreeBlocksLong();
            long size = fs.getBlockSizeLong();
            return count * size;
        }
        return -1;
    }

    /**
     * 获取SD卡的可用空间大小，单位B
     *
     * @return SD卡可用空间大小，若-1说明未加挂SD卡
     */
    public static long getSDCardAvailableSize() {
        if (isSDCardMounted()) {
            StatFs fs = new StatFs(getSDCardBasePath());
            long count = fs.getAvailableBlocksLong();
            long size = fs.getBlockSizeLong();
            return count * size;
        }
        return -1;
    }

    /**
     * 往SD卡的公有目录下保存文件
     *
     * @param data     文件的字节数组
     * @param dirType  公共目录类型，如 Environment.DIRECTORY_DOWNLOADS
     * @param fileName 指定文件名称（可以指定文件类型后缀，也可以不指定，建议指定文件类型）
     * @return true-保存成功；false-保存失败
     */
    public static boolean saveFileToSDCardPublicDir(byte[] data, String dirType, String fileName) {
        if (!isSDCardMounted()) {
            Log.e(TAG, "warning !!! SDCard is not mounted");
            return false;
        }

        BufferedOutputStream bos = null;
        File file = Environment.getExternalStoragePublicDirectory(dirType);
        try {
            bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
            bos.write(data);
            bos.flush();
            return true;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    /**
     * 往SD卡的自定义目录下保存文件
     *
     * @param data             文件的字节形式
     * @param customDirdirName 自定义目录名称
     * @param fileName         文件名，建议指定文件类型即后缀
     * @return true-保存成功；保存失败
     */
    public static boolean saveFileToSDCardCustomDir(byte[] data, String customDirdirName, String fileName) {
        if (customDirdirName.startsWith("/") || customDirdirName.endsWith("/")) {
            Log.e(TAG, "the 2nd parameter of method {saveFileToSDCardCustomDir} can't start or end with character'/'");
            return false;
        }

        if (!isSDCardMounted()) {
            Log.e(TAG, "warning !!! SDCard is not mounted");
            return false;
        }

        File file = new File(getSDCardBasePath() + File.separator + customDirdirName);
        if (!file.exists() && !file.mkdirs()) { // 如果目录不存在则递归创建自定义目录
            Log.e(TAG, "the custom directory create Fail");
            return false;
        }

        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
            bos.write(data);
            bos.flush();
            return true;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    /**
     * 往SD卡的私有files目录下保存文件
     *
     * @param context  上下文
     * @param data     文件的字节
     * @param dirType  files下一级目录类型，如 Environment.DIRECTORY_DOCUMENTS
     * @param fileName 文件名称，建议指定文件后缀
     * @return true-保存成功；false-保存失败
     */
    public static boolean saveFileToSDCardPrivateFilesDir(Context context, byte[] data,
                                                          String dirType, String fileName) {
        if (!isSDCardMounted()) {
            Log.e(TAG, "warning !!! SDCard is not mounted");
            return false;
        }

        BufferedOutputStream bos = null;
        File file = context.getExternalFilesDir(dirType);
        try {
            bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
            bos.write(data);
            bos.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    /**
     * 往SD卡的私有Cache目录下保存文件
     *
     * @param context  上下文
     * @param data     文件的字节
     * @param fileName 文件名称
     * @return true-保存成功；false-保存失败
     */
    //TODO 本方法暂不支持在cache目录下新建目录。如果需要，可以再写一个包含目录名称的方法并复用本方法
    public static boolean saveFileToSDCardPrivateCacheDir(Context context, byte[] data, String fileName) {
        if (!isSDCardMounted()) {
            Log.e(TAG, "warning !!! SDCard is not mounted");
            return false;
        }

        BufferedOutputStream bos = null;
        File file = context.getExternalCacheDir();
        try {
            bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
            bos.write(data);
            bos.flush();
            return true;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    /**
     * 保存bitmap图片到SDCard的私有Cache目录
     *
     * @param context  上下文，用于获取私有目录路径
     * @param bitmap   Bitmap对象
     * @param fileName 包含图片格式的图片名称
     * @return true-保存成功；false-保存失败
     */
    //TODO 本方法暂不支持在cache目录下新建目录，如需请添加含dirName的方法
    public static boolean saveBitmapToSDCardPrivateCacheDir(Context context, Bitmap bitmap,
                                                            String fileName) {
        if (!isSDCardMounted()) {
            Log.e(TAG, "warning !!! SDCard is not mounted");
            return false;
        }

        BufferedOutputStream bos = null;
        // 获取私有的Cache缓存目录
        File file = context.getExternalCacheDir();
        try {
            bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
            if (!TextUtils.isEmpty(fileName)
                    && (fileName.contains(".png") || fileName.contains(".PNG"))) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            } else {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            }
            bos.flush();
            return true;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    /**
     * 从SD卡获取文件的字节对象
     *
     * @param filePath 文件全路径名
     * @return 非空时为文件的字节形式
     */
    public static byte[] loadFileBytes(String filePath) {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        File file = new File(filePath);
        if (!file.isFile()) {
            Log.e(TAG, "Err: your filePath is not a File, maybe a Directory");
            return null;
        }

        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[8 * 1024];
            int c;
            while ((c = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, c);
                baos.flush();
            }
            return baos.toByteArray();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return null;
        } finally {
            try {
                baos.close();
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    /**
     * 从SDCard中寻找指定目录下的文件，返回Bitmap
     *
     * @param bitmapPath bitmap的全路径名
     * @return 非空时为Bitmap对象
     */
    public static Bitmap loadBitmapFromStorage(String bitmapPath) {
        byte[] data = loadFileBytes(bitmapPath);
        if (data == null) {
            return null;
        }
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }
}