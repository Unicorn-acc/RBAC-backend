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
public class EmpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${token}")
    private String token;


    @Test
    public void testPage_ReturnsSuccess() throws Exception{
        Integer pageNum = 1;
        Integer pageSize = 2;
        String url = "/emp/page?pageNum=" + pageNum + "&pageSize="  + pageSize;
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
        String id = "108";
        String url = "/emp/info/" + id;
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
        assertNotNull(detail.getString("empNo"));
        assertNotNull(detail.getString("empName"));
        assertNotNull(detail.getString("password"));
        assertNotNull(detail.getString("deptNo"));
        assertNotNull(detail.getString("job"));
        assertNotNull(detail.getString("phone"));
    }

    @Test
    public void testgetById_UserNotFound_ThrowsException() throws Exception{
        String id = "108111";
        String url = "/emp/info/" + id;
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

        assertEquals(code, ExceptionEnum.USER_NOT_EXIST.getCode());
    }

    @Test
    public void testsave_ReturnsSuccess() throws Exception{
        String url = "/emp/save";
        String json = "{\n" +
                "  \"empName\": \"单元测试专用用户\",\n" +
                "  \"job\": \"员工\",\n" +
                "  \"deptNo\": \"1\",\n" +
                "  \"password\": \"M9EEL9jtKXAuHSIWLyrLduM1ysxY2sopgrjQLbSU2AdNQdnyJKFgu1hq0u4AnXBcGT9Oxq1Wo0/0KQRBKXRkU4mcN9konlgSKDAar3PO364IBq+hC6TilgokWXt4eYM4YRT31DQT/8R9qvHOXMthtxaCOVT8xz/JBSzOHWHVoQ0=\",\n" +
                "  \"phone\": \"12345678901\"\n" +
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
    public void testsave_UserExist_ThrowsException() throws Exception{
        String url = "/emp/save";
        String json = "{\n" +
                "  \"empName\": \"admin\",\n" +
                "  \"job\": \"员工\",\n" +
                "  \"deptNo\": \"1\",\n" +
                "  \"password\": \"M9EEL9jtKXAuHSIWLyrLduM1ysxY2sopgrjQLbSU2AdNQdnyJKFgu1hq0u4AnXBcGT9Oxq1Wo0/0KQRBKXRkU4mcN9konlgSKDAar3PO364IBq+hC6TilgokWXt4eYM4YRT31DQT/8R9qvHOXMthtxaCOVT8xz/JBSzOHWHVoQ0=\",\n" +
                "  \"phone\": \"12345678901\"\n" +
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

        assertEquals(code, ExceptionEnum.USER_EXIST.getCode());
    }

    @Test
    public void testupdate_ReturnsSuccess() throws Exception{
        String url = "/emp/update";
        String json = "{\n" +
                "  \"empNo\": 112,\n" +
                "  \"deptNo\": 1,\n" +
                "  \"empName\": \"用户1\",\n" +
                "  \"job\": \"员工\",\n" +
                "  \"password\": \"M9EEL9jtKXAuHSIWLyrLduM1ysxY2sopgrjQLbSU2AdNQdnyJKFgu1hq0u4AnXBcGT9Oxq1Wo0/0KQRBKXRkU4mcN9konlgSKDAar3PO364IBq+hC6TilgokWXt4eYM4YRT31DQT/8R9qvHOXMthtxaCOVT8xz/JBSzOHWHVoQ0=\",\n" +
                "  \"phone\": \"12345678901\",\n" +
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
    public void testupdate_UserExist_ThrowsException() throws Exception{
        String url = "/emp/update";
        String json = "{\n" +
                "  \"empNo\": 112,\n" +
                "  \"deptNo\": 1,\n" +
                "  \"empName\": \"admin\",\n" +
                "  \"job\": \"员工\",\n" +
                "  \"password\": \"M9EEL9jtKXAuHSIWLyrLduM1ysxY2sopgrjQLbSU2AdNQdnyJKFgu1hq0u4AnXBcGT9Oxq1Wo0/0KQRBKXRkU4mcN9konlgSKDAar3PO364IBq+hC6TilgokWXt4eYM4YRT31DQT/8R9qvHOXMthtxaCOVT8xz/JBSzOHWHVoQ0=\",\n" +
                "  \"phone\": \"12345678901\",\n" +
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

        assertEquals(code, ExceptionEnum.USER_EXIST.getCode());
    }

    @Test
    public void testupdate_RecTokenError_ThrowsException() throws Exception{
        String url = "/emp/update";
        String json = "{\n" +
                "  \"empNo\": 112,\n" +
                "  \"deptNo\": 1,\n" +
                "  \"empName\": \"用户1\",\n" +
                "  \"job\": \"员工\",\n" +
                "  \"password\": \"M9EEL9jtKXAuHSIWLyrLduM1ysxY2sopgrjQLbSU2AdNQdnyJKFgu1hq0u4AnXBcGT9Oxq1Wo0/0KQRBKXRkU4mcN9konlgSKDAar3PO364IBq+hC6TilgokWXt4eYM4YRT31DQT/8R9qvHOXMthtxaCOVT8xz/JBSzOHWHVoQ0=\",\n" +
                "  \"phone\": \"12345678901\",\n" +
                "  \"recToken\": 111\n" +
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

        assertEquals(code, ExceptionEnum.USER_UPDATE_CONCURRENT_MODIFY_ERROR.getCode());
    }

    @Test
    public void testremove_ReturnsSuccess() throws Exception{
        Long empId = 108L;
        String url = "/emp/remove/" + empId;
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
    public void testremove_UserNotFound_ThrowsException() throws Exception{
        Long empId = 108111L;
        String url = "/emp/remove/" + empId;
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

        assertEquals(code, ExceptionEnum.USER_NOT_EXIST.getCode());
    }

}