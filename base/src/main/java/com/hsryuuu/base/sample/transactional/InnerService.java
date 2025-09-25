package com.hsryuuu.base.sample.transactional;

import com.hsryuuu.base.jpa.entity.TestEntity;
import com.hsryuuu.base.jpa.repository.TestEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class InnerService {
    private final TestEntityRepository testEntityRepository;


    // InnerService.class
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void innerMethod(){
        //
        testEntityRepository.save(new TestEntity(1L, "test"));
        testEntityRepository.flush();
        throw new RuntimeException("inner method exception");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void innerRandomErrorMethod(int num){
        if(num % 2 == 0){
            throw new RuntimeException("innerError");
        }else{
            testEntityRepository.save(new TestEntity((long)num, "test" + num));
        }
    }

}
