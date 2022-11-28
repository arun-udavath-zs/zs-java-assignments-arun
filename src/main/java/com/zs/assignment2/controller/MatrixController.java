package com.zs.assignment2.controller;

import com.zs.assignment2.service.MatrixService;

import java.util.Scanner;

public class MatrixController {
    public void matrix(){
        Scanner sc= new Scanner(System.in);
        System.out.println("enter no.of rows and columns values of matrix");
        int n= sc.nextInt(),m= sc.nextInt();
        // initialise the matrix
        int[][] A= new int[n][m];
        int[][] B= new int[n][m];
        int[][] res= new int[n][m];

        System.out.println("enter A matrix elements");
        insertIntoMatrix(n,m,A);
        System.out.println("enter B matrix elements");
        insertIntoMatrix(n,m,B);

        System.out.println("press 1 for addition, 2 for multiply,3 for sub, 4 for transpose, 5 for k scalar matrix");
        int input= sc.nextInt();
        MatrixService matrixService= new MatrixService();
        switch (input){
            case 1:
                res = matrixService.addMatrix(A,B,res,n,m);
                System.out.println("resultant addition matrix:");
                printMatrix(res,n,m);
                break;
            case 2:
                res = matrixService.multiplyMatrix(A,B,res,n,m);
                System.out.println("resultant multiply matrix:");
                printMatrix(res,n,m);
                break;
            case 3:
                res = matrixService.subMatrix(A,B,res,n,m);
                System.out.println("resultant subtraction matrix:");
                printMatrix(res,n,m);
                break;
            case 4:
                res = matrixService.transposeMatrix(A,res,n,m);
                System.out.println("resultant transpose matrix:");
                printMatrix(res,n,m);
                break;
            case 5:
                System.out.println("Enter the scalar value to multiply");
                int k= sc.nextInt();
                res = matrixService.scalarMatrix(A,res,n,m,k);
                System.out.println("resultant k scalar matrix:");
                printMatrix(res,n,m);
        }
    }
  // to enter the elements in the matrix
    public void insertIntoMatrix(int n,int m,int[][] matrix){
        Scanner sc= new Scanner(System.in);
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++)
            {
                matrix[i][j]= sc.nextInt();
            }
        }
    }
    // to print the matrix
    public void printMatrix(int[][] res,int n,int m){
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }
    }
}
