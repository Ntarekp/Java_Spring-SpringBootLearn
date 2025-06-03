package org.kaiLearn.ExamCorrection.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.kaiLearn.ExamCorrection.entity.Product;
import org.kaiLearn.ExamCorrection.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    public Product updateProduct(Long id, Product updatedProduct){
        return productRepository.findById(id)
                .map(product ->{
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            return productRepository.save(product);
        }).orElseThrow(()->new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}

