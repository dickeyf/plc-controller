package com.brewingmadscientists.plc.repositories;

import com.brewingmadscientists.plc.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fdickey on 2017-06-15.
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
public interface RegisterRepository extends JpaRepository<Register, String> {
}
