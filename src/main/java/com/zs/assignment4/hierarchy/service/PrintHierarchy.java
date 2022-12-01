package com.zs.assignment4.hierarchy.service;

public class PrintHierarchy {
    // to print the hierarchy of a product
    public void search(TreeNode<String> root,String input){
        searchProduct("",root,input);
    }
    public void searchProduct(String sb,TreeNode<String> root,String input)
    {
        if(root == null)return ;
        if(root.data.equals(input)) {
            sb += input;
            System.out.println(sb);
        }
        sb = sb+root.data+"/ ";

        for(int i=0;i<root.children.size();i++){
            searchProduct(sb,root.children.get(i),input);
        }
    }

    // to search the category of a product
    public String searchCategory(TreeNode<String> root)
    {
        if(root.children.size() == 0) return "/"+root.data;
        StringBuilder temp= new StringBuilder();
        temp.append(root.data).append("/");

        for(int i=0;i<root.children.size();i++)
        {
            temp.append(searchCategory(root.children.get(i)));
        }
        return temp.toString();
    }
}
