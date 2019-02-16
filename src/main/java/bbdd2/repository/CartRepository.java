package bbdd2.repository;

import org.springframework.stereotype.Repository;
import bbdd2.model.Cart;

@Repository
public interface CartRepository extends BaseRepository<Cart, String> {
}
