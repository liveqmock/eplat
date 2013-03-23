/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package test.test.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import test.test.tree.Branch.Type;

public class TreeGenerator {

    public static final int FLOWERS_NUMBER = 80;
    public int              flowersNumber  = FLOWERS_NUMBER;
    public int              treeDepth;

    public Tree generateTree(int treeDepth, double x, double y) {
        Tree tree = new Tree(treeDepth);
        tree.setTranslateX(x);
        tree.setTranslateY(y);
        Branch root = new Branch();
        Util.addChildToParent(tree, root);
        tree.generations.get(0).add(root); // root branch
        for (int i = 1; i < treeDepth; i++) {
            for (Branch parentBranch : tree.generations.get(i - 1)) {
                List<Branch> newBranches = generateBranches(parentBranch, i);
                if (newBranches.isEmpty()) {
                    tree.crown.add(parentBranch);
                }
                tree.generations.get(i).addAll(generateBranches(parentBranch, i));
            }
        }
        for (Branch crownBranch : tree.generations.get(treeDepth - 1)) {
            tree.crown.add(crownBranch);
        }
        tree.leafage.addAll(generateLeafage(tree.crown));
        tree.flowers.addAll(generateFlowers(tree.crown));
        return tree;
    }

    private List<Branch> generateBranches(Branch parentBranch, int depth) {
        List<Branch> branches = new ArrayList<Branch>();
        if (parentBranch == null) { // add root branch
            branches.add(new Branch());
        } else {
            if (parentBranch.length < 10) {
                return Collections.emptyList();
            }
            branches.add(new Branch(parentBranch, Type.LEFT, depth)); // add
                                                                      // side
                                                                      // left
                                                                      // branch
            branches.add(new Branch(parentBranch, Type.RIGHT, depth)); // add
                                                                       // side
                                                                       // right
                                                                       // branch
            branches.add(new Branch(parentBranch, Type.TOP, depth)); // add top
                                                                     // branch
        }

        return branches;
    }

    private List<Leaf> generateLeafage(List<Branch> crown) {
        List<Leaf> leafage = new ArrayList<Leaf>();
        for (int i = 0; i < crown.size(); i++) {
            Branch branch = crown.get(i);
            Leaf leaf = new Leaf(branch);
            leafage.add(leaf);
            Util.addChildToParent(branch, leaf);
        }
        return leafage;
    }

    private List<Flower> generateFlowers(List<Branch> crown) {
        List<Flower> flowers = new ArrayList<Flower>(flowersNumber);
        for (int i = 0; i < flowersNumber; i++) {
            Branch branch = crown.get(RandomUtil.getRandomIndex(0, crown.size() - 1));
            final Flower flower = new Flower(branch);
            Util.addChildToParent(branch, flower);
            flowers.add(flower);
        }
        return flowers;
    }
}
