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
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${token}")
    private String token;

    @Test
    public void testgetMenuListByRoleId_ReturnsSuccess() throws Exception{
        String roleId = "1";
        String url = "/auth/getMenuListByRoleId/" + roleId;
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
        assertNotNull(data);
    }

    @Test
    public void testgetMenuListByRoleId_RoleNotFound_ThrowsException() throws Exception{
        String roleId = "1111111";
        String url = "/auth/getMenuListByRoleId/" + roleId;
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
    public void testsaveRoleMenu_ReturnsSuccess() throws Exception{
        String url = "/auth/saveRoleMenu";
        String json = "{\n" +
                "  \"menuIdList\": [2, 3, 4],\n" +
                "  \"roleId\": 2\n" +
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
        assertNotNull(data);
    }

    @Test
    public void testSaveRoleMenu_RoleNotExist_ThrowsException() throws Exception{
        String url = "/auth/saveRoleMenu";
        String json = "{\n" +
                "  \"menuIdList\": [2, 3, 4],\n" +
                "  \"roleId\": 211111\n" +
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

        assertEquals(code, ExceptionEnum.ROLE_NOT_EXIST.getCode());
    }

    @Test
    public void testsaveRoleMenu_MenuNotExist_ThrowsException() throws Exception{
        String url = "/auth/saveRoleMenu";
        String json = "{\n" +
                "  \"menuIdList\": [2, 3, 4121212],\n" +
                "  \"roleId\": 2\n" +
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

        assertEquals(code, ExceptionEnum.MENU_NOT_FOUND.getCode());
    }

    @Test
    public void testgetempByRoleId_ReturnsSuccess() throws Exception{
        String roleId = "1";
        String url = "/auth/getempByRoleId?id=" + roleId;
        String json = "{\n" +
                "  \"pageNum\": 1,\n" +
                "  \"pageSize\": 5\n" +
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
        assertNotNull(data);
    }

    @Test
    public void testgetempByRoleId_RoleNotExist_ThrowsException() throws Exception{
        String roleId = "112121";
        String url = "/auth/getempByRoleId?id=" + roleId;
        String json = "{\n" +
                "  \"pageNum\": 1,\n" +
                "  \"pageSize\": 5\n" +
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

        assertEquals(code, ExceptionEnum.ROLE_NOT_EXIST.getCode());
    }

    @Test
    public void testgetNotAddedEmpByRoleId_ReturnsSuccess() throws Exception{
        String roleId = "1";
        String url = "/auth/getNotAddedEmpByRoleId?id=" + roleId;
        String json = "{\n" +
                "  \"pageNum\": 1,\n" +
                "  \"pageSize\": 5\n" +
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
        assertNotNull(data);
    }

    @Test
    public void testgetNotAddedEmpByRoleId_RoleNotExist_ThrowsException() throws Exception{
        String roleId = "112121";
        String url = "/auth/getNotAddedEmpByRoleId?id=" + roleId;
        String json = "{\n" +
                "  \"pageNum\": 1,\n" +
                "  \"pageSize\": 5\n" +
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

        assertEquals(code, ExceptionEnum.ROLE_NOT_EXIST.getCode());
    }

    @Test
    public void testgetRoleByEmpId_ReturnsSuccess() throws Exception{
        String empId = "108";
        String url = "/auth/getRoleByEmpId?id=" + empId;
        String json = "{\n" +
                "  \"pageNum\": 1,\n" +
                "  \"pageSize\": 5\n" +
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
        assertNotNull(data);
    }

    @Test
    public void testgetRoleByEmpId_RoleNotExist_ThrowsException() throws Exception{
        String empId = "108111";
        String url = "/auth/getRoleByEmpId?id=" + empId;
        String json = "{\n" +
                "  \"pageNum\": 1,\n" +
                "  \"pageSize\": 5\n" +
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

        assertEquals(code, ExceptionEnum.USER_NOT_EXIST.getCode());
    }


    @Test
    public void testgetNotAddedRoleByEmpId_ReturnsSuccess() throws Exception{
        String empId = "108";
        String url = "/auth/getNotAddedRoleByEmpId?id=" + empId;
        String json = "{\n" +
                "  \"pageNum\": 1,\n" +
                "  \"pageSize\": 5\n" +
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
        assertNotNull(data);
    }

    @Test
    public void testgetNotAddedRoleByEmpId_RoleNotExist_ThrowsException() throws Exception{
        String empId = "108111";
        String url = "/auth/getNotAddedRoleByEmpId?id=" + empId;
        String json = "{\n" +
                "  \"pageNum\": 1,\n" +
                "  \"pageSize\": 5\n" +
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

        assertEquals(code, ExceptionEnum.USER_NOT_EXIST.getCode());
    }

    @Test
    public void testgetAuth_ReturnsSuccess() throws Exception{
        String url = "/auth/getAuth";
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
        assertNotNull(data);
    }

    @Test
    public void testsaveRoleEmp_ReturnsSuccess() throws Exception{
        String url = "/auth/saveRoleEmp";
        String json = "{\n" +
                "  \"empIdList\": [108, 112],\n" +
                "  \"roleId\": 1\n" +
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
    public void testsaveRoleEmp_RoleNotExist_ThrowsException() throws Exception{
        String url = "/auth/saveRoleEmp";
        String json = "{\n" +
                "  \"empIdList\": [108, 112],\n" +
                "  \"roleId\": 11212121\n" +
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

        assertEquals(code, ExceptionEnum.ROLE_NOT_EXIST.getCode());
    }


    @Test
    public void testsaveRoleEmp_UserNotExist_ThrowsException() throws Exception{
        String url = "/auth/saveRoleEmp";
        String json = "{\n" +
                "  \"empIdList\": [108121, 112],\n" +
                "  \"roleId\": 1\n" +
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

        assertEquals(code, ExceptionEnum.USER_NOT_EXIST.getCode());
    }

    @Test
    public void testsaveEmpRole_ReturnsSuccess() throws Exception{
        String url = "/auth/saveEmpRole";
        String json = "{\n" +
                "  \"empId\": 108,\n" +
                "  \"roleIdList\": [1, 2]\n" +
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
    public void testsaveEmpRole_RoleNotExist_ThrowsException() throws Exception{
        String url = "/auth/saveEmpRole";
        String json = "{\n" +
                "  \"empId\": 108,\n" +
                "  \"roleIdList\": [1, 211111]\n" +
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

        assertEquals(code, ExceptionEnum.ROLE_NOT_EXIST.getCode());
    }


    @Test
    public void testsaveEmpRole_UserNotExist_ThrowsException() throws Exception{
        String url = "/auth/saveEmpRole";
        String json = "{\n" +
                "  \"empId\": 1081111,\n" +
                "  \"roleIdList\": [1, 2]\n" +
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

        assertEquals(code, ExceptionEnum.USER_NOT_EXIST.getCode());
    }

    @Test
    public void testdeleteEmpRole_ReturnsSuccess() throws Exception{
        String url = "/auth/deleteEmpRole";
        String json = "{\n" +
                "  \"empId\": 108,\n" +
                "  \"roleId\": 1\n" +
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
    public void testdeleteEmpRole_RoleNotExist_ThrowsException() throws Exception{
        String url = "/auth/deleteEmpRole";
        String json = "{\n" +
                "  \"empId\": 108,\n" +
                "  \"roleId\": 11212121\n" +
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

        assertEquals(code, ExceptionEnum.ROLE_NOT_EXIST.getCode());
    }


    @Test
    public void testdeleteEmpRole_UserNotExist_ThrowsException() throws Exception{
        String url = "/auth/deleteEmpRole";
        String json = "{\n" +
                "  \"empId\": 108121212,\n" +
                "  \"roleId\": 1\n" +
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

        assertEquals(code, ExceptionEnum.USER_NOT_EXIST.getCode());
    }

    @Test
    public void testdeleteRoleEmp_ReturnsSuccess() throws Exception{
        String url = "/auth/deleteRoleEmp";
        String json = "{\n" +
                "  \"empId\": 108,\n" +
                "  \"roleId\": 1\n" +
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
    public void testdeleteRoleEmp_RoleNotExist_ThrowsException() throws Exception{
        String url = "/auth/deleteRoleEmp";
        String json = "{\n" +
                "  \"empId\": 108,\n" +
                "  \"roleId\": 11212121\n" +
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

        assertEquals(code, ExceptionEnum.ROLE_NOT_EXIST.getCode());
    }


    @Test
    public void testdeleteRoleEmp_UserNotExist_ThrowsException() throws Exception{
        String url = "/auth/deleteRoleEmp";
        String json = "{\n" +
                "  \"empId\": 108121212,\n" +
                "  \"roleId\": 1\n" +
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

        assertEquals(code, ExceptionEnum.USER_NOT_EXIST.getCode());
    }
}