package pt.ipp.isep.dei.project.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.io.ui.MainUI;
import pt.ipp.isep.dei.project.repository.TypeAreaRepository;

import static org.testng.Assert.assertEquals;

/**
 * TypeAreaService tests class.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {MainUI.class },
        loader = AnnotationConfigContextLoader.class)
public class TypeAreaServiceTest {

    @Autowired
    TypeAreaRepository typeAreaRepository;


    @Test
    public void testFindByName() {
        // given
        TypeArea type = new TypeArea("City");
        typeAreaRepository.save(type);

        // when
        TypeArea found = typeAreaRepository.findByName(type.getName());

        // then
        assertEquals(found.getName(), type.getName());

    }
}