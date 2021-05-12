package grp.dtop.dtopui;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DtopuiApplicationTests {
	@Autowired
	private EchoClientService ecs;

	@Test
	void contextLoads() {
//		ecs.greet("hello from java");
	}

}
