package pt.ipp.isep.dei.project.model;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.repository.TypeAreaRepository;

/**
 * TypeAreaService tests class.
 */
@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
@DataJpaTest
//@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=validate"})
@ContextConfiguration(classes = { TypeAreaRepository.class },
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
        //assertEquals(found.getName(), type.getName());

    }
}