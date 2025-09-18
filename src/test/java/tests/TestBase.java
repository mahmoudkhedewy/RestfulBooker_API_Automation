package tests;

import api.*;
import api.GetBookingCreatedByID;
import data.ReadData;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    protected RestfulAPIs restfulAPIs;
    protected CreateAuth createAuth;
    protected CreateBooking createBooking;
    protected GetBookingCreatedByID getBookingCreatedByID;
    protected PutCreatedBooking putCreatedBooking;
    DeleteBookingCreated deleteBookingCreated;
    ReadData createAuthBodyData;
    ReadData createBookingBodyData;
    ReadData putBookingBodyData;;

    @BeforeClass
    public void setRestfulAPIs() throws Exception {
        createAuthBodyData = new ReadData("src/test/java/data/createAuth.json");
        createBookingBodyData = new ReadData("src/test/java/data/createBooking.json");
        putBookingBodyData = new ReadData("src/test/java/data/putCreatedBooking.json");
        RestAssured.baseURI="https://restful-booker.herokuapp.com/";
    }

    @BeforeMethod
    public void initRestfulAPIsObject(){
//        restfulAPIs = new RestfulAPIs();
        createAuth = new CreateAuth();
        createBooking = new CreateBooking();
        getBookingCreatedByID = new GetBookingCreatedByID();
        putCreatedBooking = new PutCreatedBooking();
        deleteBookingCreated = new DeleteBookingCreated();
    }


}
