package com.hsryuuu.base.sample.transactional;


import com.hsryuuu.base.jpa.repository.TestEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OuterService {

    private final InnerService innerService;
    private final TestEntityRepository testEntityRepository;

    // OuterService.class
    @Transactional(propagation = Propagation.REQUIRED)
    public void outerMethod() {
        try{
            innerService.innerMethod();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void anotherOuterMethod() {
        throw new RuntimeException("");
    }


}
