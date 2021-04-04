package com.modoodesigner.web.results;

import com.modoodesigner.domain.common.file.FTPUploader;
import com.modoodesigner.domain.model.attachment.Attachment;
import com.modoodesigner.domain.model.product.Product;
import com.modoodesigner.web.results.data.AttachmentData;
import com.modoodesigner.web.results.data.ProductData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductListResult {
    public static ResponseEntity<ApiResult> getAll(Page<Product> productAll) {

        List<Product> productList = productAll.getContent();
        List<ProductListData> productDataList = new ArrayList<>();
        productList.stream().forEach(product -> {
            productDataList.add(new ProductListData(product));
        });

        return Result.ok(ApiResult.list(productAll, productDataList));


    }

    @Getter
    @Setter
    static class ProductListData {
        private Long id;
        private String name;
        private List<AttachmentData> img;

        public ProductListData(Product product) {
            id = product.getId();
            name = product.getName();
            img = product.getImages().stream().map(img ->
                    new AttachmentData(img)
            ).collect(Collectors.toList());

        }
    }
}
