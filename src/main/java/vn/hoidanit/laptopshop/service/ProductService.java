package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final CartRepository cartRepository;
  private final CartDetailRepository cartDetailRepository;
  private final UserService userService;

  public ProductService(ProductRepository productRepository, CartRepository cartRepository,
      CartDetailRepository cartDetailRepository, UserService userService) {
    this.productRepository = productRepository;
    this.cartRepository = cartRepository;
    this.cartDetailRepository = cartDetailRepository;
    this.userService = userService;
  }

  public void handleDeleteProduct(long id) {
    this.productRepository.deleteById(id);
  }

  public Product handleSaveProduct(Product newProduct) {
    return this.productRepository.save(newProduct);
  }

  public List<Product> getAllProducts() {
    return this.productRepository.findAll();
  }

  public Product getProductById(long id) {
    return this.productRepository.findById(id);
  }

  public void deleteProduct(long id) {
    this.productRepository.deleteById(id);
  }

  public void handleAddProductToCart(String email, long productId) {
    User user = this.userService.getUserByEmail(email);
    if (user != null) {
      Cart cart = this.cartRepository.findByUser(user);
      if (cart == null) {
        cart = new Cart();
        cart.setUser(user);
        cart.setSum(1);
        this.cartRepository.save(cart);
      }
      Product product = this.productRepository.findById(productId);
      if (product != null) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setPrice(product.getPrice());
        cartDetail.setQuantity(1);
        this.cartDetailRepository.save(cartDetail);

      }
    }
  }
}
