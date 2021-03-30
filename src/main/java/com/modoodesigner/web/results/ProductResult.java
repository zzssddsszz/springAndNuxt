package com.modoodesigner.web.results;

import com.modoodesigner.domain.model.product.Product;
import com.modoodesigner.web.results.data.ProductData;
import org.springframework.http.ResponseEntity;

public class ProductResult {
    public static ResponseEntity<ApiResult> getOne(Product product) {
        ApiResult apiResult = ApiResult.blank()
                .add("data", new ProductData(product));
        return Result.ok(apiResult);
    }
}
