package com.shenpu.proxy.scheduler.dao;

import java.util.List;
import javax.persistence.Table;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.shenpu.proxy.scheduler.domain.Barrage;

@Repository("barrageDao")
@Table(name="t_barrage")
public interface IBarrageDao  extends CrudRepository<Barrage, Integer>{
	
	
	@Query("select barrage from Barrage barrage")
	List<Barrage> findAll();
	
}
