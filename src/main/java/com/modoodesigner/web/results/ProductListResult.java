package com.modoodesigner.web.results;

import com.modoodesigner.domain.model.product.Product;
import com.modoodesigner.web.results.data.ProductData;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductListResult {
    public static ResponseEntity<ApiResult> created(Page<Product> productAll) {

        List<Product> productList = productAll.getContent();
        List<ProductData> productDataList = new ArrayList<>();
        productList.stream().forEach(product -> {
            productDataList.add(new ProductData(product));
        });

        return Result.ok(ApiResult.list(productAll,productDataList));


    }
}
