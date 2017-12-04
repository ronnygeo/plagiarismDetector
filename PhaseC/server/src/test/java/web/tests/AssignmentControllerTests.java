package web.tests;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.hibernate.validator.constraints.NotBlank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.data.annotation.Id;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import cs5500.project.data.Assignment;
import cs5500.project.data.User;
import cs5500.project.web.controllers.AssignmentController;
import cs5500.project.web.repository.AssignmentRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AssignmentController.class, secure = false)
public class AssignmentControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AssignmentRepository assignRepo;
	
	
	Assignment assign = new Assignment("1","ProblemSet1","CS5500",2017,true,"12/2/17","1/2/17","1/2/17");
	java.util.List<Assignment> listOfAssignments = new ArrayList<Assignment>();
	
	
	String exampleAssignmentJson = "{\"id\":\"1\",\"name\":\"ProblemSet1\",\"course\":\"CS5500\",\"year\":2017,\"isAnalyzed\":true,\"dueDate\":\"12/2/17\",\"creationDate\":\"1/2/17\",\"analyzedDate\":\"1/2/17\"}";
	
	/*
	 *  testing the get request to return an assignment object when the assignment id is sent 
	 *  as parameter to the get request
	 */
	@Test
	public void getAssignmentByIdTest() throws Exception{
		Mockito.when(
				assignRepo.findOne(Mockito.anyString())).thenReturn(assign);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/assignments/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{name:ProblemSet1,course:CS5500}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	/*
	 *  testing the delete request to return a status 200(Success code) when 
	 *  a assignment with the specified assignment id, that is a part of the url, is deleted
	 */
	@Test
	public void deleteAssignmentTest() throws Exception{
		Mockito.doNothing().when(assignRepo).delete(Mockito.anyString());
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/api/assignments/1");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}

	/*
	 *  testing the post request to return a status 200(Success code) when 
	 *  a assignment object is sent as a part of the request body to create the object
	 */
	@Test
	public void createAssignmentTests() throws Exception{
		Assignment mockAssign = new Assignment("123","ProblemSet2","CS5800",2017,true,"12/2/17","1/2/17","1/2/17");
		Mockito.when(assignRepo.save(Mockito.any(Assignment.class))).thenReturn(mockAssign);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/assignments")
				.accept(MediaType.APPLICATION_JSON).content(exampleAssignmentJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
	}
	
	
	
}
