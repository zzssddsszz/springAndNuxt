package kr.co.iamdesigner.domain.model.user;

import kr.co.iamdesigner.domain.model.AbstractBaseId;

public class UserId extends AbstractBaseId {
    private static final long serialVersionUID = 2426889267803214263L;

    public UserId(long id) {
        super(id);
    }

    public String toString() {
        return String.valueOf(value());
    }
}
