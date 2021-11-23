//package com.revature.nova;
//
//import com.revature.nova.repositories.ProductRepo;
//import org.junit.*;
//
//public class ProductRepoTest {
//    private String thief;
//    private String terraria;
//    private String lastOfUs;
//    private String haloInfinite;
//    private String partialTerraria;
//    private ProductRepo repo;
//
//    @BeforeClass
//    public static void beforeClass()
//    {
//
//    }
//
//    @AfterClass
//    public static void afterClass()
//    {
//
//    }
//
//    @Before
//    public void before()
//    {
//        thief = "Thief";
//        terraria = "Terraria";
//        lastOfUs = "The Last of Us";
//        haloInfinite = "Halo Infinite";
//        partialTerraria = "Terr";
//    }
//
//    @After
//    public void after()
//    {
//        thief = "";
//        terraria = "";
//        lastOfUs = "";
//        haloInfinite = "";
//        partialTerraria = "";
//    }
//
//    @Test
//    public void productRepoTest()
//    {
//        Assert.assertEquals(repo.findByNameContaining(thief).size(),1);
//    }
//
//}
