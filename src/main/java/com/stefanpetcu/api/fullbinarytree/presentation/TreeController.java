package com.stefanpetcu.api.fullbinarytree.presentation;

import com.stefanpetcu.api.fullbinarytree.application.TreeService;
import com.stefanpetcu.api.fullbinarytree.domain.TreeNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TreeController {
    final private TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping(value = "/trim-tree", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public <T> ResponseEntity<FullBinaryTreeResponseDto<T>> trimTree(@RequestBody ConvertTreeToFullBinaryRequest<T> request) {
        List<TreeNode<T>> treeNodes = request.getTreeNodes();
        TreeNode<T> firstNode = treeNodes.get(0);
        TreeNode<T>[] otherNodes = treeNodes.subList(1, treeNodes.size()).toArray(new TreeNode[treeNodes.size() - 2]);
        TreeNode<T> rootNode = treeService.buildBinaryTree(firstNode, otherNodes);
        TreeNode<T> fullBinaryTreeRootNode = treeService.convertToFullBinaryTree(rootNode);
        FullBinaryTreeResponseDto<T> response = new FullBinaryTreeResponseDto<>(treeService.convertTreeToArray(fullBinaryTreeRootNode));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
