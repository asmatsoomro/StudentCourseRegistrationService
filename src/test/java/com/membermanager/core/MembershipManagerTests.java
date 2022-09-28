//package com.membermanager.core;
//
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.orm.jpa.EntityScan;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Date;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@EnableJpaRepositories(basePackages = ("com.membermanager.core"))
//@EntityScan(basePackages = "com.membermanager.core")
//public class MembershipManagerTests {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    StudentRegistrationService memberService;
//
//    private static final String MEMBER_URI = "/member";
//
//
//	@Test
//    public void test_addNewMember() throws Exception {
//	    String requestBody = "{\n" +
//                "        \"firstName\": \"Jason\",\n" +
//                "        \"lastName\": \"Lee\",\n" +
//                "        \"dateOfBirth\": 1335205543511,\n" +
//                "        \"zipCode\": 11111\n" +
//                "    }";
//	    mockMvc.perform(MockMvcRequestBuilders.post(MEMBER_URI)
//                .content(requestBody)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful());
//    }
//
//    @After
//    public void clearMemberCache(){
//	    memberService.removeAllMembers();
//    }
//
//    @Test
//    public void test_findMemberByName() throws Exception {
//        Student member = new Student("Jason", "Lee",  new Date(1988, 8, 27), 64283 );
//        memberService.addNewMember(member);
//
//        mockMvc.perform(MockMvcRequestBuilders.get(MEMBER_URI +"/Jason")
//                .accept(MediaType.ALL))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].firstName", is("Jason")))
//                .andExpect(jsonPath("$[0].lastName", is("Lee")))
//                .andExpect(jsonPath("$[0].dateOfBirth", is(new Date(1988, 8, 27).getTime())))
//                .andExpect(jsonPath("$[0].zipCode", is(64283)));
//
//    }
//
//    @Test
//    public void test_getAllMembers() throws Exception {
//        Student member = new Student("Jason", "Lee",  new Date(1988, 8, 27), 64283 );
//        memberService.addNewMember(member);
//
//        mockMvc.perform(MockMvcRequestBuilders.get(MEMBER_URI)
//                .accept(MediaType.ALL))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].firstName", is("Jason")))
//                .andExpect(jsonPath("$[0].lastName", is("Lee")))
//                .andExpect(jsonPath("$[0].dateOfBirth", is(new Date(1988, 8, 27).getTime())))
//                .andExpect(jsonPath("$[0].zipCode", is(64283)));
//    }
//
//    @Test
//    public void test_updateMembers() throws Exception {
//
//        long dob = new Date(1988, 8, 27).getTime();
//        Student member = new Student("Jason", "Lee",  new Date(1988, 8, 27), 64283 );
//        memberService.addNewMember(member);
//
//        String requestBody = "{\n" +
//                "        \"firstName\": \"Jason\",\n" +
//                "        \"lastName\": \"Lee\",\n" +
//                "        \"dateOfBirth\": "+dob+",\n" +
//                "        \"zipCode\": 22222\n" +
//                "    }";
//
//        mockMvc.perform(MockMvcRequestBuilders.put(MEMBER_URI)
//                .content(requestBody)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(MockMvcRequestBuilders.get(MEMBER_URI)
//                .accept(MediaType.ALL))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].firstName", is("Jason")))
//                .andExpect(jsonPath("$[0].lastName", is("Lee")))
//                .andExpect(jsonPath("$[0].dateOfBirth", is(new Date(1988, 8, 27).getTime())))
//                .andExpect(jsonPath("$[0].zipCode", is(22222)));
//
//    }
//
//    @Test
//    public void test_deleteMember() throws Exception {
//
//        Student member = new Student("Jason", "Lee",  new Date(1988, 8, 27), 64283 );
//        memberService.addNewMember(member);
//
//        String requestBody = "{\n" +
//                "        \"firstName\": \"Jason\",\n" +
//                "        \"lastName\": \"Lee\",\n" +
//                "        \"dateOfBirth\": 1335205543511,\n" +
//                "        \"zipCode\": 64283\n" +
//                "    }";
//
//        mockMvc.perform(MockMvcRequestBuilders.delete(MEMBER_URI)
//                .content(requestBody)
//                .contentType(MediaType.APPLICATION_JSON)) //
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(MockMvcRequestBuilders.get(MEMBER_URI)
//                .accept(MediaType.ALL))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(0)));
//    }
//
//    @Test
//    public void test_addNewMember_badRequest() throws Exception {
//        String requestBody = "{\n" +
//                "        \"firstName\": \"\",\n" +
//                "        \"lastName\": \"\",\n" +
//
//                "    }";
//        mockMvc.perform(MockMvcRequestBuilders.post(MEMBER_URI)
//                .content(requestBody)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @Test
//    public void test_updateMember_badRequest() throws Exception {
//        String requestBody = "{\n" +
//                "        \"firstName\": \"\",\n" +
//                "        \"lastName\": \"\",\n" +
//
//                "    }";
//        mockMvc.perform(MockMvcRequestBuilders.post(MEMBER_URI)
//                .content(requestBody)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @Test
//    public void test_deleteMember_badRequest() throws Exception {
//        String requestBody = "{\n" +
//                "        \"firstName\": \"\",\n" +
//                "        \"lastName\": \"\",\n" +
//
//                "    }";
//        mockMvc.perform(MockMvcRequestBuilders.post(MEMBER_URI)
//                .content(requestBody)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @Test
//    public void test_addNewMemberWithInvalidDateFormat_BadRequest() throws Exception {
//        String requestBody = "{\n" +
//                "        \"firstName\": \"Jason\",\n" +
//                "        \"lastName\": \"Lee\",\n" +
//                "        \"dateOfBirth\": date,\n" +
//                "        \"zipCode\": 11111\n" +
//                "    }";
//        mockMvc.perform(MockMvcRequestBuilders.post(MEMBER_URI)
//                .content(requestBody)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @Test
//    public void test_updateMemberNotMatchingMember_badRequest() throws Exception {
//        long dob = new Date(1988, 8, 27).getTime();
//        Student member = new Student("Jason", "Lee",  new Date(1988, 8, 27), 64283 );
//        memberService.addNewMember(member);
//
//        String requestBody = "{\n" +
//                "        \"firstName\": \"Jason\",\n" +
//                "        \"lastName\": \"Law\",\n" +
//                "        \"dateOfBirth\": "+dob+",\n" +
//                "        \"zipCode\": 22222\n" +
//                "    }";
//
//        mockMvc.perform(MockMvcRequestBuilders.put(MEMBER_URI)
//                .content(requestBody)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is4xxClientError());
//    }
//
//
//}
