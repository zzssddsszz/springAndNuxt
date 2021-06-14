package com.modoodesigner.web.payload;

import com.modoodesigner.domain.model.paper.PaperPath;
import com.modoodesigner.domain.model.paper.PaperRing;
import com.modoodesigner.domain.model.paper.PaperText;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaperRegistrationPayload {

    private PaperPath paperPath;
    private PaperText paperText;
    private PaperRing paperRing;
}
