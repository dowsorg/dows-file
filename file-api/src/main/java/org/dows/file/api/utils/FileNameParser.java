package org.dows.file.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * 文件名解析器
 * 文件名约定名称：yyyyMMddHHmmssSSS + 3位随机数 + 文件类型（个数随机）+ 20位自定义随机数
 * 1.校验文件名
 * 2.解析日期字段
 * 3.解析文件类型字段
 */
public final class FileNameParser {
    private static final FileNameParser INSTANCER = new FileNameParser();
    /**
     * 日期长度
     */
    private static final int DATE_LENGTH = 17;
    /**
     * 随机数长度
     */
    private static final int RANDOM_LENGTH = 3;
    /**
     * 用户自定义随机数长度
     */
    private static final int CUSTOM_LENGTH = 20;
    /**
     * 文件名最小长度
     */
    private static final int MIN_FILE_LENGTH = DATE_LENGTH + RANDOM_LENGTH + CUSTOM_LENGTH + 1;

    public static FileNameParser getInstance() {
        return INSTANCER;
    }

    /**
     * 校验文件名
     *
     * @param fileName
     * @return
     */
    public boolean verify(String fileName) {
        String name = getNoSuffixFileName(fileName);

        //校验文件名长度不带后缀
        if (name.length() < MIN_FILE_LENGTH) {
            return false;
        }

        boolean flag = true;

        //校验日期字符串
        String dateStr = name.substring(0, DATE_LENGTH - 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }

        //校验3位随机数
        String randomNum = name.substring(DATE_LENGTH, DATE_LENGTH + 3);
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        flag = pattern.matcher(randomNum).matches();

        if (!flag) {
            return false;
        }

        //校验后20位随机数
        int len = name.length();
        String customNum = name.substring(len - CUSTOM_LENGTH, len);
        flag = pattern.matcher(randomNum).matches();
        if (!flag) {
            return false;
        }

        return true;
    }

    /**
     * 获取日期
     *
     * @param fileName
     * @param len
     * @return
     */
    public String getDateField(String fileName, int len) {
        return getNoSuffixFileName(fileName).substring(0, len);
    }

    /**
     * 获取文件类型
     *
     * @param fileName
     * @return
     */
    public String getFileTypeField(String fileName) {
        String name = getNoSuffixFileName(fileName);
        int len = name.length() - DATE_LENGTH - RANDOM_LENGTH - CUSTOM_LENGTH;
        return name.substring(DATE_LENGTH + RANDOM_LENGTH, DATE_LENGTH + RANDOM_LENGTH + len);
    }

    private String getNoSuffixFileName(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        String name = "";
        if (lastIndex == -1) {
            name = fileName;
        } else {
            name = fileName.substring(0, lastIndex);
        }
        return name;
    }
}
