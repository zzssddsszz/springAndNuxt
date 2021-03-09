package com.modoodesigner.utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@ToString @EqualsAndHashCode
public class Size implements Serializable {

    private static final long serialVersionUID = -3479909372187129720L;

    private final int width;
    private final int height;


}
