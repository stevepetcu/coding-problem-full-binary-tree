# Daily Coding Problem #254 [Medium]

Problem asked by Yahoo.

## Problem Statement

Recall that a full binary tree is one in which each node is either a leaf node,
or has two children. Given a binary tree, convert it to a full one by removing nodes with only one child.

For example, given the following tree:

```mermaid
flowchart TD
    0 --> 1
    0 --> 2
    1 --> 3
    1 --> null_r_1
    2--> null_l_2
    2 --> 4
    3 --> null_l_3
    3 --> 5
    4 --> 6
    4 --> 7
```

You should convert it to:
```mermaid
flowchart TD
    0 ---> 5
    0 --> 4
    4 --> 6
    4 --> 7
```
