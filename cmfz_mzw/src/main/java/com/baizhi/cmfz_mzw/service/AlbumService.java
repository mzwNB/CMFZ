package com.baizhi.cmfz_mzw.service;

import com.baizhi.cmfz_mzw.enetity.Album;
import com.baizhi.cmfz_mzw.viewObject.AlbumDto;

import java.util.List;

public interface AlbumService {
    List<AlbumDto> selectAllAlbum();
    Album selectOne(Integer id);
    void insertAblum(Album album);
    void updateAblum(Album album);

}
