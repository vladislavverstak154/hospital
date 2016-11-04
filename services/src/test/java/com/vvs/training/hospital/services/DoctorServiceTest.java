package com.vvs.training.hospital.services;



import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.datamodel.Doctor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class DoctorServiceTest {

    @Inject
    private DoctorService doctorService;

    /*
     * @BeforeClass public static void prepareTestData() {
     * System.out.println("prepareTestData"); }
     * 
     * @AfterClass public static void deleteTestData() {
     * System.out.println("deleteTestData"); }
     * 
     * @Before public void prepareMethodData() {
     * System.out.println("prepareMethodData"); }
     * 
     * @After public void deleteMethodData() {
     * System.out.println("deleteMethodData"); }
     */
    
    @Test
    
    public void getByIdTest() {
        Doctor doctor = doctorService.get(1l);
        System.out.println(doctor.getFirstName());

    }

}