package com.apirest.Controllers;

import com.apirest.Exceptions.ResourceNotFoundException;
import com.apirest.Models.Product;
import com.apirest.Services.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Product Management System", description = "Operations pertaining to product in Product Management System")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "View a list of available products", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return (List<Product>) productService.findAll();
    }

    @ApiOperation(value = "Get a product by Id")
    @GetMapping("/product/{id}")

    public Product getProduct(@ApiParam(value = "Product id from whom object will retrieve",
            required = true) @PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return productService.findByProduct_id(id);
    }

    @ApiOperation(value = "Add a product")
    @PostMapping("/product")
    public Product postProduct(@ApiParam(value = "Product you want save in database",
            required = true) @Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @ApiOperation(value = "Add a product list")
    @PostMapping("/products")
    public Iterable<Product> postListProduct(@ApiParam(value = "Product list you want save in database",
            required = true) @Valid @RequestBody Iterable<Product> products) {
        return productService.saveList(products);
    }

    @ApiOperation(value = "Remove a product from database")
    @DeleteMapping("/product/{id}")
    public HttpStatus deleteProduct(@ApiParam(value = "Product id whom will be removed",
            required = true) @PathVariable(value = "id") Integer id) {
        productService.deleteById(id);
        return HttpStatus.OK;
    }

    @ApiOperation(value = "Update a product")
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> putProduct(@ApiParam(value = "Product id whose will be updated", required = true) @PathVariable(value = "id") Integer id,
                                              @ApiParam(value = "Update product object", required = true) @Valid @RequestBody Product product) throws ResourceNotFoundException {
        Product updatedProduct = productService.update(id, product);
        return ResponseEntity.ok(updatedProduct);
    }
}
