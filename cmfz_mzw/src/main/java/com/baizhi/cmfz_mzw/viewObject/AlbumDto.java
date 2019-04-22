package com.baizhi.cmfz_mzw.viewObject;

import com.baizhi.cmfz_mzw.enetity.Album;
import com.baizhi.cmfz_mzw.enetity.Chapter;
import lombok.Data;

import java.util.List;

@Data
public class AlbumDto {
    private  Integer id;
    private  String title;
    List<Chapter> children;
}
