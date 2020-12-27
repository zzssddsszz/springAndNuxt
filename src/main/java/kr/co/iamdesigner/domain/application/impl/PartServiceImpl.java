package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.PartService;
import kr.co.iamdesigner.domain.model.part.BasePart;
import kr.co.iamdesigner.domain.model.part.PartRegistrationManagement;
import kr.co.iamdesigner.domain.model.user.UserRegistrationManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
    private final PartRegistrationManagement management;

    @Override
    public BasePart findByName(String name) {
        return null;
    }
}
