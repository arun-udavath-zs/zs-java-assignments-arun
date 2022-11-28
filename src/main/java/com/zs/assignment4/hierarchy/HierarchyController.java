package com.zs.assignment4.hierarchy;

import com.zs.assignment4.hierarchy.service.PrintHierarchy;
import com.zs.assignment4.hierarchy.service.TreeNode;
import java.util.HashMap;
import java.util.Scanner;

public class HierarchyController {
    HashMap<String, TreeNode<String>> map = new HashMap<>();
    //to print hierarchy of the tree
    public void printTree(TreeNode<String> root){
        StringBuilder sb= new StringBuilder();
        sb.append(root.data).append("= ");
        map.put(root.data,root);

        for(int i=0;i< root.children.size();i++){
            sb.append(root.children.get(i).data).append(" ");
        }
        System.out.println(sb);

        for (int i=0;i< root.children.size();i++){
            printTree(root.children.get(i));
        }
    }
    public void hierarchy() {
        Scanner sc=new Scanner(System.in);
        String item = "",product = "";
        TreeNode<String> root= new TreeNode<>("Amazon");
        TreeNode<String> node1= new TreeNode<>("Electronics");
        TreeNode<String> node2= new TreeNode<>("Groceries");
        TreeNode<String> node3= new TreeNode<>("Mobiles");
        TreeNode<String> node4= new TreeNode<>("Laptops");
        TreeNode<String> node5= new TreeNode<>("Machine");
        TreeNode<String> node6= new TreeNode<>("Vegetable");
        TreeNode<String> node7= new TreeNode<>("Rice");
        TreeNode<String> node8= new TreeNode<>("Fruits");
        TreeNode<String> node9= new TreeNode<>("Iphone");
        TreeNode<String> node10= new TreeNode<>("OnePlus");
        TreeNode<String> node11= new TreeNode<>("Redmi");
        TreeNode<String> node12= new TreeNode<>("Iphone14");
        root.children.add(node1);
        root.children.add(node2);
        node1.children.add(node3);
        node1.children.add(node4);
        node1.children.add(node5);
        node2.children.add(node6);
        node2.children.add(node7);
        node2.children.add(node8);
        node3.children.add(node9);
        node3.children.add(node10);
        node3.children.add(node11);
        node9.children.add(node12);

        printTree(root);
        PrintHierarchy hierarchy= new PrintHierarchy();
        System.out.println("Enter product to print hierarchy");
        item = sc.next();
        hierarchy.search(root,item);
        System.out.println("Enter category to search");
        product=sc.next();
        if(map.containsKey(product))
            System.out.println(hierarchy.searchCategory(map.get(product)));
        else
            System.out.println("Entered product Not in list");
    }
}
