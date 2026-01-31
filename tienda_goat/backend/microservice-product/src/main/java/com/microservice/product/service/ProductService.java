package com.microservice.product.service;

import com.microservice.product.entity.Product;
import com.microservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Product update(Long id, Product product) {
        Product existing = findById(id);
        existing.setNombre(product.getNombre());
        existing.setPrecio(product.getPrecio());
        existing.setSku(product.getSku());
        existing.setDescripcion(product.getDescripcion());
        existing.setTalla(product.getTalla());
        existing.setColor(product.getColor());
        existing.setPrecioDescuento(product.getPrecioDescuento());
        existing.setTipoPrenda(product.getTipoPrenda());
        existing.setMarca(product.getMarca());
        existing.setEstilo(product.getEstilo());
        existing.setCategoria(product.getCategoria());
        existing.setStock(product.getStock());
        existing.setActivo(product.getActivo());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

