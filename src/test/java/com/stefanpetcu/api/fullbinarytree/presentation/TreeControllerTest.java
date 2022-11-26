package com.stefanpetcu.api.fullbinarytree.presentation;

import com.stefanpetcu.api.fullbinarytree.application.TreeService;
import com.stefanpetcu.api.fullbinarytree.domain.TreeNode;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc // when we don't want to mock stuff
//class TreeControllerTest {
//    @Autowired
//    private MockMvc mockMvc;

//    @Test
//    void trimTree_willReturnFullBinaryTree_givenArrayOfInputValues() throws Exception {
//        mockMvc.perform(post("/trim-tree")
//                .content("{" +
//                        "    \"nodes\": [" +
//                        "        0, 1, 2, 3, null, null, 4, null, 5, 6, 7" +
//                        "    ]" +
//                        "}")
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json("{" +
//                        "\"tree\": [" +
//                        "        {" +
//                        "            \"id\": " + rootNode.getId() + "," +
//                        "            \"value\": 0," +
//                        "            \"leftChildId\": " + firstNode.getId() + "," +
//                        "            \"rightChildId\": " + secondNode.getId() +
//                        "        }," +
//                        "        {" +
//                        "            \"id\": " + firstNode.getId() + "," +
//                        "            \"value\": 1," +
//                        "            \"leftChildId\": null," +
//                        "            \"rightChildId\": null" +
//                        "        }," +
//                        "        {" +
//                        "            \"id\": " + secondNode.getId() + "," +
//                        "            \"value\": 2," +
//                        "            \"leftChildId\": null," +
//                        "            \"rightChildId\": null" +
//                        "        }]" +
//                        "}"));
//
//    }
//}

@WebMvcTest(TreeController.class)
class TreeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TreeService treeService;

    @Test
    void trimTree_willReturnFullBinaryTree_givenArrayOfInputValues() throws Exception {
        TreeNode<Integer> rootNode = new TreeNode<>(0);
        TreeNode<Integer> firstNode = new TreeNode<>(1);
        TreeNode<Integer> secondNode = new TreeNode<>(2);
        rootNode.setLeftChild(firstNode).setRightChild(secondNode);

        when(treeService.buildBinaryTree(
                any(TreeNode.class),
                any(TreeNode.class),
                any(TreeNode.class))
        ).thenReturn(rootNode);

        when(treeService.convertToFullBinaryTree(rootNode)).thenReturn(rootNode);
        when(treeService.convertTreeToArray(rootNode)).thenReturn(new TreeNode[]{rootNode, firstNode, secondNode});
        mockMvc.perform(post("/trim-tree")
                .content("{" +
                        "\"nodes\": [0, 1, 2]" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{" +
                        "\"tree\": [" +
                        "        {" +
                        "            \"id\": " + rootNode.getId() + "," +
                        "            \"value\": 0," +
                        "            \"leftChildId\": " + firstNode.getId() + "," +
                        "            \"rightChildId\": " + secondNode.getId() +
                        "        }," +
                        "        {" +
                        "            \"id\": " + firstNode.getId() + "," +
                        "            \"value\": 1," +
                        "            \"leftChildId\": null," +
                        "            \"rightChildId\": null" +
                        "        }," +
                        "        {" +
                        "            \"id\": " + secondNode.getId() + "," +
                        "            \"value\": 2," +
                        "            \"leftChildId\": null," +
                        "            \"rightChildId\": null" +
                        "        }]" +
                        "}"));
    }
}
