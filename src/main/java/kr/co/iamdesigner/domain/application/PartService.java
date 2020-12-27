package kr.co.iamdesigner.domain.application;

import kr.co.iamdesigner.domain.model.part.BasePart;

public interface PartService {
    BasePart findByName(String name);
}
