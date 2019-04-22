package com.baizhi.cmfz_mzw.dao;

import com.baizhi.cmfz_mzw.enetity.Banner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface BannerDao extends BaseMapper<Banner> {
    List<Banner> selectAllBanner();
}
