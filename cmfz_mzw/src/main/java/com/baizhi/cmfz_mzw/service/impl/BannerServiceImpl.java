package com.baizhi.cmfz_mzw.service.impl;

import com.baizhi.cmfz_mzw.dao.BannerDao;
import com.baizhi.cmfz_mzw.enetity.Banner;
import com.baizhi.cmfz_mzw.service.BannerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao  bannerDao;

    @Override
    public Map selectAllBanner(Integer page,Integer rows) {
        System.out.println(page+"=============="+rows);
        HashMap map = new HashMap<>();
        PageHelper.startPage(page,rows);
        System.out.println(bannerDao.selectAllBanner());
        PageInfo<Banner> pageInfo =new PageInfo<>(bannerDao.selectAllBanner());
        List<Banner> list = pageInfo.getList();
        long total = pageInfo.getTotal();
        map.put("rows",list);
        map.put("total",total);
        return map;
    }

    @Override
    public void insertBanner(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    public void deleteBanner(Integer id) {
       bannerDao.deleteById(id);
    }

    @Override
    public void updateBanner(Banner banner) {
       bannerDao.updateById(banner);
    }
}
