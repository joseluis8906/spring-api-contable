import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jose.samples.api.contable.Application;
import com.jose.samples.api.contable.puc.clase.Clase;
import com.jose.samples.api.contable.puc.clase.ClaseServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@AutoConfigureMockMvc
public class PucCrontrollerTest {

	private static final String URL = "/api/v1/puc";

	@Autowired
	ClaseServiceImpl claseService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void saveClaseTest () throws  Exception {
		JacksonJsonProvider jsonParser = new JacksonJsonProvider();

		Clase clase = new ClaseServiceImpl.ClaseBuilder()
				.setTipo("Comercial")
				.setCodigo("1")
				.setNombre("Activo")
				.build();


		mvc.perform(MockMvcRequestBuilders.post(URL+"/clases")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonParser.toJson(clase)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tipo", CoreMatchers.is(clase.getTipo())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.codigo", CoreMatchers.is(clase.getCodigo())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.nombre", CoreMatchers.is(clase.getNombre())));
	}
}
