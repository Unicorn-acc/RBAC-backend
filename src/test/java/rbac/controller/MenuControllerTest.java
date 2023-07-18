package rbac.controller;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import rbac.common.enums.ExceptionEnum;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author MiracloW
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
public class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${token}")
    private String token;

    @Test
    public void testGetMenuTree() throws Exception{
        String json = "{\n" +
                "\n" +
                "}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/menu/tree")
                .content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("token",token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString(); // 获取响应的JSON字符串

        JSONObject jsonResponse = new JSONObject(responseJson);
        Integer code = Integer.valueOf(jsonResponse.getString("code"));
        String data = jsonResponse.getString("data");
        String msg = jsonResponse.getString("msg");

        assertEquals(code, ExceptionEnum.SUCCESS.getCode());
        assertNotNull(data);
    }


}