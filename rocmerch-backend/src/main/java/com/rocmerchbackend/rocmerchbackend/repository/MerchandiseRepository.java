package com.rocmerchbackend.rocmerchbackend.repository;

import com.rocmerchbackend.rocmerchbackend.model.MerchandiseItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<MerchandiseItems, Long> {

}
