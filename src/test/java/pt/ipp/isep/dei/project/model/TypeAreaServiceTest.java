package pt.ipp.isep.dei.project.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pt.ipp.isep.dei.project.io.ui.MainUI;
import pt.ipp.isep.dei.project.repository.TypeAreaRepository;

/**
 * TypeAreaService tests class.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes= MainUI.class)
public class TypeAreaServiceTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    TypeAreaRepository typeAreaRepository;


    @Test
    public void testFindByName() {
        // given
        TypeArea type = new TypeArea("City");
        testEntityManager.persist(type);

        // when
        TypeArea found = typeAreaRepository.findByName(type.getName());

        // then
        //assertThat(found.getName()).matches(type.getName());

    }
}