package com.example.cvmaker.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import com.example.cvmaker.data.Company;
import com.example.cvmaker.data.User;

import java.util.List;

public interface ApiInterface {


    @POST("user")
    Call<Void> addUser(@Body User user);

    @GET("user/{username}")
    Call<User> getUserByUsername(@Path("username") String username);


    @DELETE("user/{username}")
    Call<Void> deleteUser(@Path("username") String username);

    @PUT("user")
    Call<Void> updateUser(@Body User user);

    @POST("experience")
    Call<Void> addCompany(@Body Company company);

    @GET("experience/users/{username}")
    Call<List<Company>> findCompaniesByUsername(@Path("username") String username);







}
