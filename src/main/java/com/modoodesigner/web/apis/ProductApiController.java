package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.ProductService;
import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.model.product.Product;
import com.modoodesigner.domain.model.product.ProductRegistrationException;
import com.modoodesigner.web.payload.ProductRegistrationPayload;
import com.modoodesigner.web.results.ApiResult;
import com.modoodesigner.web.results.ProductListResult;
import com.modoodesigner.web.results.ProductResult;
import com.modoodesigner.web.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductApiController extends AbstractBaseController {
    private final ProductService service;

    @PostMapping("/api/products")
    public ResponseEntity<ApiResult> register(@RequestBody ProductRegistrationPayload payload, HttpServletRequest request){
        try {
            ProductRegisterCommand command = payload.toCommand();
            service.register(command);
            return Result.created();
        }catch (ProductRegistrationException e){
            String errorMessage = "상품등록에 실패하였습니다.";
            return Result.failure(errorMessage);
        }
    }


    @GetMapping("/api/products")
    public ResponseEntity<ApiResult> getProductAll( Pageable pageable, HttpServletRequest request) {
        Page<Product> productAll = service.findByAll(pageable);
        return ProductListResult.getAll(productAll);
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<ApiResult> getProduct(@PathVariable Long id, HttpServletRequest request) {
        Product product = service.findById(id);
        return ProductResult.getOne(product);
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<ApiResult> editProduct(@RequestBody ProductRegistrationPayload payload,
                                                 @PathVariable Long id, HttpServletRequest request) {
        try {
            ProductRegisterCommand command = payload.toCommand();
            service.edit(command,id);
            return Result.ok();
        } catch (Exception e) {
            String errorMessage = "상품수정에 실패하였습니다.";
            return Result.failure(errorMessage);
        }

    }


}
