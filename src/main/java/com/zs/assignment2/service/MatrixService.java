package com.zs.assignment2.service;

public class MatrixService {
    // to add the two matrix
    public int[][] addMatrix(int[][] A,int[][] B,int[][] res,int n,int m){

        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++)
            {
                res[i][j]= A[i][j] + B[i][j];
            }
        }
        return res;
    }
    // to multiply the two matrix
    public int[][] multiplyMatrix(int[][] A,int[][] B,int[][] res,int n,int m) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                for (int k = 0; k < n; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }
    // subtraction of two matrix
    public int[][] subMatrix(int[][] A,int[][] B,int[][] res,int n,int m) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = A[i][j] - B[i][j];
            }
        }
        return res;
    }
    // to transpose the matrix
    public int[][]  transposeMatrix(int[][] A,int[][] res,int n,int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = A[j][i];
            }
        }
        return res;
    }
    // to multiply the k with matrix
    public int[][] scalarMatrix(int[][] A,int[][] res,int n,int m,int k) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = k * A[i][j];
            }
        }
        return res;
    }
}
