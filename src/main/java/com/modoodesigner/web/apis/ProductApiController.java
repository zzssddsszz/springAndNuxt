package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.PendantService;
import com.modoodesigner.domain.application.ProductService;
import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.model.part.common.PartExistsException;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;
import com.modoodesigner.domain.model.part.pendant.Pendant;
import com.modoodesigner.domain.model.product.Product;
import com.modoodesigner.domain.model.product.ProductRegistrationException;
import com.modoodesigner.web.payload.PendantRegistrationPayload;
import com.modoodesigner.web.payload.ProductRegistrationPayload;
import com.modoodesigner.web.results.ApiResult;
import com.modoodesigner.web.results.PendantListResult;
import com.modoodesigner.web.results.ProductListResult;
import com.modoodesigner.web.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    public ResponseEntity<ApiResult> getProducts( Pageable pageable, HttpServletRequest request) {
        Page<Product> productAll = service.findByAll(pageable);
        return ProductListResult.created(productAll);
    }


}
