package com.stefanpetcu.api.fullbinarytree.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class TreeNode<T> implements JsonSerializable {
    protected final UUID id;
    protected T value;
    protected TreeNode<T> leftChild;
    protected TreeNode<T> rightChild;

    public TreeNode(T value) {
        this.value = value;
        this.id = UUID.randomUUID();
    }

    public TreeNode<T> setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;

        return this;
    }

    public TreeNode<T> setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;

        return this;
    }

    public T getValue() {
        return value;
    }

    public UUID getId() {
        return id;
    }

    public Optional<TreeNode<T>> getLeftChild() {
        return Optional.ofNullable(leftChild);
    }

    public Optional<TreeNode<T>> getRightChild() {
        return Optional.ofNullable(rightChild);
    }

    public boolean isLeaf() {
        return getLeftChild().isEmpty() && getRightChild().isEmpty();
    }

    public boolean isFullBranch() {
        return getLeftChild().isPresent() && getRightChild().isPresent();
    }

    @SneakyThrows
    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) {
        gen.writeStartObject();
        gen.writeStringField("id", id.toString());
        gen.writeObjectField("value", value);
        if (getLeftChild().isPresent()) {
            gen.writeStringField("leftChildId", getLeftChild().get().id.toString());
        } else {
            gen.writeNullField("leftChildId");
        }
        if (getRightChild().isPresent()) {
            gen.writeStringField("rightChildId", getRightChild().get().id.toString());
        } else {
            gen.writeNullField("rightChildId");
        }
        gen.writeEndObject();
    }

    @SneakyThrows
    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        serialize(gen, serializers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode<?> treeNode = (TreeNode<?>) o;

        return id.equals(treeNode.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
