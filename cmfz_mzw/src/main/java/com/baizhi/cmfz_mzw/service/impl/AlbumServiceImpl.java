package com.baizhi.cmfz_mzw.service.impl;

import com.baizhi.cmfz_mzw.dao.AlbumDao;
import com.baizhi.cmfz_mzw.enetity.Album;
import com.baizhi.cmfz_mzw.service.AlbumService;
import com.baizhi.cmfz_mzw.viewObject.AlbumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;


    @Override
    public List<AlbumDto> selectAllAlbum() {
        List<AlbumDto> list = albumDao.selectAllAlbum();
        return list;
    }

    @Override
    public Album selectOne(Integer id) {
        Album album = albumDao.selectById(id);
        return album;
    }

    @Override
    public void insertAblum(Album album) {
        System.out.println(album);
       albumDao.insert(album);
    }

    @Override
    public void updateAblum(Album album) {
        albumDao.updateById(album);
    }
}
