package com.modoodesigner.domain.model.product;

import com.modoodesigner.domain.model.attachment.Attachment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private Long price;

    private List<Attachment> images = new ArrayList<>();

    private String name;

    private String content;
}