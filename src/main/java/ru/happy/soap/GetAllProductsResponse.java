package ru.happy.soap;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "getAllProductsResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "products"
})
public class GetAllProductsResponse {

    @XmlElement(required = true)
    protected List<ProductSoap> products;

    public List<ProductSoap> getProducts() {
        if (products == null) {
            products = new ArrayList<>();
        }
        return products;
    }
}
