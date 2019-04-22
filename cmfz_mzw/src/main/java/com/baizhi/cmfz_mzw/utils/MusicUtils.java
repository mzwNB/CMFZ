package com.baizhi.cmfz_mzw.utils;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class MusicUtils {

    public static String getDuration(File file) throws EncoderException {
        Encoder encoder = new Encoder();
        MultimediaInfo m = encoder.getInfo(file);
        long ls = m.getDuration();
        StringBuilder strBuilder = new StringBuilder();
        long temp = ls;
        long hper = 60 * 60 * 1000;
        long mper = 60 * 1000;
        long sper = 1000;
        if (temp / hper > 0) {
            strBuilder.append(temp / hper).append("小时");
        }
        temp = temp % hper;

        if (temp / mper > 0) {
            strBuilder.append(temp / mper).append("分钟");
        }
        temp = temp % mper;
        if (temp / sper > 0) {
            strBuilder.append(temp / sper).append("秒");
        }
        return strBuilder.toString();
    }
}
