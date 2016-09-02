package computerstore.com.computerstore.factories;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.factories.components.MainboardTest;
import computerstore.com.computerstore.factories.components.MemoryTest;
import computerstore.com.computerstore.factories.components.MonitorTest;
import computerstore.com.computerstore.factories.components.NotebookTest;
import computerstore.com.computerstore.factories.components.OpticalDevicesTest;
import computerstore.com.computerstore.factories.components.PCUTest;
import computerstore.com.computerstore.factories.components.PrinterTest;
import computerstore.com.computerstore.factories.components.SpeakerTest;
import computerstore.com.computerstore.factories.components.StorageDeviceTest;
import computerstore.com.computerstore.factories.employees.EmployeesTest;
import computerstore.com.computerstore.factories.sales.SalesComponentsTest;
import computerstore.com.computerstore.factories.sales.SalesTest;
import computerstore.com.computerstore.factories.components.CPUTest;
import computerstore.com.computerstore.factories.components.ChassisTest;
import computerstore.com.computerstore.factories.components.DisplayCardTest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;




@RunWith(Suite.class)
@Suite.SuiteClasses({
        ChassisTest.class,
        CPUTest.class,
        DisplayCardTest.class,
        MainboardTest.class,
        MemoryTest.class,
        MonitorTest.class,
        NotebookTest.class,
        OpticalDevicesTest.class,
        PCUTest.class,
        PrinterTest.class,
        SpeakerTest.class,
        StorageDeviceTest.class,
        EmployeesTest.class,
        SalesComponentsTest.class,
        SalesTest.class})
public class AppUnitTestSuite {
}
