package rps.test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({
   PlayerTest.class,
   GameEngineTest.class,
   LeaderBoardTest.class,
   GameStateTest.class,
   FileServiceTest.class,
   DBServiceTest.class
   
})

public class TestRunner {   
}  	