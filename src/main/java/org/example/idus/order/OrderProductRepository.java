package org.example.idus.order;

import org.example.idus.order.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findAllByMemberIdx(Long memberIdx);

    List<OrderProduct> findByOrderByIdxDesc();
}
