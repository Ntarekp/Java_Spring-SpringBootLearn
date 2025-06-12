// File: src/main/java/org/greenbasket/service/ProductService.java
package org.greenbasket.service;

import org.greenbasket.dto.ProductDto;
import org.greenbasket.entity.Product;
import org.greenbasket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.io.File;
import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Directory to store uploaded images
    private final String uploadDir = "uploads/";

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(ProductDto productDto, MultipartFile image) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setSustainabilityTags(productDto.getSustainabilityTags());

        if (!image.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                String filePath = uploadDir + fileName;
                File dest = new File(filePath);
                dest.getParentFile().mkdirs();
                image.transferTo(dest);
                product.setImageUrl(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setSustainabilityTags(productDto.getSustainabilityTags());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
