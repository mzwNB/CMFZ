package com.baizhi.cmfz_mzw.service.impl;

import com.baizhi.cmfz_mzw.dao.ChapterDao;
import com.baizhi.cmfz_mzw.enetity.Chapter;
import com.baizhi.cmfz_mzw.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService{

    @Autowired
    private ChapterDao chapterDao;

    @Override
    public void insertChapter(Chapter chapter) {
        chapterDao.insert(chapter);
    }
}
