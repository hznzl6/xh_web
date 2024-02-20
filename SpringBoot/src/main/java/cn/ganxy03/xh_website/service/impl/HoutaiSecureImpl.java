package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.HoutaiSecure;
import cn.ganxy03.xh_website.repository.HoutaiSecureReposotory;
import cn.ganxy03.xh_website.service.HoutaiSecureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoutaiSecureImpl implements HoutaiSecureService {
    private HoutaiSecureReposotory houtaiSecureReposotory;

    public HoutaiSecureImpl(HoutaiSecureReposotory houtaiSecureReposotory) {
        this.houtaiSecureReposotory = houtaiSecureReposotory;
    }

    @Override
    public List<HoutaiSecure> GetAllByAccount(String account) {
        List<HoutaiSecure> houtaiSecures = houtaiSecureReposotory.findAllByAccount(account);
        return houtaiSecures;
    }
}
