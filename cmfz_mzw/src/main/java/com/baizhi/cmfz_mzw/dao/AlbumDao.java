package com.baizhi.cmfz_mzw.dao;

import com.baizhi.cmfz_mzw.enetity.Album;
import com.baizhi.cmfz_mzw.viewObject.AlbumDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface AlbumDao extends BaseMapper<Album> {

    List<AlbumDto> selectAllAlbum();
}
