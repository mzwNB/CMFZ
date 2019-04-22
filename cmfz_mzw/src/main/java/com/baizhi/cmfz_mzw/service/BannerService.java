package com.baizhi.cmfz_mzw.service;

import com.baizhi.cmfz_mzw.enetity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {

    Map selectAllBanner(Integer page,Integer rows);

    void insertBanner(Banner banner);

    void deleteBanner(Integer id);

    void updateBanner(Banner banner);
}
