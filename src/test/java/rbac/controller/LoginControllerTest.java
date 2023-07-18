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
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${token}")
    private String token;

    @Test
    public void testLogin_ReturnsSuccess() throws Exception{
        String json = "{\n" +
                "  \"name\": \"admin\",\n" +
                "  \"password\": \"M9EEL9jtKXAuHSIWLyrLduM1ysxY2sopgrjQLbSU2AdNQdnyJKFgu1hq0u4AnXBcGT9Oxq1Wo0/0KQRBKXRkU4mcN9konlgSKDAar3PO364IBq+hC6TilgokWXt4eYM4YRT31DQT/8R9qvHOXMthtxaCOVT8xz/JBSzOHWHVoQ0=\"\n" +
                "}";
        //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders // 执行一个请求
                .post("/login") // 构造一个请求，Post请求使用.post方法
                .content(json.getBytes()) //传json参数
                .accept(MediaType.APPLICATION_JSON) // 代表客户端希望接受的数据类型为application/json;charset=UTF-8
                .contentType(MediaType.APPLICATION_JSON_VALUE) // 代表发送端发送的数据格式是application/json;charset=UTF-8
//              .header("Authorization","Bearer ********-****-****-****-************") 代表在报文头添加一些必须的信息，这里添加的是token
        )
                .andExpect(MockMvcResultMatchers.status().isOk()) // ResultActions.andExpect：添加执行完成后的断言
                // 方法看请求的状态响应码是否为200如果不是则抛异常，测试不通过
//                .andDo(print()); // 添加一个结果处理器，表示要对结果做点什么事情，比如此处使用print()：输出整个响应结果信息
                .andReturn();

        String responseJson = result.getResponse().getContentAsString(); // 获取响应的JSON字符串

        System.out.println("################################################");
        System.out.println(responseJson);
        System.out.println("################################################");

        // 解析JSON字符串
        JSONObject jsonResponse = new JSONObject(responseJson);
        Integer code = Integer.valueOf(jsonResponse.getString("code"));
        String data = jsonResponse.getString("data");
        String msg = jsonResponse.getString("msg");

        assertEquals(code, ExceptionEnum.SUCCESS.getCode());
        assertNotNull(data);
    }

//    @Test
//    public void testLogin_UserNotFound_ThrowsException() throws Exception{
//        String json = "{\n" +
//                "  \"name\": \"admin123\",\n" +
//                "  \"password\": \"M9EEL9jtKXAuHSIWLyrLduM1ysxY2sopgrjQLbSU2AdNQdnyJKFgu1hq0u4AnXBcGT9Oxq1Wo0/0KQRBKXRkU4mcN9konlgSKDAar3PO364IBq+hC6TilgokWXt4eYM4YRT31DQT/8R9qvHOXMthtxaCOVT8xz/JBSzOHWHVoQ0=\"\n" +
//                "}";
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//                        .post("/login")
//                        .content(json.getBytes())
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//        )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String responseJson = result.getResponse().getContentAsString(); // 获取响应的JSON字符串
//
//        // 解析JSON字符串
//        JSONObject jsonResponse = new JSONObject(responseJson);
//        Integer code = Integer.valueOf(jsonResponse.getString("code"));
//        String data = jsonResponse.getString("data");
//        String msg = jsonResponse.getString("msg");
//
//        assertEquals(code, ExceptionEnum.USER_NOT_EXIST.getCode());
//
//    }

    @Test
    public void testLogin_ParamsEmpty_ThrowsException() throws Exception{
        String json = "{ }";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/login")
                .content(json.getBytes())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString(); // 获取响应的JSON字符串

        // 解析JSON字符串
        JSONObject jsonResponse = new JSONObject(responseJson);
        Integer code = Integer.valueOf(jsonResponse.getString("code"));
        String data = jsonResponse.getString("data");
        String msg = jsonResponse.getString("msg");

        assertEquals(code, ExceptionEnum.METHOD_ARUGMENT_NOT_VALID.getCode());
        assertNotNull(msg);

    }


