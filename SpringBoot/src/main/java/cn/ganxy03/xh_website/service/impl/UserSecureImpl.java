package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.UserSecure;
import cn.ganxy03.xh_website.repository.UserSecureRepository;
import cn.ganxy03.xh_website.service.UserSecureService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSecureImpl implements UserSecureService {

    private final UserSecureRepository userSecureRepository;

    public UserSecureImpl(UserSecureRepository userSecureRepository) {
        this.userSecureRepository = userSecureRepository;
    }

    @Override
    public List<UserSecure> GetAllBySno(String sno) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<UserSecure> userSecures = userSecureRepository.findAllBySno(sno, sort);
        return userSecures;
    }
}
