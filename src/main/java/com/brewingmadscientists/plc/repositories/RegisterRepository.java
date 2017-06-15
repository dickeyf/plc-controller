package com.brewingmadscientists.plc.repositories;

import com.brewingmadscientists.plc.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fdickey on 2017-06-15.
 */
@Repository
public interface RegisterRepository extends CrudRepository<Register, Long>, JpaRepository<Register, Long> {

}
