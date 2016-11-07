package com.vvs.training.hospital.services;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.datamodel.Patient;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class PatientServiceImpl {

    @Inject
    private PatientService patientService;

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
    	Patient patient=patientService.get(1l);  
    	Assert.assertFalse(patient.getFirstName().isEmpty());
    	System.out.println(patient.getFirstName());
    }

}
