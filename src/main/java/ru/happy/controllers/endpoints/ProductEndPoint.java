package ru.happy.controllers.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.happy.services.ProductService;
import ru.happy.soap.GetAllProductsRequest;
import ru.happy.soap.GetAllProductsResponse;
import ru.happy.soap.ProductSoap;

@RequiredArgsConstructor
@Endpoint
public class ProductEndPoint {

    private static final String NAMESPACE_URI = "http://www.happy.ru/ws/products";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProducts().stream().map(ProductSoap::new).forEach(response.getProducts()::add);
        return response;
    }
}