//    @Test
//    public void testLogin_PasswordError_ThrowsException() throws Exception{
//        String json = "{\n" +
//                "  \"name\": \"admin\",\n" +
//                "  \"password\": \"beYf/xKtAhN1KaTnhXH7HD0GdFuMfAHoWoQkTNwDHT+plMgj4X9hJXDIFXPHAY0aMVMY3Hh31LoHfq+CkjDak6V9Achc5BZY5cUEHtVvNrxu7FnIhxxZWfr+utvmnRrKRHkrkxsZQxG8v48o+ZZcFXXfLj3q+IXSAusT8E75cm8=\"\n" +
//                "}";
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//                .post("/login")
//                .content(json.getBytes())
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//        )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String responseJson = result.getResponse().getContentAsString(); // 获取响应的JSON字符串
//
//        // 解析JSON字符串
//        JSONObject jsonResponse = new JSONObject(responseJson);
//        Integer code = Integer.valueOf(jsonResponse.getString("code"));
//        String data = jsonResponse.getString("data");
//        String msg = jsonResponse.getString("msg");
//
//        assertEquals(code, ExceptionEnum.USER_NAME_OR_PASSWORD_WRONG.getCode());
//    }

    @Test
    public void testUserinfo_ReturnsSuccess() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/userinfo")
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
        assertNotNull(detail.getString("userId"));
        assertNotNull(detail.getString("userName"));

    }

    @Test
    public void testUpdatePassword_ReturnsSuccess() throws Exception{
        String json = "{\n" +
                "  \"emp_id\": 108,\n" +
                "  \"oripassword\": \"M9EEL9jtKXAuHSIWLyrLduM1ysxY2sopgrjQLbSU2AdNQdnyJKFgu1hq0u4AnXBcGT9Oxq1Wo0/0KQRBKXRkU4mcN9konlgSKDAar3PO364IBq+hC6TilgokWXt4eYM4YRT31DQT/8R9qvHOXMthtxaCOVT8xz/JBSzOHWHVoQ0=\",\n" +
                "  \"newpassword\": \"beYf/xKtAhN1KaTnhXH7HD0GdFuMfAHoWoQkTNwDHT+plMgj4X9hJXDIFXPHAY0aMVMY3Hh31LoHfq+CkjDak6V9Achc5BZY5cUEHtVvNrxu7FnIhxxZWfr+utvmnRrKRHkrkxsZQxG8v48o+ZZcFXXfLj3q+IXSAusT8E75cm8=\"\n" +
                "}\n";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/updatepassword")
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
    public void testUpdatePassword_PasswordSame_ThrowsException() throws Exception{
        String json = "{\n" +
                "  \"emp_id\": 108,\n" +
                "  \"oripassword\": \"M9EEL9jtKXAuHSIWLyrLduM1ysxY2sopgrjQLbSU2AdNQdnyJKFgu1hq0u4AnXBcGT9Oxq1Wo0/0KQRBKXRkU4mcN9konlgSKDAar3PO364IBq+hC6TilgokWXt4eYM4YRT31DQT/8R9qvHOXMthtxaCOVT8xz/JBSzOHWHVoQ0=\",\n" +
                "  \"newpassword\": \"M9EEL9jtKXAuHSIWLyrLduM1ysxY2sopgrjQLbSU2AdNQdnyJKFgu1hq0u4AnXBcGT9Oxq1Wo0/0KQRBKXRkU4mcN9konlgSKDAar3PO364IBq+hC6TilgokWXt4eYM4YRT31DQT/8R9qvHOXMthtxaCOVT8xz/JBSzOHWHVoQ0=\"\n" +
                "}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/updatepassword")
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

        assertEquals(code, ExceptionEnum.USER_PASSWORD_SAME.getCode());
    }

    @Test
    public void testUpdatePassword_PasswordError_ThrowsException() throws Exception{
        String json = "{\n" +
                "  \"emp_id\": 108,\n" +
                "  \"oripassword\": \"beYf/xKtAhN1KaTnhXH7HD0GdFuMfAHoWoQkTNwDHT+plMgj4X9hJXDIFXPHAY0aMVMY3Hh31LoHfq+CkjDak6V9Achc5BZY5cUEHtVvNrxu7FnIhxxZWfr+utvmnRrKRHkrkxsZQxG8v48o+ZZcFXXfLj3q+IXSAusT8E75cm8=\",\n" +
                "  \"newpassword\": \"M9EEL9jtKXAuHSIWLyrLduM1ysxY2sopgrjQLbSU2AdNQdnyJKFgu1hq0u4AnXBcGT9Oxq1Wo0/0KQRBKXRkU4mcN9konlgSKDAar3PO364IBq+hC6TilgokWXt4eYM4YRT31DQT/8R9qvHOXMthtxaCOVT8xz/JBSzOHWHVoQ0=\"\n" +
                "}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/updatepassword")
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

        assertEquals(code, ExceptionEnum.USER_PASSWORD_WRONG.getCode());
    }

        @Test
    public void testlogout_ReturnsSuccess() throws Exception{
        String json = "{}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/out")
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
}