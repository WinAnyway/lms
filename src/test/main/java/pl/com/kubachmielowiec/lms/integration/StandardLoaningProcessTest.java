package pl.com.kubachmielowiec.lms.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.impl.StandardLoaningProcess;
import pl.com.kubachmielowiec.model.publications.PublicationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class StandardLoaningProcessTest {

    @Autowired
    private StandardLoaningProcess loaningProcess;

    @Autowired
    private PublicationRepository publicationRepository;

    @Test
    @Sql("/fixtures/loaning.sql")
    public void shouldLoanAPublication () {
        //when
        loaningProcess.loan(1L);






    }


}
