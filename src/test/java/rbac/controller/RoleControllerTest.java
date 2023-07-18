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
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${token}")
    private String token;

    @Test
    public void testPage_ReturnsSuccess() throws Exception{
        Integer pageNum = 1;
        Integer pageSize = 2;
        String url = "/role/page?pageNum=" + pageNum + "&pageSize="  + pageSize;
        String json = "{}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
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

    @Test
    public void testgetById_ReturnsSuccess() throws Exception{
        String id = "1";
        String url = "/role/info/" + id;
        String json = "{}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
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
        JSONObject detail = new JSONObject(data);
        assertNotNull(detail);
        assertNotNull(detail.getString("id"));
        assertNotNull(detail.getString("roleName"));
        assertNotNull(detail.getString("roleDesc"));
    }

    @Test
    public void testgetById_UserNotFound_ThrowsException() throws Exception{
        String id = "108111";
        String url = "/role/info/" + id;
        String json = "{}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
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

        assertEquals(code, ExceptionEnum.ROLE_NOT_EXIST.getCode());
    }

    @Test
    public void testsave_ReturnsSuccess() throws Exception{
        String url = "/role/save";
        String json = "{\n" +
                "  \"roleDesc\": \"单元测试专用角色\",\n" +
                "  \"roleName\": \"单元测试专用角色\"\n" +
                "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
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
    }

    @Test
    public void testsave_RoleExist_ThrowsException() throws Exception{
        String url = "/role/save";
        String json = "{\n" +
                "  \"roleDesc\": \"管理员\",\n" +
                "  \"roleName\": \"管理员\"\n" +
                "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
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

        assertEquals(code, ExceptionEnum.ROLE_EXIST.getCode());
    }

    @Test
    public void testupdate_ReturnsSuccess() throws Exception{
        String url = "/role/update";
        String json = "{\n" +
                "  \"id\": 48,\n" +
                "  \"roleName\": \"角色1\",\n" +
                "  \"roleDesc\": \"sd\",\n" +
                "  \"recToken\": 0\n" +
                "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
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
    }

    @Test
    public void testupdate_RoleExist_ThrowsException() throws Exception{
        String url = "/role/update";
        String json = "{\n" +
                "  \"id\": 48,\n" +
                "  \"roleName\": \"管理员\",\n" +
                "  \"roleDesc\": \"sd\",\n" +
                "  \"recToken\": 0\n" +
                "}\n";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
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

        assertEquals(code, ExceptionEnum.ROLE_EXIST.getCode());
    }

    @Test
    public void testupdate_RecTokenError_ThrowsException() throws Exception{
        String url = "/role/update";
        String json = "{\n" +
                "  \"id\": 48,\n" +
                "  \"roleName\": \"角色1\",\n" +
                "  \"roleDesc\": \"sd\",\n" +
                "  \"recToken\": 1110\n" +
                "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
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

        assertEquals(code, ExceptionEnum.ROLE_UPDATE_CONCURRENT_MODIFY_ERROR.getCode());
    }


    @Test
    public void testremove_ReturnsSuccess() throws Exception{
        Long roleId = 1L;
        String url = "/role/remove/" + roleId;
        String json = "{}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete(url)
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
    }

    @Test
    public void testremove_RoleNotFound_ThrowsException() throws Exception{
        Long roleId = 108111L;
        String url = "/role/remove/" + roleId;
        String json = "{}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete(url)
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

        assertEquals(code, ExceptionEnum.ROLE_NOT_EXIST.getCode());
    }
}