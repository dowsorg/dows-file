package org.dows.file.api.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

public final class Md5Util {

    public static String getMd5Hex(File file) {
        try {
            MessageDigest d = MessageDigest.getInstance("MD5");
            return doMessageDigestUpdate(file, d);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSha1(File file) {
        try {
            MessageDigest d = MessageDigest.getInstance("SHA-1");
            return doMessageDigestUpdate(file, d);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMd5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }

    public static String getMd5Hex(byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    private static String doMessageDigestUpdate(File file, MessageDigest digest) {
        FileInputStream in = null;
        FileChannel ch = null;
        try {
            in = new FileInputStream(file);
            ch = in.getChannel();
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                    file.length());
            digest.update(byteBuffer);
//            Method m = FileChannelImpl.class.getDeclaredMethod("unmap",
//                    MappedByteBuffer.class);
//            m.setAccessible(true);
//            m.invoke(FileChannelImpl.class, byteBuffer);
            return DigestUtils.md5Hex(digest.digest());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }/* catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/ finally {
            if (ch != null) {
                try {
                    ch.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String getCRC32(File file) {
        CRC32 crc32 = new CRC32();
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = in.read(buffer)) != -1) {
                crc32.update(buffer, 0, length);
            }
            return crc32.getValue() + "";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMd5Hex(password);
        return s.equals(md5PwdStr);
    }
}
