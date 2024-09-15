package vn.dkt.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.dkt.laptopshop.domain.Cart;
import vn.dkt.laptopshop.domain.CartDetail;
import vn.dkt.laptopshop.domain.Product;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
  boolean existsByCartAndProduct(Cart cart, Product product);

  CartDetail findByCartAndProduct(Cart cart, Product product);

  CartDetail findById(long id);
}