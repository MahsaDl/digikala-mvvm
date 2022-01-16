package com.example.digikalamvvm.view;

import com.example.digikalamvvm.view.activity.LoginActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginActivityTest {
    LoginActivity loginActivity;


    @Before
    public void setUp(){
        loginActivity = new LoginActivity();
    }

    @After
    public void shutDown(){
        loginActivity =null;
    }

    @Test
    public void test_Check() {

        //Arrange
        String userName="ali";
        String password="12345678";
        Boolean expected=true;

        //Act
        boolean result = loginActivity.check(userName,password);

        //Assert
        Assert.assertEquals(expected,result);
    }

    @Test
    public void test_Check_With_Short_Length_Username() {

        //Arrange
        String userName="al",password="12345678";
        Boolean expected=false;

        //Act
        boolean result = loginActivity.check(userName,password);

        //Assert
        Assert.assertEquals(expected,result);
    }

    @Test
    public void test_Check_With_Short_Length_Password() {

        //Arrange
        String userName="ali",password="123";
        Boolean expected=false;

        //Act
        boolean result = loginActivity.check(userName,password);

        //Assert
        Assert.assertEquals(expected,result);
    }

    @Test
    public void test_Check_With_Empty_Username() {

        //Arrange
        String userName="",password="12345678";
        Boolean expected=false;

        //Act
        boolean result = loginActivity.check(userName,password);

        //Assert
        Assert.assertEquals(expected,result);
    }

    @Test
    public void test_Check_With_Null_Username() {

        //Arrange
        String userName=null,password="12345678";
        Boolean expected=false;

        //Act
        boolean result = loginActivity.check(userName,password);

        //Assert
        Assert.assertEquals(expected,result);
    }

    @Test
    public void test_Check_With_Empty_Password() {

        //Arrange
        String userName="ali",password="";
        Boolean expected=false;

        //Act
        boolean result = loginActivity.check(userName,password);

        //Assert
        Assert.assertEquals(expected,result);
    }

    @Test
    public void test_Check_With_Null_Password() {

        //Arrange
        String userName="ali",password=null;
        Boolean expected=false;

        //Act
        boolean result = loginActivity.check(userName,password);

        //Assert
        Assert.assertEquals(expected,result);
    }

}